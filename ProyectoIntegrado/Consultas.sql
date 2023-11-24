
-- 1) Datos de UTU con la cantidad de salas disponibles ordenadas de mayor a menor.
SELECT Localidad, SUM(CantSalasDisponibles) AS TotalSalasDisponibles
FROM InformesSalas
GROUP BY Localidad
ORDER BY TotalSalasDisponibles DESC;

-- 2) Datos de una UTU en particular, sus salas y la cantidad de PC disponibles por sala.
SELECT InformesSalas.Localidad, SI.NumSala, SI.Capacidad, COUNT(C.ID) AS CantPCDisponibles
FROM SalasInformatica SI
JOIN InformesSalas ON SI.IDInforme = InformesSalas.ID
LEFT JOIN Computadoras C ON SI.ID = C.IDInforme
WHERE InformesSalas.Localidad = ‘Pando’
GROUP BY InformesSalas.Localidad, SI.NumSala, SI.Capacidad
ORDER BY SI.NumSala;

-- 3) Datos de UNA sala en particular y las computadoras de las que dispone, mostrando sus especificaciones.
SELECT SI.NumSala, C.NumIdentificador, C.ModeloCPU, C.CantProcesadores,
C.PotenciaMhz, C.FamiliaCPU, C.CacheCPU, C.MemoriaTotal, C.MemoriaLibre,
C.MemoriaCache, C.MemoriaDisponible, C.CantAlmacenamiento, C.SONombre,
C.SOKernel, C.SOVersion, C.SOArquitectura
FROM SalasInformatica SI
JOIN Computadoras C ON SI.ID = C.IDInforme
WHERE SI.NumSala = 1;

-- 4) Listado de administradores y la cantidad de informes realizados.
SELECT U.NombreDeCuenta AS Administrador, COUNT(InformesSalas.ID) AS CantInformesRealizados
FROM Usuarios U
JOIN InformesSalas ON U.ID = InformesSalas.IDUsuario
WHERE U.TipoDeUsuario = ‘administrador’
GROUP BY U.NombreDeCuenta
ORDER BY CantInformesRealizados DESC;

-- 5) Administradores que han realizado informes en donde aparece una PC en particular.
SELECT DISTINCT U.NombreDeCuenta AS Administrador
FROM Usuarios U
JOIN InformesSalas ON U.ID = InformesSalas.IDUsuario
JOIN SalasInformatica SI ON InformesSalas.ID = SI.IDInforme
JOIN Computadoras C ON SI.ID = C.IDInforme
WHERE C.NumIdentificador = 1 AND U.TipoDeUsuario = ‘administrador’;
