package Ventanas;

import LogicaNegocio.Empleado;
import LogicaNegocio.Permiso;
import LogicaNegocio.Rol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistrarEmpleado extends JFrame {
    private JTextField idField;
    private JTextField nombreField;
    private JComboBox<Permiso> permisoField;
    private JComboBox<Rol> rolField;
    private JButton crearBoton;

    public VentanaRegistrarEmpleado() {
        setTitle("Crear cuenta de empleado");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());  // Usar GridBagLayout para un control preciso
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Añadimos márgenes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Los componentes se expanden horizontalmente

        // Crear los componentes de la interfaz
        idField = new JTextField(10); // Definir tamaño de 10 columnas
        nombreField = new JTextField(10); // Definir tamaño de 10 columnas
        permisoField = new JComboBox<>(Permiso.values());
        rolField = new JComboBox<>(Rol.values());
        crearBoton = new JButton("Crear Cuenta");

        // Añadir componentes con GridBagLayout

        // Etiqueta ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("ID:"), gbc);

        // Campo ID
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(idField, gbc);

        // Etiqueta Nombre
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Nombre:"), gbc);

        // Campo Nombre
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(nombreField, gbc);

        // Etiqueta Permiso
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Permiso:"), gbc);

        // ComboBox Permiso
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(permisoField, gbc);

        // Etiqueta Rol
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Rol:"), gbc);

        // ComboBox Rol
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(rolField, gbc);

        // Botón Crear
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // El botón ocupa dos columnas
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(crearBoton, gbc);

        // Acción del botón crear
        crearBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCuentaEmpleado();
            }
        });
    }

    private void crearCuentaEmpleado() {
        try {
            // Validar que el ID sea un número válido
            int idEmpleado = Integer.parseInt(idField.getText());

            // Validar que el nombre no esté vacío y no contenga números
            String nombre = nombreField.getText();
            if (nombre.isEmpty() || nombre.matches(".*\\d.*")) {
                throw new Exception("El nombre no puede estar vacío ni contener números.");
            }

            // Validar que se haya seleccionado un permiso y rol
            Permiso permiso = (Permiso) permisoField.getSelectedItem();
            Rol rol = (Rol) rolField.getSelectedItem();

            if (permiso == null || rol == null) {
                throw new Exception("Debe seleccionar un permiso y un rol.");
            }

            // Si todo está bien, crear el empleado
            Empleado empleado = new Empleado();
            empleado.crearCuentaEmpleado(idEmpleado, nombre, permiso, rol);

            // Mostrar un mensaje de éxito
            JOptionPane.showMessageDialog(this, "Cuenta de empleado creada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID de empleado válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
