package InterfacesGraficas;

import LogicaNegocio.Cliente;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistroCliente extends JFrame {
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JTextField txtNumero;
    private JPasswordField txtContrasenia;
    private JButton btnRegistrar;

    public VentanaRegistroCliente() {
        setTitle("Registro de LogicaNegocio.Cliente");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

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

        // Acción al hacer clic en el botón
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String correo = txtCorreo.getText();
                String numero = txtNumero.getText();
                String contrasenia = new String(txtContrasenia.getPassword());

                try {
                    Cliente cliente = new Cliente(nombre, correo, numero, contrasenia);
                    cliente.registrarCliente(cliente);
                    JOptionPane.showMessageDialog(null, "LogicaNegocio.Cliente registrado exitosamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        new VentanaRegistroCliente();
    }
}
