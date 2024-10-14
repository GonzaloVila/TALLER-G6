package LogicaNegocio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Administrador extends  Empleado{
    private Calendario calendario;
    private List<Evento> listaEventos;

    public Administrador(){
        super ();
        this.listaEventos = new ArrayList<>();
    }

    public  Administrador(List<Evento> listaEventos, int idempleado, String nombre, Permiso permiso, Rol rol, Calendario calendario){
        super(idempleado, nombre, permiso, rol);
        this.calendario = calendario;
        this.listaEventos = listaEventos;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void agregarEventos(Evento evento){
        listaEventos.add(evento);
        evento.agregarAdministrador(this);
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
