
public class GestorVentas {
    private Venta[] ventas;
    private int numVentas;

    public GestorVentas() {
        ventas = new Venta[100];
        numVentas = 0;
    }

    public void agregarVenta(String producto, String id_v, String fecha, String id_c, String id_p, double precio) {
        Venta venta = new Venta(producto, id_v, fecha, id_c, id_p, precio);
        ventas[numVentas] = venta;
        numVentas++;
        System.out.println("Venta agregada: " + venta);
    }

    public void borrarVenta(int indice) {
        if (indice >= 0 && indice < numVentas) {
            Venta ventaBorrada = ventas[indice];
            for (int i = indice; i < numVentas - 1; i++) {
                ventas[i] = ventas[i + 1];
            }
            ventas[numVentas - 1] = null;
            numVentas--;
            System.out.println("Venta borrada: " + ventaBorrada);
        } else {
            System.out.println("Índice inválido");
        }
    }

    public void mostrarVentas() {
        if (numVentas == 0) {
            System.out.println("No hay ventas registradas");
        } else {
            System.out.println("Ventas registradas:");
            for (int i = 0; i < numVentas; i++) {
                System.out.println(i + ": " + ventas[i]);
            }
        }
    }
}