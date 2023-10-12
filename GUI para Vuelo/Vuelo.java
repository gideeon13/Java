// Clase Vuelo
import java.util.ArrayList;
import java.util.List;

public class Vuelo {
    private String numeroVuelo;
    private Avion avion;
    private List<Pasajero> pasajeros;

    public Vuelo(String numeroVuelo, Avion avion) {
        this.numeroVuelo = numeroVuelo;
        this.avion = avion;
        this.pasajeros = new ArrayList<>();
    }

    public String getNumeroVuelo() {
        return numeroVuelo;
    }

    public Avion getAvion() {
        return avion;
    }

    public List<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void agregarPasajero(Pasajero pasajero) {
        pasajeros.add(pasajero);
    }
}
