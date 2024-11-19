package InterfacesGraficas;

import LogicaNegocio.Cliente;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * VentanaActualizarInfo es una clase que representa una interfaz gráfica
 * para que un cliente pueda actualizar su información personal.
 * Extiende la clase JFrame para crear una ventana personalizada.
 */
public class VentanaActualizarInfo extends JFrame {
    private Cliente cliente; // Cliente cuya información se va a actualizar
    private JTextField txtNuevoNombre; // Campo para ingresar el nuevo nombre
    private JTextField txtNuevoNumero; // Campo para ingresar el nuevo número
    private JPasswordField txtNuevaContrasenia; // Campo para ingresar la nueva contraseña
    private JButton btnActualizar; // Botón para confirmar la actualización

    /**
     * Constructor de la clase VentanaActualizarInfo.
     *
     * @param cliente el cliente cuya información se va a actualizar
     */
    public VentanaActualizarInfo(Cliente cliente) {
        this.cliente = cliente; // Inicializa con el cliente actual
        setTitle("Actualizar Información");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    /**
     * Configura los componentes de la ventana para la actualización de información.
     *
     * @param panel el panel donde se colocan los componentes
     */
    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel labelNuevoNombre = new JLabel("Nuevo Nombre:");
        labelNuevoNombre.setBounds(10, 20, 100, 25);
        panel.add(labelNuevoNombre);

        txtNuevoNombre = new JTextField(20);
        txtNuevoNombre.setBounds(120, 20, 150, 25);
        panel.add(txtNuevoNombre);

        JLabel labelNuevoNumero = new JLabel("Nuevo Número:");
        labelNuevoNumero.setBounds(10, 60, 100, 25);
        panel.add(labelNuevoNumero);

        txtNuevoNumero = new JTextField(20);
        txtNuevoNumero.setBounds(120, 60, 150, 25);
        panel.add(txtNuevoNumero);

        JLabel labelNuevaContrasenia = new JLabel("Nueva Contraseña:");
        labelNuevaContrasenia.setBounds(10, 100, 150, 25);
        panel.add(labelNuevaContrasenia);

        txtNuevaContrasenia = new JPasswordField(20);
        txtNuevaContrasenia.setBounds(160, 100, 110, 25);
        panel.add(txtNuevaContrasenia);

        btnActualizar = new JButton("Actualizar Info");
        btnActualizar.setBounds(80, 140, 150, 25);
        panel.add(btnActualizar);

        // Agrega un listener al botón de actualizar
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener nuevos datos del cliente desde los campos de texto
                String nuevoNombre = txtNuevoNombre.getText().trim();
                String nuevoNumero = txtNuevoNumero.getText().trim();
                String nuevaContrasenia = new String(txtNuevaContrasenia.getPassword());

                // Llamar al método para actualizar la información
                cliente.actualizarInfo(cliente, nuevoNombre, nuevoNumero, nuevaContrasenia);

                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(null, "Información actualizada exitosamente");

                // Cerrar la ventana después de actualizar
                dispose();
            }
        });
    }
}