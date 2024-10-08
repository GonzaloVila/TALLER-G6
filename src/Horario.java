import java.time.LocalDate;
import java.time.LocalTime;

public class Horario {
    private LocalDate Dia;
    private LocalTime HoraInicio;
    private LocalTime HoraFin;
    private Calendario calendario;

    public Horario() {
    };
    public Horario(LocalDate Dia, LocalTime HoraInicio, LocalTime HoraFin){
        this.Dia = Dia;
        this.HoraInicio = HoraInicio;
        this.HoraFin = HoraFin;
    }

    public LocalDate getDia() {
        return Dia;
    }

    public void setDia(LocalDate dia) {
        Dia = dia;
    }

    public LocalTime getHoraFin() {
        return HoraFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        HoraFin = horaFin;
    }

    public LocalTime getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        HoraInicio = horaInicio;
    }

    @Override
    public String toString() {
        return "Horario{" +
                "Dia=" + Dia +
                ", HoraInicio=" + HoraInicio +
                ", HoraFin=" + HoraFin +
                '}';
    }
}
