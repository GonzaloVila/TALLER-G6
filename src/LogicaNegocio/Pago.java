package LogicaNegocio;

public class Pago {
    private double monto;
    private int fecha;
    private TarjetaCredito tarjeta;

    public Pago(double monto, int fecha, TarjetaCredito tarjeta) {
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

    public TarjetaCredito getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(TarjetaCredito tarjeta) {
        this.tarjeta = tarjeta;
    }

    @Override
    public String toString() {
        return "LogicaNegocio.Pago{" + "monto=" + monto + ", fecha=" + fecha + ", tarjeta=" + tarjeta + '}';
    }


}
