package InterfacesGraficas;

import LogicaNegocio.Administrador;
import LogicaNegocio.Permiso;
import LogicaNegocio.Rol;
import LogicaNegocio.Calendario;
import Excepciones.EmpleadoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistrarEmpleado extends JFrame {
    private JTabbedPane tabbedPane;

    // Componentes para la pestaña de crear empleado
    private JTextField tfNombreEmpleado;
    private JTextField tfIdEmpleado;
    private JComboBox<Permiso> cbPermiso;
    private JComboBox<Rol> cbRol;
    private JButton btnCrearEmpleado;

    // Componentes para la pestaña de eliminar empleado
    private JTextField tfIdEliminar;

    // Componentes para la pestaña de establecer horarios
    private JTextField tfInicioSemana;
    private JTextField tfFinSemana;
    private JTextField tfInicioFinDeSemana;
    private JTextField tfFinFinDeSemana;
    private JButton btnEstablecerHorarios;

    private Administrador administrador;

    public VentanaRegistrarEmpleado(Administrador admin) {
        this.administrador = admin;

        setTitle("Administración de Empleados y Horarios");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();

        // Pestaña para crear empleado
        JPanel panelCrearEmpleado = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCrearEmpleado.add(new JLabel("ID del Empleado:"), gbc);
        tfIdEmpleado = new JTextField(10);
        gbc.gridx = 1;
        panelCrearEmpleado.add(tfIdEmpleado, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCrearEmpleado.add(new JLabel("Nombre del Empleado:"), gbc);
        tfNombreEmpleado = new JTextField(10);
        gbc.gridx = 1;
        panelCrearEmpleado.add(tfNombreEmpleado, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelCrearEmpleado.add(new JLabel("Permiso:"), gbc);
        cbPermiso = new JComboBox<>(Permiso.values());
        gbc.gridx = 1;
        panelCrearEmpleado.add(cbPermiso, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelCrearEmpleado.add(new JLabel("Rol:"), gbc);
        cbRol = new JComboBox<>(Rol.values());
        gbc.gridx = 1;
        panelCrearEmpleado.add(cbRol, gbc);

        btnCrearEmpleado = new JButton("Crear Empleado");
        btnCrearEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearEmpleado();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panelCrearEmpleado.add(btnCrearEmpleado, gbc);

        // Pestaña para eliminar empleado
        JPanel panelEliminarEmpleado = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelEliminarEmpleado.add(new JLabel("ID del Empleado a Eliminar:"), gbc);
        tfIdEliminar = new JTextField(10);
        gbc.gridx = 1;
        panelEliminarEmpleado.add(tfIdEliminar, gbc);

        JButton btnEliminarEmpleado = new JButton("Eliminar Empleado");
        btnEliminarEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEmpleado();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panelEliminarEmpleado.add(btnEliminarEmpleado, gbc);

        // Pestaña para establecer horarios
        JPanel panelEstablecerHorarios = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelEstablecerHorarios.add(new JLabel("Inicio Semana (HH:mm):"), gbc);
        tfInicioSemana = new JTextField("20:00", 10); // Valor predeterminado
        gbc.gridx = 1;
        panelEstablecerHorarios.add(tfInicioSemana, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelEstablecerHorarios.add(new JLabel("Fin Semana (HH:mm):"), gbc);
        tfFinSemana = new JTextField("02:00", 10); // Valor predeterminado
        gbc.gridx = 1;
        panelEstablecerHorarios.add(tfFinSemana, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelEstablecerHorarios.add(new JLabel("Inicio Fin de Semana (HH:mm):"), gbc);
        tfInicioFinDeSemana = new JTextField("20:00", 10); // Valor predeterminado
        gbc.gridx = 1;
        panelEstablecerHorarios.add(tfInicioFinDeSemana, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelEstablecerHorarios.add(new JLabel("Fin Fin de Semana (HH:mm):"), gbc);
        tfFinFinDeSemana = new JTextField("05:00", 10); // Valor predeterminado
        gbc.gridx = 1;
        panelEstablecerHorarios.add(tfFinFinDeSemana, gbc);

        btnEstablecerHorarios = new JButton("Establecer Horarios");
        btnEstablecerHorarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                establecerHorarios();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panelEstablecerHorarios.add(btnEstablecerHorarios, gbc);

        // Agregar paneles a las pestañas
        tabbedPane.addTab("Crear Empleado", panelCrearEmpleado);
        tabbedPane.addTab("Eliminar Empleado", panelEliminarEmpleado);
        tabbedPane.addTab("Establecer Horarios", panelEstablecerHorarios);

        // Agregar ChangeListener para verificar campos al cambiar de pestaña
        tabbedPane.addChangeListener(e -> {
            int selectedIndex = tabbedPane.getSelectedIndex();
            if (selectedIndex == 2) { // Pestaña de Establecer Horarios
                if (tfInicioSemana.getText().isEmpty()) {
                    tfInicioSemana.setText("20:00");
                }
                if (tfFinSemana.getText().isEmpty()) {
                    tfFinSemana.setText("02:00");
                }
                if (tfInicioFinDeSemana.getText().isEmpty()) {
                    tfInicioFinDeSemana.setText("20:00");
                }
                if (tfFinFinDeSemana.getText().isEmpty()) {
                    tfFinFinDeSemana.setText("05:00");
                }
            }
        });

        // Agregar el JTabbedPane a la ventana
        add(tabbedPane, BorderLayout.CENTER);
    }

    private void crearEmpleado() {
        try {
            int idEmpleado = Integer.parseInt(tfIdEmpleado.getText());
            String nombre = tfNombreEmpleado.getText();
            Permiso permiso = (Permiso) cbPermiso.getSelectedItem();
            Rol rol = (Rol) cbRol.getSelectedItem();

            administrador.crearCuentaEmpleado(idEmpleado, nombre, permiso, rol);
            JOptionPane.showMessageDialog(this, "Empleado creado correctamente.");
        } catch (EmpleadoException e) {
            JOptionPane.showMessageDialog(this, "Error al crear empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarEmpleado() {
        try {
            int idEmpleado = Integer.parseInt(tfIdEliminar.getText());
            administrador.eliminarEmpleadoDelArchivo(idEmpleado);
            JOptionPane.showMessageDialog(this, "Empleado eliminado correctamente.");
        } catch (EmpleadoException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void establecerHorarios() {
        try {
            String inicioSemana = tfInicioSemana.getText();
            String finSemana = tfFinSemana.getText();
            String inicioFinDeSemana = tfInicioFinDeSemana.getText();
            String finFinDeSemana = tfFinFinDeSemana.getText();

            administrador.establecerHorariosFijos(inicioSemana, finSemana, inicioFinDeSemana, finFinDeSemana);
            JOptionPane.showMessageDialog(this, "Horarios establecidos correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al establecer horarios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
