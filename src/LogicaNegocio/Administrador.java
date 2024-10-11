package LogicaNegocio;

import java.time.LocalDateTime;
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
    public static void definirHorarioRegular(LocalDateTime inicio, LocalDateTime fin){};

    /**
     *
     * @param evento: Se establece un dia especial, que es un evento.
     */
    public static void configurarDiaEspecial(Evento evento){};
}
