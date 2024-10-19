/**package InterfacesGraficas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import LogicaNegocio.Administrador;
import java.time.LocalTime;
import java.time.LocalDate;

public class VentanaEstablecerHorario extends JFrame {
    private Administrador administrador; // Atributo para el administrador

    public VentanaEstablecerHorario(Administrador administrador) {
        this.administrador = administrador;

        setTitle("Establecer Horarios del Restaurante");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // Componentes de la interfaz
        JLabel labelInicioSemana = new JLabel("Inicio (Domingo a Jueves):");
        JTextField textInicioSemana = new JTextField("20:00");

        JLabel labelFinSemana = new JLabel("Fin (Domingo a Jueves):");
        JTextField textFinSemana = new JTextField("02:00");

        JLabel labelInicioFinDeSemana = new JLabel("Inicio (Viernes y Sábado):");
        JTextField textInicioFinDeSemana = new JTextField("20:00");

        JLabel labelFinFinDeSemana = new JLabel("Fin (Viernes y Sábado):");
        JTextField textFinFinDeSemana = new JTextField("05:00");

        JButton btnGuardar = new JButton("Establecer Horarios");

        // Añadir componentes al marco
        add(labelInicioSemana);
        add(textInicioSemana);
        add(labelFinSemana);
        add(textFinSemana);
        add(labelInicioFinDeSemana);
        add(textInicioFinDeSemana);
        add(labelFinFinDeSemana);
        add(textFinFinDeSemana);
        add(btnGuardar);

        // Acción al hacer clic en el botón
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                establecerHorariosFijos(
                        textInicioSemana.getText(),
                        textFinSemana.getText(),
                        textInicioFinDeSemana.getText(),
                        textFinFinDeSemana.getText()
                );
            }
        });
    }

    // Cambiar la implementación para delegar al Administrador
    private void establecerHorariosFijos(String inicioSemanaStr, String finSemanaStr, String inicioFinDeSemanaStr, String finFinDeSemanaStr) {
        try {
            administrador.establecerHorariosFijos(inicioSemanaStr, finSemanaStr, inicioFinDeSemanaStr, finFinDeSemanaStr);
            JOptionPane.showMessageDialog(this, "Horarios establecidos correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
*/