class Venta {
    private int idV;
    private String fecha;
    private int idC;
    private String nombreC;
    private int idP;
    private String nombreP;

    public Venta(int idV, String fecha, int idC, String nombreC, int idP, String nombreP) {
        this.idV = idV;
        this.fecha = fecha;
        this.idC = idC;
        this.nombreC = nombreC;
        this.idP = idP;
        this.nombreP = nombreP;
    }

    public int getIdV() {
        return idV;
    }

    public void setIdV(int idV) {
        this.idV = idV;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }
}