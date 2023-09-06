public class Resta extends Operar{
   double resta;
   public Resta(double n1, double n2){
     super(n1, n2, '-');
     this.resta = n1-n2;
     this.setRes(this.resta);
     
  }
}