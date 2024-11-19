package LogicaNegocio;

/**
 * La clase TarjetaCredito representa una tarjeta de crédito que puede ser utilizada
 * para realizar transacciones dentro del sistema.
 */
public class TarjetaCredito {
    private String nombre;          // Nombre del titular de la tarjeta
    private String emisor;          // Emisor de la tarjeta (por ejemplo, Visa, MasterCard)
    private long numerotarje;       // Número de la tarjeta de crédito
    private int codverificacion;    // Código de verificación (CVV) de la tarjeta

    /**
     * Constructor de la clase TarjetaCredito.
     *
     * @param nombre          Nombre del titular de la tarjeta.
     * @param emisor          Emisor de la tarjeta.
     * @param numerotarje     Número de la tarjeta de crédito.
     * @param codverificacion  Código de verificación de la tarjeta.
     */
    public TarjetaCredito(String nombre, String emisor, long numerotarje, int codverificacion) {
        this.nombre = nombre;
        this.emisor = emisor;
        this.numerotarje = numerotarje;
        this.codverificacion = codverificacion;
    }

    // Métodos getter y setter

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public long getNumerotarje() {
        return numerotarje;
    }

    public void setNumerotarje(long numerotarje) {
        this.numerotarje = numerotarje;
    }

    public int getCodverificacion() {
        return codverificacion;
    }

    public void setCodverificacion(int codverificacion) {
        this.codverificacion = codverificacion;
    }

    /**
     * Método toString que devuelve una representación en forma de cadena del objeto TarjetaCredito.
     *
     * @return Una cadena que representa los detalles de la tarjeta de crédito.
     */
    @Override
    public String toString() {
        return "LogicaNegocio.TarjetaCredito{" +
                "nombre='" + nombre + '\'' +
                ", emisor='" + emisor + '\'' +
                ", numerotarje=" + numerotarje +
                ", codverificacion=" + codverificacion +
                '}';
    }
}