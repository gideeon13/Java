import java.util.Scanner;

public class Administrador {
    public static void main(String[] args) {
        
        // Crear las cuentas iniciales
        Cuenta cuenta1 = new Cuenta(50.00);
        Cuenta cuenta2 = new Cuenta(-7.53);

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion!=4){
            System.out.println();
            System.out.println("- $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ -");
            System.out.println("- Bienvenido al Administrador de Cuentas -");
            System.out.println("- $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ -");
            System.out.println();

            // Mostrar opciones
            System.out.println("--------------------------------");
            System.out.println("> 1. Listar saldos de cuentas");
            System.out.println("> 2. Depositar en cuenta1");
            System.out.println("> 3. Depositar en cuenta2");
            System.out.println("> 4. Salir");
            System.out.println("--------------------------------");
            System.out.print("Ingrese la opcion deseada > ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println();
                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$");                   
                    System.out.println("$ Saldo de cuenta1: $" + cuenta1.obtenerSaldo());
                    System.out.println("$ Saldo de cuenta2: $" + cuenta2.obtenerSaldo());
                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$");    

                    break;
                case 2:
                    System.out.println();
                    System.out.print("Ingrese el monto a depositar en Cuenta 1  >  ");
                    double monto1 = scanner.nextDouble();
                    cuenta1.depositar(monto1);
                    System.out.println("¡Depósito realizado!");

                    break;
                case 3:
                    System.out.println();
                    System.out.println("Ingrese el monto a depositar en Cuenta 2  >  ");
                    double monto2 = scanner.nextDouble();
                    cuenta2.depositar(monto2);
                    System.out.println("¡Depósito realizado!");

                    break;
                case 4:
                    System.out.println();
                    System.out.println("¡Hasta luego!");
                    System.out.println();
                    break;
                default:
                    System.out.println();
                    System.out.println("Opción inválida. Intente nuevamente.");
                    System.out.println();
                    break;
            }

            System.out.println(); // Línea en blanco para separar las iteraciones
        } 
    }
}
