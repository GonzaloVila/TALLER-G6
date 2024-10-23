package InterfacesGraficas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import LogicaNegocio.Reserva;
import LogicaNegocio.Cliente;
import LogicaNegocio.Mesa;
import LogicaNegocio.Ubicacion;

public class VentanaRegistrarReserva extends JFrame {
    private JTextField nombreClienteField, emailClienteField, telefonoClienteField, mesaField, idReservaField;
    private JTextField capacidadField, ubicacionField, comentarioField;
    private JTextField nombreClienteMalo, mesaClienteMalo, mesaClienteModifica, idReservaCancelada;
    private JSpinner fechaSpinner, horaSpinner;
    private JButton realizarReservaButton, modificarReservaButton, cancelarReservaButton, filtrarMesasButton, realizarEventoButton;
    private JTextArea resultadoFiltradoArea;
    private Reserva reserva;

    public VentanaRegistrarReserva(Reserva reserva) {
        this.reserva = reserva;
        setTitle("Gestión de Reservas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel con pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Panel para realizar reserva
        JPanel panelRealizarReserva = crearPanelRealizarReserva();
        tabbedPane.addTab("Realizar Reserva", panelRealizarReserva);

        // Panel para realizar evento
        JPanel panelRealizarEvento = crearPanelRealizarEvento();
        tabbedPane.addTab("Realizar Evento", panelRealizarEvento);

        // Panel para modificar reserva
        JPanel panelModificarReserva = crearPanelModificarReserva();
        tabbedPane.addTab("Modificar Reserva", panelModificarReserva);

        // Panel para cancelar reserva
        JPanel panelCancelarReserva = crearPanelCancelarReserva();
        tabbedPane.addTab("Cancelar Reserva", panelCancelarReserva);

        // Panel para filtrar mesas
        JPanel panelFiltrarMesas = crearPanelFiltrarMesas();
        tabbedPane.addTab("Filtrar Mesas", panelFiltrarMesas);

        add(tabbedPane);
    }

    // Crear panel para realizar la reserva
    private JPanel crearPanelRealizarReserva() {
        JPanel panel = new JPanel(new GridLayout(9, 2)); // Aumentar a 9 filas para comentarios

        panel.add(new JLabel("   Nombre:"));
        nombreClienteField = new JTextField();
        panel.add(nombreClienteField);

        panel.add(new JLabel("   Email:"));
        emailClienteField = new JTextField();
        panel.add(emailClienteField);

        panel.add(new JLabel("   Teléfono:"));
        telefonoClienteField = new JTextField();
        panel.add(telefonoClienteField);

        panel.add(new JLabel("   Número de Mesa:"));
        mesaField = new JTextField();
        panel.add(mesaField);

        panel.add(new JLabel("   Fecha:"));
        fechaSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(fechaSpinner, "yyyy-MM-dd");
        fechaSpinner.setEditor(dateEditor);
        panel.add(fechaSpinner);

        panel.add(new JLabel("   Hora de Inicio:"));
        horaSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(horaSpinner, "HH:mm");
        horaSpinner.setEditor(timeEditor);
        panel.add(horaSpinner);

        panel.add(new JLabel("   Comentarios:"));
        comentarioField = new JTextField();
        panel.add(comentarioField);

        // Botón para realizar la reserva
        realizarReservaButton = new JButton("Realizar Reserva");
        realizarReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    realizarReserva();
                } catch (Exception ex) {
                    mostrarError("Error al realizar la reserva: " + ex.getMessage());
                }
            }
        });
        panel.add(realizarReservaButton);

        return panel;
    }

    // Método para realizar la reserva
    public void realizarReserva() {
        String nombreCliente = nombreClienteField.getText();
        String emailCliente = emailClienteField.getText();
        String telefonoCliente = telefonoClienteField.getText();
        String numeroMesaTexto = mesaField.getText();
        String comentario = comentarioField.getText();

        // Validaciones
        if (nombreCliente.isEmpty()) {
            mostrarError("El campo de nombre no puede estar vacío");
            return;
        }
        if (!validarNombre(nombreCliente)) {
            mostrarError("El nombre solo debe contener letras y espacios");
            return;
        }
        if (emailCliente.isEmpty()) {
            mostrarError("El campo de email no puede estar vacío");
            return;
        }
        if (!validarEmail(emailCliente)) {
            mostrarError("Correo electrónico no válido");
            return;
        }
        if (telefonoCliente.isEmpty()) {
            mostrarError("El campo de teléfono no puede estar vacío");
            return;
        }
        if (!validarTelefono(telefonoCliente)) {
            mostrarError("El teléfono solo debe contener números");
            return;
        }
        if (numeroMesaTexto.isEmpty() || !validarNumeroMesa(numeroMesaTexto)) {
            mostrarError("Por favor ingrese un número de mesa válido");
            return;
        }

        // Realizar la reserva
        int numeroMesa = Integer.parseInt(numeroMesaTexto);
        LocalDate dia = ((SpinnerDateModel) fechaSpinner.getModel()).getDate().toInstant()
                .atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalTime horaInicio = ((SpinnerDateModel) horaSpinner.getModel()).getDate().toInstant()
                .atZone(java.time.ZoneId.systemDefault()).toLocalTime();

        Cliente cliente = new Cliente(nombreCliente, emailCliente, telefonoCliente);
        Mesa mesa = new Mesa(numeroMesa, Ubicacion.TERRAZA, 5);

        reserva.realizarReserva(cliente, dia, horaInicio, mesa, comentario);

        JOptionPane.showMessageDialog(null, "Reserva realizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    // Crear panel para realizar el evento
    private JPanel crearPanelRealizarEvento() {
        JPanel panel = new JPanel(new GridLayout(6, 2));

        panel.add(new JLabel("Nombre Evento:"));
        JTextField nombreEventoField = new JTextField();
        panel.add(nombreEventoField);

        panel.add(new JLabel("Fecha:"));
        JSpinner fechaEventoSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(fechaEventoSpinner, "yyyy-MM-dd");
        fechaEventoSpinner.setEditor(dateEditor);
        panel.add(fechaEventoSpinner);

        panel.add(new JLabel("Hora:"));
        JSpinner horaEventoSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(horaEventoSpinner, "HH:mm");
        horaEventoSpinner.setEditor(timeEditor);
        panel.add(horaEventoSpinner);

        realizarEventoButton = new JButton("Realizar Evento");
        realizarEventoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarEvento(nombreEventoField.getText(), fechaEventoSpinner, horaEventoSpinner);
            }
        });
        panel.add(realizarEventoButton);

        return panel;
    }

    // Método para realizar el evento
    public void realizarEvento(String nombreEvento, JSpinner fechaEventoSpinner, JSpinner horaEventoSpinner) {
        if (nombreEvento.isEmpty()) {
            mostrarError("El campo de nombre del evento no puede estar vacío");
            return;
        }

        LocalDate fecha = ((SpinnerDateModel) fechaEventoSpinner.getModel()).getDate().toInstant()
                .atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalTime hora = ((SpinnerDateModel) horaEventoSpinner.getModel()).getDate().toInstant()
                .atZone(java.time.ZoneId.systemDefault()).toLocalTime();

        // Aquí se podría añadir la lógica para guardar el evento
        // Por ejemplo:
        // Evento evento = new Evento(nombreEvento, fecha, hora);
        // reserva.agregarEvento(evento);

        JOptionPane.showMessageDialog(null, "Evento realizado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    // Panel para modificar la reserva
    private JPanel crearPanelModificarReserva() {
        JPanel panel = new JPanel(new GridLayout(6, 2));

        panel.add(new JLabel("ID Reserva:"));
        idReservaField = new JTextField();
        panel.add(idReservaField);

        panel.add(new JLabel("Nueva Mesa:"));
        mesaClienteModifica = new JTextField();
        panel.add(mesaClienteModifica);

        panel.add(new JLabel("Nueva Fecha:"));
        fechaSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(fechaSpinner, "yyyy-MM-dd");
        fechaSpinner.setEditor(dateEditor);
        panel.add(fechaSpinner);

        panel.add(new JLabel("Nueva Hora:"));
        horaSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(horaSpinner, "HH:mm");
        horaSpinner.setEditor(timeEditor);
        panel.add(horaSpinner);

        modificarReservaButton = new JButton("Modificar Reserva");
        modificarReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarReserva();
            }
        });
        panel.add(modificarReservaButton);

        return panel;
    }

    // Método para modificar la reserva
    public void modificarReserva() {
        try {
            int idReserva = Integer.parseInt(idReservaField.getText());
            int numeroMesa = Integer.parseInt(mesaClienteModifica.getText());

            LocalDate nuevoDia = ((SpinnerDateModel) fechaSpinner.getModel()).getDate().toInstant()
                    .atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            LocalTime nuevaHora = ((SpinnerDateModel) horaSpinner.getModel()).getDate().toInstant()
                    .atZone(java.time.ZoneId.systemDefault()).toLocalTime();

            // Crear una nueva instancia de Mesa
            Mesa nuevaMesa = new Mesa(numeroMesa, Ubicacion.TERRAZA, 5); // Asegúrate de que Ubicacion y la capacidad sean correctas

            // Llamar al método modificarReserva de la clase Reserva
            reserva.modificarReserva(idReserva, nuevaMesa, nuevaHora, nuevoDia);
            JOptionPane.showMessageDialog(null, "Reserva modificada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            mostrarError("Error al modificar la reserva: " + e.getMessage());
        }
    }


    // Panel para cancelar reserva
    private JPanel crearPanelCancelarReserva() {
        JPanel panel = new JPanel(new GridLayout(5, 2)); // Aumentar filas si agregas más campos

        panel.add(new JLabel("ID Reserva:"));
        idReservaCancelada = new JTextField();
        panel.add(idReservaCancelada);

        panel.add(new JLabel("Nombre Cliente:"));
        nombreClienteMalo = new JTextField();
        panel.add(nombreClienteMalo);

        panel.add(new JLabel("Número de Mesa:"));
        mesaClienteMalo = new JTextField();
        panel.add(mesaClienteMalo);

        // Espacio vacío para mejorar la distribución
        panel.add(new JLabel("")); // Para mantener el diseño

        // Botón para cancelar la reserva
        cancelarReservaButton = new JButton("Cancelar Reserva");
        cancelarReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarReserva();
            }
        });
        panel.add(cancelarReservaButton);

        return panel;
    }

    // Método para cancelar la reserva
    // Método para cancelar la reserva
    public void cancelarReserva() {
        try {
            Integer idReservaCancel = Integer.parseInt(idReservaCancelada.getText());
            String nombreClienteQueCancela = nombreClienteMalo.getText();
            int numeroMesaClienteMalo = Integer.parseInt(mesaClienteMalo.getText());

            // Crea el cliente y mesa si son necesarios en la lógica de tu método
            Cliente cliente = new Cliente(nombreClienteQueCancela, "", "");
            Mesa mesa = new Mesa(numeroMesaClienteMalo, Ubicacion.TERRAZA, 5);

            // Ajusta aquí la llamada a cancelarReserva según la firma del método
            reserva.cancelarReserva(idReservaCancel, cliente, mesa); // Asegúrate de que esto sea correcto según tu método
            JOptionPane.showMessageDialog(null, "Reserva cancelada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            mostrarError("Error al cancelar la reserva: " + e.getMessage());
        }
    }



    // Panel para filtrar mesas
    // Panel para filtrar mesas
    // Panel para filtrar mesas
    // Panel para filtrar mesas
    // Panel para filtrar mesas
    private JPanel crearPanelFiltrarMesas() {
        JPanel panel = new JPanel(new GridLayout(3, 2)); // Ajustamos el GridLayout a 3 filas y 2 columnas

        // Campo de capacidad
        panel.add(new JLabel("Capacidad:"));
        capacidadField = new JTextField();
        panel.add(capacidadField); // Mantener el campo de capacidad

        // Etiqueta y campo de ubicación
        panel.add(new JLabel("Ubicación:"));
        ubicacionField = new JTextField();
        panel.add(ubicacionField); // Mantener el campo de ubicación

        // Botón para filtrar mesas
        filtrarMesasButton = new JButton("Filtrar Mesas");
        filtrarMesasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarMesas();
            }
        });
        panel.add(filtrarMesasButton); // Botón para filtrar mesas

        return panel;
    }

    // Método para filtrar mesas
    public void filtrarMesas() {
        try {
            Integer capacidad = Integer.parseInt(capacidadField.getText());
            String ubicacion = ubicacionField.getText();

            ArrayList<Mesa> mesasFiltradas = reserva.filtrarMesa(capacidad, null);
            resultadoFiltradoArea.setText("");
            for (Mesa mesa : mesasFiltradas) {
                resultadoFiltradoArea.append(mesa.toString() + "\n");
            }
        } catch (Exception e) {
            mostrarError("Error al filtrar mesas");
        }
    }

    // Métodos auxiliares
    private boolean validarNombre(String nombre) {
        nombre = nombre.trim(); // Elimina espacios
        return nombre.matches("[\\p{L}\\s]+");
    }

    private boolean validarEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private boolean validarTelefono(String telefono) {
        return telefono.matches("\\d+");
    }

    private boolean validarNumeroMesa(String numeroMesaTexto) {
        try {
            Integer.parseInt(numeroMesaTexto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
