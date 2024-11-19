package InterfacesGraficas;

import Excepciones.ReservaException;

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

/**
 * VentanaRegistrarReserva es una clase que representa la interfaz gráfica
 * para la gestión de reservas en un restaurante. Permite realizar, modificar,
 * cancelar reservas, filtrar mesas y crear eventos.
 */
public class VentanaRegistrarReserva extends JFrame {
    private JTextField nombreClienteField, emailClienteField, telefonoClienteField, mesaField, idReservaField;
    private JTextField capacidadField, comentarioField;
    private JTextField nombreClienteMalo, mesaClienteMalo, mesaClienteModifica, idReservaCancelada, idReservaModificada;
    private JSpinner fechaSpinner, horaSpinner, fechaSpinnerModificar, horaSpinnerModificar;
    private JButton realizarReservaButton, modificarReservaButton, cancelarReservaButton, filtrarMesasButton, realizarEventoButton;
    private JTextArea resultadoFiltradoArea;
    private Reserva reserva;

    /**
     * Constructor que inicializa la ventana de gestión de reservas.
     * Configura el título, tamaño, comportamiento de cierre y añade paneles para las distintas acciones.
     */
    public VentanaRegistrarReserva() {
        this.reserva = new Reserva();
        setTitle("Gestión de Reservas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel con pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Paneles para cada acción de reserva
        JPanel panelRealizarReserva = crearPanelRealizarReserva();
        tabbedPane.addTab("Realizar Reserva", panelRealizarReserva);

        JPanel panelModificarReserva = crearPanelModificarReserva();
        tabbedPane.addTab("Modificar Reserva", panelModificarReserva);

        JPanel panelCancelarReserva = crearPanelCancelarReserva();
        tabbedPane.addTab("Cancelar Reserva", panelCancelarReserva);

        JPanel panelFiltrarMesas = crearPanelFiltrarMesas();
        tabbedPane.addTab("Filtrar Mesas", panelFiltrarMesas);

        JPanel panelRealizarEvento = crearPanelRealizarEvento();
        tabbedPane.addTab("Crear Evento", panelRealizarEvento);

        // Panel para mapa del restaurante
        RestauranteMapa mapa = new RestauranteMapa();
        tabbedPane.addTab("Mapa del Restaurante", mapa);

        add(tabbedPane);
    }

    /**
     * Crea el panel para realizar una nueva reserva.
     * @return JPanel configurado para realizar reservas.
     */
    private JPanel crearPanelRealizarReserva() {
        JPanel panel = new JPanel(new GridLayout(8, 2));

        // Campos del formulario
        panel.add(new JLabel("Ingrese su nombre completo: "));
        nombreClienteField = new JTextField();
        panel.add(nombreClienteField);

        panel.add(new JLabel("Ingrese su dirección de correo electrónico: "));
        emailClienteField = new JTextField();
        panel.add(emailClienteField);

        panel.add(new JLabel("Ingrese su número de celular: "));
        telefonoClienteField = new JTextField();
        panel.add(telefonoClienteField);

        panel.add(new JLabel("Ingrese el número de mesa que reservará:"));
        mesaField = new JTextField();
        panel.add(mesaField);

        panel.add(new JLabel("Seleccione la fecha para la reserva: "));
        fechaSpinner = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(fechaSpinner, "yyyy-MM-dd");
        fechaSpinner.setEditor(dateEditor);
        panel.add(fechaSpinner);

        panel.add(new JLabel("Seleccione la hora de inicio para la reserva: "));
        horaSpinner= new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(horaSpinner, "HH:mm");
        horaSpinner.setEditor(timeEditor);
        panel.add(horaSpinner);

        panel.add(new JLabel("Ingrese un comentario sobre alergias (opcional): "));
        comentarioField = new JTextField();
        panel.add(comentarioField);

        // Botón para realizar la reserva
        realizarReservaButton = new JButton("Realizar Reserva");
        realizarReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    realizarReservaVentana();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al realizar la reserva: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(realizarReservaButton);

        return panel;
    }

    /**
     * Realiza la reserva utilizando los datos ingresados por el usuario.
     * Valida los campos antes de realizar la operación de reserva.
     * @throws ReservaException si ocurre un error durante el proceso de reserva.
     */
    public void realizarReservaVentana() throws ReservaException {
        String nombreCliente = nombreClienteField.getText().trim();
        String emailCliente = emailClienteField.getText().trim();
        String telefonoCliente = telefonoClienteField.getText().trim();
        String numeroMesaTexto = mesaField.getText().trim();
        String comentario = comentarioField.getText().trim();

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

        Estado estadoInicial = Estado.RESERVADA;

        Cliente cliente = new Cliente(nombreCliente, emailCliente, telefonoCliente);

        Mesa mesa = new Mesa(numeroMesa);

        reserva.realizarReserva(cliente, dia, horaInicio, mesa, comentario);

        JOptionPane.showMessageDialog(null, "Reserva realizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Crea el panel para realizar un nuevo evento.
     * @return JPanel configurado para realizar eventos.
     */
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

    /**
     * Realiza un evento con los detalles proporcionados.
     * @param nombreEvento Nombre del evento.
     * @param fechaEventoSpinner Selector de fecha del evento.
     * @param horaEventoSpinner Selector de hora del evento.
     */
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

    /**
     * Crea el panel para modificar una reserva existente.
     * @return JPanel configurado para modificar reservas.
     */
    private JPanel crearPanelModificarReserva() {
        JPanel panel = new JPanel(new GridLayout(6, 2));

        panel.add(new JLabel("Ingrese el ID de la reserva a modificar:"));
        idReservaModificada = new JTextField();
        panel.add(idReservaModificada);

        panel.add(new JLabel("Ingrese el número de mesa:"));
        mesaClienteModifica = new JTextField();
        panel.add(mesaClienteModifica);

        panel.add(new JLabel("Seleccione la fecha:"));
        fechaSpinnerModificar = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(fechaSpinnerModificar, "yyyy-MM-dd");
        fechaSpinnerModificar.setEditor(dateEditor);
        panel.add(fechaSpinnerModificar);

        panel.add(new JLabel("Seleccione la hora de inicio: "));
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

    /**
     * Modifica una reserva existente utilizando los datos ingresados.
     */
    public void modificarReservaVentana() {
        try {
            int idReservaModificad = Integer.parseInt(idReservaModificada.getText().trim());
            int numeroMesaModificada = Integer.parseInt(mesaClienteModifica.getText().trim());

            LocalDate nuevoDia = ((SpinnerDateModel) fechaSpinnerModificar.getModel()).getDate().toInstant()
                    .atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            LocalTime nuevaHora = ((SpinnerDateModel) horaSpinnerModificar.getModel()).getDate().toInstant()
                    .atZone(java.time.ZoneId.systemDefault()).toLocalTime();

            Mesa nuevaMesa = new Mesa(numeroMesaModificada);

            reserva.modificarReserva(idReservaModificad, nuevaMesa, nuevaHora, nuevoDia);
            JOptionPane.showMessageDialog(null, "Reserva modificada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error al modificar la reserva: " + e.getMessage());
        }
    }

    /**
     * Crea el panel para cancelar una reserva existente.
     * @return JPanel configurado para cancelar reservas.
     */
    private JPanel crearPanelCancelarReserva() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        panel.add(new JLabel("Ingrese el ID de la reserva a cancelar:"));
        idReservaCancelada = new JTextField();
        panel.add(idReservaCancelada);

        panel.add(new JLabel("Ingrese su nombre completo:"));
        nombreClienteMalo = new JTextField();
        panel.add(nombreClienteMalo);

        panel.add(new JLabel("Ingrese el número de mesa que tenía reservado: "));
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

    /**
     * Cancela una reserva existente utilizando los datos ingresados.
     */
    public void cancelarReservaVentana() {
        try {
            int idReservaCancel = Integer.parseInt(idReservaCancelada.getText().trim());
            String nombreClienteQueCancela = nombreClienteMalo.getText().trim();
            int numeroMesa = Integer.parseInt(mesaClienteMalo.getText().trim());

            Mesa mesa = new Mesa(numeroMesa);

            Cliente cliente = new Cliente(nombreClienteQueCancela, "email", "teléfono"); // Reemplazar con la lógica de búsqueda de cliente

            reserva.cancelarReserva(idReservaCancel, cliente, mesa);
            JOptionPane.showMessageDialog(null, "Reserva cancelada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            mostrarError("Error al cancelar la reserva");
            e.printStackTrace();
        }
    }

    /**
     * Crea el panel para filtrar mesas disponibles según la capacidad y ubicación.
     * @return JPanel configurado para filtrar mesas.
     */
    private JPanel crearPanelFiltrarMesas() {
        JPanel panel = new JPanel(new GridBagLayout()); // Uso de GridBagLayout para mayor control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: Etiqueta de capacidad
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Seleccione la capacidad que desea:"), gbc);

        // Fila 1: JComboBox de capacidad
        gbc.gridx = 1;
        JComboBox<Integer> capacidadComboBox = new JComboBox<>();
        capacidadComboBox.addItem(2);
        capacidadComboBox.addItem(4);
        capacidadComboBox.addItem(6);
        capacidadComboBox.addItem(8);
        panel.add(capacidadComboBox, gbc);

        // Fila 2: Etiqueta de ubicación
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Seleccione la ubicación que desea:"), gbc);

        // Fila 2: JComboBox de ubicación
        gbc.gridx = 1;
        JComboBox<Ubicacion> ubicacionComboBox = new JComboBox<>(Ubicacion.values());
        panel.add(ubicacionComboBox, gbc);

        // Fila 3: JTextArea con JScrollPane para mostrar los resultados
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Ocupará las dos columnas
        resultadoFiltradoArea = new JTextArea(8, 25);
        JScrollPane scrollPane = new JScrollPane(resultadoFiltradoArea);
        panel.add(scrollPane, gbc);

        // Fila 4: Botón de filtrar mesas
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Ocupará las dos columnas
        filtrarMesasButton = new JButton("Filtrar Mesas");
        filtrarMesasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarMesasVentana(ubicacionComboBox, capacidadComboBox);
            }
        });
        panel.add(filtrarMesasButton, gbc);

        return panel;
    }

    /**
     * Filtra las mesas disponibles según la capacidad y ubicación seleccionadas.
     * @param ubicacionComboBox JComboBox de ubicaciones.
     * @param capacidadComboBox JComboBox de capacidades.
     */
    public void filtrarMesasVentana(JComboBox<Ubicacion> ubicacionComboBox, JComboBox<Integer> capacidadComboBox) {
        try {
            Integer capacidadSeleccionada = (Integer)capacidadComboBox.getSelectedItem();
            Ubicacion ubicacionSeleccionada = (Ubicacion) ubicacionComboBox.getSelectedItem();

            ArrayList<Mesa> mesasFiltradas = reserva.filtrarMesa(capacidadSeleccionada, ubicacionSeleccionada);
            resultadoFiltradoArea.setText("");
            for (Mesa mesa : mesasFiltradas) {
                resultadoFiltradoArea.append("Número de mesa: " + mesa.getNumMesa() + "\n");
            }
        } catch (Exception e) {
            mostrarError("Error al filtrar mesas");
            e.printStackTrace();
        }
    }

    /**
     * Muestra un mensaje de error en un cuadro de diálogo.
     * @param mensaje El mensaje de error a mostrar.
     */
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Valida que el nombre proporcionado sea correcto.
     * @param nombre El nombre a validar.
     * @return true si el nombre es válido, false en caso contrario.
     */
    private boolean validarNombre(String nombre) {
        return nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+");
    }

    /**
     * Valida que el número de teléfono proporcionado sea correcto.
     * @param telefono El teléfono a validar.
     * @return true si el teléfono es válido, false en caso contrario.
     */
    private boolean validarTelefono(String telefono) {
        return telefono.matches("\\d{1,10}");
    }

    /**
     * Valida que el correo electrónico proporcionado sea correcto.
     * @param email El correo electrónico a validar.
     * @return true si el email es válido, false en caso contrario.
     */
    private boolean validarEmail(String email) {
        return email.contains("@");
    }

    /**
     * Valida que el número de mesa proporcionado sea correcto.
     * @param numeroMesaTexto El texto que representa el número de mesa.
     * @return true si el número de mesa es válido, false en caso contrario.
     */
    private boolean validarNumeroMesa(String numeroMesaTexto) {
        try {
            int numero = Integer.parseInt(numeroMesaTexto);
            return numero > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
