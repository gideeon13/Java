package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Negocio.Computadora;

public class ComputadoraDAO {
    private Connection connection;

    public ComputadoraDAO() {
        connection = Conexion.getConexion();
    }

    public void agregarComputadora(Computadora computadora) {
        try {
            String query = "INSERT INTO Computadoras (IDInforme, NumIdentificador, ModeloCPU, CantProcesadores, " +
                           "PotenciaMhz, FamiliaCPU, CacheCPU, MemoriaTotal, MemoriaLibre, MemoriaCache, " +
                           "MemoriaDisponible, CantAlmacenamiento, SONombre, SOKernel, SOVersion, SOArquitectura) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, computadora.getIDInforme());
            preparedStatement.setInt(2, computadora.getNumIdentificador());
            preparedStatement.setString(3, computadora.getModeloCPU());
            preparedStatement.setInt(4, computadora.getCantProcesadores());
            preparedStatement.setInt(5, computadora.getPotenciaMhz());
            preparedStatement.setString(6, computadora.getFamiliaCPU());
            preparedStatement.setInt(7, computadora.getCacheCPU());
            preparedStatement.setInt(8, computadora.getMemoriaTotal());
            preparedStatement.setInt(9, computadora.getMemoriaLibre());
            preparedStatement.setInt(10, computadora.getMemoriaCache());
            preparedStatement.setInt(11, computadora.getMemoriaDisponible());
            preparedStatement.setInt(12, computadora.getCantAlmacenamiento());
            preparedStatement.setString(13, computadora.getSONombre());
            preparedStatement.setString(14, computadora.getSOKernel());
            preparedStatement.setString(15, computadora.getSOVersion());
            preparedStatement.setString(16, computadora.getSOArquitectura());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Computadora> obtenerComputadoras() {
        List<Computadora> computadoras = new ArrayList<>();
        try {
            String query = "SELECT * FROM Computadoras";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Computadora computadora = new Computadora();
                computadora.setID(resultSet.getInt("ID"));
                computadora.setIDInforme(resultSet.getInt("IDInforme"));
                computadora.setNumIdentificador(resultSet.getInt("NumIdentificador"));
                computadora.setModeloCPU(resultSet.getString("ModeloCPU"));
                computadora.setCantProcesadores(resultSet.getInt("CantProcesadores"));
                computadora.setPotenciaMhz(resultSet.getInt("PotenciaMhz"));
                computadora.setFamiliaCPU(resultSet.getString("FamiliaCPU"));
                computadora.setCacheCPU(resultSet.getInt("CacheCPU"));
                computadora.setMemoriaTotal(resultSet.getInt("MemoriaTotal"));
                computadora.setMemoriaLibre(resultSet.getInt("MemoriaLibre"));
                computadora.setMemoriaCache(resultSet.getInt("MemoriaCache"));
                computadora.setMemoriaDisponible(resultSet.getInt("MemoriaDisponible"));
                computadora.setCantAlmacenamiento(resultSet.getInt("CantAlmacenamiento"));
                computadora.setSONombre(resultSet.getString("SONombre"));
                computadora.setSOKernel(resultSet.getString("SOKernel"));
                computadora.setSOVersion(resultSet.getString("SOVersion"));
                computadora.setSOArquitectura(resultSet.getString("SOArquitectura"));
                computadoras.add(computadora);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return computadoras;
    }

	public List<Computadora> obtenerComputadorasPorDepartamento(String departamento) {
		// TODO Auto-generated method stub
		return null;
	}
}

