package LogicaNegocio;

import java.time.LocalDateTime;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailAutomatico {
    private String tipoAviso;
    private LocalDateTime fechaEnvio;
    private String contenido;


    // Constructor
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

    // Método para enviar un correo de confirmación de reserva
    public void enviar_Mail_Confirmacion_Reserva(String emailCliente, String detallesReserva) {
        if (!esEmailValido(emailCliente) || detallesReserva == null || detallesReserva.isEmpty()) {
            throw new IllegalArgumentException("El email o los detalles de la reserva no son válidos.");
        }

        String asunto = "Confirmación de Reserva";
        String mensaje = "Estimado cliente,\n\nSu reserva ha sido confirmada con éxito. Los detalles de la reserva son los siguientes:\n"
                + detallesReserva + "\n\nGracias por elegirnos.\n\nSaludos,\nDelicias Gourmet";

        enviarCorreo(emailCliente, asunto, mensaje);
    }

    // Método para enviar un correo de recuperación de contraseña
    public void enviar_Mail_Recuperacion(String emailCliente) {
        if (!esEmailValido(emailCliente)) {
            throw new IllegalArgumentException("Email inválido.");
        }

        String asunto = "Recuperación de Contraseña";
        String mensaje = "Estimado cliente,\n\nHemos recibido una solicitud de recuperación de contraseña. Si no fue usted quien hizo esta solicitud, ignore este mensaje. De lo contrario, puede restablecer su contraseña haciendo clic en el enlace a continuación.\n\nEnlace para restablecer la contraseña: [Enlace de recuperación].\n\nSaludos,\nDelicias Gourmet";

        enviarCorreo(emailCliente, asunto, mensaje);
    }

    // Método para enviar recordatorios de reservas
    public void enviar_Mail_Recordatorio(String emailCliente, String detallesReserva) {
        if (!esEmailValido(emailCliente) || detallesReserva == null || detallesReserva.isEmpty()) {
            throw new IllegalArgumentException("El email o los detalles de la reserva no son válidos.");
        }

        String asunto = "Recordatorio de Reserva Próxima";
        String mensaje = "Estimado cliente,\n\nEste es un recordatorio de su próxima reserva. Los detalles de la reserva son los siguientes:\n"
                + detallesReserva + "\n\nEsperamos verlo pronto.\n\nSaludos,\nDelicias Gourmet";

        enviarCorreo(emailCliente, asunto, mensaje);
    }

    // Método auxiliar que realiza el envío del correo
    private void enviarCorreo(String destinatario, String asunto, String mensajeTexto) {
        String remitente = "tucorreo@gmail.com"; // Cambia por tu correo
        String host = "smtp.gmail.com";
        String clave = "tucontraseña"; // Cambia por tu contraseña

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587"); // Puerto para TLS
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });

        try {
            MimeMessage mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mensaje.setSubject(asunto);
            mensaje.setText(mensajeTexto);

            Transport.send(mensaje);
            System.out.println("Correo enviado con éxito a " + destinatario);
        } catch (MessagingException ex) {
            throw new RuntimeException("Error al enviar el correo: " + ex.getMessage());
        }
    }

    //Método para validar el email
    private boolean esEmailValido(String email) {
        return email != null && email.matches("^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,4}$");
    }

}

