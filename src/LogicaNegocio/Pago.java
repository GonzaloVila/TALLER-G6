package LogicaNegocio;

/**
 * La clase Pago representa un pago realizado en el sistema.
 * Contiene información sobre el monto, la fecha y la tarjeta de crédito utilizada para el pago.
 */
public class Pago {
    private double monto; // Monto del pago
    private int fecha; // Fecha del pago (puede ser en formato YYYYMMDD)
    private TarjetaCredito tarjeta; // Tarjeta de crédito utilizada para el pago

    /**
     * Constructor de la clase Pago.
     *
     * @param monto Monto del pago
     * @param fecha Fecha del pago
     * @param tarjeta Tarjeta de crédito utilizada para el pago
     */
    public Pago(double monto, int fecha, TarjetaCredito tarjeta) {
        this.monto = monto;
        this.fecha = fecha;
        this.tarjeta = tarjeta;
    }

    /**
     * Obtiene el monto del pago.
     *
     * @return El monto del pago
     */
    public double getMonto() {
        return monto;
    }

    /**
     * Establece un nuevo monto para el pago.
     *
     * @param monto El nuevo monto del pago
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }

    /**
     * Obtiene la fecha del pago.
     *
     * @return La fecha del pago
     */
    public int getFecha() {
        return fecha;
    }

    /**
     * Establece una nueva fecha para el pago.
     *
     * @param fecha La nueva fecha del pago
     */
    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la tarjeta de crédito utilizada para el pago.
     *
     * @return La tarjeta de crédito del pago
     */
    public TarjetaCredito getTarjeta() {
        return tarjeta;
    }

    /**
     * Establece una nueva tarjeta de crédito para el pago.
     *
     * @param tarjeta La nueva tarjeta de crédito
     */
    public void setTarjeta(TarjetaCredito tarjeta) {
        this.tarjeta = tarjeta;
    }

    /**
     * Devuelve una representación en forma de cadena del objeto Pago.
     *
     * @return Una cadena que representa el pago
     */
    @Override
    public String toString() {
        return "LogicaNegocio.Pago{" + "monto=" + monto + ", fecha=" + fecha + ", tarjeta=" + tarjeta + '}';
    }
}