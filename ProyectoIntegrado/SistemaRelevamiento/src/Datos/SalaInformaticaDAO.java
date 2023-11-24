package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Negocio.Computadora;
import Negocio.SalaInformatica;

public class SalaInformaticaDAO {
    private Connection connection;

    public SalaInformaticaDAO() {
        connection = Conexion.getConexion();
    }

    public void agregarSalaInformatica(SalaInformatica salaInformatica) {
        try {
            String query = "INSERT INTO SalasInformatica (IDInforme, NumSala, Capacidad) " +
                           "VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, salaInformatica.getIDInforme());
            preparedStatement.setInt(2, salaInformatica.getNumSala());
            preparedStatement.setInt(3, salaInformatica.getCapacidad());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SalaInformatica> obtenerSalasInformatica() {
        List<SalaInformatica> salasInformatica = new ArrayList<>();
        try {
            String query = "SELECT * FROM SalasInformatica";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SalaInformatica salaInformatica = new SalaInformatica();
                salaInformatica.setID(resultSet.getInt("ID"));
                salaInformatica.setIDInforme(resultSet.getInt("IDInforme"));
                salaInformatica.setNumSala(resultSet.getInt("NumSala"));
                salaInformatica.setCapacidad(resultSet.getInt("Capacidad"));
                salasInformatica.add(salaInformatica);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salasInformatica;
    }

    public List<SalaInformatica> obtenerSalasInformaticaFiltradas(String departamento, String nombreLocalidad, int numeroSala) {
        List<SalaInformatica> salasInformatica = new ArrayList<>();
        try {
            String query = "SELECT * FROM SalasInformatica WHERE ";
            List<String> condiciones = new ArrayList<>();
            
            if (!departamento.isEmpty()) {
                condiciones.add("Departamento = ?");
            }
            
            if (!nombreLocalidad.isEmpty()) {
                condiciones.add("Localidad = ?");
            }
            
            if (numeroSala > 0) {
                condiciones.add("NumSala = ?");
            }
            
            if (condiciones.isEmpty()) {

                query = "SELECT * FROM SalasInformatica";
            } else {
                query += String.join(" AND ", condiciones);
            }
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int parameterIndex = 1;
            
            if (!departamento.isEmpty()) {
                preparedStatement.setString(parameterIndex, departamento);
                parameterIndex++;
            }
            
            if (!nombreLocalidad.isEmpty()) {
                preparedStatement.setString(parameterIndex, nombreLocalidad);
                parameterIndex++;
            }
            
            if (numeroSala > 0) {
                preparedStatement.setInt(parameterIndex, numeroSala);
            }
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                SalaInformatica salaInformatica = new SalaInformatica();
                salaInformatica.setID(resultSet.getInt("ID"));
                // Resto de la lógica para obtener datos de la salaInformatica y agregarla a la lista
                salasInformatica.add(salaInformatica);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salasInformatica;
    }


    public List<Computadora> obtenerComputadorasPorIDsInforme(List<Integer> idInformes) {
        List<Computadora> computadoras = new ArrayList<>();
        try {
            if (idInformes.isEmpty()) {
                return computadoras; 
            }
            
            String query = "SELECT * FROM Computadoras WHERE IDInforme IN (";
            for (int i = 0; i < idInformes.size(); i++) {
                query += "?";
                if (i < idInformes.size() - 1) {
                    query += ", ";
                }
            }
            query += ")";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            
            for (int i = 0; i < idInformes.size(); i++) {
                preparedStatement.setInt(i + 1, idInformes.get(i));
            }
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Computadora computadora = new Computadora();
                computadora.setID(resultSet.getInt("ID"));
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
}
