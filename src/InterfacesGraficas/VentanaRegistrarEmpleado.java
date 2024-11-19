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

/**
 * Clase que representa la ventana para registrar empleados en el sistema.
 * Extiende JFrame y proporciona una interfaz gráfica para gestionar empleados.
 */
public class VentanaRegistrarEmpleado extends JFrame {
    // Panel de pestañas que contiene las diferentes secciones de la ventana
    private JTabbedPane tabbedPane;

    // Componentes para la pestaña de crear empleado
    private JTextField tfNombreEmpleado; // Campo para el nombre del empleado
    private JTextField tfIdEmpleado; // Campo para el ID del empleado
    private JTextField tfEmailEmpleado; // Campo para el correo electrónico del empleado
    private JTextField tfContraseniaEmpleado; // Campo para la contraseña del empleado
    private JComboBox<Permiso> cbPermiso; // ComboBox para seleccionar el permiso del empleado
    private JComboBox<Rol> cbRol; // ComboBox para seleccionar el rol del empleado
    private JButton btnCrearEmpleado; // Botón para crear un nuevo empleado

    // Componentes para la pestaña de eliminar empleado
    private JTextField tfIdEliminar; // Campo para el ID del empleado a eliminar

    // Componentes para la pestaña de eliminar cliente
    private JTextField tfEmailEliminar; // Campo para el correo del cliente a eliminar

    // Componentes para la pestaña de establecer horarios
    private JTextField tfInicioSemana; // Campo para el inicio de la semana
    private JTextField tfFinSemana; // Campo para el fin de la semana
    private JTextField tfInicioFinDeSemana; // Campo para el inicio del fin de semana
    private JTextField tfFinFinDeSemana; // Campo para el fin del fin de semana
    private JButton btnEstablecerHorarios; // Botón para establecer los horarios

    private Administrador administrador; // Objeto administrador para gestionar operaciones de empleados

    /**
     * Constructor de la ventana para registrar empleados.
     *
     * @param admin El administrador que gestiona la ventana.
     */

    public VentanaRegistrarEmpleado(Administrador admin) {
        this.administrador = admin;

        setTitle("Administración de Empleados y Clientes");
        setSize(500, 500);
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
        panelCrearEmpleado.add(new JLabel("Email:"), gbc);
        tfEmailEmpleado = new JTextField(10);
        gbc.gridx = 1;
        panelCrearEmpleado.add(tfEmailEmpleado, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelCrearEmpleado.add(new JLabel("Contraseña:"), gbc);
        tfContraseniaEmpleado = new JTextField(10);
        gbc.gridx = 1;
        panelCrearEmpleado.add(tfContraseniaEmpleado, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelCrearEmpleado.add(new JLabel("Permiso:"), gbc);
        cbPermiso = new JComboBox<>(Permiso.values());
        gbc.gridx = 1;
        panelCrearEmpleado.add(cbPermiso, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
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
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panelCrearEmpleado.add(btnCrearEmpleado, gbc);


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


        JPanel panelEliminarCliente = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelEliminarCliente.add(new JLabel("Correo del Cliente a Eliminar:"), gbc);
        tfEmailEliminar = new JTextField(10);
        gbc.gridx = 1;
        panelEliminarCliente.add(tfEmailEliminar, gbc);

        JButton btnEliminarCliente = new JButton("Eliminar Cliente");
        btnEliminarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCliente();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panelEliminarCliente.add(btnEliminarCliente, gbc);


        JPanel panelEstablecerHorarios = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelEstablecerHorarios.add(new JLabel("Inicio Semana (HH:mm):"), gbc);
        tfInicioSemana = new JTextField("20:00", 10);
        gbc.gridx = 1;
        panelEstablecerHorarios.add(tfInicioSemana, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelEstablecerHorarios.add(new JLabel("Fin Semana (HH:mm):"), gbc);
        tfFinSemana = new JTextField("02:00", 10);
        gbc.gridx = 1;
        panelEstablecerHorarios.add(tfFinSemana, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelEstablecerHorarios.add(new JLabel("Inicio Fin de Semana (HH:mm):"), gbc);
        tfInicioFinDeSemana = new JTextField("20:00", 10);
        gbc.gridx = 1;
        panelEstablecerHorarios.add(tfInicioFinDeSemana, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelEstablecerHorarios.add(new JLabel("Fin Fin de Semana (HH:mm):"), gbc);
        tfFinFinDeSemana = new JTextField("05:00", 10);
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


        tabbedPane.addTab("Crear Empleado", panelCrearEmpleado);
        tabbedPane.addTab("Eliminar Empleado", panelEliminarEmpleado);
        tabbedPane.addTab("Eliminar Cliente", panelEliminarCliente);
        tabbedPane.addTab("Establecer Horarios", panelEstablecerHorarios);


        tabbedPane.addChangeListener(e -> {
            int selectedIndex = tabbedPane.getSelectedIndex();
            if (selectedIndex == 2) {
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

        add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Método para crear un nuevo empleado a partir de la información proporcionada en los campos de entrada.
     *
     * Este método obtiene el ID, nombre, email, contraseña, permiso y rol del empleado desde los campos
     * de texto y menús desplegables. Luego, llama al método `crearCuentaEmpleado` del administrador
     * para crear la cuenta del empleado. Si se crea exitosamente, se muestra un mensaje de confirmación.
     * En caso de error en el ID o al crear la cuenta, se muestra un mensaje de error correspondiente.
     *
     * @throws NumberFormatException Si el ID proporcionado no es un número válido.
     * @throws EmpleadoException Si ocurre un error al intentar crear la cuenta del empleado.
     */
    private void crearEmpleado() {
        try {
            int id = Integer.parseInt(tfIdEmpleado.getText());
            String nombre = tfNombreEmpleado.getText();
            String email = tfEmailEmpleado.getText();
            String contrasenia = tfContraseniaEmpleado.getText();
            Permiso permiso = (Permiso) cbPermiso.getSelectedItem();
            Rol rol = (Rol) cbRol.getSelectedItem();

            administrador.crearCuentaEmpleado(id, nombre, email, contrasenia, permiso, rol);
            JOptionPane.showMessageDialog(this, "Empleado creado exitosamente");
            limpiarCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido. Debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (EmpleadoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método para eliminar un empleado de la lista a partir del ID proporcionado.
     *
     * Este método obtiene el ID del empleado a eliminar desde el campo de texto,
     * intenta convertirlo a un número entero y luego llama al método
     * `eliminarEmpleadoDelArchivo` del administrador para realizar la eliminación.
     * Si se elimina exitosamente, se muestra un mensaje de confirmación y se limpia
     * el campo de texto. En caso de que el ID no sea válido o si ocurre un error,
     * se muestra un mensaje de error correspondiente.
     *
     * @throws NumberFormatException Si el ID proporcionado no es un número válido.
     * @throws EmpleadoException Si ocurre un error al intentar eliminar el empleado.
     */
    private void eliminarEmpleado() {
        String idStr = tfIdEliminar.getText();
        try {
            int id = Integer.parseInt(idStr);
            administrador.eliminarEmpleadoDelArchivo(id);
            JOptionPane.showMessageDialog(this, "Empleado eliminado exitosamente");
            tfIdEliminar.setText(""); // Limpiar campo
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (EmpleadoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método para eliminar un cliente de la lista a partir de su dirección de correo electrónico.
     *
     * Este método obtiene el correo electrónico del cliente a eliminar desde el campo de texto
     * y llama al método `eliminarCliente` del administrador para realizar la eliminación.
     * Si se elimina exitosamente, se muestra un mensaje de confirmación y se limpia
     * el campo de texto. En caso de que ocurra un error, se muestra un mensaje de error correspondiente.
     *
     * @throws Exception Si ocurre un error al intentar eliminar el cliente.
     */
    private void eliminarCliente() {
        String email = tfEmailEliminar.getText();
        try {
            administrador.eliminarCliente(email);
            JOptionPane.showMessageDialog(this, "Cliente eliminado exitosamente");
            tfEmailEliminar.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método para establecer los horarios de trabajo fijos para la semana y el fin de semana.
     *
     * Este método obtiene los horarios de inicio y fin de la semana y del fin de semana
     * desde los campos de texto correspondientes y los pasa al método `establecerHorariosFijos`
     * del administrador. Si los horarios se establecen correctamente, se muestra un mensaje
     * de confirmación.
     *
     * @throws IllegalArgumentException Si alguno de los horarios ingresados no es válido.
     */
    private void establecerHorarios() {
        String inicioSemana = tfInicioSemana.getText();
        String finSemana = tfFinSemana.getText();
        String inicioFinDeSemana = tfInicioFinDeSemana.getText();
        String finFinDeSemana = tfFinFinDeSemana.getText();

        administrador.establecerHorariosFijos(inicioSemana, finSemana, inicioFinDeSemana, finFinDeSemana);
        JOptionPane.showMessageDialog(this, "Horarios establecidos exitosamente");
    }

    /**
     * Método para limpiar los campos de entrada en la pestaña de creación de empleado.
     *
     * Este método restablece todos los campos de texto y las combinaciones seleccionadas
     * a sus valores predeterminados, lo que permite al usuario ingresar información
     * para un nuevo empleado sin que queden datos de entradas anteriores.
     */
    private void limpiarCampos() {
        tfIdEmpleado.setText("");
        tfNombreEmpleado.setText("");
        tfEmailEmpleado.setText("");
        tfContraseniaEmpleado.setText("");
        cbPermiso.setSelectedIndex(0);
        cbRol.setSelectedIndex(0);
    }
}
