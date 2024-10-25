package LogicaNegocio;
import Excepciones.ReservaException;

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
import java.io.File;
import java.io.FileReader;

public class Reserva {
    private Integer idReserva;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private String comentarios;
    private LocalTime horaFinal;
    private Estado estado;
    private Cliente cliente;
    private Mesa mesa;
    private static ArrayList<Reserva> listaDeReservas = new ArrayList<>();
    private ArrayList<Evento> listaEventos;
    private static int generadorIDReservas = 0;

    public Reserva(Cliente cliente, Mesa mesa, LocalDate fecha, LocalTime horaInicio, String comentarios) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        if (mesa == null) {
            throw new IllegalArgumentException("La mesa no puede ser nula.");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }
        if (horaInicio == null) {
            throw new IllegalArgumentException("La hora de inicio no puede ser nula.");
        }
        if (fecha.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser anterior a la fecha actual.");
        }
        if (horaInicio.isBefore(LocalTime.of(20, 0)) || horaInicio.isAfter(LocalTime.of(23, 59))) {
            throw new IllegalArgumentException("La hora de inicio debe estar entre las 20:00 y las 23:59.");
        }
        this.idReserva = generadorIDReservas++;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.comentarios = comentarios;
        this.horaFinal = null;
        this.estado = Estado.EN_CURSO;
        this.cliente = cliente;
        this.mesa = mesa;
        listaDeReservas.add(this);
        this.listaEventos = new ArrayList<>();
    }

    public Reserva(){
        this.idReserva = generadorIDReservas++;
        this.listaEventos = new ArrayList<>();
    }


    public Integer getIdReserva() {
        return idReserva;
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

    public ArrayList<Reserva> getListaDeReservas(){return listaDeReservas;}

    public static int getContadorReservas() {   return generadorIDReservas;}


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

    public void exportarReservaATXT(Reserva reserva) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reservas.txt", true))) {
            File file = new File("reservas.txt");
            if(file.length() == 0) {
                writer.write("Lista de Reservas");
                writer.newLine();
            }
            writer.write("ID Reserva: " + reserva.getIdReserva() +
                    ", Nombre: " + reserva.cliente.getNombre() +
                    ", Fecha: " + reserva.getFecha() +
                    ", Hora " + reserva.getHoraInicio() +
                    ", Mesa: " + reserva.mesa.getNumMesa());
            writer.newLine();
            System.out.println("La reserva se archivó correctamente.");
        } catch (IOException e) {
            System.err.println("Error al exportar la reserva: " + e.getMessage());
        }
    }

    /**
     * realizarReserva: realiza una reserva a nombre de un cliente
     *
     * @param cliente    : nombre de quien realiza la reserva
     * @param dia        : dia en el que se realizó la reserva
     * @param horaInicio : comienzo de la reserva
     * @param mesa       : informacion de la mesa(número, ubicación y capacidad)
     * @throws IllegalArgumentException si alguno de los parámetros que se pasan es inválido
     */
    public void realizarReserva(Cliente cliente, LocalDate dia, LocalTime horaInicio, Mesa mesa, String comentarios) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }

        if (mesa == null) {
            throw new IllegalArgumentException("La mesa no puede ser nula.");
        }

        // Validación de 24 horas de anticipación
        LocalDateTime hoyAhora = LocalDateTime.now();
        LocalDateTime FechaHora = LocalDateTime.of(dia, horaInicio);
        if (FechaHora.isBefore(hoyAhora.plusHours(24))) {
            throw new IllegalArgumentException("La reserva debe realizarse con al menos 24 horas de anticipación.");
        }


        // Validar que la hora de inicio esté dentro del horario permitido del restaurante
        if (horaInicio.isBefore(LocalTime.of(20, 0)) || horaInicio.isAfter(LocalTime.of(23, 59))) {
            throw new IllegalArgumentException("La hora de la reserva debe estar entre las 20:00 y las 23:59.");
        }

        //si no se pasa hora final se le da por defecto 3 horas despues del comienzo.
        if (horaFinal == null) {
            horaFinal = horaInicio.plusHours(3);
        }

        // Verifico si la mesa esta disponible
        if (!mesa.consultarDisponibilidad(mesa, dia, horaInicio)) {
            throw new IllegalArgumentException("La mesa " + mesa.getNumMesa() + " no está disponible para el día " + dia + " a las " + horaInicio);
        }

        // Crear la nueva reserva
        Reserva nuevaReserva = new Reserva(cliente, mesa, dia, horaInicio, comentarios);

        // Agregar la nueva reserva a la lista
        listaDeReservas.add(nuevaReserva);
        mesa.agregarReserva(nuevaReserva);
        exportarReservaATXT(nuevaReserva);

        System.out.println("La reserva se realizó con éxito.");
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Mesa: " + mesa.getNumMesa());
        System.out.println("Día: " + dia);
        System.out.println("Hora: " + horaInicio);
        System.out.println("Número de identificación de la reserva: " + nuevaReserva.getIdReserva());
    }




    /**
     * modificarReserva
     * @param idReserva: número de identificación de la reserva
     * @param nuevaMesa: nueva mesa en caso de que sea modificada, sino se conserva la misma
     * @param nuevaHora: nueva hora en caso de que sea modificada, sino se conserva la misma
     * @param nuevoDia: nuevo día en caso de que sea modificado, sino se conserva el mismo
     * @throws IllegalArgumentException: por si ocurre algun error a la hora de encontrar algun dato
     */
    public void modificarReserva(Integer idReserva, Mesa nuevaMesa, LocalTime nuevaHora, LocalDate nuevoDia) {
        if (idReserva == null) {
            throw new IllegalArgumentException("El ID de reserva no puede ser nulo.");
        }

        // Buscar la reserva mediante el id
        Reserva reservaAModificar = null;
        for (Reserva reserva : listaDeReservas) {
            if (reserva.getIdReserva().equals(idReserva)) {
                reservaAModificar = reserva;
                break;
            }
        }

        if (reservaAModificar == null) {
            throw new IllegalArgumentException("No se encontró una reserva con el ID: " + idReserva);
        }

        boolean cambiarMesa = nuevaMesa != null && !nuevaMesa.equals(reservaAModificar.getMesa());
        //validamos la hora nueva
        if (nuevaHora != null) {
            if (nuevaHora.isBefore(LocalTime.of(20, 0))) {
                throw new IllegalArgumentException("La hora de la reserva debe ser después de las 20:00.");
            }
            if (nuevaHora.isAfter(LocalTime.of(23, 59))) {
                throw new IllegalArgumentException("La hora de la reserva no puede ser después de las 00:00.");
            }
        }

        if (nuevoDia != null && nuevoDia.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Fecha incorrecta, no ingrese fechas en el pasado");
        }

        // Validar 24 horas de anticipación para la nueva fecha
        if (nuevoDia != null || nuevaHora != null) {
            LocalDateTime hoyAhora = LocalDateTime.now();
            LocalDateTime nuevaFechaHora = LocalDateTime.of(nuevoDia != null ? nuevoDia : reservaAModificar.getFecha(),
                    nuevaHora != null ? nuevaHora : reservaAModificar.getHoraInicio());
            if (nuevaFechaHora.isBefore(hoyAhora.plusHours(24))) {
                throw new IllegalArgumentException("La modificación de la reserva debe realizarse con al menos 24 horas de anticipación.");
            }
        }

        // Actualizar la disponibilidad de la mesa
        reservaAModificar.getMesa().actualizarDisponibilidad(reservaAModificar, nuevoDia, nuevaHora, cambiarMesa);

        // Actualizar los valores de la reserva
        if (nuevaHora != null && !nuevaHora.equals(reservaAModificar.getHoraInicio())) {
            reservaAModificar.setHoraInicio(nuevaHora); //se cambia la hora
        }

        if (nuevoDia != null && !nuevoDia.equals(reservaAModificar.getFecha())) {
            reservaAModificar.setFecha(nuevoDia); // se cambia el dia
        }

        if (cambiarMesa) {
            reservaAModificar.setMesa(nuevaMesa); // se cambia la mesa
        }

        System.out.println("Reserva modificada con éxito.");
    }


    /**
     * cancelarReserva
     * @param idReserva: número de identificación de la reserva
     * @param cliente: cliente que cancela
     * @param mesa: mesa que deja de estar reservada
     */
    public void cancelarReserva(int idReserva, Cliente cliente, Mesa mesa) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }

        if (mesa == null) {
            throw new IllegalArgumentException("La mesa no puede ser nula.");
        }

        Reserva reservaACancelar = null;
        for (Reserva reserva : listaDeReservas) {
            if (reserva.getIdReserva().equals(idReserva)) {
                reservaACancelar = reserva;
                break;
            }
        }

        // compruebo si se encontró la reserva que se quiere cancelar
        if (reservaACancelar == null) {
            throw new IllegalArgumentException("No se encontró una reserva con el ID: " + idReserva);
        }

        // compruebo que el cliente que cancela sea el que hizo la reserva
        if (!reservaACancelar.getCliente().equals(cliente)) {
            throw new IllegalArgumentException("El cliente no coincide con la reserva. No se puede cancelar.");
        }

        // Actualizar la disponibilidad de la mesa, es decir, libera la cancelada
        mesa.actualizarDisponibilidad(reservaACancelar, null, null, false);

        // elimina la reserva de la lista
        listaDeReservas.remove(reservaACancelar);
        mesa.quitarReserva(reservaACancelar);

        // suma en uno el contador de cancelaciones del cliente
        cliente.incrementarContadorCancelaciones();

        // Confirmar la cancelación
        System.out.println("Reserva cancelada con éxito. ID Reserva: " + idReserva);
    }


    /**
     * filtrarMesa: filtra las mesas basándose en su capacidad y ubicación.
     * @param capacidad: capacidad de comensales que soporta la mesa
     * @param ubicacion: ubicación espacial en el restaurante
     * @return: lista de mesas que cumplen con los requisitos especificados
     */
    public ArrayList<Mesa> filtrarMesa(Integer capacidad, Ubicacion ubicacion) {
        // Crear una lista para almacenar las mesas filtradas
        ArrayList<Mesa> mesasFiltradas = new ArrayList<>();

        // Asegúrate de que la lista de mesas no sea nula
        List<Mesa> listaMesas = Mesa.getListaMesasUbicaciones(); // Asegúrate de que este método sea estático o de instancia.

        if (listaMesas == null) {
            throw new IllegalStateException("La lista de mesas no puede ser nula.");
        }

        // Recorrer la lista de mesas para aplicar los filtros
        for (Mesa mesa : listaMesas) {
            boolean cumpleCapacidad = (capacidad == null || mesa.getCapacidad() >= capacidad);
            boolean cumpleUbicacion = (ubicacion == null || mesa.getUbicacion().equals(ubicacion));

            if (cumpleCapacidad && cumpleUbicacion) {
                mesasFiltradas.add(mesa);
            }
        }

        return mesasFiltradas;
    }



    /**
     * realizarComentario: se le solicita al cliente que realice un comentario previo a la reserva con el objetivo
     * de conocer si cuenta con algunas alergias o enfermedades que no le permitan disfrutar de todos los alimentos
     * @return: comentario realizado por el cliente
     */
    public String realizarComentario() {
        return "";
    }

    public void reservaEvento(Cliente cliente, String nombre, Piso piso, LocalDate fecha, LocalTime horaInicio, LocalTime horaFinal, String comentarios, TipoDeDia tipoDeDia) {
        if (piso == null || cliente == null) {
            throw new IllegalArgumentException("El cliente y el piso no pueden ser nulos");
        }

        // Validación de horarios
        if (horaFinal.isBefore(horaInicio)) {
            throw new IllegalArgumentException("La hora final debe ser después de la hora de inicio");
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