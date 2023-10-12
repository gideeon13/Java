//Clase Administrador que contiene el método main
public class Administrador {
  
  public static void main(String[] args) {
    
    //Creamos una instancia de Profesor
   Profesor profesor1 = new Profesor ("Sebastian", "Martinez", 35);
    // Le asigno un valor al atributo id
   profesor1.setIdProfesor("01");
    // Y lo imprimo en pantalla
    System.out.println("");
    System.out.println("---------------------------");
    System.out.println("------    Profesor   ------");
    System.out.println("---------------------------");
    System.out.println("");
    System.out.println("Profesor: " + profesor1.getNombre() + " " +  profesor1.getApellido()  + " | Edad: " + profesor1.getEdad() + " | Id de profesor: " + profesor1.getIdProfesor());
    System.out.println(""); 
    System.out.println("---------------------------");
    System.out.println("");
    //Creamos una instancia de Estudiante
   Estudiante estudiante1 = new Estudiante ("Martin", "Rodriguez", 18);
    // Le asigno un valor al atributo grupo
    estudiante1.setGrupo("2BE");
    // Y lo imprimo en pantalla
    System.out.println("---------------------------");
    System.out.println("-------  Estudiante -------");
    System.out.println("---------------------------");
    System.out.println("");
    System.out.println("Estudiante: " + estudiante1.getNombre() + " " +  estudiante1.getApellido()  + " | Edad: " + estudiante1.getEdad() + " | Grupo: " + estudiante1.getGrupo());
    System.out.println("");
    System.out.println("---------------------------");

  }
}