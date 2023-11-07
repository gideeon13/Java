package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Negocio.InformeSala;

public class InformeDAO {
    private Connection connection;

    public InformeDAO() {
        connection = Conexion.getConexion();
    }

    public int agregarInformeSala(InformeSala informeSala) {
        try {
            String query = "INSERT INTO InformesSalas (IDUsuario, FechaDeRealizacion, Localidad, CantSalasDisponibles) " +
                           "VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); // Observe el uso de Statement.RETURN_GENERATED_KEYS
            preparedStatement.setInt(1, informeSala.getIDUsuario());
            preparedStatement.setDate(2, new java.sql.Date(informeSala.getFechaDeRealizacion().getTime()));
            preparedStatement.setString(3, informeSala.getLocalidad());
            preparedStatement.setInt(4, informeSala.getCantSalasDisponibles());
            preparedStatement.executeUpdate();

            // Recupera el ID generado automáticamente
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                return -1; // Indicar un valor predeterminado o error si no se pudo recuperar el ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Manejar el error y devolver un valor predeterminado
        }
    }

    public List<InformeSala> obtenerInformesSalas() {
        List<InformeSala> informesSalas = new ArrayList<>();
        try {
            String query = "SELECT * FROM InformesSalas";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                InformeSala informeSala = new InformeSala();
                informeSala.setID(resultSet.getInt("ID"));
                informeSala.setIDUsuario(resultSet.getInt("IDUsuario"));
                informeSala.setFechaDeRealizacion(resultSet.getDate("FechaDeRealizacion"));
                informeSala.setLocalidad(resultSet.getString("Localidad"));
                informeSala.setCantSalasDisponibles(resultSet.getInt("CantSalasDisponibles"));
                informesSalas.add(informeSala);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informesSalas;
    }

	public int agregarInformeSala(int idInforme) {
		// TODO Auto-generated method stub
		return 0;
	}
}


