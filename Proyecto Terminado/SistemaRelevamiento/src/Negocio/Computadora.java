package Negocio;

public class Computadora {
    private int ID;
    private int IDInforme;
    private int numIdentificador;
    private String modeloCPU;
    private int cantProcesadores;
    private int potenciaMhz;
    private String familiaCPU;
    private int cacheCPU;
    private int memoriaTotal;
    private int memoriaLibre;
    private int memoriaCache;
    private int memoriaDisponible;
    private int cantAlmacenamiento;
    private String soNombre;
    private String soKernel;
    private String soVersion;
    private String soArquitectura;

    // Constructor con parámetros
    public Computadora(int IDInforme, int numIdentificador, String modeloCPU, int cantProcesadores,
                       int potenciaMhz, String familiaCPU, int cacheCPU, int memoriaTotal, int memoriaLibre,
                       int memoriaCache, int memoriaDisponible, int cantAlmacenamiento, String soNombre,
                       String soKernel, String soVersion, String soArquitectura) {
        this.IDInforme = IDInforme;
        this.numIdentificador = numIdentificador;
        this.modeloCPU = modeloCPU;
        this.cantProcesadores = cantProcesadores;
        this.potenciaMhz = potenciaMhz;
        this.familiaCPU = familiaCPU;
        this.cacheCPU = cacheCPU;
        this.memoriaTotal = memoriaTotal;
        this.memoriaLibre = memoriaLibre;
        this.memoriaCache = memoriaCache;
        this.memoriaDisponible = memoriaDisponible;
        this.cantAlmacenamiento = cantAlmacenamiento;
        this.soNombre = soNombre;
        this.soKernel = soKernel;
        this.soVersion = soVersion;
        this.soArquitectura = soArquitectura;
    }

    public Computadora() {
        // Constructor vacío
    }

    // Getters y setters para los atributos
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDInforme() {
        return IDInforme;
    }

    public void setIDInforme(int IDInforme) {
        this.IDInforme = IDInforme;
    }

    public int getNumIdentificador() {
        return numIdentificador;
    }

    public void setNumIdentificador(int numIdentificador) {
        this.numIdentificador = numIdentificador;
    }

    public String getModeloCPU() {
        return modeloCPU;
    }

    public void setModeloCPU(String modeloCPU) {
        this.modeloCPU = modeloCPU;
    }

    public int getCantProcesadores() {
        return cantProcesadores;
    }

    public void setCantProcesadores(int cantProcesadores) {
        this.cantProcesadores = cantProcesadores;
    }

    public int getPotenciaMhz() {
        return potenciaMhz;
    }

    public void setPotenciaMhz(int potenciaMhz) {
        this.potenciaMhz = potenciaMhz;
    }

    public String getFamiliaCPU() {
        return familiaCPU;
    }

    public void setFamiliaCPU(String familiaCPU) {
        this.familiaCPU = familiaCPU;
    }

    public int getCacheCPU() {
        return cacheCPU;
    }

    public void setCacheCPU(int cacheCPU) {
        this.cacheCPU = cacheCPU;
    }

    public int getMemoriaTotal() {
        return memoriaTotal;
    }

    public void setMemoriaTotal(int memoriaTotal) {
        this.memoriaTotal = memoriaTotal;
    }

    public int getMemoriaLibre() {
        return memoriaLibre;
    }

    public void setMemoriaLibre(int memoriaLibre) {
        this.memoriaLibre = memoriaLibre;
    }

    public int getMemoriaCache() {
        return memoriaCache;
    }

    public void setMemoriaCache(int memoriaCache) {
        this.memoriaCache = memoriaCache;
    }

    public int getMemoriaDisponible() {
        return memoriaDisponible;
    }

    public void setMemoriaDisponible(int memoriaDisponible) {
        this.memoriaDisponible = memoriaDisponible;
    }

    public int getCantAlmacenamiento() {
        return cantAlmacenamiento;
    }

    public void setCantAlmacenamiento(int cantAlmacenamiento) {
        this.cantAlmacenamiento = cantAlmacenamiento;
    }

    public String getSONombre() {
        return soNombre != null ? soNombre.toUpperCase() : null;
    }

    public void setSONombre(String soNombre) {
        this.soNombre = soNombre;
    }

    public String getSOKernel() {
        return soKernel != null ? soKernel.toUpperCase() : null;
    }

    public void setSOKernel(String soKernel) {
        this.soKernel = soKernel;
    }

    public String getSOVersion() {
        return soVersion != null ? soVersion.toUpperCase() : null;
    }

    public void setSOVersion(String soVersion) {
        this.soVersion = soVersion;
    }

    public String getSOArquitectura() {
        return soArquitectura != null ? soArquitectura.toUpperCase() : null;
    }

    public void setSOArquitectura(String soArquitectura) {
        this.soArquitectura = soArquitectura;
    }
}
