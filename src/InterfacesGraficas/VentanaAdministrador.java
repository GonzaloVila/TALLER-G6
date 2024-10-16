package InterfacesGraficas;

import LogicaNegocio.Administrador;
import LogicaNegocio.Evento;
import LogicaNegocio.TipoDeDia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

public class VentanaAdministrador extends JFrame {
    private Administrador administrador;
    private JTextField txtDia;
    private JTextField txtHoraInicio;
    private JTextField txtHoraFin;
    private JTextField txtNombreEvento;
    private JTextField txtFechaEvento;
    private JButton BotonDefinirHorario;
    private JButton BotonDefinirDiaEspecial;

    public VentanaAdministrador(Administrador administrador) {
        this.administrador = administrador;

        setTitle("Interfaz Administrador");
        setSize(400, 300);  // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centrar la ventana
        setLayout(new GridBagLayout());  // Usar GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Añadir márgenes
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Expandir componentes horizontalmente

        // Crear los componentes de la interfaz
        txtDia = new JTextField(10);
        txtHoraInicio = new JTextField(10);
        txtHoraFin = new JTextField(10);
        txtNombreEvento = new JTextField(10);
        txtFechaEvento = new JTextField(10);
        BotonDefinirHorario = new JButton("Definir Horario");
        BotonDefinirDiaEspecial = new JButton("Configurar día especial");

        // Ajustar el comportamiento de expansión y alineación de los componentes

        // Etiqueta Día
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        add(new JLabel("Día (YYYY-MM-DD):"), gbc);

        // Campo Día
        gbc.gridx = 1;
        gbc.weightx = 1;
        add(txtDia, gbc);

        // Etiqueta Hora Inicio
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        add(new JLabel("Hora Inicio (HH:MM):"), gbc);

        // Campo Hora Inicio
        gbc.gridx = 1;
        gbc.weightx = 1;
        add(txtHoraInicio, gbc);

        // Etiqueta Hora Fin
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        add(new JLabel("Hora Fin (HH:MM):"), gbc);

        // Campo Hora Fin
        gbc.gridx = 1;
        gbc.weightx = 1;
        add(txtHoraFin, gbc);

        // Botón Definir Horario
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(BotonDefinirHorario, gbc);

        // Etiqueta Nombre del Evento
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        add(new JLabel("Nombre del Evento:"), gbc);

        // Campo Nombre del Evento
        gbc.gridx = 1;
        gbc.weightx = 1;
        add(txtNombreEvento, gbc);

        // Etiqueta Fecha del Evento
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0;
        add(new JLabel("Fecha del Evento (YYYY-MM-DD):"), gbc);

        // Campo Fecha del Evento
        gbc.gridx = 1;
        gbc.weightx = 1;
        add(txtFechaEvento, gbc);

        // Botón Definir Día Especial
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(BotonDefinirDiaEspecial, gbc);

        // Acción del botón Definir Horario
        BotonDefinirHorario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validar que los campos no estén vacíos
                    if (txtDia.getText().isEmpty() || txtHoraInicio.getText().isEmpty() || txtHoraFin.getText().isEmpty()) {
                        throw new Exception("Todos los campos deben estar llenos.");
                    }

                    // Convertir los textos en LocalDate y LocalTime
                    LocalDate dia = LocalDate.parse(txtDia.getText());
                    LocalTime inicio = LocalTime.parse(txtHoraInicio.getText());
                    LocalTime fin = LocalTime.parse(txtHoraFin.getText());

                    // Definir el horario
                    administrador.definirHorarioRegular(dia, inicio, fin);
                    JOptionPane.showMessageDialog(null, "Horario definido correctamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // Acción del botón Definir Día Especial
        BotonDefinirDiaEspecial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = txtNombreEvento.getText();
                    LocalDate fecha = LocalDate.parse(txtFechaEvento.getText());
                    LocalTime horainicio = LocalTime.of(12, 0);  // Valores arbitrarios
                    LocalTime horafin = LocalTime.of(14, 0);     // Valores arbitrarios
                    TipoDeDia tipoDeDia = TipoDeDia.Normal;

                    Evento evento = new Evento(nombre, fecha.getDayOfMonth(), horainicio.getHour(), horafin.getHour(), tipoDeDia);
                    administrador.configurarDiaEspecial(evento);
                    JOptionPane.showMessageDialog(null, "Día especial configurado correctamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // Hacer la ventana visible
        setVisible(true);
    }
}
