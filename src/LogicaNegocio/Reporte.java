package LogicaNegocio;

public class Reporte {
    private String repototal;
<<<<<<< HEAD
    private Formato formato;

    public Reporte(String repototal, Formato formato) {
        this.repototal = repototal;
        this.formato = formato;
=======

    public Reporte(String repototal){
        this.repototal = repototal;
>>>>>>> 8d43a089b5a3a434da9e33bd4ca2a9e58f80ae1c
    }

    public String getRepototal() {
        return repototal;
    }

    public void setRepototal(String repototal) {
        this.repototal = repototal;
    }

<<<<<<< HEAD
    public Formato getFormato() {
        return formato;
    }

    public void setFormato(Formato formato) {
        this.formato = formato;
    }

    @Override
    public String toString() {
        return "LogicaNegocio.Reporte{" + "repototal=" + repototal + ", formato=" + formato + '}';
=======

    @Override
    public String toString() {
        return "";
>>>>>>> 8d43a089b5a3a434da9e33bd4ca2a9e58f80ae1c
    }


}
