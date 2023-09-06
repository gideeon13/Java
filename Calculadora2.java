import java.util.Scanner;

public class Calculadora2 {
   public static void main(String[] args) {
   Scanner teclado = new Scanner(System.in);
   int opcion = 0;
   double n1, n2;

   System.out.print("Ingrese el primer numero: ");
   n1 = teclado.nextDouble();
   System.out.print("Ingrese el segundo numero: ");
   n2 = teclado.nextDouble();

   System.out.print(" -- Menu -- ");
   System.out.print(" 1 - Suma  ");
   System.out.print(" 2 - Resta  ");
   System.out.print(" 3 - Multiplicacion  ");
   System.out.print(" 4 - Division  ");   
   System.out.print(" 6 - Salir  ");
   System.out.println("");
   System.out.print("Ingrese una opcion: ");
   opcion = teclado.nextInt();

   if( opcion == 1)
   	  System.out.print( "El resultado de la suma es: " + (n1 + n2) );
   else if( opcion == 2)
   	  System.out.print( "El resultado de la resta es: " + (n1 - n2) );
   else if( opcion == 3)
   	  System.out.print( "El resultado de la multiplicacion es: " + (n1 * n2) );
   else if( opcion == 4)
   	  System.out.print( "El resultado de la division es: " + (n1 / n2) );
   else if( opcion != 6) {
   } else {
   }
   
   }
}
