public class Perecedero extends Producto {
    private int diasACaducar;

    public Perecedero(String nombre, double precio, int diasACaducar) {
        super(nombre, precio);
        this.diasACaducar = diasACaducar;
    }

    public int getDiasACaducar() {
        return diasACaducar;
    }

    public void setDiasACaducar(int diasACaducar) {
        this.diasACaducar = diasACaducar;
    }

    @Override
    public double calcular(int cantidad) {
        double precioFinal = super.calcular(cantidad);
        if (diasACaducar == 1) {
            precioFinal /= 4;
        } else if (diasACaducar == 2) {
            precioFinal /= 3;
        } else if (diasACaducar == 3) {
            precioFinal /= 2;
        }
        return precioFinal;
    }
}