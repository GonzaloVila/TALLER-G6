package LogicaNegocio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Administrador extends  Empleado{
    private Calendario calendario;
    private List<Evento> eventos;

    public Administrador(){
        super();
        this.eventos = new ArrayList<>();
    }

    public  Administrador(List<Evento> eventos, int idempleado, String nombre, Permiso permiso, Rol rol, Calendario calendario){
        super(idempleado, nombre, permiso, rol);
        this.calendario = calendario;
        this.eventos = eventos;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    @Override
    public String toString() {
        return "LogicaNegocio.Administrador{" +
                "calendario=" + calendario +
                '}';
    }


    /**
     * Definir horario del restaurante.
     *
     * @param inicio: hora de apertura del restaurante.
     * @param fin: hora de cierre del restaurante.
     */
    public void definirHorarioRegular(LocalDate dia, LocalTime inicio, LocalTime fin){
        try{
            if(dia == null){
                throw new Exception("El dia no puede ser nulo");
            }
            if(inicio == null || fin == null){
                throw  new Exception("Las horas de incio y fin no pueden ser nulas");
            }
            if(inicio.isAfter(fin)){
                throw new Exception("La hora de inicio no puede ser despues de la hora de finalizacion");
            }
            Horario nuevoHorario = new Horario(dia, inicio, fin);
            if (!calendario.estaDisponible(nuevoHorario)){
                throw new Exception("El horario no esta disponible");
            }
            calendario.agregarDia(nuevoHorario);
        } catch (Exception e) {
            System.out.println("Error al definir horario: " + e.getMessage());
        }
    };

    /**
     *
     * @param evento: Se establece un dia especial, que es un evento.
     */
    public  void configurarDiaEspecial(Evento evento) {
        try {
            if (evento == null) {
                throw new Exception("El evento no puede ser nulo");

            }
            if (evento.getFecha() <= 0) {
                throw new Exception("La fecha del evento debe ser valida");
            }
            eventos.add(evento);
            System.out.println("Dia especial configurado" + evento.getNombre());
        } catch (Exception e) {
            System.out.println("Error al configurar dia especial: " + e.getMessage());
        }
    }
}
