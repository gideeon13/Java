package Interface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Datos.Conexion;
import Datos.UsuarioDAO;
import Negocio.Usuario;

public class VentanaRoot extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1078484944296433855L;
	private JTable tableUsuarios;
    private DefaultTableModel tableModel;
    private List<String> administradores;
    private List<String> usuarios;
    private UsuarioDAO usuarioDAO;

        public VentanaRoot() {
            setTitle("Ventana de Root");
            setSize(1100, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            administradores = new ArrayList<>();
            usuarios = new ArrayList<String>();
            
            // Inicializar usuarioDAO con la conexión a la base de datos
            Connection conexion = Conexion.getConexion();
            setUsuarioDAO(new UsuarioDAO(conexion));
            
            JPanel panelRoot = new JPanel(new BorderLayout());

            // Crear la tabla de usuarios
            tableModel = new DefaultTableModel();
            tableUsuarios = new JTable(tableModel);
            tableModel.addColumn("ID");
            tableModel.addColumn("Nombre de Cuenta");
            tableModel.addColumn("Tipo de Usuario");
            
            // Agregar el renderizador para centrar los datos
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            tableUsuarios.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // ID
            tableUsuarios.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // Nombre de Cuenta
            tableUsuarios.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); // Tipo de Usuario            
            
            JScrollPane scrollPane = new JScrollPane(tableUsuarios);

            panelRoot.add(scrollPane, BorderLayout.CENTER);

            // Panel para los botones
            JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

            // Botones de administradores
            JButton agregarAdminButton = new JButton("Agregar Administrador");
            JButton eliminarAdminButton = new JButton("Eliminar Administrador");

            // Botones de usuarios
            JButton agregarUsuarioButton = new JButton("Agregar Usuario");
            JButton eliminarUsuarioButton = new JButton("Eliminar Usuario");

            // Botón de listar usuarios
            JButton listarUsuariosButton = new JButton("Listar Usuarios");
            
            // Botón de estructura del sistema
            JButton crearEstructuraButton = new JButton("Crear Estructura del Sistema");

            // Botón de salir
            JButton salirButton = new JButton("Salir");

            agregarAdminButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Obtener nombre de usuario y contraseña
                    String nuevoUsuario = JOptionPane.showInputDialog("Ingrese el nombre del nuevo administrador:");
                    if (nuevoUsuario != null && !nuevoUsuario.isEmpty()) {
                        String contrasenaUsuario = JOptionPane.showInputDialog("Ingrese la contraseña del administrador:");
                        if (contrasenaUsuario != null && !contrasenaUsuario.isEmpty()) {
                            // Crear un objeto Usuario con tipo de usuario "administrador"
                            Usuario usuario = new Usuario(0, nuevoUsuario, "administrador", contrasenaUsuario);
                            // Llamar al método para insertar en la base de datos
                            usuarioDAO.insertarUsuario(usuario);

                            // Agregar el nuevo administrador a la lista de administradores
                            administradores.add(nuevoUsuario);

                            actualizarTablaUsuarios(); // Actualiza la tabla

                            JOptionPane.showMessageDialog(VentanaRoot.this, "Administrador agregado: " + nuevoUsuario);
                        } else {
                            JOptionPane.showMessageDialog(VentanaRoot.this, "Contraseña no válida. Administrador no agregado.");
                        }
                    }
                }
            });

            eliminarAdminButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Obtener administradores desde la base de datos
                    List<String> administradoresEnBaseDeDatos = getAdministradoresDesdeBaseDeDatos();

                    JOptionPane.showMessageDialog(VentanaRoot.this, "Administradores locales: " + administradores);
                    JOptionPane.showMessageDialog(VentanaRoot.this, "Administradores en la base de datos: " + administradoresEnBaseDeDatos);

                    if (!administradoresEnBaseDeDatos.isEmpty()) {
                        String adminAEliminar = (String) JOptionPane.showInputDialog(
                            VentanaRoot.this,
                            "Seleccione el administrador a eliminar:",
                            "Eliminar Administrador",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            administradoresEnBaseDeDatos.toArray(),
                            administradoresEnBaseDeDatos.get(0)
                        );

                        if (adminAEliminar != null) {
                            // Eliminar el administrador de la base de datos
                            eliminarAdminDeBaseDeDatos(adminAEliminar);

                            // Actualizar la lista de administradores
                            administradoresEnBaseDeDatos.remove(adminAEliminar);

                            actualizarTablaUsuarios(); // Actualizar la tabla

                            JOptionPane.showMessageDialog(VentanaRoot.this, "Administrador eliminado: " + adminAEliminar);
                        }
                    } else {
                        JOptionPane.showMessageDialog(VentanaRoot.this, "No hay administradores en la base de datos para eliminar.");
                    }
                }
            });

        agregarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener nombre de usuario y contraseña
                String nuevoUsuario = JOptionPane.showInputDialog("Ingrese el nombre del nuevo usuario:");
                if (nuevoUsuario != null && !nuevoUsuario.isEmpty()) {
                    String contrasenaUsuario = JOptionPane.showInputDialog("Ingrese la contraseña del usuario:");
                    if (contrasenaUsuario != null && !contrasenaUsuario.isEmpty()) {
                        // Crear un objeto Usuario con tipo de usuario "usuario"
                        Usuario usuario = new Usuario(0, nuevoUsuario, "usuario", contrasenaUsuario);
                        // Llamar al método para insertar en la base de datos
                        usuarioDAO.insertarUsuario(usuario);

                        // Agregar el nuevo usuario a la lista
                        usuarios.add(nuevoUsuario);

                        JOptionPane.showMessageDialog(VentanaRoot.this, "Usuario agregado: " + nuevoUsuario);

                    } else {
                        JOptionPane.showMessageDialog(VentanaRoot.this, "Contraseña no válida. Usuario no agregado.");
                    }
                }
            }
        });

        eliminarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener usuarios normales desde la base de datos
                List<String> usuariosNormalesEnBaseDeDatos = getUsuariosNormalesDesdeBaseDeDatos();

                JOptionPane.showMessageDialog(VentanaRoot.this, "Usuarios locales: " + usuarios);
                JOptionPane.showMessageDialog(VentanaRoot.this, "Usuarios en la base de datos: " + usuariosNormalesEnBaseDeDatos);

                if (!usuariosNormalesEnBaseDeDatos.isEmpty()) {
                    String usuarioAEliminar = (String) JOptionPane.showInputDialog(
                        VentanaRoot.this,
                        "Seleccione el usuario a eliminar:",
                        "Eliminar Usuario",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        usuariosNormalesEnBaseDeDatos.toArray(),
                        usuariosNormalesEnBaseDeDatos.get(0)
                    );

                    if (usuarioAEliminar != null) {
                        // Eliminar el usuario de la base de datos
                        eliminarUsuarioDeBaseDeDatos(usuarioAEliminar);

                        // Actualizar la lista de usuarios normales
                        usuariosNormalesEnBaseDeDatos.remove(usuarioAEliminar);

                        JOptionPane.showMessageDialog(VentanaRoot.this, "Usuario eliminado: " + usuarioAEliminar);
                    }
                } else {
                    JOptionPane.showMessageDialog(VentanaRoot.this, "No hay usuarios en la base de datos para eliminar.");
                }
            }


        });

        listarUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTablaUsuarios();
            }
        });

        crearEstructuraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmacion = JOptionPane.showConfirmDialog(VentanaRoot.this, "¿Seguro que deseas crear la estructura del sistema? Esto eliminará datos existentes.");
                if (confirmacion == JOptionPane.YES_OPTION) {
                    crearEstructuraDelSistema();
                }
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarVentana();
            }
        });

        // Agregar botones al panel de botones
        panelBotones.add(agregarAdminButton);
        panelBotones.add(eliminarAdminButton);
        panelBotones.add(agregarUsuarioButton);
        panelBotones.add(eliminarUsuarioButton);
        panelBotones.add(listarUsuariosButton);
        panelBotones.add(crearEstructuraButton);
        panelBotones.add(salirButton);

        // Agregar el panel de botones al panel principal
        panelRoot.add(panelBotones, BorderLayout.SOUTH);

        getContentPane().add(panelRoot);

        setLocationRelativeTo(null);
        setVisible(true);
    }
        
        public void cerrarVentana() {
            dispose(); // Cierra la VentanaRoot
            new SistemaRelevamientoGUI(); // Abre una nueva instancia de VentanaLogin
        }
  
        public void insertarUsuario(Usuario usuario) {
            try {
                String consultaSQL = "INSERT INTO Usuarios (ID, NombreDeCuenta, Contrasena, TipoDeUsuario) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = Conexion.prepareStatement(consultaSQL);
                preparedStatement.setInt(1, usuario.getId());
                preparedStatement.setString(2, usuario.getNombreDeCuenta());
                preparedStatement.setString(3, usuario.getContrasena());
                preparedStatement.setString(4, usuario.getTipoDeUsuario()); // Añade el tipo de usuario si es necesario
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private List<String> getAdministradoresDesdeBaseDeDatos() {
            List<String> administradoresEnBaseDeDatos = new ArrayList<>();;


            try {
                Connection conexion = Conexion.getConexion();
                String consultaSQL = "SELECT NombreDeCuenta FROM Usuarios WHERE TipoDeUsuario = 'administrador'";
                PreparedStatement preparedStatement = conexion.prepareStatement(consultaSQL);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    administradoresEnBaseDeDatos.add(resultSet.getString("NombreDeCuenta"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al obtener los administradores desde la base de datos.");

            }

            return administradoresEnBaseDeDatos;
        }
        
        private List<String> getUsuariosNormalesDesdeBaseDeDatos() {
            List<String> usuariosNormalesEnBaseDeDatos = new ArrayList<>();

            try {
                Connection conexion = Conexion.getConexion();
                String consultaSQL = "SELECT NombreDeCuenta FROM Usuarios WHERE TipoDeUsuario = 'usuario'";
                PreparedStatement preparedStatement = conexion.prepareStatement(consultaSQL);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    usuariosNormalesEnBaseDeDatos.add(resultSet.getString("NombreDeCuenta"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al obtener los usuarios normales desde la base de datos.");
            }

            return usuariosNormalesEnBaseDeDatos;
        }        
        
     // Método para eliminar un administrador de la base de datos
        private void eliminarAdminDeBaseDeDatos(String adminAEliminar) {

            try {
                String consultaSQL = "DELETE FROM Usuarios WHERE NombreDeCuenta = ?";
                Connection conexion = Conexion.getConexion();
                PreparedStatement preparedStatement = conexion.prepareStatement(consultaSQL);
                preparedStatement.setString(1, adminAEliminar);
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al eliminar el administrador de la base de datos.");
            
            }
        }
        
     // Método para eliminar un usuario de la base de datos
        private void eliminarUsuarioDeBaseDeDatos(String usuarioAEliminar) {

            try {
                String consultaSQL = "DELETE FROM Usuarios WHERE NombreDeCuenta = ?";
                Connection conexion = Conexion.getConexion();
                PreparedStatement preparedStatement = conexion.prepareStatement(consultaSQL);
                preparedStatement.setString(1, usuarioAEliminar);
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al eliminar el usuario de la base de datos.");

            }
        }
        
        private void actualizarTablaUsuarios() {
            // Limpia la tabla antes de actualizarla
            while (tableModel.getRowCount() > 0) {
                tableModel.removeRow(0);
            }

            Connection conexion = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultado = null;

            try {
                // Establecer la conexión a la base de datos
                String dbURL = "jdbc:mysql://localhost:3306/Sistema";
                String usuarioDB = "root";
                String contrasenaDB = "root";
                conexion = DriverManager.getConnection(dbURL, usuarioDB, contrasenaDB);

                // Ejecutar una consulta SQL para obtener los usuarios desde la base de datos
                String consultaSQL = "SELECT ID, NombreDeCuenta, TipoDeUsuario FROM Usuarios";
                preparedStatement = conexion.prepareStatement(consultaSQL);
                resultado = preparedStatement.executeQuery();

                // Llenar la tabla con los usuarios cargados desde la base de datos
                while (resultado.next()) {
                    int id = resultado.getInt("ID");
                    String nombreDeCuenta = resultado.getString("NombreDeCuenta");
                    String tipoDeUsuario = resultado.getString("TipoDeUsuario");
                    tableModel.addRow(new Object[]{id, nombreDeCuenta, tipoDeUsuario});
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al cargar usuarios desde la base de datos.");
            } finally {
                try {
                    if (resultado != null) {
                        resultado.close();
                    }
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (conexion != null) {
                        conexion.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        private void crearEstructuraDelSistema() {
            Connection conexion = null;
            Statement sentencia = null;

            try {
                // Establecer la conexión a la base de datos
                String dbURL = "jdbc:mysql://localhost:3306/Sistema";
                String usuarioDB = "root";
                String contrasenaDB = "root";
                conexion = DriverManager.getConnection(dbURL, usuarioDB, contrasenaDB);
                sentencia = conexion.createStatement();

                // Elimina las tablas si existen
                sentencia.execute("DROP TABLE IF EXISTS SalasInformatica;");
                sentencia.execute("DROP TABLE IF EXISTS Computadoras;");
                sentencia.execute("DROP TABLE IF EXISTS InformesSalas;");
                sentencia.execute("DROP TABLE IF EXISTS Usuarios;");

                // Creación de la tabla de Usuarios sin Nombre, Direccion y Telefono
                sentencia.execute("CREATE TABLE Usuarios ("
                        + "ID INT AUTO_INCREMENT PRIMARY KEY,"
                        + "NombreDeCuenta VARCHAR(255) NOT NULL,"
                        + "Contrasena VARCHAR(255) NOT NULL,"
                        + "TipoDeUsuario ENUM('root', 'administrador', 'usuario') NOT NULL"
                        + ");");

                // Eliminar todos los usuarios excepto 'root'
                sentencia.execute("DELETE FROM Usuarios WHERE NombreDeCuenta <> 'root';");

                // Creación de la tabla de Informes de Salas de Informática
                sentencia.execute("CREATE TABLE InformesSalas ("
                        + "ID INT AUTO_INCREMENT PRIMARY KEY,"
                        + "IDUsuario INT,"
                        + "FechaDeRealizacion DATE,"
                        + "Departamento VARCHAR(255),"
                        + "Localidad VARCHAR(255),"
                        + "CantSalasDisponibles INT NOT NULL,"
                        + "FOREIGN KEY (IDUsuario) REFERENCES Usuarios(ID)"
                        + ");");

                // Creación de la tabla de Computadoras
                sentencia.execute("CREATE TABLE Computadoras ("
                        + "ID INT AUTO_INCREMENT PRIMARY KEY,"
                        + "IDInforme INT,"
                        + "NumIdentificador INT,"
                        + "ModeloCPU VARCHAR(255),"
                        + "CantProcesadores INT,"
                        + "PotenciaMhz INT,"
                        + "FamiliaCPU VARCHAR(255),"
                        + "CacheCPU INT,"
                        + "MemoriaTotal INT,"
                        + "MemoriaLibre INT,"
                        + "MemoriaCache INT,"
                        + "MemoriaDisponible INT,"
                        + "CantAlmacenamiento INT,"
                        + "SONombre VARCHAR(255),"
                        + "SOKernel VARCHAR(255),"
                        + "SOVersion VARCHAR(255),"
                        + "SOArquitectura VARCHAR(50),"
                        + "FOREIGN KEY (IDInforme) REFERENCES InformesSalas(ID)"
                        + ");");

                // Creación de la tabla de Salas de Informática
                sentencia.execute("CREATE TABLE SalasInformatica ("
                        + "ID INT AUTO_INCREMENT PRIMARY KEY,"
                        + "IDInforme INT,"
                        + "NumSala INT,"
                        + "Capacidad INT,"
                        + "FOREIGN KEY (IDInforme) REFERENCES InformesSalas(ID)"
                        + ");");
                
                // Agregar información de muestra a la tabla Usuarios
                sentencia.execute("INSERT INTO Usuarios (NombreDeCuenta, Contrasena, TipoDeUsuario) VALUES "
                        + "('root', 'root', 'root'), "
                        + "('cristian', 'cristian', 'administrador'), "
                        + "('federico', 'federico', 'usuario');");
                
                // Agregar información de muestra a la tabla InformesSalas
                sentencia.execute("INSERT INTO InformesSalas (IDUsuario, FechaDeRealizacion, Departamento, Localidad, CantSalasDisponibles) VALUES "
                        + "('1', '2023-10-11', 'Canelones', 'Pando', '1');");
                
                // Agregar información de muestra a la tabla Computadoras
                sentencia.execute("INSERT INTO Computadoras (IDInforme, NumIdentificador, ModeloCPU, CantProcesadores, PotenciaMhz, FamiliaCPU, CacheCPU, MemoriaTotal, "
                        + "MemoriaLibre, MemoriaCache, MemoriaDisponible, CantAlmacenamiento, SONombre, SOKernel, SOVersion, SOArquitectura) VALUES "
                        + "('1', '1', 'Intel Core i7 8700K', '1', '4300', '6', '12', '12', '4', '2', '6', '1', 'Windows 10', '4.18.128', '22H2', '64 bits');");
                
                // Agregar información de muestra a la tabla SalasInformatica
                sentencia.execute("INSERT INTO SalasInformatica (IDInforme, NumSala, Capacidad) VALUES "
                        + "('1', '1', '1');");

                JOptionPane.showMessageDialog(this, "Estructura del sistema creada y usuarios (excepto 'root') eliminados.");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al crear la estructura del sistema y eliminar usuarios (excepto 'root').");
            } finally {
                try {
                    if (sentencia != null) {
                        sentencia.close();
                    }
                    if (conexion != null) {
                        conexion.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaRoot();
            }
        });
    }

	public void mostrarVentana() {
		// TODO Auto-generated method stub
		
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
}


