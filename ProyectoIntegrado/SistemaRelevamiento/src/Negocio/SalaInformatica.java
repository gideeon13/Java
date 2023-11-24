package Negocio;

public class SalaInformatica {
    private int ID;
    private int IDInforme;
    private int numSala;
    private int capacidad;

    // Constructor
    public SalaInformatica(int ID, int IDInforme, int numSala, int capacidad) {
        this.ID = ID;
        this.IDInforme = IDInforme;
        this.numSala = numSala;
        this.capacidad = capacidad;
    }

    public SalaInformatica() {
		// TODO Auto-generated constructor stub
	}

	// Getters
    public int getID() {
        return ID;
    }

    public int getIDInforme() {
        return IDInforme;
    }

    public int getNumSala() {
        return numSala;
    }

    public int getCapacidad() {
        return capacidad;
    }

    // Setters
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setIDInforme(int IDInforme) {
        this.IDInforme = IDInforme;
    }

    public void setNumSala(int numSala) {
        this.numSala = numSala;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

}
