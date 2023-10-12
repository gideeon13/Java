public class Main {
    public static void main(String[] args) {
        Producto[] productos = new Producto[6];
        
        productos[0] = new Producto("Arroz", 10.0);
        productos[1] = new Producto("Maicena", 15.0);
        
        productos[2] = new Perecedero("Leche", 8.0, 1);
        productos[3] = new Perecedero("Pescado", 12.0, 2);
        
        productos[4] = new NoPerecedero("Harina", 20.0, "Tipo X");
        productos[5] = new NoPerecedero("Azucar", 25.0, "Tipo Y");

        double precioTotal = 0.0;
        
        for (Producto producto : productos) {
            precioTotal += producto.calcular(5);
        }

        System.out.println("Precio total de vender 5 productos de cada tipo: " + precioTotal);
    }
}

