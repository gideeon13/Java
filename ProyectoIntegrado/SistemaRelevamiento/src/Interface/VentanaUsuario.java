package Interface;

import javax.swing.*;

import Datos.Conexion;
import Negocio.Computadora;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentanaUsuario extends JFrame {
    private static final long serialVersionUID = 5281374574728399278L;

    public VentanaUsuario() {
        setTitle("Bienvenido al Menú de Usuario Regular");
        setSize(400, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        // Panel principal con GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Panel para botones con BoxLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton btnMostrarInfo = new JButton("Mostrar Información");
        JButton btnAbrirInforme = new JButton("Abrir PDF");
        JButton btnSalir = new JButton("Salir");

        // Tamaño de botones
        Dimension buttonSize = new Dimension(200, 60);
        btnMostrarInfo.setPreferredSize(buttonSize);
        btnAbrirInforme.setPreferredSize(buttonSize);
        btnSalir.setPreferredSize(buttonSize);

        // Aumentar el tamaño de los botones
        btnMostrarInfo.setMargin(new Insets(10, 40, 10, 30));
        btnAbrirInforme.setMargin(new Insets(10, 68, 10, 65));
        btnSalir.setMargin(new Insets(10, 80, 10, 80));
        
        // Espacio vertical entre los botones
        int espacioVertical = 10;

        // Configuración para centrar el panel de botones en el centro
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonPanel, gbc);

        // Agregar elementos al panel de botones con espacio vertical
        buttonPanel.add(Box.createVerticalStrut(espacioVertical));
        buttonPanel.add(btnMostrarInfo);
        buttonPanel.add(Box.createVerticalStrut(espacioVertical));
        buttonPanel.add(btnAbrirInforme);
        buttonPanel.add(Box.createVerticalStrut(espacioVertical));
        buttonPanel.add(btnSalir);
        
        btnMostrarInfo.addActionListener(e -> mostrarInformacionComputadoras());
        
        btnAbrirInforme.addActionListener(e -> abrirInforme());

        // Configurar el botón "Salir" para cerrar la ventana y volver a la ventana principal
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra esta ventana
                SistemaRelevamientoGUI sistemaRelevamiento = new SistemaRelevamientoGUI();
                sistemaRelevamiento.mostrarVentana();
            }
        });


        // Agregar el panel principal a la ventana
        getContentPane().add(mainPanel);

        setVisible(true);
    }
    
 // Método para abrir y mostrar el archivo PDF generado
    public void abrirInforme() {
    	try {
    		// Obtener la ruta del escritorio del usuario
    		String escritorioRuta = System.getProperty("user.home") + File.separator + "Desktop";
    		
    		// Ruta al archivo PDF
    		String pdfNombre = "Informe.pdf";
    		
    		// Ruta completa al archivo PDF
    		String pdfRuta = escritorioRuta + File.separator + pdfNombre;
    		
    		// Crear un objeto File con la ruta al archivo PDF
    		File file = new File(pdfRuta);
    		
    		// Verificar si el archivo existe
    		if (file.exists()) {
    			// Obtener el escritorio
    			Desktop desktop = Desktop.getDesktop();
    			// Abrir el archivo con el visor predeterminado
    			desktop.open(file);
    		} else {
    			// Mostrar mensaje si el archivo no existe
    			JOptionPane.showMessageDialog(null, "El archivo PDF no existe.", "Error", JOptionPane.ERROR_MESSAGE);
    		}
    	} catch (IOException ex) {
    	  ex.printStackTrace();	
    	}
        
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
                    "INNER JOIN InformesSalas i ON s.IDInforme = i.ID " +
                    "WHERE i.Departamento = ?";

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
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaUsuario();
            }
        });
    }

    public void mostrarVentana() {
        // TODO Auto-generated method stub
    }
}







