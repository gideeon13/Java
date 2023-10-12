//Clase Estudiante que hereda de Persona
public class Estudiante extends Persona {
  //Atributo de la clase Estudiante
  private String grupo;
  
  //Constructor de la clase Estudiante
  public Estudiante(String nombre, String apellido, int edad) {
    //Llamamos al constructor de la superclase con super
    super(nombre, apellido, edad);

    grupo="No asignado";    
  }

  public void setGrupo(String grupo){ 
    this.grupo = grupo;   
  }
  
  //Método de la clase Estudiante
  public String getGrupo() {
    return grupo;
  }

}
  
