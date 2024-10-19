package LogicaNegocio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
public class Mesa {
    private Integer numMesa;
    private String ubicacion;
    private Integer capacidad;
    private ArrayList<Reserva> listaReservas;
    private Estado estado;

    public Mesa(){
        this.listaReservas =  new ArrayList<Reserva>();
    }
    public Mesa(Integer numMesa, String Ubicacion, Integer capacidad){
        this.numMesa = numMesa;
        this.ubicacion = Ubicacion;
        this.capacidad = capacidad;
    }

    public Mesa(Integer numMesa, String ubicacion, Integer capacidad, ArrayList<Reserva>listaReservas) {
        this.numMesa = numMesa;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.listaReservas = listaReservas;
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



    public boolean consultarDisponibilidad(Mesa mesa, LocalDate dia, LocalTime hora) {
        //definir limites de hora
        LocalTime inicioLimite = LocalTime.of(20, 0); // 8 PM
        LocalTime finLimite = LocalTime.of(2, 0); // 2 AM

        // Validar si la hora está fuera de los límites
        if ((hora.isBefore(inicioLimite) && !hora.isAfter(finLimite))) {
            throw new IllegalArgumentException("La hora debe estar entre las 20:00 y las 02:00.");
        }

        // Consulta la disponibilidad de la mesa comparando 2 objetos de mesa, una fecha y una hora
        for (Reserva reserva : listaReservas) {
            if (reserva.getMesa().equals(mesa) && reserva.getFecha().equals(dia) && reserva.getHoraInicio().equals(hora)) {
                return false; // La mesa no está disponible
            }
        }
        return true; // La mesa está disponible
    }


    /**
     * actualizarDisponibilidad: cambio el valor de la disponibilidad de la mesa.
     */

    /*
        public void actualizarDisponibilidad(Mesa mesa ,LocalDate dia, LocalTime horaInicio) {
        // Usa 'this' para referenciar la mesa actual
        if (consultarDisponibilidad(this, dia, horaInicio)) {
            // Si la mesa está disponible, puedes cambiar el estado o realizar alguna acción adicional
            estado = Estado.RESERVADA; // O el estado que corresponda
            System.out.println("La mesa " + numMesa + " ha sido reservada.");
        } else {
            // Si no está disponible
            System.out.println("La mesa " + numMesa + " no está disponible para el día " + dia + " a las " + horaInicio);
        }
    }

    */



    /**
     *bloquearMesa: bloquea las mesas que no estén disponibles.
     */

    public void bloquearMesa(Mesa mesa, LocalDate dia, LocalTime hora){
if (!consultarDisponibilidad(mesa,dia,hora)){
    throw new IllegalStateException("La mesa ha sido bloqueada.");
        }else{
    throw new IllegalStateException("No se puede bloquear.");
        }
    }

}
