import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
public class Mesa {
    private Integer numMesa;
    private Integer ubicacion;
    private Integer capacidad;
    private ArrayList<Reserva> listaReservas;

    public Mesa(){
        this.listaReservas =  new ArrayList<Reserva>();
    }

    public Mesa(Integer numMesa, Integer ubicacion, Integer capacidad, ArrayList<Reserva>listaReservas) {
        this.numMesa = numMesa;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.listaReservas = listaReservas;
    }

    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    public int getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(int ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
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
        return "Mesa{" + "numMesa=" + numMesa + ", ubicacion=" + ubicacion + ", capacidad=" + capacidad + '}';
    }

    /**
     * consultarDisponibilidad: te informa si la mesa está disponible en caso de que no se encuentre bloqueada o reservada.
     * @param dia: dia en el que se consulta la mesa
     * @param hora: horario en el que se busca la mesa
     * @return: returna true en caso de que la mesa esté disponible y false en caso de que no
     */
    public boolean consultarDisponibilidad(LocalDate dia, LocalTime hora){
        return true;
    }

    /**
     * actualizarDisponibilidad: cambio el valor de la disponibilidad de la mesa.
     */
    public void actualizarDisponibilidad(){
    }

    /**
     *bloquearMesa: bloquea las mesas que no estén disponibles.
     */

    public void bloquearMesa(){

    }

}
