package Negocio;

import java.util.Date;

public class InformeSala {
    private int ID;
    private int IDUsuario;
    private Date fechaDeRealizacion;
    private String localidad;
    private int cantSalasDisponibles;

    public InformeSala() {
        // Constructor vacío
    }

    public InformeSala(int IDUsuario, Date fechaDeRealizacion, String localidad, int cantSalasDisponibles) {
        this.IDUsuario = IDUsuario;
        this.fechaDeRealizacion = fechaDeRealizacion;
        this.localidad = localidad;
        this.cantSalasDisponibles = cantSalasDisponibles;
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

    public String getLocalidad() {
        return localidad;
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
        return "InformeSala [ID=" + ID + ", IDUsuario=" + IDUsuario + ", fechaDeRealizacion=" + fechaDeRealizacion
                + ", localidad=" + localidad + ", cantSalasDisponibles=" + cantSalasDisponibles + "]";
    }

	public void setDepartamento(String departamento) {
		// TODO Auto-generated method stub
		
	}

	public String getDepartamento() {
		// TODO Auto-generated method stub
		return null;
	}
}

