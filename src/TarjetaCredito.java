import java.util.ArrayList;
import java.util.List;

public class TarjetaCredito {
    private String nombre;
    private String emisor;
    private long numerotarje;
    private int codverificacion;
    private ArrayList<Pago> pagos;

    public TarjetaCredito(){
        this.pagos = new ArrayList<Pago>();
    }
    public TarjetaCredito(String nombre, String emisor, long numerotarje, int codverificacion){
        this.nombre = nombre;
        this.emisor = emisor;
        this.numerotarje = numerotarje;
        this.codverificacion = codverificacion;
    }
    public TarjetaCredito(String nombre, String emisor, long numerotarje, int codverificacion, ArrayList<Pago> pagos) {
        this.nombre = nombre;
        this.emisor = emisor;
        this.numerotarje = numerotarje;
        this.codverificacion = codverificacion;
        this.pagos = pagos;
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
        return "TarjetaCredito{" + "nombre=" + nombre + ", emisor=" + emisor + ", numerotarje=" + numerotarje + ", codverificacion=" + codverificacion + '}';
    }
}
