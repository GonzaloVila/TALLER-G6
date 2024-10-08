public class Pago {
    private double monto;
    private int fecha;
    private TarjetaCredito tarjeta;
    private Reserva reserva;

    public Pago(double monto, int fecha, TarjetaCredito tarjeta, Reserva reserva) {
        this.monto = monto;
        this.fecha = fecha;
        this.tarjeta = tarjeta;
        this.reserva = reserva;
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

    public TarjetaCredito getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(TarjetaCredito tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "monto=" + monto +
                ", fecha=" + fecha +
                ", tarjeta=" + tarjeta +
                ", reserva=" + reserva +
                '}';
    }
}
