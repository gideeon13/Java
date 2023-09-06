public class Venta {
    private String producto, id_v, fecha, id_c, id_p;
    private double precio;

    public Venta(String producto, String id_v, String fecha, String id_c, String id_p, double precio) {
        this.producto = producto;
        this.id_v = id_v;
        this.fecha = fecha;
        this.id_c = id_c;
        this.id_p = id_p;
        this.precio = precio;

    }

    public String getProducto() {
        return producto;
    }

    public String getId_v() {
        return id_v;
    }

    public String getFecha() {
        return fecha;
    }

    public String getId_c() {
        return id_c;

    }

    public String getId_p() {
        return id_p;
    }

    public double getPrecio() {
        return precio;
    }

    public String toString() {
        return " | Producto: " + producto + ", Id_v: " + id_v + ", Fecha: " + fecha + ", Id_c " + id_c + ", Id_p " + id_p + ", Precio: " + precio + " |";
    }
}