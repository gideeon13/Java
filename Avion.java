public class Avion {
    private String numeroDeSerie;
    private int capacidad;

    public Avion(String numeroDeSerie, int capacidad) {
        this.numeroDeSerie = numeroDeSerie;
        this.capacidad = capacidad;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public int getCapacidad() {
        return capacidad;
    }
}