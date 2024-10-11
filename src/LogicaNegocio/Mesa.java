package LogicaNegocio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
public class Mesa {
    private Integer numMesa;
    private String ubicacion;
    private Integer capacidad;
    private ArrayList<Reserva> listaReservas;

    public Mesa(){
        this.listaReservas =  new ArrayList<Reserva>();
    }

    public Mesa(Integer numMesa, String ubicacion, Integer capacidad, ArrayList<Reserva>listaReservas) {
        this.numMesa = numMesa;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.listaReservas = listaReservas;
    }

    public Mesa(Integer numMesa, String ubicacion, Integer capacidad) {
        this.numMesa = numMesa;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
    }

    public Integer getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(Integer numMesa) {
        this.numMesa = numMesa;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setListaReservas(ArrayList<Reserva>listaReservas){
        this.listaReservas = listaReservas;
    }

    public void agregarReserva(Reserva reserva){
        this.listaReservas.add(reserva);
    }


    @Override
    public String toString() {
        return "LogicaNegocio.Mesa{" + "numMesa=" + numMesa + ", ubicacion=" + ubicacion + ", capacidad=" + capacidad + '}';
    }

    /**
     * consultarDisponibilidad: te informa si la mesa está disponible en caso de que no se encuentre bloqueada o reservada.
     * @param dia: dia en el que se consulta la mesa
     * @param hora: horario en el que se busca la mesa
     * @return: returna true en caso de que la mesa esté disponible y false en caso de que no
     */
    public boolean consultarDisponibilidad(Mesa mesa, LocalDate dia, LocalTime hora){
        //Consulta la disponibilidad de la mesa comparando 2 objetos de mesa, una fecha y una hora
        for (Reserva reserva : listaReservas){
            if(reserva.getMesa().equals(mesa) && reserva.getFecha().equals(dia) && reserva.getHoraInicio().equals(hora)){
                return false;
            }
        }
        return true;
    }

    /**
     * actualizarDisponibilidad: cambio el valor de la disponibilidad de la mesa.
     */
    public void actualizarDisponibilidad(){
        //FALTA
    }

    /**
     *bloquearMesa: bloquea las mesas que no estén disponibles.
     */

    public void bloquearMesa(){

    }

}
