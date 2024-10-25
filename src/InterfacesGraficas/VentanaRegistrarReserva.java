package InterfacesGraficas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import LogicaNegocio.*;

public class VentanaRegistrarReserva extends JFrame {
    private JTextField nombreClienteField, emailClienteField, telefonoClienteField, mesaField, idReservaField;
    private JTextField capacidadField, comentarioField;
    private JTextField nombreClienteMalo, mesaClienteMalo, mesaClienteModifica, idReservaCancelada;
    private JSpinner fechaSpinner, horaSpinner, fechaSpinnerModificar, horaSpinnerModificar;
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
        JPanel panel = new JPanel(new GridLayout(8, 2));

        // Campos del formulario
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
        fechaSpinner = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(fechaSpinner, "yyyy-MM-dd");
        fechaSpinner.setEditor(dateEditor);
        panel.add(fechaSpinner);

        panel.add(new JLabel("   Hora de Inicio:"));
        horaSpinner= new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
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
                    realizarReservaVentana();
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al realizar la reserva: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(realizarReservaButton);

        return panel;
    }

    // Método para realizar la reserva
    public void realizarReservaVentana() {
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
            mostrarError("El número de la mesa debe ser un número entero válido");
            return;
        }

        // Realizar la reserva
        int numeroMesa = Integer.parseInt(numeroMesaTexto);
        LocalDate dia = ((SpinnerDateModel) fechaSpinner.getModel()).getDate().toInstant()
                .atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalTime horaInicio = ((java.util.Date) horaSpinner.getValue()).toInstant()
                .atZone(java.time.ZoneId.systemDefault()).toLocalTime();

        Estado estadoInicial = Estado.EN_CURSO;

        Cliente cliente = new Cliente(nombreCliente, emailCliente, telefonoCliente);

        Mesa mesa = new Mesa(numeroMesa);
        //Integer idReserva, LocalDate fecha, LocalTime horaInicio, String comentarios, LocalTime horaFinal, Estado estado, Cliente cliente, Mesa mesa

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
        fechaSpinnerModificar = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(fechaSpinnerModificar, "yyyy-MM-dd");
        fechaSpinnerModificar.setEditor(dateEditor);
        panel.add(fechaSpinnerModificar);

        panel.add(new JLabel("Nueva Hora:"));
        horaSpinnerModificar = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(horaSpinnerModificar, "HH:mm");
        horaSpinnerModificar.setEditor(timeEditor);
        panel.add(horaSpinnerModificar);

        modificarReservaButton = new JButton("Modificar Reserva");
        modificarReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarReservaVentana();
            }
        });
        panel.add(modificarReservaButton);

        return panel;
    }

    // Método para modificar la reserva
    public void modificarReservaVentana() {
        try {
            Integer idReserva = Integer.parseInt(idReservaField.getText());
            int numeroMesaModificada = Integer.parseInt(mesaClienteModifica.getText());

            LocalDate nuevoDia = ((SpinnerDateModel) fechaSpinnerModificar.getModel()).getDate().toInstant()
                    .atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            LocalTime nuevaHora = ((SpinnerDateModel) horaSpinnerModificar.getModel()).getDate().toInstant()
                    .atZone(java.time.ZoneId.systemDefault()).toLocalTime();

            Mesa nuevaMesa = new Mesa(numeroMesaModificada);

            reserva.modificarReserva(idReserva, nuevaMesa, nuevaHora, nuevoDia);
            JOptionPane.showMessageDialog(null, "Reserva modificada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            mostrarError("Error al modificar la reserva");
        }
    }

    // Panel para cancelar la reserva
    private JPanel crearPanelCancelarReserva() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        panel.add(new JLabel("ID Reserva:"));
        idReservaCancelada = new JTextField();
        panel.add(idReservaCancelada);

        panel.add(new JLabel("Nombre Cliente:"));
        nombreClienteMalo = new JTextField();
        panel.add(nombreClienteMalo);

        panel.add(new JLabel("Número de Mesa:"));
        mesaClienteMalo = new JTextField();
        panel.add(mesaClienteMalo);

        cancelarReservaButton = new JButton("Cancelar Reserva");
        cancelarReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarReservaVentana();
            }
        });
        panel.add(cancelarReservaButton);

        return panel;
    }

    // Método para cancelar la reserva
    public void cancelarReservaVentana() {
        try {
            int idReserva = Integer.parseInt(idReservaCancelada.getText());
            String nombreCliente = nombreClienteMalo.getText();
            int numeroMesa = Integer.parseInt(mesaClienteMalo.getText());

            Mesa mesa = new Mesa(numeroMesa);

            Cliente cliente = new Cliente(nombreCliente, "email", "teléfono"); // Reemplazar con la lógica de búsqueda de cliente

            reserva.cancelarReserva(idReserva, cliente, mesa);
            JOptionPane.showMessageDialog(null, "Reserva cancelada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            mostrarError("Error al cancelar la reserva");
        }
    }

    // Panel para filtrar mesas
    private JPanel crearPanelFiltrarMesas() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        panel.add(new JLabel("Capacidad:"));
        capacidadField = new JTextField();
        panel.add(capacidadField);

        panel.add(new JLabel("Ubicación:"));

        // JComboBox para seleccionar la ubicación
        JComboBox<String> ubicacionComboBox = new JComboBox<>();
        ubicacionComboBox.addItem("SALON PRINCIPAL");
        ubicacionComboBox.addItem("SEGUNDO PISO");
        ubicacionComboBox.addItem("TERRAZA");
        panel.add(ubicacionComboBox);

        resultadoFiltradoArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(resultadoFiltradoArea);
        panel.add(scrollPane);

        filtrarMesasButton = new JButton("Filtrar Mesas");
        filtrarMesasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarMesasVentana(ubicacionComboBox);
            }
        });
        panel.add(filtrarMesasButton);

        return panel;
    }

    // Método para filtrar mesas modificado
    public void filtrarMesasVentana(JComboBox<String> ubicacionComboBox) {
        try {
            Integer capacidad = Integer.parseInt(capacidadField.getText());
            String ubicacionSeleccionada = (String) ubicacionComboBox.getSelectedItem();

            Ubicacion ubicacion = Ubicacion.valueOf(ubicacionSeleccionada); // Convierte el valor seleccionado en Ubicacion enum

            ArrayList<Mesa> mesasFiltradas = reserva.filtrarMesa(capacidad, ubicacion);
            resultadoFiltradoArea.setText("");
            for (Mesa mesa : mesasFiltradas) {
                resultadoFiltradoArea.append(mesa.toString() + "\n");
            }
        } catch (Exception e) {
            mostrarError("Error al filtrar mesas");
        }
    }



    // Método para mostrar errores
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Método para validar nombre
    private boolean validarNombre(String nombre) {
        return nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+");
    }


    // Método para validar número de teléfono
    private boolean validarTelefono(String telefono) {
        return telefono.matches("\\d{1,10}");
    }


    // Método para validar email
    private boolean validarEmail(String email) {
        return email.contains("@");
    }

    // Método para validar número de mesa
    private boolean validarNumeroMesa(String numeroMesaTexto) {
        try {
            int numero = Integer.parseInt(numeroMesaTexto);
            return numero > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
