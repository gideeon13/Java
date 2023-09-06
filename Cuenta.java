
class Cuenta {
    private double saldo;

    public Cuenta(double saldoInicial) {
        saldo = saldoInicial;
    }

    public double obtenerSaldo() {
        return saldo;
    }

    public void depositar(double monto) {
        saldo += monto;
    }
}
