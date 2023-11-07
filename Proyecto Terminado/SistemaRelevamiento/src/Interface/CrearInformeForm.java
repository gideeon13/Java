package Interface;

import javax.swing.*;

import Datos.ComputadoraDAO;
import Datos.Conexion;
import Datos.InformeDAO;
import Datos.SalaInformaticaDAO;
import Negocio.Computadora;
import Negocio.InformeSala;
import Negocio.SalaInformatica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrearInformeForm extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 5749882292958246747L;
    private JTextField txtCuentaAdmin;
    private JTextField txtDepartamento;
    private JTextField txtLocalidad;
    private JTextField txtCantidadSalas;
    private JButton btnGuardar;

    public CrearInformeForm() {
        setTitle("Crear Informe");
        setSize(530, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Cuenta de Administrador:"), gbc);
        gbc.gridy++;
        mainPanel.add(new JLabel("Departamento:"), gbc);
        gbc.gridy++;
        mainPanel.add(new JLabel("Localidad o Nombre de UTU/Escuela Técnica:"), gbc);
        gbc.gridy++;
        mainPanel.add(new JLabel("Cantidad de Salas Disponibles:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        txtCuentaAdmin = new JTextField(20); // Espacio para 20 caracteres
        mainPanel.add(txtCuentaAdmin, gbc);
        gbc.gridy++;
        txtDepartamento = new JTextField(20);
        mainPanel.add(txtDepartamento, gbc);
        gbc.gridy++;
        txtLocalidad = new JTextField(20);
        mainPanel.add(txtLocalidad, gbc);
        gbc.gridy++;
        txtCantidadSalas = new JTextField(20);
        mainPanel.add(txtCantidadSalas, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    guardarInforme();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (UsuarioNoEncontradoException e1) {
                    e1.printStackTrace();
                }
            }
        });
        mainPanel.add(btnGuardar, gbc);

        getContentPane().add(mainPanel);
    }

    private void guardarInforme() throws SQLException, UsuarioNoEncontradoException {
        try {
            String cuentaAdmin = txtCuentaAdmin.getText();
            String departamento = txtDepartamento.getText();
            String localidad = txtLocalidad.getText();
            int cantidadSalas = Integer.parseInt(txtCantidadSalas.getText());

            Date fecha = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fechaRealizacion = dateFormat.format(fecha);

            int idUsuario = obtenerIDUsuarioPorCuenta(cuentaAdmin);

            if (idUsuario != -1) {
            	InformeSala informeSala = new InformeSala();
            	informeSala.setIDUsuario(idUsuario);
            	informeSala.setFechaDeRealizacion(java.sql.Date.valueOf(fechaRealizacion));
            	informeSala.setDepartamento(departamento);
            	informeSala.setLocalidad(localidad);
            	informeSala.setCantSalasDisponibles(cantidadSalas);

            	InformeDAO informeDAO = new InformeDAO();
            	int idInforme = informeDAO.agregarInformeSala(informeSala);

            	int numSalas = cantidadSalas;
            	for (int i = 1; i <= numSalas; i += 1) {
            	    SalaInformatica sala = new SalaInformatica();
            	    sala.setIDInforme(idInforme); // Utiliza el ID generado automáticamente
            	    sala.setNumSala(i);
            	    sala.setUbicacion(departamento + ", " + localidad);
            	    sala.setCapacidad(cantidadSalas);

            	    SalaInformaticaDAO salasDAO = new SalaInformaticaDAO();
            	    salasDAO.agregarSalaInformatica(sala);
            	    
            	        String input = JOptionPane.showInputDialog("Ingrese el número de identificación de la computadora para la sala " + i + ":");
            	        int numIdentificador = Integer.parseInt(input);

            	        // Solicitar al usuario que ingrese los detalles de la computadora
            	        String modeloCPU = JOptionPane.showInputDialog("Ingrese el modelo de CPU para la sala " + i + ":");
            	        int cantProcesadores = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de procesadores para la sala " + i + ":"));
            	        int potenciaMhz = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la potencia (Mhz) para la sala " + i + ":"));
            	        String familiaCPU = JOptionPane.showInputDialog("Ingrese la familia de CPU para la sala " + i + ":");
            	        int cacheCPU = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de caché (KB) para la sala " + i + ":"));
            	        int memoriaTotal = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la memoria total (MB) para la sala " + i + ":"));
            	        int memoriaLibre = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la memoria libre (MB) para la sala " + i + ":"));
            	        int memoriaCache = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la memoria de caché (MB) para la sala " + i + ":"));
            	        int memoriaDisponible = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la memoria disponible (MB) para la sala " + i + ":"));
            	        int cantAlmacenamiento = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de unidades de almacenamiento para la sala " + i + ":"));
            	        String soNombre = JOptionPane.showInputDialog("Ingrese el nombre del sistema operativo para la sala " + i + ":");
            	        String soKernel = JOptionPane.showInputDialog("Ingrese el nombre del kernel para la sala " + i + ":");
            	        String soVersion = JOptionPane.showInputDialog("Ingrese la versión del kernel para la sala " + i + ":");
            	        String soArquitectura = JOptionPane.showInputDialog("Ingrese la arquitectura del sistema operativo para la sala " + i + ":");

            	        // Crear un objeto Computadora con los detalles ingresados
            	        Computadora computadora = new Computadora();
            	        computadora.setIDInforme(idInforme);
            	        computadora.setNumIdentificador(numIdentificador);
            	        computadora.setModeloCPU(modeloCPU);
            	        computadora.setCantProcesadores(cantProcesadores);
            	        computadora.setPotenciaMhz(potenciaMhz);
            	        computadora.setFamiliaCPU(familiaCPU);
            	        computadora.setCacheCPU(cacheCPU);
            	        computadora.setMemoriaTotal(memoriaTotal);
            	        computadora.setMemoriaLibre(memoriaLibre);
            	        computadora.setMemoriaCache(memoriaCache);
            	        computadora.setMemoriaDisponible(memoriaDisponible);
            	        computadora.setCantAlmacenamiento(cantAlmacenamiento);
            	        computadora.setSONombre(soNombre);
            	        computadora.setSOKernel(soKernel);
            	        computadora.setSOVersion(soVersion);
            	        computadora.setSOArquitectura(soArquitectura);

            	        // Guardar la computadora en la base de datos
            	        ComputadoraDAO computadoraDAO = new ComputadoraDAO();
            	        computadoraDAO.agregarComputadora(computadora);
            	    }
                
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida de salas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int obtenerIDUsuarioPorCuenta(String cuentaAdmin) throws UsuarioNoEncontradoException {
        try {
            String query = "SELECT ID FROM Usuarios WHERE NombreDeCuenta = ?";
            PreparedStatement preparedStatement = Conexion.getConexion().prepareStatement(query);
            preparedStatement.setString(1, cuentaAdmin);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("ID");
            } else {
                throw new UsuarioNoEncontradoException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UsuarioNoEncontradoException();
        }
    }

    public void mostrarVentana() {
        SwingUtilities.invokeLater(() -> {
            CrearInformeForm form = new CrearInformeForm();
            form.setVisible(true);
        });
    }
}


