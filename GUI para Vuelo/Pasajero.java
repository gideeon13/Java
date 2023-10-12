// Clase Pasajero
public class Pasajero {
    private String nombre;
    private String apellido;
    private String pasaporte;

    public Pasajero(String nombre, String apellido, String pasaporte) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.pasaporte = pasaporte;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getPasaporte() {
        return pasaporte;
    }
}
