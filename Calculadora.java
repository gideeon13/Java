import java.util.Scanner;

public class Calculadora {
  public static void main(String[]args) {
    Scanner tc = new Scanner (System.in);
    int op = 0;
    String nom;
    double num1 = 0;
    double num2 = 0;

    while(op!=5) {
       System.out.println();
       System.out.println("|---------------------------------|");
       System.out.println("|-     Bienvenido/a usuario      -|");
       System.out.println("|---------------------------------|");
       System.out.println("|-          1: Suma              -|");
       System.out.println("|-          2: Resta             -|");
       System.out.println("|-          3: Multiplicacion    -|");
       System.out.println("|-          4: Division          -|");
       System.out.println("|-          5: Salir             -|");
       System.out.println("|---------------------------------|");
       System.out.print("--> Elige una opción: ");
       op = tc.nextInt();
       System.out.println();

    switch (op) {
       case 1:
          System.out.print("> Ingrese su primer valor: ");
          num1 = tc.nextInt();
          System.out.print("> Ingrese su segundo valor: ");
          num2 = tc.nextInt();
          System.out.println("");
          Suma s = new Suma(num1,num2);
          System.out.println("|--------------------|");
          s.mostrarResultado(); 
          System.out.println("|--------------------|");
       break;

       case 2:    
          System.out.print("> Ingrese su primer valor: ");
          num1 = tc.nextInt();
          System.out.print("> Ingrese su segundo valor: ");
          num2 = tc.nextInt();
          System.out.println("");
          Resta r = new Resta(num1,num2);
          System.out.println("|--------------------|");
          r.mostrarResultado();    
          System.out.println("|--------------------|");
       break;

       case 3:
          System.out.print("> Ingrese su primer valor: ");
          num1 = tc.nextInt();
          System.out.print("> Ingrese su segundo valor: ");
          num2 = tc.nextInt();
          System.out.println("");
          Producto p = new Producto(num1,num2);
          System.out.println("|--------------------|");
          p.mostrarResultado();   
          System.out.println("|--------------------|");
       break;

       case 4:        
          System.out.print("> Ingrese su primer valor: ");
          num1 = tc.nextInt();
          System.out.print("> Ingrese su segundo valor: ");
          num2 = tc.nextInt();
          System.out.println("");
          Division d = new Division(num1,num2);
          System.out.println("|--------------------|");
          d.mostrarResultado(); 
          System.out.println("|--------------------|");
       break;

       case 5:  
          System.out.println("> |--------------------------------------| <");
          System.out.println("> |- Nos vemos, espero vuelva pronto :D -| <");
          System.out.println("> |--------------------------------------| <");      
       break;

       default:
          System.out.println("¡Opción incorrecta, por favor vuelva a ingresar!");
       break;

      } // switch
    } // while
  } // main
} // clase