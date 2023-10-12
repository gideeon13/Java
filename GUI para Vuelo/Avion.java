// Clase Avión
public class Avion {
    private String numeroSerie;
    private int capacidad;

    public Avion(String numeroSerie, int capacidad) {
        this.numeroSerie = numeroSerie;
        this.capacidad = capacidad;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public int getCapacidad() {
        return capacidad;
    }
}
