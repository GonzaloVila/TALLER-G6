package LogicaNegocio;

import java.util.ArrayList;
import java.time.*;

public class ReservaPiso {
    private ArrayList<Piso> espacios; // Lista de espacios disponibles
    private ArrayList<Reserva> reservasPiso;

    public ReservaPiso() {
        this.espacios = new ArrayList<>();
        this.reservasPiso = new ArrayList<>();
    }

    /**
     * Reserva un espacio para un evento.
     *
     * @param cliente    El cliente que realiza la reserva.
     * @param piso      El piso a reservar.
     * @param dia       La fecha del evento.
     * @param horaInicio La hora de inicio del evento.
     * @param horaFin    La hora de finalización del evento.
     */
   public void reservarEspacio(Cliente cliente, Piso piso, LocalDate dia, LocalTime horaInicio, LocalTime horaFin) {
        // Validar que el cliente y el piso no sean nulos
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        if (piso == null) {
            throw new IllegalArgumentException("El piso no puede ser nulo.");
        }

        // Verificar la disponibilidad del piso
        if (piso.bloquearMesas(dia, horaInicio, horaFin)) {
            //Reserva nuevaReserva = new Reserva(null, dia, horaInicio, null, horaFin, Estado.RESERVADA, cliente, null, piso);
            //reservasPiso.add(nuevaReserva);
            System.out.println("La reserva del piso " + piso.getTipodepiso() + " se realizó con éxito para el evento.");
        } else {
            System.out.println("No se pudo reservar el piso " + piso.getTipodepiso() + ". Hay mesas ocupadas en el horario solicitado.");
        }
    }

    public ArrayList<Reserva> getListaDeReservas() {
        return reservasPiso;
    }

    public ArrayList<Piso> getEspacios() {
        return espacios;
    }
}
