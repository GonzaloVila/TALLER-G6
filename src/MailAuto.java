public class MailAuto {
    // ATRIBUTOS
    private String tipoAviso;
    private Integer fecha;
    private String contenido;

    // CONSTRUCTORES
    public MailAuto(String tipoAviso, int fecha, String contenido) {
        this.tipoAviso = tipoAviso;
        this.fecha = fecha;
        this.contenido = contenido;
    }

    // SETTERS Y GETTERS
    public String getTipoaviso() {
        return tipoAviso;
    }

    public void setTipoaviso(String tipoaviso) {
        this.tipoAviso = tipoaviso;
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
        return "MailAuto{" + "tipoaviso=" + tipoAviso + ", fecha=" + fecha + ", contenido=" + contenido + '}';
    }

    // METODOS

    /**
     * enviarMailConfirmacionReserva: envia un mail al usuario para confirmar su reserva
     * */
    public void enviarMailConfirmacionReserva(){}

    /**
     * enviarMailRecuperacion: envia un mail al usuario para recuperar sus datos
     * */
    public void enviarMailRecuperacion(){}

    /**
     * enviarMailRecuperacion: envia un mail al usuario para recordarle su reserva
     * */
    public void enviarMailRecordatorio(){}
}
