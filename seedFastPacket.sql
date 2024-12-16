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
('Yucatán'),
('Puebla'),
('Querétaro'),
('Chiapas');

INSERT INTO Municipio (nombre, idEstado)
VALUES 
('Guadalajara', 1),
('Monterrey', 2),
('Cuauhtémoc', 3),
('Veracruz', 4),
('Mérida', 5),
('Puebla', 6),
('Querétaro', 7),
('Tuxtla Gutiérrez', 8);

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
('Laura', 'Gómez', 'Torres', '8112345678', 'laura.gomez@example.com', NULL),
('Sofía', 'Hernández', 'Pérez', '5512345678', 'sofia.hernandez@example.com', NULL),
('Jorge', 'Mendoza', 'Ruiz', '2291234567', 'jorge.mendoza@example.com', 4),
('Lucía', 'Martínez', 'Fernández', '9991234567', 'lucia.martinez@example.com', NULL);

INSERT INTO Colaborador (nombre, apellidoPaterno, apellidoMaterno, curp, correo, noPersonal, password, idRol, fotografia, numeroLicencia)
VALUES 
('Juan', 'Pérez', 'Gómez', 'PEHJ890101HDFRNS01', 'juan.perez@example.com', 'NP001', 'password123', 1, NULL, NULL),
('Ana', 'López', 'Martínez', 'LOMA920202MJCRLN02', 'ana.lopez@example.com', 'NP002', 'password123', 3, NULL, 1002),
('Luis', 'Hernández', 'Sánchez', 'HESL850303HGRRLN03', 'luis.hernandez@example.com', 'NP003', 'password123', 1, NULL, NULL),
('María', 'García', 'Flores', 'GAFL910404MJCRLN04', 'maria.garcia@example.com', 'NP004', 'password123', 3, NULL, 1001),
('Carlos', 'Rodríguez', 'Luna', 'ROLU880505HDFRNR05', 'carlos.rodriguez@example.com', 'NP005', 'password123', 2, NULL, NULL);

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
('FP123456', 150.50, 1, NULL, 4, 2),
('FP234567', 200.00, 2, NULL, 4, 3),
('FP345678', 175.75, 3, NULL, 4, 4),
('FP456789', 100.25, 1, NULL, 2, 5),
('FP567890', 250.00, 2, NULL, 2, 1);

INSERT INTO HistorialEnvio (idColaborador, idEnvio, comentario, idEstadoEnvio)
VALUES 
(1, 1, "Paquete en tránsito", 2),
(2, 3, "Entregado sin contratiempos", 3),
(4, 5, "Requiere revisión adicional", 1),
(5, 2, "Retraso por clima", 2),
(3, 4, "Paquete cancelado por remitente", 4);

INSERT INTO Paquete (descripcion, peso, altura, ancho, profundidad, idEnvio)
VALUES 
('Paquete pequeño', 1, 10, 15, 20, 1),
('Paquete mediano', 5, 20, 25, 30, 2),
('Paquete grande', 10, 30, 35, 40, 3),
('Paquete frágil', 2, 15, 20, 25, 4),
('Paquete voluminoso', 20, 50, 60, 70, 5);
