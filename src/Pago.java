public class Pago {
    private double monto;
    private int fecha;
    private TarjetaCredi tarjeta;

    public Pago(double monto, int fecha, TarjetaCredi tarjeta) {
        this.monto = monto;
        this.fecha = fecha;
        this.tarjeta = tarjeta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public TarjetaCredi getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(TarjetaCredi tarjeta) {
        this.tarjeta = tarjeta;
    }

    @Override
    public String toString() {
        return "Pago{" + "monto=" + monto + ", fecha=" + fecha + ", tarjeta=" + tarjeta + '}';
    }


}
