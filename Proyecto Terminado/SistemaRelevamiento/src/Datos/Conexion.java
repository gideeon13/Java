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
                String dbURL = "jdbc:mysql://localhost/Sistema";
                String usuarioDB = "root";
                String contrasenaDB = "root";
                conexion = DriverManager.getConnection(dbURL, usuarioDB, contrasenaDB);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conexion;
    }

    public static PreparedStatement prepareStatement(String consultaSQL) throws SQLException {
        Connection connection = getConexion();
        return connection.prepareStatement(consultaSQL);
    }
}


