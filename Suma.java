//Clase Suma que hereda de Operacion
public class Suma extends Operacion {
  
  double suma;
  //Constructor de la clase Suma
  public Suma(double v1, double v2) {
    //Llamamos al constructor de la superclase con super
    super(v1, v2, '+');
    this.suma = v1 + v2;
    this.setResultado(this.suma);
  }
  
  //Sobreescribimos el método operar para realizar la suma
  @Override
  public void operar() {
    //Asignamos al atributo resultado la suma de los atributos valor1 y valor2
    this.setResultado(valor1 + valor2);
  }
}
