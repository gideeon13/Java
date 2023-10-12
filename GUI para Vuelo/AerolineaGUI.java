import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AerolineaGUI extends JFrame {
    private Avion avion;
    private Pasajero pasajero1, pasajero2;
    private Vuelo vuelo;

    private JTextField avionTextField, capacidadTextField, nombreTextField1, apellidoTextField1, pasaporteTextField1,
            nombreTextField2, apellidoTextField2, pasaporteTextField2, numeroVueloTextField;
    private JButton crearAvionButton, crearPasajerosButton, crearVueloButton, agregarPasajeroButton;

    public AerolineaGUI() {
        setTitle("Administración de Aerolínea");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        avionTextField = new JTextField();
        capacidadTextField = new JTextField();
        nombreTextField1 = new JTextField();
        apellidoTextField1 = new JTextField();
        pasaporteTextField1 = new JTextField();
        nombreTextField2 = new JTextField();
        apellidoTextField2 = new JTextField();
        pasaporteTextField2 = new JTextField();
        numeroVueloTextField = new JTextField();

        crearAvionButton = new JButton("Crear Avión");
        crearPasajerosButton = new JButton("Crear Pasajeros");
        crearVueloButton = new JButton("Crear Vuelo");
        agregarPasajeroButton = new JButton("Agregar Pasajero");

        crearAvionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroSerie = avionTextField.getText();
                int capacidad = Integer.parseInt(capacidadTextField.getText());
                avion = new Avion(numeroSerie, capacidad);
                JOptionPane.showMessageDialog(null, "Avión creado con éxito.");
            }
        });

        crearPasajerosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre1 = nombreTextField1.getText();
                String apellido1 = apellidoTextField1.getText();
                String pasaporte1 = pasaporteTextField1.getText();
                pasajero1 = new Pasajero(nombre1, apellido1, pasaporte1);

                String nombre2 = nombreTextField2.getText();
                String apellido2 = apellidoTextField2.getText();
                String pasaporte2 = pasaporteTextField2.getText();
                pasajero2 = new Pasajero(nombre2, apellido2, pasaporte2);

                JOptionPane.showMessageDialog(null, "Pasajeros creados con éxito.");
            }
        });

        crearVueloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroVuelo = numeroVueloTextField.getText();
                vuelo = new Vuelo(numeroVuelo, avion);
                JOptionPane.showMessageDialog(null, "Vuelo creado con éxito.");
            }
        });

        agregarPasajeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vuelo != null && pasajero1 != null && pasajero2 != null) {
                    vuelo.agregarPasajero(pasajero1);
                    vuelo.agregarPasajero(pasajero2);
                    JOptionPane.showMessageDialog(null, "Pasajeros agregados al vuelo.");
                } else {
                    JOptionPane.showMessageDialog(null, "Debe crear el vuelo y los pasajeros primero.");
                }
            }
        });

        add(new JLabel("Número de Serie del Avión:"));
        add(avionTextField);
        add(new JLabel("Capacidad del Avión:"));
        add(capacidadTextField);
        add(new JLabel("Nombre del Pasajero 1:"));
        add(nombreTextField1);
        add(new JLabel("Apellido del Pasajero 1:"));
        add(apellidoTextField1);
        add(new JLabel("Pasaporte del Pasajero 1:"));
        add(pasaporteTextField1);
        add(new JLabel("Nombre del Pasajero 2:"));
        add(nombreTextField2);
        add(new JLabel("Apellido del Pasajero 2:"));
        add(apellidoTextField2);
        add(new JLabel("Pasaporte del Pasajero 2:"));
        add(pasaporteTextField2);
        add(new JLabel("Número de Vuelo:"));
        add(numeroVueloTextField);
        add(crearAvionButton);
        add(crearPasajerosButton);
        add(crearVueloButton);
        add(agregarPasajeroButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AerolineaGUI();
            }
        });
    }
}

