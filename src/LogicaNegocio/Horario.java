package LogicaNegocio;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * La clase Horario representa un período de tiempo en un día específico,
 * definiendo la fecha y las horas de inicio y fin. Es utilizada en el sistema
 * para gestionar los horarios disponibles o reservados.
 */
public class Horario {
    private LocalDate Dia;
    private LocalTime HoraInicio;
    private LocalTime HoraFin;
    private Calendario calendario;

    /**
     * Constructor por defecto de la clase Horario.
     */
    public Horario() {
    }

    /**
     * Constructor que inicializa un Horario con la fecha, la hora de inicio y la hora de fin especificadas.
     *
     * @param Dia        La fecha del horario.
     * @param HoraInicio La hora de inicio del horario.
     * @param HoraFin    La hora de fin del horario.
     */
    public Horario(LocalDate Dia, LocalTime HoraInicio, LocalTime HoraFin) {
        this.Dia = Dia;
        this.HoraInicio = HoraInicio;
        this.HoraFin = HoraFin;
    }

    /**
     * Obtiene la fecha del horario.
     *
     * @return La fecha en la que se establece el horario.
     */
    public LocalDate getDia() {
        return Dia;
    }

    /**
     * Establece la fecha del horario.
     *
     * @param dia La nueva fecha del horario.
     */
    public void setDia(LocalDate dia) {
        Dia = dia;
    }

    /**
     * Obtiene la hora de inicio del horario.
     *
     * @return La hora de inicio.
     */
    public LocalTime getHoraInicio() {
        return HoraInicio;
    }

    /**
     * Establece la hora de inicio del horario.
     *
     * @param horaInicio La nueva hora de inicio.
     */
    public void setHoraInicio(LocalTime horaInicio) {
        HoraInicio = horaInicio;
    }

    /**
     * Obtiene la hora de fin del horario.
     *
     * @return La hora de fin.
     */
    public LocalTime getHoraFin() {
        return HoraFin;
    }

    /**
     * Establece la hora de fin del horario.
     *
     * @param horaFin La nueva hora de fin.
     */
    public void setHoraFin(LocalTime horaFin) {
        HoraFin = horaFin;
    }

    /**
     * Retorna una representación en forma de cadena del horario, incluyendo la fecha, la hora de inicio y la hora de fin.
     *
     * @return Una cadena que representa el horario.
     */
    @Override
    public String toString() {
        return "LogicaNegocio.Horario{" +
                "Dia=" + Dia +
                ", HoraInicio=" + HoraInicio +
                ", HoraFin=" + HoraFin +
                '}';
    }
}