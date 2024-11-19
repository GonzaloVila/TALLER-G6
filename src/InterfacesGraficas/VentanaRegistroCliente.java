package InterfacesGraficas;

import LogicaNegocio.Cliente;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * VentanaRegistroCliente es una clase que representa una interfaz gráfica
 * para el registro de nuevos clientes en el sistema.
 * Extiende la clase JFrame para crear una ventana personalizada.
 */
public class VentanaRegistroCliente extends JFrame {
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JTextField txtNumero;
    private JPasswordField txtContrasenia;
    private JButton btnRegistrar;

    private VentanaIniciarSesion ventanaIniciarSesion; // Referencia a la ventana de inicio de sesión

    /**
     * Constructor de la clase VentanaRegistroCliente.
     *
     * @param ventanaIniciarSesion referencia a la ventana de inicio de sesión
     */
    public VentanaRegistroCliente(VentanaIniciarSesion ventanaIniciarSesion) {
        this.ventanaIniciarSesion = ventanaIniciarSesion; // Inicializa la referencia
        setTitle("Registro de Cliente");
        setSize(300, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    /**
     * Configura los componentes de la ventana de registro.
     *
     * @param panel el panel donde se colocan los componentes
     */
    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel labelNombre = new JLabel("Nombre:");
        labelNombre.setBounds(10, 20, 80, 25);
        panel.add(labelNombre);

        txtNombre = new JTextField(20);
        txtNombre.setBounds(100, 20, 165, 25);
        panel.add(txtNombre);

        JLabel labelCorreo = new JLabel("Correo:");
        labelCorreo.setBounds(10, 50, 80, 25);
        panel.add(labelCorreo);

        txtCorreo = new JTextField(20);
        txtCorreo.setBounds(100, 50, 165, 25);
        panel.add(txtCorreo);

        JLabel labelNumero = new JLabel("Número:");
        labelNumero.setBounds(10, 80, 80, 25);
        panel.add(labelNumero);

        txtNumero = new JTextField(20);
        txtNumero.setBounds(100, 80, 165, 25);
        panel.add(txtNumero);

        JLabel labelContrasenia = new JLabel("Contraseña:");
        labelContrasenia.setBounds(10, 110, 80, 25);
        panel.add(labelContrasenia);

        txtContrasenia = new JPasswordField(20);
        txtContrasenia.setBounds(100, 110, 165, 25);
        panel.add(txtContrasenia);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(100, 140, 150, 25);
        panel.add(btnRegistrar);

        // Agrega un listener al botón de registrar
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarCliente();
            }
        });
    }

    /**
     * Método que se encarga de registrar un nuevo cliente.
     * Valida los campos de entrada y crea un nuevo objeto Cliente.
     */
    private void registrarCliente() {
        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();
        String numero = txtNumero.getText().trim();
        String contrasenia = new String(txtContrasenia.getPassword()).trim();

        // Validaciones
        if (nombre.isEmpty() || correo.isEmpty() || numero.isEmpty() || contrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validación simple de correo
        if (!correo.contains("@") || !correo.contains(".")) {
            JOptionPane.showMessageDialog(this, "El correo debe contener '@' y un dominio.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Cliente cliente = new Cliente(nombre, correo, numero, contrasenia);
            cliente.registrarCliente(cliente); // Llama al método para guardar el cliente

            // Actualiza la lista de clientes
            Cliente.setListaClientes(new ArrayList<>()); // Limpiar la lista anterior
            //Cliente.setListaClientes(Cliente.cargarClientesDesdeArchivo()); // Cargar la lista actualizada

            JOptionPane.showMessageDialog(this, "Cliente registrado exitosamente.");
            dispose(); // Cierra la ventana de registro
            ventanaIniciarSesion.setVisible(true); // Muestra nuevamente la ventana de inicio de sesión
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Limpia los campos de entrada de la ventana.
     */
    private void clearFields() {
        txtNombre.setText("");
        txtCorreo.setText("");
        txtNumero.setText("");
        txtContrasenia.setText("");
    }
}