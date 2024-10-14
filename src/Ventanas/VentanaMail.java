package Ventanas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import LogicaNegocio.MailAutomatico;

public class VentanaMail extends JFrame {
    private JTextField emailField, detallesField;
    private JButton enviarConfirmacionButton, enviarRecuperacionButton, enviarRecordatorioButton;

    public VentanaMail() {
        setTitle("Envío de Correos Automáticos");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Campo para el email
        add(new JLabel("Correo del Cliente:"));
        emailField = new JTextField();
        add(emailField);

        // Campo para los detalles de la reserva (si es necesario)
        add(new JLabel("Detalles de la Reserva:"));
        detallesField = new JTextField();
        add(detallesField);

        // Botón para enviar confirmación de reserva
        enviarConfirmacionButton = new JButton("Enviar Confirmación de Reserva");
        enviarConfirmacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarConfirmacion();
            }
        });
        add(enviarConfirmacionButton);

        // Botón para enviar correo de recuperación de contraseña
        enviarRecuperacionButton = new JButton("Enviar Recuperación de Contraseña");
        enviarRecuperacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarRecuperacion();
            }
        });
        add(enviarRecuperacionButton);

        // Botón para enviar recordatorio de reserva
        enviarRecordatorioButton = new JButton("Enviar Recordatorio de Reserva");
        enviarRecordatorioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarRecordatorio();
            }
        });
        add(enviarRecordatorioButton);
    }

    // Método para enviar confirmación de reserva
    private void enviarConfirmacion() {
        String email = emailField.getText();
        String detalles = detallesField.getText();

        if (email.isEmpty() || detalles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese todos los datos", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salimos del método si hay error
        }

        try {
            MailAutomatico mail = new MailAutomatico("Confirmación de Reserva", LocalDateTime.now(), detalles);
            mail.enviar_Mail_Confirmacion_Reserva(email, detalles);
            JOptionPane.showMessageDialog(this, "Correo de confirmación enviado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un problema al enviar el correo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para enviar recuperación de contraseña
    private void enviarRecuperacion() {
        String email = emailField.getText();

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el correo", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salimos del método si hay error
        }

        try {
            MailAutomatico mail = new MailAutomatico("Recuperación de Contraseña", LocalDateTime.now(), "");
            mail.enviar_Mail_Recuperacion(email);
            JOptionPane.showMessageDialog(this, "Correo de recuperación enviado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un problema al enviar el correo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para enviar recordatorio de reserva
    private void enviarRecordatorio() {
        String email = emailField.getText();
        String detalles = detallesField.getText();

        if (email.isEmpty() || detalles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese todos los datos", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salimos del método si hay error
        }

        try {
            MailAutomatico mail = new MailAutomatico("Recordatorio de Reserva", LocalDateTime.now(), detalles);
            mail.enviar_Mail_Recordatorio(email, detalles);
            JOptionPane.showMessageDialog(this, "Correo de recordatorio enviado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un problema al enviar el correo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

