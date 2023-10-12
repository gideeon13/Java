
public class Main {
    public static void main(String[] args) {
        // Crear un avión
        Avion avion = new Avion("ABC123", 150);

        // Crear dos pasajeros
        Pasajero pasajero1 = new Pasajero("Juan", "Perez", "123456789");
        Pasajero pasajero2 = new Pasajero("Maria", "Gonzalez", "987654321");

        // Crear un vuelo y agregar pasajeros
        Vuelo vuelo = new Vuelo("V123", avion);
        vuelo.agregarPasajero(pasajero1);
        vuelo.agregarPasajero(pasajero2);

        System.out.println("");
        System.out.println("|---------------------------|");
        System.out.println("|-  Informacion de Vuelos  -|");
        System.out.println("|---------------------------|");

        // Mostrar información del vuelo
        System.out.println("  >  Número de vuelo: " + vuelo.getNumeroDeVuelo());
        System.out.println("  >  Avión: " + vuelo.getAvion().getNumeroDeSerie());
        System.out.println("  >  Capacidad del avión: " + vuelo.getAvion().getCapacidad());
        System.out.println("|---------------------------|");

        System.out.println("");
        System.out.println("");

        System.out.println("|---------------------------|");
        System.out.println("|-   Pasajeros del vuelo   -|");
        System.out.println("|---------------------------|");
        for (Pasajero pasajero : vuelo.getPasajeros()) {
            System.out.println("  > Nombre: " + pasajero.getNombre());
            System.out.println("  > Apellido: " + pasajero.getApellido());
            System.out.println("  > Pasaporte: " + pasajero.getPasaporte());
        System.out.println("|---------------------------|");
        }
    }
}