public class Division extends Operar{
   double division;
   public Division(double n1, double n2){
     super(n1, n2, '/');
     if(n2==0){ System.out.println("Error, no se puede dividir entre cero");
     }else{ 
     this.division = n1/n2;
     this.setRes(this.division);
     }

     
  }
}