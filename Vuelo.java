import java.util.ArrayList;
import java.util.List;

public class Vuelo {
    public String numeroDeVuelo;
    public Avion avion;
    public List<Pasajero> pasajeros;

    public Vuelo(String numeroDeVuelo, Avion avion) {
        this.numeroDeVuelo = numeroDeVuelo;
        this.avion = avion;
        this.pasajeros = new ArrayList<>();
    }

    public String getNumeroDeVuelo() {
        return numeroDeVuelo;
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