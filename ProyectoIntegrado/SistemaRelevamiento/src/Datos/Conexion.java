package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {
    private static Connection conexion;

    public static Connection getConexion() {
        if (conexion == null) {
            try {
            	String dbURL = "jdbc:mysql://localhost:3306/Sistema";
                String usuarioDB = "root";
                String contrasenaDB = "root";
                conexion = DriverManager.getConnection(dbURL, usuarioDB, contrasenaDB);
            } catch (SQLException e) {
                throw new RuntimeException("Error al establecer la conexión a la base de datos", e);
            }
        }
        return conexion;
    }

    public static void closeConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cerrar la conexión a la base de datos", e);
        }
    }

    public static PreparedStatement prepareStatement(String consultaSQL) throws SQLException {
        Connection connection = getConexion();
        return connection.prepareStatement(consultaSQL);
    }
}


