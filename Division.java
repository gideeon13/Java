//Clase Division que hereda de Operacion
public class Division extends Operacion {
  
  double division;
  //Constructor de la clase Division
  public Division(double v1, double v2) {
    //Llamamos al constructor de la superclase con super
    super(v1, v2, '/');
    this.division = v1 / v2;
    this.setResultado(this.division);
  }
  
  //Sobreescribimos el método operar para realizar la division
  @Override
  public void operar() {
    //Asignamos al atributo resultado la resta de los atributos valor1 y valor2
     this.setResultado(valor1 / valor2);
    } 
  }
