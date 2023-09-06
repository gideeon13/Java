public class Operar {
  
  double num1;
  double num2;
  double res;
  char operar;

  public Operar(double n1, double n2, char operar){
    this.num1 = n1;
    this.num2 = n2;
    this.operar = operar;
  }
  public void setRes(double resul){
    this.res=resul;
  }     
  public void mostrarResultado(){
    System.out.println("> "+this.num1+" "+this.operar+" "+this.num2+" = "+this.res);
  }

}