import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorVentas gestor = new GestorVentas();

        int opcion = 0;

        while (opcion!=4) {
            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Agregar venta");
            System.out.println("2. Borrar venta");
            System.out.println("3. Mostrar ventas");
            System.out.println("4. Salir");
            System.out.println("");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("");
                    System.out.println("Ingrese el nombre del producto:");
                    String producto = scanner.next();

                    System.out.println("Ingrese Id_V: ");
                    String id_v = scanner.next();

                    System.out.println("Ingrese la fecha: ");
                    String fecha = scanner.next();

                    System.out.println("Ingrese Id_C: ");
                    String id_c = scanner.next();

                    System.out.println("Id_P: ");
                    String id_p = scanner.next();

                    System.out.println("Ingrese el precio del producto:");
                    double precio = scanner.nextDouble();
                    
                    gestor.agregarVenta(producto, id_v, fecha, id_c, id_p, precio);
                    break;
                case 2:
                    System.out.println("Ingrese el índice de la venta a borrar:");
                    int indiceBorrar = scanner.nextInt();
                    gestor.borrarVenta(indiceBorrar);
                    break;
                case 3:
                    gestor.mostrarVentas();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }

    }
}
