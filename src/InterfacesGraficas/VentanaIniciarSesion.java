package InterfacesGraficas;

import LogicaNegocio.Cliente;
import LogicaNegocio.Reserva;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaIniciarSesion extends JFrame {
    private JTextField txtCorreo;
    private JPasswordField txtContrasenia;
    private JButton btnIniciarSesion;
    private JButton btnRegistrar;
    private ArrayList<Cliente> listaClientes;

    public VentanaIniciarSesion() {
        // Cargar clientes desde el archivo
        listaClientes = Cliente.cargarClientesDesdeArchivo();
        Cliente.setListaClientes(listaClientes); // Actualiza la lista estática de clientes

        setTitle("Iniciar Sesión");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel labelCorreo = new JLabel("Correo:");
        labelCorreo.setBounds(10, 20, 80, 25);
        panel.add(labelCorreo);

        txtCorreo = new JTextField(20);
        txtCorreo.setBounds(100, 20, 165, 25);
        panel.add(txtCorreo);

        JLabel labelContrasenia = new JLabel("Contraseña:");
        labelContrasenia.setBounds(10, 50, 80, 25);
        panel.add(labelContrasenia);

        txtContrasenia = new JPasswordField(20);
        txtContrasenia.setBounds(100, 50, 165, 25);
        panel.add(txtContrasenia);

        btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setBounds(100, 80, 150, 25);
        panel.add(btnIniciarSesion);

        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });

        btnRegistrar = new JButton("Crear Cuenta");
        btnRegistrar.setBounds(100, 110, 150, 25);
        panel.add(btnRegistrar);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaRegistroCliente(VentanaIniciarSesion.this);
                dispose();
            }
        });
    }

    private void iniciarSesion() {
        String correo = txtCorreo.getText().trim();
        String contrasenia = new String(txtContrasenia.getPassword()).trim();

        // Verificar las credenciales
        Cliente cliente = new Cliente(); // Crear una instancia del cliente
        if (cliente.iniciarSesion(correo, contrasenia)) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
            this.dispose();

            // Abrir la ventana de reservas
            VentanaRegistrarReserva ventanaReserva = new VentanaRegistrarReserva(new Reserva()); // Ajusta según sea necesario
            ventanaReserva.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Correo o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new VentanaIniciarSesion();
    }
}
