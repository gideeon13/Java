package Negocio;

import java.util.Date;

public class InformeSala {
    private int ID;
    private int IDUsuario;
    private Date fechaDeRealizacion;
    private String departamento;
    private String localidad;
    private int cantSalasDisponibles;

    public InformeSala(int IDUsuario, Date fechaDeRealizacion, String departamento, String localidad, int cantSalasDisponibles) {
        this.IDUsuario = IDUsuario;
        this.fechaDeRealizacion = fechaDeRealizacion;
        this.departamento = departamento;
        this.localidad = localidad;
        this.cantSalasDisponibles = cantSalasDisponibles;
    }

    public InformeSala() {
        // Constructor vacío
    }

	public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDUsuario() {
        return IDUsuario;
    }

    public void setIDUsuario(int IDUsuario) {
        this.IDUsuario = IDUsuario;
    }

    public Date getFechaDeRealizacion() {
        return fechaDeRealizacion;
    }

    public void setFechaDeRealizacion(Date fechaDeRealizacion) {
        this.fechaDeRealizacion = fechaDeRealizacion;
    }
    
    public String getDepartamento() {
        return departamento;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
  

    public int getCantSalasDisponibles() {
        return cantSalasDisponibles;
    }

    public void setCantSalasDisponibles(int cantSalasDisponibles) {
        this.cantSalasDisponibles = cantSalasDisponibles;
    }

    @Override
    public String toString() {
        return "InformeSala [ID=" + ID + ", IDUsuario=" + IDUsuario + ", fechaDeRealizacion=" + fechaDeRealizacion +
                ", departamento=" + departamento + ", localidad=" + localidad + ", cantSalasDisponibles=" + cantSalasDisponibles + "]";
    }

}

