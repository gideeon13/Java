package Negocio;

public class Usuario {
    private int id;
    private String nombreDeCuenta;
    private String tipoDeUsuario;
    private String contrasena;

    public Usuario(int id, String nombreDeCuenta, String tipoDeUsuario, String contrasena) {
        this.id = id;
        this.nombreDeCuenta = nombreDeCuenta;
        this.tipoDeUsuario = tipoDeUsuario;
        this.contrasena = contrasena;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreDeCuenta() {
        return nombreDeCuenta;
    }

    public void setNombreDeCuenta(String nombreDeCuenta) {
        this.nombreDeCuenta = nombreDeCuenta;
    }

    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(String tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // Otros métodos relacionados con la entidad de usuario
    public String obtenerInformacionUsuario() {
        return "ID: " + id + ", Nombre de Cuenta: " + nombreDeCuenta + ", Tipo de Usuario: " + tipoDeUsuario;
    }
}

