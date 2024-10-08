import java.time.LocalTime;
import java.util.ArrayList;

public class Evento {
    private String nombre;
    private int fecha;
    private int horainicio;
    private int horafinal;
    private Dia_Especial tipo_evento;
    private ArrayList<Administrador> administradores;

    public Evento(){
        this.administradores = new ArrayList<Administrador>();
    }

    public Evento(String nombre, int fecha, int horainicio, int horafinal, Dia_Especial tipo_evento) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.horainicio = horainicio;
        this.horafinal = horafinal;
        this.tipo_evento = tipo_evento;
    }

    public Evento(String nombre, int fecha, int horainicio, int horafinal, Dia_Especial tipo_evento, ArrayList<Administrador> administradores){
        this.nombre = nombre;
        this.fecha = fecha;
        this.horainicio = horainicio;
        this.horafinal = horafinal;
        this.tipo_evento = tipo_evento;
        this.administradores = administradores;
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

    public Dia_Especial getTipo_evento() {
        return tipo_evento;
    }

    public void setTipo_evento(Dia_Especial tipo_evento) {
        this.tipo_evento = tipo_evento;
    }

    /**
     * PROBAR
    public ArrayList<Administrador> getAdministradores() {
        return administradores;
    }
    public void agregarAdminsitrador(Administrador administrador){
        administradores.add(administrador);
    }
    */

    public void setAdministradores(ArrayList<Administrador> administradores) {
        this.administradores = administradores;
    }

    @Override
    public String toString() {
        return "Evento{" + "nombre=" + nombre + ", fecha=" + fecha + ", horainicio=" + horainicio + ", horafinal=" + horafinal + ", tipo_evento=" + tipo_evento + '}';
    }

    /**
     * bloquearMesa: bloquea una determinada mesa
     * @param numMesa numero de mesa a bloquear
     * */
    public void bloquearMesa(Integer numMesa){
    }

    /**
     * bloquearFranjaHoraria: bloquea una franja horaria
     * @param inicio referencia el inicio de la franja horaria
     * @param fin referencia el fin de la franja horaria
     * */
    public void bloquearFranjaHoraria(LocalTime inicio, LocalTime fin){}
}