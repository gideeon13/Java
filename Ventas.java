import java.util.ArrayList;
import java.util.Scanner;

public class Ventas {

    private static ArrayList<String[]> ventas = new ArrayList<String[]>();
    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("Seleccione una opcion:");
            System.out.println("1. Ingresar datos de venta");
            System.out.println("2. Borrar todas las ventas");
            System.out.println("3. Mostrar datos de ventas");
            System.out.println("4. Salir");

            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    ingresarVenta();
                    break;
                case 2:
                    borrarVentas();
                    break;
                case 3:
                    mostrarVentas();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 4);
    }

    private static void ingresarVenta() {
        System.out.println("Ingrese los datos de la venta:");

        System.out.print("Id_V: ");
        String id_v = teclado.next();

        System.out.print("Fecha: ");
        String fecha = teclado.next();

        System.out.print("Id_C: ");
        String id_c = teclado.next();

        System.out.print("Nombre_C: ");
        String nombre_c = teclado.next();

        System.out.print("Id_P: ");
        String id_p = teclado.next();

        System.out.print("Nombre_P: ");
        String nombre_p = teclado.next();

        String[] venta = {id_v, fecha, id_c, nombre_c, id_p, nombre_p};
        ventas.add(venta);

        System.out.println("Venta agregada correctamente.");
    }

    private static void borrarVentas() {
        ventas.clear();
        System.out.println("Todas las ventas han sido borradas.");
    }

    private static void mostrarVentas() {
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            System.out.println("Ventas registradas:");
            for (String[] venta : ventas) {
                System.out.println("Id_V: " + venta[0] + " | Fecha: " + venta[1] + " | Id_C: " + venta[2] + " | Nombre_C: " + venta[3] + " | Id_P: " + venta[4] + " | Nombre_P: " + venta[5]);
            }
        }
    }
}
