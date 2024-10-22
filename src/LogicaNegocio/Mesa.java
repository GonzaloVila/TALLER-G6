package LogicaNegocio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
public class Mesa {
    private Integer numMesa;
    private Ubicacion ubicacion;
    private Integer capacidad;
    private ArrayList<Reserva> listaReservas;
    private ArrayList<Mesa> listaMesasUbicaciones;

    public Mesa() {
        this.listaReservas = new ArrayList<Reserva>();
        this.listaMesasUbicaciones = new ArrayList<>();
    }

    public Mesa(Integer numMesa, Ubicacion Ubicacion, Integer capacidad) {
        this.numMesa = numMesa;
        this.ubicacion = Ubicacion;
        this.capacidad = capacidad;
    }

    public Mesa(Integer numMesa, Ubicacion ubicacion, Integer capacidad, ArrayList<Reserva> listaReservas) {
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

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setListaReservas(ArrayList<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }

    public ArrayList<Mesa> getListaMesasUbicaciones() {
        return listaMesasUbicaciones;
    }

    public void agregarListaMesasUbicaciones(Mesa mesa) {
        listaMesasUbicaciones.add(mesa);
    }

    public void agregarReserva(Reserva reserva) {
        this.listaReservas.add(reserva);
    }


    @Override
    public String toString() {
        return "LogicaNegocio.Mesa{" + "numMesa=" + numMesa + ", ubicacion=" + ubicacion + ", capacidad=" + capacidad + '}';
    }

    /**
     * consultarDisponibilidad: te informa si la mesa está disponible en caso de que no se encuentre bloqueada o reservada.
     *
     * @param dia:  dia en el que se consulta la mesa
     * @param hora: horario en el que se busca la mesa
     * @return: returna true en caso de que la mesa esté disponible y false en caso de que no
     */
    public boolean consultarDisponibilidad(Mesa mesa, LocalDate dia, LocalTime hora) {
        //Consulta la disponibilidad de la mesa comparando 2 objetos de mesa, una fecha y una hora
        for (Reserva reserva : listaReservas) {
            if (reserva.getMesa().equals(mesa) && reserva.getFecha().equals(dia) && reserva.getHoraInicio().equals(hora)) {
                return false;
            }
        }
        return true;
    }

    /**
     * actualizarDisponibilidad: cambio el valor de la disponibilidad de la mesa.
     */
    // Método para actualizar la disponibilidad
    public void actualizarDisponibilidad(Reserva reservaOriginal, LocalDate nuevoDia, LocalTime nuevaHora, boolean cambiarMesa) {
        // Liberar la disponibilidad anterior
        if (reservaOriginal != null) {
            listaReservas.removeIf(reserva -> reserva.getFecha().equals(reservaOriginal.getFecha()) &&
                    reserva.getHoraInicio().equals(reservaOriginal.getHoraInicio()));
            System.out.println("Se liberó la disponibilidad de la reserva original.");
        }

        if (cambiarMesa) {
            // Variables intermedias para fecha y hora
            LocalDate fechaReserva = (nuevoDia != null) ? nuevoDia : reservaOriginal.getFecha();
            LocalTime horaReserva = (nuevaHora != null) ? nuevaHora : reservaOriginal.getHoraInicio();

            // Crear la nueva reserva
            //Integer idReserva, LocalDate fecha, LocalTime horaInicio, String comentarios,
            // LocalTime horaFinal, Estado estado, Cliente cliente, Mesa mesa.... reservaOriginal.getCliente()
            Reserva nuevaReserva = new Reserva(reservaOriginal.getCliente(), this, fechaReserva, horaReserva, reservaOriginal.getComentarios());

            // Agregar la nueva reserva a la lista
            listaReservas.add(nuevaReserva);

            System.out.println("Se asignó la nueva disponibilidad para el día " + fechaReserva + " a las " + horaReserva);
        }

    }

    /**
     * bloquearMesa: bloquea las mesas que no estén disponibles.
     */

    public void bloquearMesa(Mesa mesa, LocalDate dia, LocalTime hora) {
        if (!consultarDisponibilidad(mesa, dia, hora)) {
            throw new IllegalStateException("La mesa ha sido bloqueada.");
        } else {
            throw new IllegalStateException("No se puede bloquear.");
        }


    }
}
