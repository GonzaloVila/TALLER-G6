import java.util.ArrayList;
import java.util.List;

public class Calendario {
    private ArrayList<Horario> Dias;
    private TipoDeDia tipo_de_dia;
    private List<Administrador> administradores;
    private List<Horario> horarios;

    public Calendario(){
        this.administradores = new ArrayList<>();
        this.horarios = new ArrayList<>();
    };

    public Calendario(List<Administrador> administradores, ArrayList<Horario> dias, List<Horario> horarios, TipoDeDia tipo_de_dia) {
        this.administradores = administradores;
        Dias = dias;
        this.horarios = horarios;
        this.tipo_de_dia = tipo_de_dia;
    }

    public ArrayList<Horario> getDias() {
        return Dias;
    }

    public void setDias(ArrayList<Horario> dias) {
        Dias = dias;
    }

    public TipoDeDia getTipo_de_dia() {
        return tipo_de_dia;
    }

    public void setTipo_de_dia(TipoDeDia tipo_de_dia) {
        this.tipo_de_dia = tipo_de_dia;
    }

    public List<Administrador> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(List<Administrador> administradores) {
        this.administradores = administradores;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    @Override
    public String toString() {
        return "Calendario{" +
                "administradores=" + administradores +
                ", Dias=" + Dias +
                ", tipo_de_dia=" + tipo_de_dia +
                ", horarios=" + horarios +
                '}';
    }

    /**
     *
     * @param d: agrega un dia al calendario.
     */
    public static void agregarDia(Horario d){};

    /**
     *
     * @param d: elimina un dia.
     */
    public static void eliminarDia(Horario d){};

    /**
     *
     * @return: retorna una lista.
     */
    public ArrayList<Horario> obtenerDia(){
        return null;
    }

    /**
     *
     * @param d: recibe un horario.
     * @return: retorna un boolean para saber si el horario esta disponible o no.
     */
    public boolean estaDisponible( Horario d){
        return true;
    };
}
