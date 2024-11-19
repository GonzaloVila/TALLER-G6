package LogicaNegocio;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;
import javax.mail.*;
import javax.mail.internet.*;


public class MailAutomatico {
    private String tipoAviso; // Tipo de aviso del correo
    private LocalDateTime fechaEnvio; // Fecha y hora de envío del correo
    private String contenido; // Contenido del correo

    public MailAutomatico(String tipoAviso, LocalDateTime fechaEnvio, String contenido) {
        this.tipoAviso = tipoAviso;
        this.fechaEnvio = fechaEnvio;
        this.contenido = contenido;
    }


    public MailAutomatico(){}

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

    /**
     * enviar_Mail_Confirmacion_Reserva: Envía un correo de confirmación de reserva al cliente.
     * @param emailCliente Correo del cliente.
     * @param detallesReserva Detalles de la reserva.
     * @throws IllegalArgumentException si el correo o los detalles de la reserva no son válidos.
     */
    public void enviar_Mail_Confirmacion_Reserva(String emailCliente, String detallesReserva) {
        if (!esEmailValido(emailCliente) || detallesReserva == null || detallesReserva.isEmpty()) {
            throw new IllegalArgumentException("El email o los detalles de la reserva no son válidos.");
        }

        String asunto = "Confirmación de Reserva";
        String mensaje = "Estimado cliente,\n\nSu reserva ha sido confirmada con éxito. Los detalles de la reserva son los siguientes:\n"
                + detallesReserva + "\n\nGracias por elegirnos.\n\nSaludos,\nDelicias Gourmet";

        enviarCorreo(emailCliente, asunto, mensaje);
    }



    /**
     * enviar_Mail_Recuperacion: Envía un correo de recuperación de contraseña al cliente.
     * @param emailCliente Correo del cliente.
     * @throws IllegalArgumentException si el correo no es válido.
     */
    public void enviar_Mail_Recuperacion(String emailCliente) {
        if (!esEmailValido(emailCliente)) {
            throw new IllegalArgumentException("Email inválido.");
        }
        // Genera el enlace de recuperación de contraseña
        String enlaceRecuperacion = "https://tuapp.com/recuperacion?token=" + generarTokenUnico();

        String asunto = "Recuperación de Contraseña";
        String mensaje = "Estimado cliente,\n\nHemos recibido una solicitud de recuperación de contraseña. Si no fue usted quien hizo esta solicitud, ignore este mensaje. De lo contrario, puede restablecer su contraseña haciendo clic en el enlace a continuación.\n\nEnlace para restablecer la contraseña: " + enlaceRecuperacion + "\n\nSaludos,\nDelicias Gourmet";
        enviarCorreo(emailCliente, asunto, mensaje);
    }

    /**
     * generarTokenUnico: Genera un token único para la recuperación de contraseña.
     * @return Un token único generado con UUID.
     */
    private String generarTokenUnico() {
        return UUID.randomUUID().toString();
    }



    /**
     * enviar_Mail_Recordatorio: Envía un correo de recordatorio de reserva próxima al cliente.
     * @param emailCliente Correo del cliente.
     * @param detallesReserva Detalles de la reserva.
     * @throws IllegalArgumentException si el correo o los detalles de la reserva no son válidos.
     */
    public void enviar_Mail_Recordatorio(String emailCliente, String detallesReserva) {
        if (!esEmailValido(emailCliente) || detallesReserva == null || detallesReserva.isEmpty()) {
            throw new IllegalArgumentException("El email o los detalles de la reserva no son válidos.");
        }

        String asunto = "Recordatorio de Reserva Próxima";
        String mensaje = "Estimado cliente,\n\nEste es un recordatorio de su próxima reserva. Los detalles de la reserva son los siguientes:\n"
                + detallesReserva + "\n\nEsperamos verlo pronto.\n\nSaludos,\nDelicias Gourmet";

        enviarCorreo(emailCliente, asunto, mensaje);
    }



    /**
     * enviarCorreo: Envía un correo electrónico al destinatario con el asunto y mensaje proporcionados.
     * @param destinatario Correo del destinatario.
     * @param asunto Asunto del correo.
     * @param mensajeTexto Contenido del correo.
     */
    private void enviarCorreo(String destinatario, String asunto, String mensajeTexto) {
        String remitente = "grupete6poo@gmail.com"; // Cambia por tu correo
        String clave = "f a w l c l d l c p b v u e x l"; // Cambia por tu contraseña

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587"); // Usa 587 para TLS
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Habilita STARTTLS

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
            ex.printStackTrace(); // Imprime el stack trace del error
            throw new RuntimeException("Error al enviar el correo: " + ex.getMessage());
        }
    }


    /**
     * esEmailValido: Valida el formato de un correo electrónico.
     * @param email Correo electrónico a validar.
     * @return true si el correo tiene un formato válido, false en caso contrario.
     */
    private boolean esEmailValido(String email) {
        return email != null && email.matches("^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,4}$");
    }
}