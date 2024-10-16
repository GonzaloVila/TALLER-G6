package LogicaNegocio;



import java.util.ArrayList;

public class Evento {
    private String nombre;
    private int fecha;
    private int horainicio;
    private int horafinal;
    private TipoDeDia tipo_evento;
    private ArrayList<Administrador> listaAdministradores;

    public Evento(String nombre, int fecha, int horainicio, int horafinal, TipoDeDia tipo_evento) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.horainicio = horainicio;
        this.horafinal = horafinal;
        this.tipo_evento = tipo_evento;
        this.listaAdministradores = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public int getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(int horainicio) {
        this.horainicio = horainicio;
    }

    public int getHorafinal() {
        return horafinal;
    }

    public void setHorafinal(int horafinal) {
        this.horafinal = horafinal;
    }

    public TipoDeDia getTipo_evento() {
        return tipo_evento;
    }

    public void setTipo_evento(TipoDeDia tipo_evento) {
        this.tipo_evento = tipo_evento;
    }

    public ArrayList<Administrador> getListaAdministradores(){return listaAdministradores;}

    public void agregarAdministrador(Administrador administrador){
        listaAdministradores.add(administrador);
    }


    @Override
    public String toString() {
        return "LogicaNegocio.Evento{" + "nombre=" + nombre + ", fecha=" + fecha + ", horainicio=" + horainicio + ", horafinal=" + horafinal + ", tipo_evento=" + tipo_evento + '}';
    }
}