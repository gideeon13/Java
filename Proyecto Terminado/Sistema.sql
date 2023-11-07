-- Elimina la base de datos si existe
DROP DATABASE IF EXISTS Sistema;

-- Crea una nueva base de datos llamada "Sistema"
CREATE DATABASE Sistema;

-- Usa la base de datos "Sistema"
USE Sistema;

-- Creación de la tabla de Usuarios sin Nombre, Direccion y Telefono
CREATE TABLE Usuarios (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NombreDeCuenta VARCHAR(255) NOT NULL,
    Contrasena VARCHAR(255) NOT NULL,
    TipoDeUsuario ENUM('root', 'administrador', 'usuario') NOT NULL
    -- Otras propiedades de usuario aquí
);
-- Agregar información de muestra a la tabla Usuarios
INSERT INTO Usuarios (NombreDeCuenta, Contrasena, TipoDeUsuario) VALUES
('root', 'root', 'root');

-- Creación de la tabla de Informes de Salas de Informática
CREATE TABLE InformesSalas (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    IDUsuario INT,
    FechaDeRealizacion DATE,
    Localidad VARCHAR(255),
    CantSalasDisponibles INT,
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
    Ubicacion VARCHAR(255),
    Capacidad INT,
    FOREIGN KEY (IDInforme) REFERENCES InformesSalas(ID)
);

SELECT * FROM informessalas;
