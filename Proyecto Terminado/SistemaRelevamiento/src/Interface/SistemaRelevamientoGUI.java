package Interface;

import javax.swing.*;

import Datos.Conexion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SistemaRelevamientoGUI {

    private JFrame frame;
    private String userType;

    public SistemaRelevamientoGUI() {
        frame = new JFrame("Sistema de Relevamiento de Salas de Informática");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Centrar la ventana en la pantalla
        frame.setLocationRelativeTo(null);

        // Crear un panel con un diseño elegante
        JPanel contentPanel = new JPanel(new BorderLayout());

        // Título de bienvenida alejado del borde superior
        JLabel welcomeLabel = new JLabel("Bienvenido al Sistema de Relevamiento de Salas de Informática");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Panel para el formulario de inicio de sesión con GridBagLayout
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2)); // Agregar un borde al panel

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes

        // Etiqueta y campo de nombre de usuario
        JLabel usernameLabel = new JLabel("Nombre de Usuario:");
        JTextField usernameField = new JTextField(20);
        c.gridx = 0;
        c.gridy = 0;
        loginPanel.add(usernameLabel, c);
        c.gridx = 1;
        c.gridy = 0;
        loginPanel.add(usernameField, c);

        // Etiqueta y campo de contraseña
        JLabel passwordLabel = new JLabel("Contraseña:");
        JPasswordField passwordField = new JPasswordField(20);
        c.gridx = 0;
        c.gridy = 1;
        loginPanel.add(passwordLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        loginPanel.add(passwordField, c);

        // Botón de inicio de sesión
        JButton loginButton = new JButton("Iniciar Sesión");
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2; // Ocupa dos columnas
        c.anchor = GridBagConstraints.CENTER; // Centrar el botón
        loginPanel.add(loginButton, c);

        // Botón de Salir para salir de la aplicación
        JButton exitButton = new JButton("Salir");
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2; // Ocupa dos columnas
        c.anchor = GridBagConstraints.CENTER; // Centrar el botón
        loginPanel.add(exitButton, c);

        // Añadir el panel del formulario al centro del contenido
        contentPanel.add(loginPanel, BorderLayout.CENTER);

        // Añadir el panel al marco
        frame.add(contentPanel, BorderLayout.CENTER);

        // Configurar el botón de inicio de sesión
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                // Verificar el inicio de sesión y mostrar la ventana correspondiente
                if (verificarCredenciales(username, password)) {
                    mostrarVentanaSegunTipoDeUsuario();
                } else {
                    JOptionPane.showMessageDialog(frame, "Credenciales incorrectas. Inténtalo de nuevo.");
                }
            }
        });

        // Configurar el botón de Salir para cerrar la aplicación
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Cierra la aplicación
            }
        });

        frame.setVisible(true);
    }

    private boolean verificarCredenciales(String username, String password) {
        try {
            Connection connection = Conexion.getConexion(); 
            
            // Crear una consulta SQL parametrizada para buscar el usuario y contraseña
            String sql = "SELECT TipoDeUsuario FROM Usuarios WHERE NombreDeCuenta = ? AND Contrasena = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            // Ejecutar la consulta
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                // Si se encontró un registro, significa que las credenciales son válidas
                userType = resultSet.getString("TipoDeUsuario");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Si no se encontró un registro, las credenciales no son válidas
        return false;
    }

    private void mostrarVentanaSegunTipoDeUsuario() {
        // Dependiendo del tipo de usuario, abre la ventana correspondiente
        if (userType.equals("root")) {
            abrirVentanaRoot();
        } else if (userType.equals("administrador")) {
            abrirVentanaAdmin();
        } else if (userType.equals("usuario")) {
            abrirVentanaUsuario();
        }
    }

    private void abrirVentanaRoot() {
        // Verificar si el tipo de usuario es 'root'
        if (userType.equals("root")) {
            // Cerrar la VentanaLogin actual
            frame.dispose();

            // Abrir la VentanaRoot
            VentanaRoot ventanaRoot = new VentanaRoot();
            ventanaRoot.mostrarVentana();
        } else {
            JOptionPane.showMessageDialog(frame, "No tienes permisos para acceder a la ventana de Root.");
        }
    }
    
    private void abrirVentanaAdmin() {
        // Verificar si el tipo de usuario es 'administrador'
        if (userType.equals("administrador")) {
            // Cierra la ventana de inicio de sesión actual
            if (frame != null) {
                frame.dispose(); // Cierra la ventana de inicio de sesión
            }

            // Crear una ventana para el administrador
            VentanaAdmin ventanaAdmin = new VentanaAdmin();
            ventanaAdmin.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(frame, "No tienes permisos para acceder a la ventana de Administrador.");
        }
    }

    private void abrirVentanaUsuario() {
        // Verificar si el tipo de usuario es 'usuario'
        if (userType.equals("usuario")) {
            // Cierra la ventana de inicio de sesión actual
            if (frame != null) {
                frame.dispose(); // Cierra la ventana de inicio de sesión
            }

            // Crear una ventana para el usuario
            VentanaUsuario ventanaUsuario = new VentanaUsuario();

            // Mostrar la ventana del usuario
            ventanaUsuario.mostrarVentana();
        } else {
            JOptionPane.showMessageDialog(frame, "No tienes permisos para acceder a la ventana de Usuario.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SistemaRelevamientoGUI();
            }
        });
    }

	public void mostrarVentana() {
		// TODO Auto-generated method stub
		
	}
	
}

