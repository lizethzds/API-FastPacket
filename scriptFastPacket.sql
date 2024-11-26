-- 1. Crear el usuario con las credenciales proporcionadas
CREATE USER 'adminFP'@'localhost' IDENTIFIED BY 'f4stp4ck3tt';

-- 2. Otorgar todos los permisos en la base de datos especificada
GRANT ALL PRIVILEGES ON FastPacket.* TO 'adminFP'@'localhost';

-- 3. Aplicar los cambios en los permisos
FLUSH PRIVILEGES;
drop database Fastpacket;
CREATE DATABASE FastPacket;
USE FastPacket;

CREATE TABLE Rol (
  idRol INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(255)
);

CREATE TABLE Colaborador (
  idColaborador INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(255),
  apellidoPaterno VARCHAR(255),
  apellidoMaterno VARCHAR(255),
  curp VARCHAR(18) UNIQUE,
  correo VARCHAR(255),
  noPersonal VARCHAR(255),
  password VARCHAR(255),
  idRol INT,
  fotografia LONGBLOB,
  FOREIGN KEY (idRol) REFERENCES Rol(idRol)
);

CREATE TABLE TipoUnidad (
  idTipoUnidad INT PRIMARY KEY AUTO_INCREMENT,
  tipo VARCHAR(255)
);

CREATE TABLE Unidad (
  idUnidad INT PRIMARY KEY AUTO_INCREMENT,
  marca VARCHAR(255),
  modelo VARCHAR(255),
  anio VARCHAR(4),
  vin VARCHAR(255) UNIQUE,
  noIdentificacion VARCHAR(255),
  idTipoUnidad INT,
  FOREIGN KEY (idTipoUnidad) REFERENCES TipoUnidad(idTipoUnidad)
);

CREATE TABLE ColaboradorUnidad (
  idColaboradorUnidad INT PRIMARY KEY AUTO_INCREMENT,
  idUnidad INT,
  idColaborador INT,
  numeroLicencia Varchar(20),
  FOREIGN KEY (idUnidad) REFERENCES Unidad(idUnidad),
  FOREIGN KEY (idColaborador) REFERENCES Colaborador(idColaborador)
);

CREATE TABLE Estado (
  idEstado INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(255)
);

CREATE TABLE Municipio (
  idMunicipio INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(255),
  idEstado INT,
  FOREIGN KEY (idEstado) REFERENCES Estado(idEstado)
);

CREATE TABLE Direccion (
  idDireccion INT PRIMARY KEY AUTO_INCREMENT,
  calle VARCHAR(255),
  numero VARCHAR(10),
  colonia VARCHAR(255),
  ciudad VARCHAR(255),
  codigoPostal VARCHAR(10),
  idMunicipio INT,
  FOREIGN KEY (idMunicipio) REFERENCES Municipio(idMunicipio)
);

CREATE TABLE Cliente (
  idCliente INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(255),
  apellidoPaterno VARCHAR(255),
  apellidoMaterno VARCHAR(255),
  telefono VARCHAR(15),
  correo VARCHAR(255),
  idDireccion INT,
  FOREIGN KEY (idDireccion) REFERENCES Direccion(idDireccion)
);

CREATE TABLE EstadoEnvio (
  idEstadoEnvio INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(255)
);

CREATE TABLE Envio (
  idEnvio INT PRIMARY KEY AUTO_INCREMENT,
  noGuia VARCHAR(255),
  costoEnvio FLOAT,
  idEstadoEnvio INT,
  idCliente INT,
  idColaborador INT,
  idDireccionDestino INT,
  FOREIGN KEY (idEstadoEnvio) REFERENCES EstadoEnvio(idEstadoEnvio),
  FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente),
  FOREIGN KEY (idColaborador) REFERENCES Colaborador(idColaborador),
  FOREIGN KEY (idDireccionDestino) REFERENCES Direccion(idDireccion)
);

CREATE TABLE HistorialEnvio (
  idHistorial INT PRIMARY KEY AUTO_INCREMENT,
  idColaborador INT,
  idEnvio INT,
  comentario varchar(500),
  horaModificacion TIMESTAMP,
  fechaModificacion DATE,
  idEstadoEnvio INT,
  FOREIGN KEY (idColaborador) REFERENCES Colaborador(idColaborador),
  FOREIGN KEY (idEnvio) REFERENCES Envio(idEnvio),
  FOREIGN KEY (idEstadoEnvio) REFERENCES EstadoEnvio(idEstadoEnvio)
);

CREATE TABLE Paquete (
  idPaquete INT PRIMARY KEY AUTO_INCREMENT,
  descripcion VARCHAR(255),
  peso INT,
  altura INT,
  ancho INT,
  alto VARCHAR(255),
  idEnvio INT,
  FOREIGN KEY (idEnvio) REFERENCES Envio(idEnvio)
);

DELIMITER $$

CREATE PROCEDURE eliminarColaborador(IN colaboradorID INT)
BEGIN
    DECLARE envioAsignado INT;

    SELECT COUNT(*) INTO envioAsignado
    FROM Envio
    WHERE idColaborador = colaboradorID;

	INSERT INTO HistorialEnvio (
		idColaborador, idEnvio, comentario, horaModificacion, fechaModificacion, idEstadoEnvio
	)
    SELECT colaboradorID, idEnvio, 
		'El colaborador fue despedido en el trayecto, pronto se le asignará otro', 
		CURRENT_TIMESTAMP, CURDATE(), 2
	FROM Envio
	WHERE idColaborador = colaboradorID;

	UPDATE Envio 
	SET idColaborador = NULL, idEstadoEnvio = 3
	WHERE idColaborador = colaboradorID;

    DELETE FROM HistorialEnvio WHERE idColaborador = colaboradorID;

    DELETE FROM ColaboradorUnidad WHERE idColaborador = colaboradorID;

    DELETE FROM Colaborador WHERE idColaborador = colaboradorID;
END$$

DELIMITER ;

