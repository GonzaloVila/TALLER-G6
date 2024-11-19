package InterfacesGraficas;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que representa la ventana donde los empleados pueden ver las reservas del día.
 *
 * Esta clase extiende JFrame y proporciona una interfaz gráfica que muestra las
 * reservas cargadas desde un archivo. Incluye un área de texto que muestra las
 * reservas para la fecha actual y maneja la carga de datos desde el archivo
 * correspondiente.
 */
public class VentanaReservasEmpleado extends JFrame {
    private JTextArea textAreaReservas;

    /**
     * Constructor de la clase VentanaReservasEmpleado.
     *
     * Inicializa la ventana, establece el título, el tamaño, y la disposición del
     * layout. Carga las reservas del día y las muestra en un área de texto.
     */
    public VentanaReservasEmpleado() {
        setTitle("Reservas del Día");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        textAreaReservas = new JTextArea();
        textAreaReservas.setEditable(false);
        cargarReservasDelDia();

        JScrollPane scrollPane = new JScrollPane(textAreaReservas);
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Carga las reservas del día desde el archivo y las muestra en el área de texto.
     *
     * Esta función obtiene la fecha actual y compara cada reserva cargada desde el
     * archivo con esta fecha. Si una reserva coincide con la fecha actual, se agrega
     * al área de texto. Si no hay reservas para el día, se muestra un mensaje
     * correspondiente.
     */
    private void cargarReservasDelDia() {
        List<String> reservasDelDia = cargarReservasDesdeArchivo();
        StringBuilder reservasTexto = new StringBuilder();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = sdf.format(new Date());
        System.out.println("Fecha actual: " + fechaActual);

        for (String reserva : reservasDelDia) {
            System.out.println("Reserva cargada: " + reserva);
            if (!reserva.startsWith("Lista de Reservas") && !reserva.trim().isEmpty()) {
                String[] partes = reserva.split(",");
                if (partes.length > 2) {
                    String fechaReserva = partes[2].trim().split(": ")[1];
                    System.out.println("Fecha de reserva: " + fechaReserva);
                    if (fechaReserva.equals(fechaActual)) {
                        reservasTexto.append(reserva).append("\n");
                    }
                }
            }
        }


        textAreaReservas.setText(reservasTexto.toString());

        if (reservasTexto.length() == 0) {
            textAreaReservas.setText("No hay reservas para el día de hoy.");
        }
    }

    /**
     * Carga las reservas desde el archivo "reservas.txt".
     *
     * Este método lee las líneas del archivo y las agrega a una lista. En caso de
     * que ocurra un error al leer el archivo, se imprime el stack trace.
     *
     * @return Una lista de cadenas que representan las reservas cargadas desde el archivo.
     */
    private List<String> cargarReservasDesdeArchivo() {
        List<String> reservas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("reservas.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                reservas.add(line);
                System.out.println("Reserva cargada desde archivo: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reservas;
    }
}