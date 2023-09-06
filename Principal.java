package Administrador;

import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Principal {
  public static void main(String[] args){
    try (Scanner tc = new Scanner(System.in)){

      Cuenta ct1 = new Cuenta(50, "Cristian", 10011, "5.536.895-8", "Corriente", "USD");
      Cuenta ct2 = new Cuenta(-7.53, "Federico", 10012, "5.489.203-9", "Ahorro", "UYU");
      double deposito, withdraw, scta1, scta2;
      boolean ret;
      int opc = 0;

//*
    Date fecha = new Date();
    String f = "dd/MM/yyyy";
    SimpleDateFormat sdf = new SimpleDateFormat(f);
//*

        try{ 
          while(opc!=7){
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println("--> 1 - Listar saldos       -");
            System.out.println("--> 2 - Depositar Cuenta 1  -");  
            System.out.println("--> 3 - Depositar Cuenta 2  -");
            System.out.println("--> 4 - Retirar Cuenta 2    -");
            System.out.println("--> 5 - Retirar Cuenta 2    -");
            System.out.println("--> 7 - Salir               -");        
            System.out.println("-----------------------------");
            System.out.println("");
            System.out.println("--> Elige una opcion: ");
            opc = tc.nextInt();
            tc.nextLine();
            System.out.println();

          switch (opc){
            case 1:
              System.out.println("|-------------------------|"); 
              System.out.println("|" + sdf.format(fecha) + "|");
              System.out.println("|-------------------------|");
              System.out.println(""); 
              System.out.println("|--------------------------------------|"); 
              System.out.println("|"          + "Cuenta 1" +            "|");
              System.out.println("|--------------------------------------|"); 
              System.out.println("|-------------------------------------------------------------|"); 
              System.out.println("|        Cuenta: " + ct1.getnumcuenta());
              System.out.println("|        Nombre: " + ct1.getnombre());
              System.out.println("|          C.I.: " + ct1.getdocumento());
              System.out.println("|   Tipo Cuenta: " + ct1.gettypecuenta());                  
              System.out.println("|Saldo y Moneda: " + ct1.getsaldo() +ct1.getcurrency());
              System.out.println("|-------------------------------------------------------------|");
              System.out.println("|--------------------------------------|");             
              System.out.println("|"          + "Cuenta 2" +            "|");
              System.out.println("|-------------------------------------------------------------|"); 
              System.out.println("|        Cuenta: " + ct2.getnumcuenta());
              System.out.println("|        Nombre: " + ct2.getnombre());
              System.out.println("|          C.I.: " + ct2.getdocumento());
              System.out.println("|   Tipo Cuenta: " + ct2.gettypecuenta());
              System.out.println("|Saldo y Moneda: " + ct2.getsaldo() +ct2.getcurrency());
              System.out.println("|-------------------------------------------------------------|");
              System.out.println("");
            break;

            case 2:
              System.out.println("|-------------------------|-------------------------|"); 
              System.out.println("| Fecha del deposito " + "|" + sdf.format(fecha) + "|");
              System.out.println("|-------------------------|-------------------------|");
              System.out.print("-> Ingrese el valor que desea sumar a su cuenta: ");
              deposito = tc.nextDouble();
              ct1.deposit(deposito);
              System.out.println("-> Se ha depositado " + deposito + " a la cuenta: " + ct1.getnumcuenta());
            break;

            case 3:
              System.out.println("|-------------------------|-------------------------|"); 
              System.out.println("| Fecha del deposito " + "|" + sdf.format(fecha) + "|");
              System.out.println("|-------------------------|-------------------------|");
              System.out.print("-> Ingrese el valor que desea sumar a su cuenta: ");
              deposito = tc.nextDouble();
              ct2.deposit(deposito);
              System.out.println("-> Se ha depositado " + deposito + " a la cuenta: " + ct2.getnumcuenta());
            break;
 
            case 4:
              System.out.println("|-------------------------|-------------------------|"); 
              System.out.println("| Fecha del retiro " +   "|" + sdf.format(fecha) + "|");
              System.out.println("|-------------------------|-------------------------|");
              System.out.print("-> Ingrese el valor que desea retirar: ");
              withdraw = tc.nextDouble();
              if (withdraw < 0){
              System.out.println("No es posible realizar esta operación, intente nuevamente.");
              }
              else{
              ret = ct1.withdraw(withdraw);
              if (ret){
              System.out.print("-> Se ha retirado " + withdraw + " de la cuenta: " + ct1.getnumcuenta());
              }
              else{
              System.out.println("No tiene dinero disponible");
              }
             }
            break;

            case 5:
              System.out.println("|-------------------------|-------------------------|"); 
              System.out.println("| Fecha del retiro " +   "|" + sdf.format(fecha) + "|");
              System.out.println("|-------------------------|-------------------------|");
              System.out.print("-> Ingrese el valor que desea retirar: ");
              withdraw = tc.nextDouble();
              if (withdraw < 0){
              System.out.println("No es posible realizar esta operación, intente nuevamente.");
              }
              else{
              ret = ct2.withdraw(withdraw);
              if (ret){
              System.out.print("-> Se ha retirado " + withdraw + " de la cuenta: " + ct2.getnumcuenta());
              }
              else{
              System.out.println("No tiene dinero disponible");
              }
             }
            break;

            case 7:
            break;

            default:
                 System.out.print("Selecciona una opcion valida");
              

          }
        }
      }catch(Exception error){ 
      System.out.println("-> Se produjo un error en nuestro servicio, vuelva más tarde <-" + error);
      }
    }
  }
}