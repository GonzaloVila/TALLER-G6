package LogicaNegocio;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * La clase Evento representa un evento que se puede programar en el sistema.
 * Incluye detalles como el nombre del evento, la fecha, el horario de inicio y fin,
 * y el tipo de día en que se realiza el evento.
 */
public class Evento {
    // Atributos de la clase
    private String nombre;          // Nombre del evento
    private LocalDate fecha;        // Fecha del evento
    private LocalTime horainicio;   // Hora de inicio del evento
    private LocalTime horafinal;    // Hora de finalización del evento
    private TipoDeDia tipo_evento;  // Tipo de día del evento

    /**
     * Constructor de la clase Evento.
     *
     * @param nombre      Nombre del evento.
     * @param fecha       Fecha en que se llevará a cabo el evento.
     * @param horainicio  Hora de inicio del evento.
     * @param horafinal   Hora de finalización del evento.
     * @param tipo_evento Tipo de día en que se realiza el evento.
     */
    public Evento(String nombre, LocalDate fecha, LocalTime horainicio, LocalTime horafinal, TipoDeDia tipo_evento) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.horainicio = horainicio;
        this.horafinal = horafinal;
        this.tipo_evento = tipo_evento;
    }

    // Métodos getter y setter

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(LocalTime horainicio) {
        this.horainicio = horainicio;
    }

    public LocalTime getHorafinal() {
        return horafinal;
    }

    public void setHorafinal(LocalTime horafinal) {
        this.horafinal = horafinal;
    }

    public TipoDeDia getTipo_evento() {
        return tipo_evento;
    }

    public void setTipo_evento(TipoDeDia tipo_evento) {
        this.tipo_evento = tipo_evento;
    }

    /**
     * Método toString que devuelve una representación en forma de cadena del objeto Evento.
     *
     * @return Una cadena que representa los detalles del evento.
     */
    @Override
    public String toString() {
        return "LogicaNegocio.Evento{" +
                "nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                ", horainicio=" + horainicio +
                ", horafinal=" + horafinal +
                ", tipo_evento=" + tipo_evento +
                '}';
    }
}