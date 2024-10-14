package InterfacesGraficas;

import LogicaNegocio.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaIniciarSesion extends JFrame {
    private JTextField txtCorreo;
    private JPasswordField txtContrasenia;
    private JButton btnIniciarSesion;

    public VentanaIniciarSesion(ArrayList<Cliente> listaClientes) {
        setTitle("Iniciar Sesión");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        // Acción al hacer clic en el botón
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = txtCorreo.getText();
                String contrasenia = new String(txtContrasenia.getPassword());

                // Aquí debes crear una instancia de Cliente, o si tienes una lista de clientes, buscar el cliente correspondiente.
                // Para simplificar, crearé un cliente de prueba.
                Cliente cliente = new Cliente("Nombre", "correo@example.com", "123456789", "password");

                if (cliente.iniciarSesion(correo, contrasenia)) {
                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
                } else {
                    JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
