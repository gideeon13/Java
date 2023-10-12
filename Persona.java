//Clase abstracta Persona
public class Persona {
  //Atributos de la clase Persona
  private String nombre;
  private String apellido;
  private int edad;
  
  //Constructor de la clase Persona
  public Persona(String nombre, String apellido, int edad) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.edad = edad;
  }
  
  //Métodos de la clase Persona
  public String getNombre() {
    return nombre;
  }
  
  public String getApellido() {
    return apellido;
  }
  
  public int getEdad() {
    return edad;
  }

}