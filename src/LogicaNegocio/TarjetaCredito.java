package LogicaNegocio;

public class TarjetaCredito {
    private String nombre;
    private String emisor;
    private long numerotarje;
    private int codverificacion;

    public TarjetaCredito(String nombre, String emisor, long numerotarje, int codverificacion) {
        this.nombre = nombre;
        this.emisor = emisor;
        this.numerotarje = numerotarje;
        this.codverificacion = codverificacion;
    }

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

    @Override
    public String toString() {
        return "LogicaNegocio.TarjetaCredito{" + "nombre=" + nombre + ", emisor=" + emisor + ", numerotarje=" + numerotarje + ", codverificacion=" + codverificacion + '}';
    }



}
