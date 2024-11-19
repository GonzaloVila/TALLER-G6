package InterfacesGraficas;

import LogicaNegocio.Administrador;
import LogicaNegocio.Calendario;
import LogicaNegocio.Cliente;
import LogicaNegocio.Reserva;
import LogicaNegocio.Empleado;
import InterfacesGraficas.VentanaRegistrarEmpleado;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * Clase que representa la ventana de inicio de sesión del sistema.
 *
 * Esta clase permite a los clientes iniciar sesión en el sistema utilizando su
 * correo electrónico y contraseña. También proporciona la opción de registrar una
 * nueva cuenta si el cliente no tiene una.
 */
public class VentanaIniciarSesion extends JFrame {
    private JTextField txtCorreo;
    private JPasswordField txtContrasenia;
    private JButton btnIniciarSesion;
    private JButton btnRegistrar;
    private ArrayList<Cliente> listaClientes;

    /**
     * Constructor de la clase VentanaIniciarSesion.
     *
     * Este constructor inicializa la ventana de inicio de sesión, carga la lista
     * de clientes desde un archivo y configura los componentes de la interfaz
     * gráfica. Al finalizar, muestra la ventana al usuario.
     */
    public VentanaIniciarSesion() {
        // Cargar clientes desde el archivo
        listaClientes = Cliente.cargarClientesDesdeArchivo();
        Cliente.setListaClientes(listaClientes);
        setTitle("Iniciar Sesión");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    /**
     * Método auxiliar para colocar los componentes de la interfaz gráfica en el panel.
     *
     * Este método configura el diseño del panel de la ventana de inicio de sesión
     * al establecer los componentes necesarios, incluidos los campos de texto para
     * el correo y la contraseña, y los botones para iniciar sesión y crear una cuenta.
     *
     * @param panel El panel donde se agregarán los componentes.
     */
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


    /**
     * Método que gestiona el inicio de sesión de los usuarios en la aplicación.
     *
     * Este método verifica las credenciales ingresadas por el usuario (correo y
     * contraseña) y determina si el usuario es un administrador, un empleado o un cliente.
     * Según el tipo de usuario, abre la ventana correspondiente y cierra la ventana actual.
     * Si las credenciales son incorrectas, se muestra un mensaje de error.
     */
    private void iniciarSesion() {
        String correo = txtCorreo.getText().trim();
        String contrasenia = new String(txtContrasenia.getPassword()).trim();

        // Verificar las credenciales
        Cliente cliente = new Cliente(); // Crear una instancia del cliente

        if(cliente.esAdministrador(correo, contrasenia)){
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso como administrador.");
            this.dispose();
            VentanaRegistrarEmpleado ventana = new VentanaRegistrarEmpleado(new Administrador(new Calendario()));
            ventana.setVisible(true);
        }else if (cliente.iniciarSesion(correo, contrasenia)) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
            for (Cliente c : listaClientes) {
                if (c.getCorreo().equals(correo)) {
                    cliente = c;
                }
            }
            this.dispose();
            //Abrir la ventana de cliente
            VentanaCliente ventanaCliente = new VentanaCliente(cliente);
            ventanaCliente.setVisible(true);
        }else if (Cliente.esEmpleado(correo, contrasenia)) {
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso como empleado.");
                this.dispose();
                VentanaReservasEmpleado ventanaReservasEmpleado = new VentanaReservasEmpleado();
                ventanaReservasEmpleado.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Correo o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}