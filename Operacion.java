//Clase abstracta Operacion
public abstract class Operacion {
  //Atributos de la clase Operacion
  protected double valor1;
  protected double valor2;
  protected double resultado;
  protected char operacion;

  //Constructor de la clase Operacion
  public Operacion(double valor1, double valor2, char operacion) {
    this.valor1 = valor1;
    this.valor2 = valor2;
    this.operacion = operacion;
  }

  
  //Métodos de la clase Operacion

  public abstract void operar();

  public void setValor1(double v1){
    this.valor1 = v1;
  }

  public void setValor2(double v2){
    this.valor2 = v2;
  }

  public void setResultado(double resul){
    this.resultado = resul;
  }

  public void mostrarResultado(){
     System.out.println(this.valor1+" "+this.operacion+" "+this.valor2+" = "+this.resultado);
  }

}