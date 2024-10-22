package LogicaNegocio;

import java.util.ArrayList;
import java.time.LocalTime;
import java.time.LocalDate;

/**
 * Clase que representa un piso del restaurante, que puede contener varias mesas.
 */
public class Piso {
    private Ubicacion tipodepiso; // Tipo de piso (ejemplo: salón principal, segundo piso, terraza)
    private ArrayList<Mesa> mesasDelPiso; // Lista de mesas disponibles en este piso

    /**
     * Constructor por defecto que inicializa la lista de mesas.
     */
    public Piso() {
        this.mesasDelPiso = new ArrayList<>(); // Inicializar la lista de mesas
    }

    /**
     * Constructor que inicializa el tipo de piso y la lista de mesas.
     *
     * @param tipodepiso Tipo de piso a establecer.
     */
    public Piso(Ubicacion tipodepiso) {
        this.tipodepiso = tipodepiso;
        this.mesasDelPiso = new ArrayList<>(); // Inicializar la lista de mesas
    }

    /**
     * Obtiene el tipo de piso.
     *
     * @return Ubicacion El tipo de piso.
     */
    public Ubicacion getTipodepiso() {
        return tipodepiso;
    }

    /**
     * Establece el tipo de piso.
     *
     * @param tipodepiso Tipo de piso a establecer.
     */
    public void setTipodepiso(Ubicacion tipodepiso) {
        this.tipodepiso = tipodepiso;
    }

    /**
     * Agrega una mesa al piso.
     *
     * @param mesa Mesa a agregar.
     * @throws IllegalArgumentException Si la mesa es nula.
     */
    public void agregarMesa(Mesa mesa) {
        if (mesa == null) {
            throw new IllegalArgumentException("La mesa no puede ser nula.");
        }
        mesasDelPiso.add(mesa);
    }

    /**
     * Bloquea todas las mesas del piso para una determinada fecha y horario.
     *
     * @param dia Fecha de la reserva.
     * @param horaInicio Hora de inicio de la reserva.
     * @param horaFinal Hora de finalización de la reserva.
     * @return true si todas las mesas se bloquean exitosamente, false si alguna mesa ya está ocupada.
     * @throws IllegalArgumentException Si la fecha, horas son nulas o si la hora de inicio es posterior a la hora final.
     */
    public boolean bloquearMesas(LocalDate dia, LocalTime horaInicio, LocalTime horaFinal) {
        if (dia == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }
        if (horaInicio == null || horaFinal == null) {
            throw new IllegalArgumentException("Las horas no pueden ser nulas.");
        }
        if (horaInicio.isAfter(horaFinal)) {
            throw new IllegalArgumentException("La hora de inicio debe ser anterior a la hora final.");
        }

        boolean todasDisponibles = true;
        for (Mesa mesa : mesasDelPiso) {
            if (!mesa.consultarDisponibilidad(mesa, dia, horaInicio)) {
                todasDisponibles = false;
                break;
            }
        }

        if (todasDisponibles) {
            for (Mesa mesa : mesasDelPiso) {
                mesa.bloquearMesa(mesa, dia, horaInicio);
            }
            return true; // Todas las mesas se bloquearon con éxito
        }

        return false; // No se pudo bloquear todas las mesas
    }

    /**
     * Devuelve una representación en formato de cadena del piso y sus mesas.
     *
     * @return String que representa el piso.
     */
    @Override
    public String toString() {
        return "Piso{" +
                "tipodepiso=" + tipodepiso +
                ", mesasDelPiso=" + mesasDelPiso +
                '}';
    }
}
