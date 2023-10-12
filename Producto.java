//Clase Multiplicacion que hereda de Operacion
public class Producto extends Operacion {
  
  double producto;
  //Constructor de la clase Multiplicacion
  public Producto(double v1, double v2) {
    //Llamamos al constructor de la superclase con super
    super(v1, v2, '*');
    this.producto = v1 * v2;
    this.setResultado(this.producto);
  }
  
  //Sobreescribimos el método operar para realizar la multiplicación
  @Override
  public void operar() {
    //Asignamos al atributo resultado la resta de los atributos valor1 y valor2
    this.setResultado(valor1 * valor2);
  }
}