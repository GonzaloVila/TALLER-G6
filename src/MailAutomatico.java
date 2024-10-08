import java.time.LocalDateTime;

public class MailAutomatico {
    private String tipoAviso;
    private LocalDateTime fechaEnvio;
    private String contenido;

    public MailAutomatico(String tipoAviso, LocalDateTime fechaEnvio, String contenido) {
        this.tipoAviso = tipoAviso;
        this.fechaEnvio = fechaEnvio;
        this.contenido = contenido;
    }

    public String getTipoaviso() {
        return tipoAviso;
    }

    public void setTipoaviso(String tipoAviso) {
        this.tipoAviso = tipoAviso;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFecha(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "MailAuto{" + "tipoAviso=" + tipoAviso + ", fechaEnvio=" + fechaEnvio + ", contenido=" + contenido + '}';
    }


}
