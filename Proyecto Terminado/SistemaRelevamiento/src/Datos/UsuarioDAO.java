package Datos;

import Negocio.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection conexion;

    public UsuarioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarUsuario(Usuario usuario) {
        try {
            String consultaSQL = "INSERT INTO Usuarios (NombreDeCuenta, Contrasena, TipoDeUsuario) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(consultaSQL);
            preparedStatement.setString(1, usuario.getNombreDeCuenta());
            preparedStatement.setString(2, usuario.getContrasena());
            preparedStatement.setString(3, usuario.getTipoDeUsuario());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarUsuario(Usuario usuario) {
        try {
            String consultaSQL = "UPDATE Usuarios SET Contrasena = ?, TipoDeUsuario = ? WHERE NombreDeCuenta = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(consultaSQL);
            preparedStatement.setString(1, usuario.getContrasena());
            preparedStatement.setString(2, usuario.getTipoDeUsuario());
            preparedStatement.setString(3, usuario.getNombreDeCuenta());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarUsuario(String nombreDeCuenta) {
        try {
            String consultaSQL = "DELETE FROM Usuarios WHERE NombreDeCuenta = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(consultaSQL);
            preparedStatement.setString(1, nombreDeCuenta);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario obtenerUsuarioPorNombreDeCuenta(String nombreDeCuenta) {
        try {
            String consultaSQL = "SELECT ID, NombreDeCuenta, Contrasena, TipoDeUsuario FROM Usuarios WHERE NombreDeCuenta = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(consultaSQL);
            preparedStatement.setString(1, nombreDeCuenta);
            ResultSet resultado = preparedStatement.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("ID");
                String contrasena = resultado.getString("Contrasena");
                String tipoDeUsuario = resultado.getString("TipoDeUsuario");

                return new Usuario(id, nombreDeCuenta, contrasena, tipoDeUsuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            String consultaSQL = "SELECT ID, NombreDeCuenta, Contrasena, TipoDeUsuario FROM Usuarios";
            PreparedStatement preparedStatement = conexion.prepareStatement(consultaSQL);
            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("ID");
                String nombreDeCuenta = resultado.getString("NombreDeCuenta");
                String contrasena = resultado.getString("Contrasena");
                String tipoDeUsuario = resultado.getString("TipoDeUsuario");

                usuarios.add(new Usuario(id, nombreDeCuenta, contrasena, tipoDeUsuario));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
}
