import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int MAX_VENTAS = 100;
        Venta[] ventas = new Venta[MAX_VENTAS];
        int totalVentas = 0;
        int opcion = 0;
        boolean ventaEncontrada = false;    


        while(opcion!=6) {
            System.out.println("");
            System.out.println("=========== Menú ============");
            System.out.println("1. Ingresar venta");
            System.out.println("2. Mostrar ventas");
            System.out.println("3. Modificar venta");
            System.out.println("4. Borrar venta");            
            System.out.println("5. Borrar todas las ventas");
            System.out.println("6. Salir");
            System.out.println("=============================");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();
            System.out.println("");

            switch (opcion) {
                case 1:
                        if (totalVentas >= MAX_VENTAS) {
                        System.out.println("Se ha alcanzado el límite máximo de ventas.");
                                    System.out.println("");
                        break;
                        }
                    System.out.print("Ingrese el ID de la venta: ");
                    int idV = scanner.nextInt();
                    System.out.print("Ingrese la fecha: ");
                    String fecha = scanner.next();
                    System.out.print("Ingrese el ID del cliente: ");
                    int idC = scanner.nextInt();
                    System.out.print("Ingrese el nombre del cliente: ");
                    String nombreC = scanner.next();
                    System.out.print("Ingrese el ID del producto: ");
                    int idP = scanner.nextInt();
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombreP = scanner.next();

                    Venta venta = new Venta(idV, fecha, idC, nombreC, idP, nombreP);
                    ventas[totalVentas] = venta;
                    totalVentas++;

                    System.out.println("Venta ingresada exitosamente.");
                                System.out.println("");       
                    break;
                case 2:
                        if (totalVentas == 0) {
                        System.out.println("No hay ventas registradas.");
                                    System.out.println("");
                        break;
                        }

                    System.out.println("=== Ventas ===");
                    for (int i = 0; i < totalVentas; i++)
                    {
                    venta = ventas[i];
                    System.out.println("");
                    System.out.println("-------------------------");
                    System.out.println("ID de Venta: " + venta.getIdV());
                    System.out.println("Fecha: " + venta.getFecha());
                    System.out.println("ID del Cliente: " + venta.getIdC());
                    System.out.println("Nombre del Cliente: " + venta.getNombreC());
                    System.out.println("ID del Producto: " + venta.getIdP());
                    System.out.println("Nombre del Producto: " + venta.getNombreP());
                    System.out.println("-------------------------");
                    System.out.println("");
                    }
                    break;
                case 3:
                        if (totalVentas == 0) {
                        System.out.println("No hay ventas registradas para modificar.");
                                    System.out.println("");
                        break;
                        }            

                        System.out.println("");
                        System.out.print("Ingrese el ID de la venta a modificar: ");
                        int idVentaModificar = scanner.nextInt();

                        for (int i = 0; i < totalVentas; i++) {
                        if (ventas[i].getIdV() == idVentaModificar) {
                        ventaEncontrada = true;
                        System.out.println("Ingrese los nuevos datos de la venta:");
                        
                        System.out.print("Nuevo ID de la venta: ");
                        idV = scanner.nextInt();
                        System.out.print("Nueva fecha: ");
                        fecha = scanner.next();
                        System.out.print("Nuevo ID del cliente: ");
                        idC = scanner.nextInt();
                        System.out.print("Nuevo nombre del cliente: ");
                        nombreC = scanner.next();
                        System.out.print("Nuevo ID del producto: ");
                        idP = scanner.nextInt();
                        System.out.print("Nuevo nombre del producto: ");
                        nombreP = scanner.next();

                        ventas[i].setIdV(idV);
                        ventas[i].setFecha(fecha);
                        ventas[i].setIdC(idC);
                        ventas[i].setNombreC(nombreC);
                        ventas[i].setIdP(idP);
                        ventas[i].setNombreP(nombreP);

                        System.out.println("Venta modificada exitosamente.");
                                    System.out.println("");
                           }
                        }

                        if (!ventaEncontrada) {
                        System.out.println("No se encontró ninguna venta con el ID especificado.");
                                    System.out.println("");
                        }
                    break;
                case 4:
                        if (totalVentas == 0) {
                        System.out.println("No hay ventas registradas para borrar.");
                        break;
                        }

                       System.out.print("Ingrese el ID de la venta a borrar: ");
                       idV = scanner.nextInt();

                       for (int i = 0; i < totalVentas; i++) {
                       if (ventas[i].getIdV() == idV) {
                       ventaEncontrada = true;
                       for (int j = i; j < totalVentas - 1; j++) {
                       ventas[j] = ventas[j + 1];
                       }
                       ventas[totalVentas - 1] = null;
                       totalVentas--;
                       System.out.println("Venta eliminada exitosamente.");
                          }
                       }

                       if (!ventaEncontrada) {
                       System.out.println("No se encontró ninguna venta con el ID especificado.");
                       }
                    break;    
                case 5:
                        if (totalVentas == 0) {
                                    System.out.println("");
                        System.out.println("No hay ventas registradas para borrar.");
                                    System.out.println("");
                        break;
                        }

                        ventas = new Venta[MAX_VENTAS];
                        totalVentas = 0;
                                    System.out.println("");
                        System.out.println("Todas las ventas han sido borradas.");
                                    System.out.println("");
                    break;
                case 6:
                                System.out.println("");
                    System.out.println("¡Hasta luego!");
                                System.out.println("");
                    break;
                default:
                                System.out.println("");
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
                                System.out.println("");
                    break;
           }
       }
    }
}






