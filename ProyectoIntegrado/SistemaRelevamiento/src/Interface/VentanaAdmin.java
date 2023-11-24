package Interface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Datos.Conexion;
import Negocio.Computadora;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Image;

public class VentanaAdmin extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public VentanaAdmin() {
        // Configura la ventana
        setTitle("Bienvenido al Menú de Administradores");
        setSize(450, 250); // Achicar la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Usamos DISPOSE_ON_CLOSE para permitir la apertura de VentanaLogin
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        
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
        JButton btnGenerarInforme = new JButton("Generar Informe");
        btnGenerarInforme.setFont(new Font("Arial", Font.PLAIN, 16));
        JButton btnAbrirInforme = new JButton("Abrir PDF");
        btnAbrirInforme.setFont(new Font("Arial", Font.PLAIN, 16));
        
        // Agrega listeners a los botones para realizar las acciones
        btnCrearInforme.addActionListener(e -> crearInforme());
        btnMostrarInformacion.addActionListener(e -> mostrarInformacionComputadoras());
        btnGenerarInforme.addActionListener(e -> generarInforme());
        btnAbrirInforme.addActionListener(e -> abrirInforme());
        
        // Agrega los botones al panel de botones
        buttonPanel.add(btnCrearInforme);
        buttonPanel.add(btnMostrarInformacion);
        buttonPanel.add(btnGenerarInforme);
        buttonPanel.add(btnAbrirInforme);
        
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

    private void generarInforme() {
    	
    	Document documento = new Document();
    	
    	try {
    		String ruta = System.getProperty("user.home");
    		PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Informe.pdf"));
    	
    		Image header = Image.getInstance("src/img/portada.jpeg");
            header.scaleToFit(600, 990);
            header.setAlignment(Chunk.ALIGN_CENTER);
                    
    		documento.open();
    		documento.add(header);
    		
    		PdfPTable tabla = new PdfPTable(15);
    		tabla.addCell("ID");
    		tabla.addCell("M. CPU");
    		tabla.addCell("C. Proc");
    		tabla.addCell("Pot.");
    		tabla.addCell("F. CPU");
    		tabla.addCell("C. CPU");    	
    		tabla.addCell("Mem T.");
    		tabla.addCell("Mem L.");
    		tabla.addCell("Mem C.");
    		tabla.addCell("Mem D.");
    		tabla.addCell("C. Alm.");
    		tabla.addCell("SON.");
    		tabla.addCell("SOK.");
    		tabla.addCell("SOV.");
    		tabla.addCell("SOA.");
    		
            // Ajustar el ancho de las celdas
            float[] anchos = {5f, 8f, 8f, 6f, 8f, 8f, 8f, 8f, 8f, 8f, 7f, 6f, 6f, 6f, 6f};
            tabla.setTotalWidth(500); 
            tabla.setWidths(anchos);
    		
            // Ajustar tamaño de las celdas
    		float altoCelda = 30; 

    		for (int i = 0; i < 15; i++) {
    		    PdfPCell celda = tabla.getRow(0).getCells()[i];
    		    celda.setFixedHeight(altoCelda);
    		}
    		    		
    		try {
    			Connection Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sistema", "root", "root");
    			PreparedStatement pst = Conexion.prepareStatement("SELECT InformesSalas.ID, "
                        + "Computadoras.ModeloCPU, Computadoras.CantProcesadores, "
                        + "Computadoras.PotenciaMhz, Computadoras.FamiliaCPU, "
                        + "Computadoras.CacheCPU, Computadoras.MemoriaTotal, "
                        + "Computadoras.MemoriaLibre, Computadoras.MemoriaCache, "
                        + "Computadoras.MemoriaDisponible, Computadoras.CantAlmacenamiento, "
                        + "Computadoras.SONombre, Computadoras.SOKernel, "
                        + "Computadoras.SOVersion, Computadoras.SOArquitectura "
                        + "FROM Usuarios "
                        + "INNER JOIN InformesSalas ON Usuarios.ID = InformesSalas.IDUsuario "
                        + "INNER JOIN SalasInformatica ON InformesSalas.ID = SalasInformatica.IDInforme "
                        + "INNER JOIN Computadoras ON InformesSalas.ID = Computadoras.IDInforme");
    			
    			ResultSet rs = pst.executeQuery();
    			
    			if(rs.next()){
    				
    			do {	
    				tabla.addCell(rs.getString(1));
    				tabla.addCell(rs.getString(2));
    				tabla.addCell(rs.getString(3));
    				tabla.addCell(rs.getString(4));
    				tabla.addCell(rs.getString(5));
    				tabla.addCell(rs.getString(6));
    				tabla.addCell(rs.getString(7));
    				tabla.addCell(rs.getString(8));
    				tabla.addCell(rs.getString(9));
    				tabla.addCell(rs.getString(10));
    				tabla.addCell(rs.getString(11));
    				tabla.addCell(rs.getString(12));
    				tabla.addCell(rs.getString(13));
    				tabla.addCell(rs.getString(14));
    				tabla.addCell(rs.getString(15));
    			} while (rs.next());
    			documento.add(tabla);
    			} 		
    		} catch (DocumentException | SQLException e) {
    	    }
    		documento.close();
    		JOptionPane.showMessageDialog(null, "Informe creado.");
    	} catch (DocumentException | HeadlessException | FileNotFoundException e) {
        } catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
            // Crear un modelo de tabla
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Modelo CPU");
            model.addColumn("Cant. Procesadores");
            model.addColumn("Potencia(MHz)");
            model.addColumn("Familia CPU");
            model.addColumn("Cache CPU");
            model.addColumn("Memoria Total");
            model.addColumn("Memoria Libre");
            model.addColumn("Memoria Caché");
            model.addColumn("Memoria Disponible");
            model.addColumn("Cant. Almacenamiento");
            model.addColumn("SO Nombre");
            model.addColumn("SO Kernel");
            model.addColumn("SO Versión");
            model.addColumn("SO Arquitectura");

            // Agregar filas al modelo
            for (Computadora computadora : computadoras) {
                model.addRow(new Object[]{
                        computadora.getID(),
                        computadora.getModeloCPU(),
                        computadora.getCantProcesadores(),
                        computadora.getPotenciaMhz(),
                        computadora.getFamiliaCPU(),
                        computadora.getCacheCPU(),
                        computadora.getMemoriaTotal(),
                        computadora.getMemoriaLibre(),
                        computadora.getMemoriaCache(),
                        computadora.getMemoriaDisponible(),
                        computadora.getCantAlmacenamiento(),
                        computadora.getSONombre(),
                        computadora.getSOKernel(),
                        computadora.getSOVersion(),
                        computadora.getSOArquitectura()
                });
            }   
            // Crear la tabla con el modelo de datos
            JTable table = new JTable(model);

            // Agregar la tabla a un JScrollPane para permitir el desplazamiento si hay muchas filas
            JScrollPane scrollPane = new JScrollPane(table);

            // Crear y configurar la ventana
            JFrame frame = new JFrame("Computadoras Filtradas");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.setSize(1350, 400);
            frame.setLocationRelativeTo(this);
            frame.setVisible(true);
        }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaAdmin ventanaAdmin = new VentanaAdmin();
            ventanaAdmin.setVisible(true);
            ventanaAdmin.setLocationRelativeTo(null); // Centrar la ventana en el centro de la pantalla
        });
    }

}


