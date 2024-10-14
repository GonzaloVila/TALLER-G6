package LogicaNegocio;

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
        return "LogicaNegocio.Calendario{" +
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
    public void agregarDia(Horario d){
        try{
            if(d == null){
                throw new IllegalArgumentException("El dia no puede ser nulo");
            }
            if(!getDias().contains(d)){
                Dias.add(d);
            }else{
                System.out.println("El dia ya esta agregado");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al agregar el dia: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado al agregar el dia: " + e.getMessage());
        }

    };


    /**
     *
     * @param d: elimina un dia.
     */
    public  void eliminarDia(Horario d){
        try {
            if (d == null) {
                throw new IllegalArgumentException("El día no puede ser nulo.");
            }
            if (Dias.contains(d)) {
                Dias.remove(d);
                System.out.println("Día eliminado: " + d);
            } else {
                System.out.println("El día no está en el calendario: " + d);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al eliminar día: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado al eliminar día: " + e.getMessage());
        }
    };

    /**
     *
     * @return: retorna una lista.
     */
    public ArrayList<Horario> obtenerDia(){
        return new ArrayList<>(Dias);
    }

    /**
     *
     * @param d: recibe un horario.
     * @return: retorna un boolean para saber si el horario esta disponible o no.
     */
    public boolean estaDisponible(Horario d){
        try {
            if (d == null) {
                throw new IllegalArgumentException("El horario no puede ser nulo.");
            }
            for (Horario horario : Dias) {
                if (horario.getDia().equals(d.getDia()) &&
                        d.getHoraInicio().isBefore(horario.getHoraFin()) &&
                        d.getHoraFin().isAfter(horario.getHoraInicio())) {
                    return false;
                }
            }
            return true; // Horario disponible
        } catch (IllegalArgumentException e) {
            System.out.println("Error al verificar disponibilidad: " + e.getMessage());
            return false; // Se devuelve false ante un error
        } catch (Exception e) {
            System.out.println("Error inesperado al verificar disponibilidad: " + e.getMessage());
            return false; // Se devuelve false ante un error inesperado
        }
    }
}
