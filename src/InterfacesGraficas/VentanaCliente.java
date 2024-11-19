package InterfacesGraficas;

import LogicaNegocio.Cliente;
import LogicaNegocio.Reserva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * VentanaCliente es una clase que representa la interfaz gráfica para el menú de un cliente.
 * Permite al cliente realizar reservas, consultar su historial de reservas y actualizar su información personal.
 */
public class VentanaCliente extends JFrame {
    // Instancia del cliente actual
    private Cliente clienteActual;

    /**
     * Constructor de la clase VentanaCliente.
     *
     * @param clienteActual El cliente que está utilizando la ventana.
     */
    public VentanaCliente(Cliente clienteActual) {
        this.clienteActual = clienteActual; // Inicializa el cliente actual

        // Configuración de la ventana
        setTitle("Menú de Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 3));

        // Botón para realizar una nueva reserva
        JButton btnRealizarReserva = new JButton("Realizar reserva");
        btnRealizarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear una nueva instancia de Reserva
                Reserva nuevaReserva = new Reserva();
                nuevaReserva.setCliente(clienteActual);

                new VentanaRegistrarReserva().setVisible(true);
            }
        });

        // Botón para consultar el historial de reservas
        JButton btnConsultarHistorial = new JButton("Consultar historial de reservas");
        JTextArea areaHistorial = new JTextArea(10, 30);
        areaHistorial.setEditable(false); // El área de texto no es editable

        btnConsultarHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica si clienteActual es nulo
                if (clienteActual == null) {
                    System.err.println("Error: clienteActual es nulo.");
                    areaHistorial.setText("Error: clienteActual es nulo.");
                    return; // Salir del método si es nulo
                }

                // Obtener el nombre del cliente
                String nombre = clienteActual.getNombre();
                System.out.println("Nombre del cliente: " + nombre); // Verifica el nombre

                // Verificar si el nombre es nulo o vacío
                if (nombre == null || nombre.trim().isEmpty()) {
                    System.err.println("Error: El nombre del cliente es nulo o vacío.");
                    areaHistorial.setText("Error: El nombre del cliente es nulo o vacío.");
                    return; // Salir del método si el nombre es nulo o vacío
                }

                // Llamar al método para consultar historial de reservas
                ArrayList<Reserva> historial = clienteActual.consultarHistorialReservas();

                // Limpiar el área de texto y mostrar el historial
                areaHistorial.setText(""); // Limpiar el área de texto
                if (historial.isEmpty()) {
                    areaHistorial.append("No se encontraron reservas para el cliente " + nombre + ".\n");
                } else {
                    for (Reserva reserva : historial) {
                        areaHistorial.append(reserva.toString() + "\n");
                    }
                }
            }
        });

        // Botón para actualizar la información del cliente
        JButton btnActualizarInformacion = new JButton("Actualizar información");
        btnActualizarInformacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear la nueva ventana de actualización
                VentanaActualizarInfo ventanaActualizarInfo = new VentanaActualizarInfo(clienteActual);

                // Hacer visible la nueva ventana
                ventanaActualizarInfo.setVisible(true);

                // Cerrar la ventana actual
                VentanaCliente.this.dispose();
            }
        });

        // Añadir los botones al panel de botones
        panelBotones.add(btnRealizarReserva);
        panelBotones.add(btnConsultarHistorial);
        panelBotones.add(btnActualizarInformacion);

        // Añadir los componentes al JFrame
        add(panelBotones, BorderLayout.NORTH);
        add(new JScrollPane(areaHistorial), BorderLayout.CENTER);
    }
}
