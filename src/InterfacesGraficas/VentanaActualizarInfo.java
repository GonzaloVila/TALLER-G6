package InterfacesGraficas;

import LogicaNegocio.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaActualizarInfo extends JFrame {
    private JTextField txtCorreo;
    private JPasswordField txtContrasenia;
    private JTextField txtNuevoNombre;
    private JTextField txtNuevoNumero;
    private JPasswordField txtNuevaContrasenia;
    private JButton btnActualizar;
    private ArrayList<Cliente> listaClientes;

    public VentanaActualizarInfo(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes; // Inicializar la lista de clientes
        setTitle("Actualizar Información");
        setSize(300, 250);
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

        JLabel labelNuevoNombre = new JLabel("Nuevo Nombre:");
        labelNuevoNombre.setBounds(10, 80, 100, 25);
        panel.add(labelNuevoNombre);

        txtNuevoNombre = new JTextField(20);
        txtNuevoNombre.setBounds(120, 80, 145, 25);
        panel.add(txtNuevoNombre);

        JLabel labelNuevoNumero = new JLabel("Nuevo Número:");
        labelNuevoNumero.setBounds(10, 110, 100, 25);
        panel.add(labelNuevoNumero);

        txtNuevoNumero = new JTextField(20);
        txtNuevoNumero.setBounds(120, 110, 145, 25);
        panel.add(txtNuevoNumero);

        JLabel labelNuevaContrasenia = new JLabel("Nueva Contraseña:");
        labelNuevaContrasenia.setBounds(10, 140, 120, 25);
        panel.add(labelNuevaContrasenia);

        txtNuevaContrasenia = new JPasswordField(20);
        txtNuevaContrasenia.setBounds(140, 140, 125, 25);
        panel.add(txtNuevaContrasenia);

        btnActualizar = new JButton("Actualizar Info");
        btnActualizar.setBounds(100, 180, 150, 25);
        panel.add(btnActualizar);

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = txtCorreo.getText();
                String contrasenia = new String(txtContrasenia.getPassword());
                String nuevoNombre = txtNuevoNombre.getText();
                String nuevoNumero = txtNuevoNumero.getText();
                String nuevaContrasenia = new String(txtNuevaContrasenia.getPassword());

                // Intentar encontrar al cliente
                for (Cliente cliente : listaClientes) {
                    if (cliente.getCorreo().equals(correo) && cliente.getContrasenia().equals(contrasenia)) {
                        // Llamar al método para actualizar la información
                        cliente.actualizarInfo(nuevoNombre, nuevoNumero, nuevaContrasenia);
                        JOptionPane.showMessageDialog(null, "Información actualizada exitosamente");
                        return; // Salir del método después de la actualización
                    }
                }
                // Si no se encuentra el cliente, lanzar un error
                JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
