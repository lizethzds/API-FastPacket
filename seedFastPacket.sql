INSERT INTO Rol (nombre)
VALUES 
('Administrador'),
('Ejecutivo de tienda'),
('Conductor');

INSERT INTO EstadoEnvio (nombre)
VALUES 
('En transito'),
('Detenido'),
('Entregado'),
('Cancelado');

INSERT INTO Estado (nombre)
VALUES 
('Jalisco'),
('Nuevo León'),
('Ciudad de México'),
('Veracruz'),
('Yucatán');

INSERT INTO Municipio (nombre, idEstado)
VALUES 
('Guadalajara', 1),
('Monterrey', 2),
('Cuauhtémoc', 3),
('Veracruz', 4),
('Mérida', 5);

INSERT INTO Direccion (calle, numero, colonia, ciudad, codigoPostal, idMunicipio)
VALUES 
('Av. Patria', '123', 'Centro', 'Guadalajara', '44100', 1),
('Calle Juárez', '456', 'Centro', 'Monterrey', '64000', 2),
('Insurgentes', '789', 'Roma', 'Ciudad de México', '06700', 3),
('Av. Díaz Mirón', '321', 'Zaragoza', 'Veracruz', '91700', 4),
('Calle 60', '654', 'Centro', 'Mérida', '97000', 5);

INSERT INTO Cliente (nombre, apellidoPaterno, apellidoMaterno, telefono, correo, idDireccion)
VALUES 
('Pedro', 'Ramírez', 'Díaz', '3312345678', 'pedro.ramirez@example.com', 1),
('Laura', 'Gómez', 'Torres', '8112345678', 'laura.gomez@example.com', 2),
('Sofía', 'Hernández', 'Pérez', '5512345678', 'sofia.hernandez@example.com', 3),
('Jorge', 'Mendoza', 'Ruiz', '2291234567', 'jorge.mendoza@example.com', 4),
('Lucía', 'Martínez', 'Fernández', '9991234567', 'lucia.martinez@example.com', 5);

INSERT INTO Colaborador (nombre, apellidoPaterno, apellidoMaterno, curp, correo, noPersonal, password, idRol, fotografia, numeroLicencia)
VALUES 
('Juan', 'Pérez', 'Gómez', 'CURP12345678901234', 'juan.perez@example.com', 'NP001', 'password123', 1, NULL,NULL),
('Ana', 'López', 'Martínez', 'CURP23456789012345', 'ana.lopez@example.com', 'NP002', 'password123', 3, NULL,1002),
('Luis', 'Hernández', 'Sánchez', 'CURP34567890123456', 'luis.hernandez@example.com', 'NP003', 'password123', 1, NULL,NULL),
('María', 'García', 'Flores', 'CURP45678901234567', 'maria.garcia@example.com', 'NP004', 'password123', 3, NULL, 1001),
('Carlos', 'Rodríguez', 'Luna', 'CURP56789012345678', 'carlos.rodriguez@example.com', 'NP005', 'password123', 2, NULL,NULL);

INSERT INTO TipoUnidad (tipo)
VALUES 
('Camioneta'),
('Sedán'),
('SUV'),
('Motocicleta'),
('Camión');

INSERT INTO Unidad (marca, modelo, anio, vin, noIdentificacion, idTipoUnidad)
VALUES 
('Toyota', 'Hilux', '2022', 'VIN1234567890', 'ID001', 1),
('Nissan', 'Versa', '2021', 'VIN2345678901', 'ID002', 2),
('Honda', 'CR-V', '2020', 'VIN3456789012', 'ID003', 3),
('Yamaha', 'FZ', '2023', 'VIN4567890123', 'ID004', 4),
('Freightliner', 'Cascadia', '2019', 'VIN5678901234', 'ID005', 5);

INSERT INTO ColaboradorUnidad (idUnidad, idColaborador)
VALUES 
(1, 2),
(2, 4);

INSERT INTO Envio (noGuia, costoEnvio, idEstadoEnvio, idCliente, idColaborador, idDireccionDestino)
VALUES 
('G12345', 150.50, 1, 1, 1, 2),
('G23456', 200.00, 2, 2, 2, 3),
('G34567', 175.75, 3, 3, 3, 4),
('G45678', 100.25, 1, 4, 4, 5),
('G56789', 250.00, 2, 5, 5, 1);


INSERT INTO HistorialEnvio (idColaborador, idEnvio, comentario, horaModificacion, fechaModificacion, idEstadoEnvio)
VALUES 
(1, 1, 101, CURRENT_TIMESTAMP, CURDATE(), 1),
(2, 2, 102, CURRENT_TIMESTAMP, CURDATE(), 2),
(3, 3, 103, CURRENT_TIMESTAMP, CURDATE(), 3),
(4, 4, 104, CURRENT_TIMESTAMP, CURDATE(), 1),
(5, 5, 105, CURRENT_TIMESTAMP, CURDATE(), 2);

INSERT INTO Paquete (descripcion, peso, altura, ancho, profundidad, idEnvio)
VALUES 
('Paquete pequeño', 1, 10, 15, 20, 1),
('Paquete mediano', 5, 20, 25, 30, 2),
('Paquete grande', 10, 30, 35, 40, 3),
('Paquete frágil', 2, 15, 20, 25, 4),
('Paquete voluminoso', 20, 50, 60, 70, 5);
