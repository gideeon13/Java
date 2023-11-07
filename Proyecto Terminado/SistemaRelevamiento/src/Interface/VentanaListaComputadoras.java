package Interface;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import java.util.List;
import Negocio.Computadora;
import Datos.ComputadoraDAO;

public class VentanaListaComputadoras extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -1920052103979894078L;

    public VentanaListaComputadoras() {
        super("Lista de Computadoras"); // Título de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ComputadoraDAO computadoraDAO = new ComputadoraDAO();

        // Obtener la lista de todas las computadoras
        List<Computadora> computadoras = computadoraDAO.obtenerComputadoras();

        // Crear un modelo de tabla
        DefaultTableModel tableModel = new DefaultTableModel();

        // Definir las columnas de la tabla
        String[] columnNames = {
            "ID",
            "Modelo CPU",
            "Cant Procesadores",
            "Potencia (MHz)",
            "Familia CPU",
            "Cache CPU",
            "Memoria Total",
            "Memoria Libre",
            "Memoria Cache",
            "Memoria Disponible",
            "Cant Almacenamiento",
            "SO Nombre",
            "SO Kernel",
            "SO Versión",
            "SO Arquitectura"
        };

        tableModel.setColumnIdentifiers(columnNames);

        // Llenar la tabla con los datos de las computadoras
        for (Computadora computadora : computadoras) {
            Object[] rowData = {
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
            };
            tableModel.addRow(rowData);
        }

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Establecer el tamaño preferido de la ventana
        int preferredWidth = 1350;
        int preferredHeight = 400;
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));

        add(scrollPane);
        pack();
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaListaComputadoras();
    }
}
