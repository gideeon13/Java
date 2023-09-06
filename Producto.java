public class Producto extends Operar{
   double producto;
   public Producto(double n1, double n2){
     super(n1, n2, '*');
     this.producto = n1*n2;
     this.setRes(this.producto);
     
  }
}