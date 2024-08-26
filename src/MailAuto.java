public class MailAuto {
    private String tipoaviso;
    private int fecha;
    private String contenido;

    public MailAuto(String tipoaviso, int fecha, String contenido) {
        this.tipoaviso = tipoaviso;
        this.fecha = fecha;
        this.contenido = contenido;
    }

    public String getTipoaviso() {
        return tipoaviso;
    }

    public void setTipoaviso(String tipoaviso) {
        this.tipoaviso = tipoaviso;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "MailAuto{" + "tipoaviso=" + tipoaviso + ", fecha=" + fecha + ", contenido=" + contenido + '}';
    }


}
