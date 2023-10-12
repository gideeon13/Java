//Clase Profesor que hereda de Persona
public class Profesor extends Persona {
  //Atributo de la clase Profesor
  private String idProfesor;
  
  //Constructor de la clase Profesor
  public Profesor(String nombre, String apellido, int edad) {
    //Llamamos al constructor de la superclase con super
    super(nombre, apellido, edad);

    idProfesor="No asignado"; 
  }

  public void setIdProfesor(String IdProfesor){ 
    this.idProfesor = IdProfesor;   
  }
  
  //Método de la clase Profesor
  public String getIdProfesor() {
    return idProfesor;
  }

}