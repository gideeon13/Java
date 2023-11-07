package Interface;

import javax.swing.*;

import Datos.Conexion;
import Negocio.Computadora;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class VentanaAdmin extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public VentanaAdmin() {
        // Configura la ventana
        setTitle("Bienvenido al Menú de Administradores");
        setSize(450, 200); // Achicar la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Usamos DISPOSE_ON_CLOSE para permitir la apertura de VentanaLogin

        // Crea un panel principal con BorderLayout y un espacio en blanco en los bordes
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Añade espacio en blanco

        // Crea un título en la parte superior
        JLabel titleLabel = new JLabel("Bienvenido al Menú de Administradores");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Crea un panel para los botones en el centro
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Crea los botones con el tamaño y el estilo de fuente adecuados
        JButton btnCrearInforme = new JButton("Crear Informe");
        btnCrearInforme.setFont(new Font("Arial", Font.PLAIN, 16));
        JButton btnMostrarInformacion = new JButton("Mostrar Información");
        btnMostrarInformacion.setFont(new Font("Arial", Font.PLAIN, 16));

        // Agrega listeners a los botones para realizar las acciones
        btnCrearInforme.addActionListener(e -> crearInforme());
        btnMostrarInformacion.addActionListener(e -> mostrarInformacionComputadoras());

        // Agrega los botones al panel de botones
        buttonPanel.add(btnCrearInforme);
        buttonPanel.add(btnMostrarInformacion);

        // Crea un nuevo panel para el botón "Salir"
        JPanel exitButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Crea el botón "Salir" con el tamaño y el estilo de fuente adecuados
        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 16));

        // Agrega un listener para cerrar VentanaAdmin y abrir SistemaRelevamientoGUI al hacer clic en el botón "Salir"
        btnSalir.addActionListener(e -> {
            VentanaAdmin.this.dispose(); // Cierra VentanaAdmin
            SistemaRelevamientoGUI sistemaRelevamientoGUI = new SistemaRelevamientoGUI();
            sistemaRelevamientoGUI.mostrarVentana(); // Abre SistemaRelevamientoGUI
        });

        // Agrega el botón "Salir" al panel de "Salir"
        exitButtonPanel.add(btnSalir);

        // Agrega el panel de botones al centro del panel principal
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Agrega el panel de "Salir" debajo de los otros botones
        mainPanel.add(exitButtonPanel, BorderLayout.SOUTH);

        // Agrega el panel principal a la ventana
        getContentPane().add(mainPanel);
    }

    // Método para crear un informe
    public void crearInforme() {
        SwingUtilities.invokeLater(() -> {
            CrearInformeForm form = new CrearInformeForm();
            form.mostrarVentana();
        });
    }

 // Método para mostrar información de las computadoras
    public void mostrarInformacionComputadoras() {
        // Crear un array con las opciones de filtrado
        String[] opcionesFiltrado = {"Filtrar por Departamento", "Filtrar por Localidad o UTU", "Filtrar por Número de Sala", "Listado Completo"};

        // Crear un menú desplegable para que el usuario elija la opción de filtrado
        String seleccion = (String) JOptionPane.showInputDialog(this, "Seleccione una opción de filtrado:", "Opciones de Filtrado", JOptionPane.QUESTION_MESSAGE, null, opcionesFiltrado, opcionesFiltrado[0]);

        if (seleccion != null) {
            if (seleccion.equals("Filtrar por Departamento")) {
                String departamento = JOptionPane.showInputDialog(this, "Ingrese el Departamento:");
                if (departamento != null && !departamento.isEmpty()) {
                    // Lógica para filtrar por Departamento
                    filtrarComputadorasPorUbicacion(departamento);
                }
            } else if (seleccion.equals("Filtrar por Localidad o UTU")) {
                String nombreLocalidad = JOptionPane.showInputDialog(this, "Ingrese el Nombre de Localidad o UTU:");
                if (nombreLocalidad != null && !nombreLocalidad.isEmpty()) {
                    // Lógica para filtrar por Localidad o UTU
                	buscarComputadorasPorLocalidad(nombreLocalidad);
                }
            } else if (seleccion.equals("Filtrar por Número de Sala")) {
                String numeroSalaStr = JOptionPane.showInputDialog(this, "Ingrese el Número de Sala:");
                if (numeroSalaStr != null && !numeroSalaStr.isEmpty()) {
                    int numeroSala = Integer.parseInt(numeroSalaStr);
                    // Lógica para filtrar por Número de Sala
                    buscarComputadorasPorNumeroSala(numeroSala);
                }
            } else if (seleccion.equals("Listado Completo")) {
                // Lógica para mostrar todas las computadoras sin aplicar filtros
                mostrarTodasLasComputadoras();
            }
        }
    }
 
    private void mostrarTodasLasComputadoras() {
        new VentanaListaComputadoras(); // Mostrar la ventana VentanaListaComputadoras
    }
    
 // Método para mostrar las computadoras en un cuadro de diálogo
    private void mostrarComputadorasEnDialogo(List<Computadora> computadoras) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Computadoras encontradas en la ubicación especificada:\n");
        
        for (Computadora computadora : computadoras) {
            mensaje.append("ID: ").append(computadora.getID()).append("\n");
            mensaje.append("Modelo CPU: ").append(computadora.getModeloCPU()).append("\n");
            mensaje.append("Cantidad de Procesadores: ").append(computadora.getCantProcesadores()).append("\n");
            mensaje.append("Potencia (MHz): ").append(computadora.getPotenciaMhz()).append("\n");
            mensaje.append("Familia del CPU: ").append(computadora.getFamiliaCPU()).append("\n");
            mensaje.append("Tamaño de Caché del CPU: ").append(computadora.getCacheCPU()).append("\n");
            mensaje.append("Memoria Total: ").append(computadora.getMemoriaTotal()).append("\n");
            mensaje.append("Memoria Libre: ").append(computadora.getMemoriaLibre()).append("\n");
            mensaje.append("Memoria Caché: ").append(computadora.getMemoriaCache()).append("\n");
            mensaje.append("Memoria Disponible: ").append(computadora.getMemoriaDisponible()).append("\n");
            mensaje.append("Cantidad de Almacenamiento: ").append(computadora.getCantAlmacenamiento()).append("\n");
            mensaje.append("Sistema Operativo Nombre: ").append(computadora.getSONombre()).append("\n");
            mensaje.append("Sistema Operativo Kernel: ").append(computadora.getSOKernel()).append("\n");
            mensaje.append("Sistema Operativo Versión: ").append(computadora.getSOVersion()).append("\n");
            mensaje.append("Arquitectura del Sistema Operativo: ").append(computadora.getSOArquitectura()).append("\n");

            mensaje.append("\n");
        }

        JOptionPane.showMessageDialog(this, mensaje.toString(), "Computadoras Filtradas", JOptionPane.INFORMATION_MESSAGE);
    }
    
 // Lógica para filtrar las computadoras por ubicación
    private void filtrarComputadorasPorUbicacion(String ubicacion) {
        List<Computadora> computadorasFiltradas = new ArrayList<>();

        try {
            Connection connection = Conexion.getConexion();
            
            String sql = "SELECT c.* " +
                         "FROM Computadoras c " +
                         "INNER JOIN SalasInformatica s ON c.IDInforme = s.IDInforme " +
                         "WHERE s.Ubicacion = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ubicacion);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Computadora computadora = new Computadora();
                computadora.setID(resultSet.getInt("ID"));
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

                computadorasFiltradas.add(computadora);
            }

            resultSet.close();
            preparedStatement.close();

            if (!computadorasFiltradas.isEmpty()) {
                // Mostrar las computadoras en un cuadro de diálogo
                mostrarComputadorasEnDialogo(computadorasFiltradas);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron computadoras en la ubicación especificada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void buscarComputadorasPorLocalidad(String nombreLocalidad) {
        List<Computadora> computadoras = new ArrayList<>();

        try {
            Connection connection = Conexion.getConexion();

            String sql = "SELECT C.* " +
                         "FROM Computadoras C " +
                         "INNER JOIN InformesSalas I ON C.IDInforme = I.ID " +
                         "WHERE I.Localidad = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nombreLocalidad);

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
            resultSet.close();
            preparedStatement.close();
            
            if (!computadoras.isEmpty()) {
                // Mostrar las computadoras en un cuadro de diálogo
                mostrarComputadorasEnDialogo1(computadoras);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron computadoras en la ubicación especificada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
 // Método para mostrar las computadoras en un cuadro de diálogo
    private void mostrarComputadorasEnDialogo1(List<Computadora> computadoras) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Computadoras encontradas en la ubicación especificada:\n");
        
        for (Computadora computadora : computadoras) {
            mensaje.append("ID: ").append(computadora.getID()).append("\n");
            mensaje.append("Modelo CPU: ").append(computadora.getModeloCPU()).append("\n");
            mensaje.append("Cantidad de Procesadores: ").append(computadora.getCantProcesadores()).append("\n");
            mensaje.append("Potencia (MHz): ").append(computadora.getPotenciaMhz()).append("\n");
            mensaje.append("Familia del CPU: ").append(computadora.getFamiliaCPU()).append("\n");
            mensaje.append("Tamaño de Caché del CPU: ").append(computadora.getCacheCPU()).append("\n");
            mensaje.append("Memoria Total: ").append(computadora.getMemoriaTotal()).append("\n");
            mensaje.append("Memoria Libre: ").append(computadora.getMemoriaLibre()).append("\n");
            mensaje.append("Memoria Caché: ").append(computadora.getMemoriaCache()).append("\n");
            mensaje.append("Memoria Disponible: ").append(computadora.getMemoriaDisponible()).append("\n");
            mensaje.append("Cantidad de Almacenamiento: ").append(computadora.getCantAlmacenamiento()).append("\n");
            mensaje.append("Sistema Operativo Nombre: ").append(computadora.getSONombre()).append("\n");
            mensaje.append("Sistema Operativo Kernel: ").append(computadora.getSOKernel()).append("\n");
            mensaje.append("Sistema Operativo Versión: ").append(computadora.getSOVersion()).append("\n");
            mensaje.append("Arquitectura del Sistema Operativo: ").append(computadora.getSOArquitectura()).append("\n");

            mensaje.append("\n");
        }

        JOptionPane.showMessageDialog(this, mensaje.toString(), "Computadoras Filtradas", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void buscarComputadorasPorNumeroSala(int numeroSala) {
        List<Computadora> computadoras = new ArrayList<>();

        try {
            Connection connection = Conexion.getConexion();

            String sql = "SELECT C.* " +
                         "FROM Computadoras C " +
                         "INNER JOIN SalasInformatica S ON C.IDInforme = S.IDInforme " +
                         "WHERE S.NumSala = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, numeroSala);

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
            resultSet.close();
            preparedStatement.close();

            if (!computadoras.isEmpty()) {
                // Mostrar las computadoras en un cuadro de diálogo utilizando el mismo método
                mostrarComputadorasEnDialogo2(computadoras);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron computadoras en la sala especificada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 // Método para mostrar las computadoras en un cuadro de diálogo
    private void mostrarComputadorasEnDialogo2(List<Computadora> computadoras) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Computadoras encontradas en la ubicación especificada:\n");
        
        for (Computadora computadora : computadoras) {
            mensaje.append("ID: ").append(computadora.getID()).append("\n");
            mensaje.append("Modelo CPU: ").append(computadora.getModeloCPU()).append("\n");
            mensaje.append("Cantidad de Procesadores: ").append(computadora.getCantProcesadores()).append("\n");
            mensaje.append("Potencia (MHz): ").append(computadora.getPotenciaMhz()).append("\n");
            mensaje.append("Familia del CPU: ").append(computadora.getFamiliaCPU()).append("\n");
            mensaje.append("Tamaño de Caché del CPU: ").append(computadora.getCacheCPU()).append("\n");
            mensaje.append("Memoria Total: ").append(computadora.getMemoriaTotal()).append("\n");
            mensaje.append("Memoria Libre: ").append(computadora.getMemoriaLibre()).append("\n");
            mensaje.append("Memoria Caché: ").append(computadora.getMemoriaCache()).append("\n");
            mensaje.append("Memoria Disponible: ").append(computadora.getMemoriaDisponible()).append("\n");
            mensaje.append("Cantidad de Almacenamiento: ").append(computadora.getCantAlmacenamiento()).append("\n");
            mensaje.append("Sistema Operativo Nombre: ").append(computadora.getSONombre()).append("\n");
            mensaje.append("Sistema Operativo Kernel: ").append(computadora.getSOKernel()).append("\n");
            mensaje.append("Sistema Operativo Versión: ").append(computadora.getSOVersion()).append("\n");
            mensaje.append("Arquitectura del Sistema Operativo: ").append(computadora.getSOArquitectura()).append("\n");

            mensaje.append("\n");
        }

        JOptionPane.showMessageDialog(this, mensaje.toString(), "Computadoras Filtradas", JOptionPane.INFORMATION_MESSAGE);
    }    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaAdmin ventanaAdmin = new VentanaAdmin();
            ventanaAdmin.setVisible(true);
            ventanaAdmin.setLocationRelativeTo(null); // Centrar la ventana en el centro de la pantalla
        });
    }

}


