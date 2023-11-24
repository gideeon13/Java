-- Elimina la base de datos si existe
DROP DATABASE IF EXISTS Sistema;

-- Crea una nueva base de datos llamada "Sistema"
CREATE DATABASE IF NOT EXISTS Sistema;

-- Usa la base de datos "Sistema"
USE Sistema;

-- Creación de la tabla de Usuarios
CREATE TABLE Usuarios (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NombreDeCuenta VARCHAR(255) NOT NULL,
    Contrasena VARCHAR(255) NOT NULL,
    TipoDeUsuario ENUM('root', 'administrador', 'usuario') NOT NULL
    -- Otras propiedades de usuario aquí
);

-- Creación de la tabla de Informes de Salas de Informática
CREATE TABLE InformesSalas (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    IDUsuario INT,
    FechaDeRealizacion DATE,
	Departamento VARCHAR(255),
    Localidad VARCHAR(255),
    CantSalasDisponibles INT NOT NULL,
    FOREIGN KEY (IDUsuario) REFERENCES Usuarios(ID)
);

-- Creación de la tabla de Computadoras
CREATE TABLE Computadoras (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    IDInforme INT,
    NumIdentificador INT,
    ModeloCPU VARCHAR(255),
    CantProcesadores INT,
    PotenciaMhz INT,
    FamiliaCPU VARCHAR(255),
    CacheCPU INT,
    MemoriaTotal INT,
    MemoriaLibre INT,
    MemoriaCache INT,
    MemoriaDisponible INT,
    CantAlmacenamiento INT,
    SONombre VARCHAR(255),
    SOKernel VARCHAR(255),
    SOVersion VARCHAR(255),
    SOArquitectura VARCHAR(50),
    FOREIGN KEY (IDInforme) REFERENCES InformesSalas(ID)
);

-- Creación de la tabla de Salas de Informática
CREATE TABLE SalasInformatica (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    IDInforme INT,
    NumSala INT,
    Capacidad INT,
    FOREIGN KEY (IDInforme) REFERENCES InformesSalas(ID)
);

-- Agregar información de muestra a la tabla Usuarios
INSERT INTO Usuarios (NombreDeCuenta, Contrasena, TipoDeUsuario) VALUES
('root', 'root', 'root'), ('cristian', 'cristian', 'administrador'), ('federico', 'federico', 'usuario') ;

-- Agregar información de muestra a la tabla InformesSalas
INSERT INTO InformesSalas (IDUsuario, FechaDeRealizacion, Departamento, Localidad, CantSalasDisponibles) VALUES
('1', '2021-10-11', 'Canelones', 'Pando', '1'),
('1', '2022-11-14', 'Montevideo', 'Centro', '1'),
('1', '2022-12-19', 'Maldonado', 'Piriapolis', '1'),
('1', '2023-10-24', 'Artigas', 'Norte', '1'),
('1', '2023-11-18', 'Soriano', 'Sur', '1'),
('1', '2023-11-22', 'San Jose', '12', '1'),
('1', '2023-11-24', 'Tacuarembo', '1234', '1');

-- Agregar información de muestra a la tabla Computadoras
INSERT INTO Computadoras (IDInforme, NumIdentificador, ModeloCPU, CantProcesadores, PotenciaMhz, FamiliaCPU, CacheCPU, MemoriaTotal, 
                         MemoriaLibre, MemoriaCache, MemoriaDisponible, CantAlmacenamiento, SONombre, SOKernel, SOVersion, SOArquitectura) VALUES
('1', '1', 'Intel Core i9 8700K', '1', '4500', '9', '12', '12', '4', '2', '6', '2', 'Windows 11', '4.25.128', '23H4', '64 bits'),
('2', '2', 'Intel Core i7 8700K', '1', '4400', '8', '11', '11', '4', '2', '6', '2', 'Windows 11', '4.20.128', '23H3', '64 bits'),
('3', '3', 'Intel Core i5 8700K', '1', '4300', '7', '10', '10', '4', '2', '6', '1', 'Windows 10', '4.18.128', '22H2', '64 bits'),
('4', '4', 'Ryzen 5', '1', '4800', '6', '09', '09', '4', '2', '6', '1', 'Windows 10', '5.16.126', '22H1', '64 bits'),
('5', '5', 'Ryzen 7', '1', '4700', '5', '08', '08', '4', '2', '6', '1', 'Windows 8', '4.16.136', '21H1', '64 bits'),
('6', '6', 'Ryzen 3', '1', '4600', '4', '07', '07', '4', '2', '6', '1', 'Windows 7', '3.16.126', '18C3', '32 bits'),
('7', '7', 'Ryzen 9', '1', '4200', '3', '06', '06', '4', '2', '6', '1', 'Windows 11', '5.16.126', '23H2', '64 bits');

-- Agregar información de muestra a la tabla SalasInformatica
INSERT INTO SalasInformatica (IDInforme, NumSala, Capacidad) VALUES
('1', '1', '1'),
('2', '2', '1'),
('3', '3', '1'),
('4', '4', '1'),
('5', '5', '1'),
('6', '6', '1'),
('7', '7', '1');

SELECT * FROM usuarios;

