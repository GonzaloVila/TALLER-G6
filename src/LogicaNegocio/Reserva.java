package LogicaNegocio;

import Excepciones.ReservaException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.File;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;

/**
 * Clase que representa una reserva en el sistema de gestión de reservas de un restaurante.
 *
 * Esta clase permite crear reservas, gestionar su información y almacenar las reservas en un archivo de texto.
 * Cada reserva está asociada a un cliente y una mesa, y contiene información como la fecha, la hora de inicio,
 * los comentarios y el estado de la reserva.
 */
public class Reserva {
    private Integer idReserva; // Identificador único de la reserva
    private LocalDate fecha; // Fecha de la reserva
    private LocalTime horaInicio; // Hora de inicio de la reserva
    private String comentarios; // Comentarios adicionales de la reserva
    private LocalTime horaFinal; // Hora final de la reserva
    private Estado estado; // Estado actual de la reserva (por ejemplo, en curso, cancelada)
    private Cliente cliente; // Cliente asociado a la reserva
    private Mesa mesa; // Mesa asignada a la reserva
    private static ArrayList<Reserva> listaDeReservas = new ArrayList<>(); // Lista estática de todas las reservas
    private ArrayList<Evento> listaEventos; // Lista de eventos asociados a la reserva
    private static int generadorIDReservas = 0; // Contador para generar ID únicos de reservas

    /**
     * Constructor de la clase Reserva que crea una nueva reserva con los parámetros proporcionados.
     *
     * @param cliente El cliente que realiza la reserva.
     * @param mesa La mesa asignada a la reserva.
     * @param fecha La fecha de la reserva.
     * @param horaInicio La hora de inicio de la reserva.
     * @param comentarios Comentarios adicionales sobre la reserva.
     * @throws ReservaException Si alguno de los parámetros es nulo o si la fecha/hora no son válidas.
     */
    public Reserva(Cliente cliente, Mesa mesa, LocalDate fecha, LocalTime horaInicio, String comentarios) throws ReservaException {
        // Validaciones de entrada
        if (cliente == null || mesa == null || fecha == null || horaInicio == null) {
            throw new ReservaException("Todos los campos de la reserva deben estar completos.");
        }
        if (fecha.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser anterior a la fecha actual.");
        }
        if (horaInicio.isBefore(LocalTime.of(20, 0)) || horaInicio.isAfter(LocalTime.of(23, 59))) {
            throw new ReservaException("La hora de inicio debe estar entre las 20:00 y las 23:59.");
        }

        this.idReserva = generadorIDReservas++;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.comentarios = comentarios;
        this.horaFinal = null; // Inicialmente, no hay hora final
        this.estado = Estado.EN_CURSO; // Estado inicial de la reserva
        this.cliente = cliente; // Asignar cliente
        this.mesa = mesa; // Asignar mesa
        listaDeReservas.add(this); // Añadir reserva a la lista
        this.listaEventos = new ArrayList<>(); // Inicializar lista de eventos
    }

    // Constructor por defecto
    public Reserva(){
        this.idReserva = generadorIDReservas++;
        this.listaEventos = new ArrayList<>();
        listaDeReservas.add(this);
    }

    // Métodos getter y setter para los atributos de la clase

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer id) {
        this.idReserva = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getComentarios() {
        return comentarios;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public ArrayList<Reserva> getListaDeReservas() {
        return listaDeReservas;
    }

    public static void setListaDeReservas(ArrayList<Reserva> lista) {
        listaDeReservas = lista;
    }

    public static int getContadorReservas() {
        return generadorIDReservas;
    }

    @Override
    public String toString() {
        return "LogicaNegocio.Reserva{" +
                "cliente=" + cliente +
                ", idReserva=" + idReserva +
                ", fecha=" + fecha +
                ", horaInicio=" + horaInicio +
                ", comentarios='" + comentarios + '\'' +
                ", horaFinal=" + horaFinal +
                ", estado=" + estado +
                ", mesa=" + mesa +
                '}';
    }

    /**
     * Exporta la información de la reserva a un archivo de texto.
     *
     * @param reserva La reserva que se desea exportar.
     */
    public void exportarReservaATXT(Reserva reserva) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reservas.txt", true))) {
            writer.write("ID Reserva: " + reserva.getIdReserva() +
                    ", Nombre: " + reserva.cliente.getNombre() +
                    ", Correo: " + reserva.cliente.getCorreo() +
                    ", Número de celular: " + reserva.cliente.getNumero() +
                    ", Fecha: " + reserva.getFecha() +
                    ", Hora: " + reserva.getHoraInicio() +
                    ", Mesa: " + reserva.mesa.getNumMesa());
            writer.newLine();
            System.out.println("La reserva se archivó correctamente.");
        } catch (IOException e) {
            System.err.println("Error al exportar la reserva: " + e.getMessage());
        }
    }

    /**
     * Carga reservas desde un archivo de texto y las devuelve en una lista.
     *
     * @return Lista de reservas cargadas.
     */
    public static ArrayList<Reserva> cargarReservasDesdeArchivo() {
        ArrayList<Reserva> reservasCargadas = new ArrayList<>();
        int maxId = 0; // Variable para almacenar el máximo ID encontrado
        try (BufferedReader reader = new BufferedReader(new FileReader("reservas.txt"))) {
            String linea;
            // Procesar el archivo línea por línea
            while ((linea = reader.readLine()) != null) {
                // Verificar que la línea no esté vacía
                if (!linea.trim().isEmpty()) {
                    try {
                        // Separar los datos basados en el formato de exportación
                        String[] datos = linea.split(", ");
                        // Extraer los campos de la reserva
                        int idReserva = Integer.parseInt(datos[0].split(": ")[1]);
                        if (idReserva > maxId) {
                            maxId = idReserva; // Actualizar el máximo ID si es necesario
                        }
                        String nombreCliente = datos[1].split(": ")[1];
                        String correoCliente = datos[2].split(": ")[1];
                        String numeroCliente = datos[3].split(": ")[1];
                        LocalDate fechaReserva = LocalDate.parse(datos[4].split(": ")[1]);
                        LocalTime horaInicioReserva = LocalTime.parse(datos[5].split(": ")[1]);
                        int numMesa = Integer.parseInt(datos[6].split(": ")[1]);

                        // Crear un nuevo cliente y mesa basados en los datos cargados
                        Cliente cliente = new Cliente(nombreCliente, correoCliente, numeroCliente);
                        Mesa mesa = new Mesa(numMesa);
                        // Crear una nueva reserva usando el constructor con parámetros
                        Reserva reservaCargada = new Reserva(cliente, mesa, fechaReserva, horaInicioReserva, "");

                        // Agregar la reserva a la lista
                        reservasCargadas.add(reservaCargada);
                        reservaCargada.setIdReserva(idReserva); // Establecer ID a la reserva cargada

                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException | DateTimeParseException e) {
                        System.err.println("Error al procesar la línea: " + linea);
                    } catch (ReservaException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            generadorIDReservas = maxId + 1; // Actualizar el generador de IDs
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de reservas: " + e.getMessage());
        }
        return reservasCargadas; // Devolver la lista de reservas cargadas
    }

    /**
     * Actualiza una reserva en el archivo de texto.
     *
     * @param reservaModificada La reserva con la información actualizada.
     */
    public static void actualizarReservaEnArchivo(Reserva reservaModificada) {
        File archivo = new File("reservas.txt");
        List<String> lineasArchivo = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean reservaModificadaEncontrada = false;

            while ((linea = reader.readLine()) != null) {
                if (linea.contains("ID Reserva: " + reservaModificada.getIdReserva())) {
                    // Modificar la línea correspondiente a la reserva
                    lineasArchivo.add("ID Reserva: " + reservaModificada.getIdReserva() +
                            ", Nombre: " + reservaModificada.getCliente().getNombre() +
                            ", Correo: " + reservaModificada.getCliente().getCorreo() +
                            ", Número de celular: " + reservaModificada.getCliente().getNumero() +
                            ", Fecha: " + reservaModificada.getFecha() +
                            ", Hora: " + reservaModificada.getHoraInicio() +
                            ", Mesa: " + reservaModificada.getMesa().getNumMesa());
                    reservaModificadaEncontrada = true;
                } else {
                    lineasArchivo.add(linea);
                }
            }
            if (!reservaModificadaEncontrada) {
                System.err.println("No se encontró la reserva con ID: " + reservaModificada.getIdReserva());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sobrescribir el archivo con las líneas actualizadas
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (String linea : lineasArchivo) {
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina una reserva del archivo de texto.
     *
     * @param idReserva El ID de la reserva que se desea eliminar.
     */
    public static void eliminarReservaDelArchivo(int idReserva) {
        File archivo = new File("reservas.txt");
        List<String> lineasArchivo = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean reservaEncontrada = false;

            while ((linea = reader.readLine()) != null) {
                // Verifica si la línea contiene la reserva que se desea cancelar
                if (!linea.contains("ID Reserva: " + idReserva)) {
                    lineasArchivo.add(linea); // Mantener la línea si no es la que se cancela
                } else {
                    reservaEncontrada = true; // Reserva encontrada y se omite en la escritura
                }
            }

            if (!reservaEncontrada) {
                System.err.println("No se encontró la reserva con ID: " + idReserva);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sobrescribir el archivo con las líneas restantes
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (String linea : lineasArchivo) {
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Realiza una reserva a nombre de un cliente.
     *
     * @param cliente    El cliente que realiza la reserva.
     * @param dia        El día en el que se realiza la reserva.
     * @param horaInicio El inicio de la reserva.
     * @param mesa       La mesa reservada (número, ubicación y capacidad).
     * @param comentarios Comentarios adicionales sobre la reserva.
     * @throws ReservaException si alguno de los parámetros es inválido o si la reserva no puede ser realizada.
     */
    public void realizarReserva(Cliente cliente, LocalDate dia, LocalTime horaInicio, Mesa mesa, String comentarios) throws ReservaException {
        if (cliente == null || mesa == null || dia == null || horaInicio == null) {
            throw new ReservaException("Todos los campos de la reserva deben estar completos.");
        }

        LocalDateTime hoyAhora = LocalDateTime.now();
        LocalDateTime fechaHora = LocalDateTime.of(dia, horaInicio);

        if (fechaHora.isBefore(hoyAhora.plusHours(24))) {
            throw new ReservaException("La reserva debe realizarse con al menos 24 horas de anticipación.");
        }

        if (horaInicio.isBefore(LocalTime.of(20, 0)) || horaInicio.isAfter(LocalTime.of(23, 59))) {
            throw new ReservaException("La hora de la reserva debe estar entre las 20:00 y las 23:59.");
        }

        LocalTime horaFinal = horaInicio.plusHours(3); // Hora final por defecto

        if (!mesa.consultarDisponibilidad(mesa, dia, horaInicio)) {
            throw new ReservaException("La mesa " + mesa.getNumMesa() + " no está disponible para el día " + dia + " a las " + horaInicio);
        }

        Reserva nuevaReserva = new Reserva(cliente, mesa, dia, horaInicio, comentarios);
        listaDeReservas.add(nuevaReserva);
        mesa.agregarReserva(nuevaReserva);
        exportarReservaATXT(nuevaReserva);

        // Confirmación de la reserva
        System.out.println("La reserva se realizó con éxito.");
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Mesa: " + mesa.getNumMesa());
        System.out.println("Día: " + dia);
        System.out.println("Hora: " + horaInicio);
        System.out.println("Número de identificación de la reserva: " + nuevaReserva.getIdReserva());
    }

    /**
     * Modifica una reserva existente.
     *
     * @param idReservaModificada El ID de la reserva que se desea modificar.
     * @param nuevaMesa           La nueva mesa (si se modifica).
     * @param nuevaHora           La nueva hora (si se modifica).
     * @param nuevoDia            El nuevo día (si se modifica).
     * @throws ReservaException si ocurre un error al modificar la reserva.
     */
    public void modificarReserva(Integer idReservaModificada, Mesa nuevaMesa, LocalTime nuevaHora, LocalDate nuevoDia) throws ReservaException {
        if (idReservaModificada == null) {
            throw new IllegalArgumentException("El ID de reserva no puede ser nulo.");
        }

        Reserva reservaAModificar = listaDeReservas.stream()
                .filter(reserva -> reserva.getIdReserva().equals(idReservaModificada))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No se encontró una reserva con el ID: " + idReservaModificada));

        boolean cambiarMesa = nuevaMesa != null && !nuevaMesa.getNumMesa().equals(reservaAModificar.getMesa().getNumMesa());

        // Validar nueva hora
        if (nuevaHora != null) {
            if (nuevaHora.isBefore(LocalTime.of(20, 0)) || nuevaHora.isAfter(LocalTime.of(23, 59))) {
                throw new IllegalArgumentException("La hora de la reserva debe estar entre las 20:00 y las 23:59.");
            }
        }

        // Validar nueva fecha
        if (nuevoDia != null && nuevoDia.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Fecha incorrecta, no ingrese fechas en el pasado.");
        }

        // Validar 24 horas de anticipación
        if (nuevoDia != null || nuevaHora != null) {
            LocalDateTime nuevaFechaHora = LocalDateTime.of(nuevoDia != null ? nuevoDia : reservaAModificar.getFecha(),
                    nuevaHora != null ? nuevaHora : reservaAModificar.getHoraInicio());
            if (nuevaFechaHora.isBefore(LocalDateTime.now().plusHours(24))) {
                throw new IllegalArgumentException("La modificación de la reserva debe realizarse con al menos 24 horas de anticipación.");
            }
        }

        if (!reservaAModificar.getMesa().consultarDisponibilidad(nuevaMesa, nuevoDia, nuevaHora)) {
            throw new ReservaException("La mesa " + nuevaMesa.getNumMesa() + " no está disponible para el día " + nuevoDia + " a las " + nuevaHora);
        }

        reservaAModificar.getMesa().actualizarDisponibilidad(reservaAModificar, nuevoDia, nuevaHora, cambiarMesa);

        if (nuevaHora != null) {
            reservaAModificar.setHoraInicio(nuevaHora);
        }

        if (nuevoDia != null) {
            reservaAModificar.setFecha(nuevoDia);
        }

        if (cambiarMesa) {
            reservaAModificar.setMesa(nuevaMesa);
        }

        actualizarReservaEnArchivo(reservaAModificar);
        listaDeReservas = Reserva.cargarReservasDesdeArchivo();

        // Confirmación de la modificación
        System.out.println("La reserva se modificó con éxito.");
        System.out.println("Nombre: " + reservaAModificar.getCliente().getNombre());
        System.out.println("Mesa: " + reservaAModificar.getMesa().getNumMesa());
        System.out.println("Día: " + nuevoDia);
        System.out.println("Hora: " + nuevaHora);
        System.out.println("Número de identificación de la reserva: " + reservaAModificar.getIdReserva());
    }

    /**
     * Cancela una reserva existente.
     *
     * @param idReservaCancelada El ID de la reserva que se desea cancelar.
     * @param cliente            El cliente que cancela la reserva.
     * @param mesa               La mesa que queda disponible tras la cancelación.
     * @throws ReservaException si ocurre un error al cancelar la reserva.
     */
    public void cancelarReserva(int idReservaCancelada, Cliente cliente, Mesa mesa) throws ReservaException {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }

        if (mesa == null) {
            throw new IllegalArgumentException("La mesa no puede ser nula.");
        }

        Reserva reservaACancelar = listaDeReservas.stream()
                .filter(reserva -> reserva.getIdReserva().equals(idReservaCancelada))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No se encontró una reserva con el ID: " + idReservaCancelada));

        // Verificar que el cliente que cancela es el mismo que hizo la reserva
        if (!reservaACancelar.getCliente().getNombre().equalsIgnoreCase(cliente.getNombre())) {
            throw new IllegalArgumentException("El cliente no coincide con la reserva. No se puede cancelar.");
        }

        // Actualizar la disponibilidad de la mesa
        mesa.actualizarDisponibilidad(reservaACancelar, null, null, false);

        // Eliminar la reserva de la lista
        listaDeReservas.remove(reservaACancelar);
        mesa.quitarReserva(reservaACancelar);

        // Incrementar contador de cancelaciones del cliente
        Cliente.incrementarContadorCancelaciones();
        eliminarReservaDelArchivo(idReservaCancelada);

        // Confirmación de la cancelación
        System.out.println("Reserva cancelada con éxito. ID Reserva: " + idReservaCancelada);
    }

    /**
     * Filtra las mesas basándose en su capacidad y ubicación.
     *
     * @param capacidad La capacidad de comensales que soporta la mesa.
     * @param ubicacion La ubicación espacial en el restaurante.
     * @return Lista de mesas que cumplen con los requisitos especificados.
     * @throws IllegalStateException si la lista de mesas es nula.
     */
    public ArrayList<Mesa> filtrarMesa(Integer capacidad, Ubicacion ubicacion) {
        ArrayList<Mesa> mesasFiltradas = new ArrayList<>();
        List<Mesa> listaMesas = Mesa.getListaMesasUbicaciones();

        if (listaMesas == null) {
            throw new IllegalStateException("La lista de mesas no puede ser nula.");
        }

        for (Mesa mesa : listaMesas) {
            if (mesa.getCapacidad().equals(capacidad) && mesa.getUbicacion().equals(ubicacion)) {
                mesasFiltradas.add(mesa);
            }
        }
        return mesasFiltradas;
    }

    /**
     * Solicita al cliente que realice un comentario previo a la reserva.
     *
     * @return Comentario realizado por el cliente.
     */
    public String realizarComentario() {
        // Implementar lógica para solicitar comentario al cliente
        return "";
    }

    /**
     * Reserva un evento en el restaurante.
     *
     * @param cliente      El cliente que realiza la reserva del evento.
     * @param nombre       El nombre del evento.
     * @param piso         El piso donde se llevará a cabo el evento.
     * @param fecha        La fecha del evento.
     * @param horaInicio   La hora de inicio del evento.
     * @param horaFinal    La hora final del evento.
     * @param comentarios  Comentarios adicionales sobre el evento.
     * @param tipoDeDia   El tipo de día (especial, regular, etc.).
     * @throws IllegalArgumentException si alguno de los parámetros es inválido.
     */
    public void reservaEvento(Cliente cliente, String nombre, Piso piso, LocalDate fecha, LocalTime horaInicio, LocalTime horaFinal, String comentarios, TipoDeDia tipoDeDia) {
        if (piso == null || cliente == null) {
            throw new IllegalArgumentException("El cliente y el piso no pueden ser nulos.");
        }

        // Validación de horarios
        if (horaFinal.isBefore(horaInicio)) {
            throw new IllegalArgumentException("La hora final debe ser después de la hora de inicio.");
        }

        // Verificar si el piso ya está reservado
        if (!piso.bloquearMesas(fecha, horaInicio, horaFinal)) {
            throw new IllegalArgumentException("El piso " + piso.getTipodepiso() + " no está disponible para el evento en la fecha y hora solicitadas.");
        }

        // Crear y agregar nuevo evento
        Evento nuevoEvento = new Evento(nombre, fecha, horaInicio, horaFinal, tipoDeDia);
        listaEventos.add(nuevoEvento);

        // Registro del evento
        System.out.println("El evento se ha reservado con éxito. Detalles del evento: " + nuevoEvento);
    }
}