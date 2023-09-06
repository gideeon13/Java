import java.util.Scanner;

public class Calculadora {
   public static void main(String[] args) {
   Scanner teclado = new Scanner(System.in);

   System.out.print("Ingrese el primer numero: ");
   int n1 = teclado.nextInt();
   System.out.print("Ingrese el segundo numero: ");
   int n2 = teclado.nextInt();

   int suma= n1 + n2;
   int resta = (n1 - n2);
   int multiplicacion = n1 * n2;
   double n1d = n1;
   double n2d = n2;
   double division = n1d / n2d;

   System.out.println("");
   System.out.println("El resultado de la suma es: " + suma);
   System.out.println("El resultado de la resta es: " + resta);
   System.out.println("El resultado de la multiplicacion es: " + multiplicacion);
   System.out.println("El resultado de la division es: " + division);
   
   }
}
