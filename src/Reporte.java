public class Reporte {
    private String repototal;
    private Formato formato;

    public Reporte(String repototal, Formato formato) {
        this.repototal = repototal;
        this.formato = formato;
    }

    public String getRepototal() {
        return repototal;
    }

    public void setRepototal(String repototal) {
        this.repototal = repototal;
    }

    public Formato getFormato() {
        return formato;
    }

    public void setFormato(Formato formato) {
        this.formato = formato;
    }

    @Override
    public String toString() {
        return "Reporte{" + "repototal=" + repototal + ", formato=" + formato + '}';
    }


}
