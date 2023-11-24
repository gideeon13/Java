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
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO InformesSalas (IDUsuario, FechaDeRealizacion, Departamento, Localidad, CantSalasDisponibles) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, informeSala.getIDUsuario());
            preparedStatement.setDate(2, new java.sql.Date(informeSala.getFechaDeRealizacion().getTime()));
            preparedStatement.setString(3, informeSala.getDepartamento());
            preparedStatement.setString(4, informeSala.getLocalidad());
            preparedStatement.setInt(5, informeSala.getCantSalasDisponibles());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
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
                informeSala.setDepartamento(resultSet.getString("Departamento"));
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


