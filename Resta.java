//Clase Resta que hereda de Operacion
public class Resta extends Operacion {
  
  double resta;
  //Constructor de la clase Resta
  public Resta(double v1, double v2) {
    //Llamamos al constructor de la superclase con super
    super(v1, v2, '-');
    this.resta = v1 - v2;
    this.setResultado(this.resta);
  }
  
  //Sobreescribimos el método operar para realizar la resta
  @Override
  public void operar() {
    //Asignamos al atributo resultado la resta de los atributos valor1 y valor2
    this.setResultado(valor1 + valor2);
  }
}