package LogicaNegocio;

import java.time.LocalDate;
import java.time.LocalTime;
public class Evento {
    private String nombre;
    private LocalDate fecha;
    private LocalTime horainicio;
    private LocalTime horafinal;
    private TipoDeDia tipo_evento;


    public Evento(String nombre, LocalDate fecha, LocalTime horainicio, LocalTime horafinal, TipoDeDia tipo_evento) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.horainicio = horainicio;
        this.horafinal = horafinal;
        this.tipo_evento = tipo_evento;

    }

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


    @Override
    public String toString() {
        return "LogicaNegocio.Evento{" + "nombre=" + nombre + ", fecha=" + fecha + ", horainicio=" + horainicio + ", horafinal=" + horafinal + ", tipo_evento=" + tipo_evento + '}';
    }



}


