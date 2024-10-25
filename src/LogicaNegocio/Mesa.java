package LogicaNegocio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class Mesa {
    private Integer numMesa;
    private Ubicacion ubicacion;
    private Integer capacidad;
    private ArrayList<Reserva> listaReservasMesa;
    private static ArrayList<Mesa> listaMesasUbicaciones = new ArrayList<>();


    public Mesa() {
        this.listaReservasMesa = new ArrayList<>();
    }

    public Mesa(Integer numMesa) {
        this.numMesa = numMesa;
        this.ubicacion = asignarUbicacion(numMesa);
        this.capacidad = asignarCapacidad(numMesa);
        listaMesasUbicaciones.add(this);
        this.listaReservasMesa = new ArrayList<>();
    }

    public static void inicializarMesas() {
        for (int i = 1; i <= 35; i++) {
            listaMesasUbicaciones.add(new Mesa(i)); // Crea y añade la mesa a la lista
        }
    }

    public Integer getNumMesa() {return numMesa;}

    public void setNumMesa(Integer numMesa) {this.numMesa = numMesa;}

    public Ubicacion getUbicacion() {return ubicacion;}

    public void setUbicacion(Ubicacion ubicacion) {this.ubicacion = ubicacion;}

    public Integer getCapacidad() {return capacidad;}

    public void setCapacidad(Integer capacidad) {this.capacidad = capacidad;}

    public void agregarReserva(Reserva reserva) {
        if (!this.listaReservasMesa.contains(reserva)) {
            this.listaReservasMesa.add(reserva);
        }
    }

    public void quitarReserva(Reserva reserva){this.listaReservasMesa.remove(reserva);}

    public ArrayList<Reserva> getListaReservasMesa() {return listaReservasMesa;}

    public static ArrayList<Mesa> getListaMesasUbicaciones() {return listaMesasUbicaciones;}


    @Override
    public String toString() {
        return "LogicaNegocio.Mesa{" + "numMesa=" + numMesa + ", ubicacion=" + ubicacion + ", capacidad=" + capacidad + '}';
    }

    /**
     * consultarDisponibilidad: te informa si la mesa está disponible en caso de que no se encuentre bloqueada o reservada.
     *
     * @param dia:  día en el que se consulta la mesa
     * @param hora: horario en el que se busca la mesa
     * @return: retorna true en caso de que la mesa esté disponible y false en caso de que no
     * @throws IllegalArgumentException si alguno de los parámetros es inválido
     */
    public boolean consultarDisponibilidad(Mesa mesa, LocalDate dia, LocalTime hora) {
        // Validación de la mesa
        if (mesa == null) {
            throw new IllegalArgumentException("La mesa no puede ser nula.");
        }
        // Validación del día
        if (dia == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }
        if (dia.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha es incorrecta.");
        }

        // Validación de la hora
        if (hora == null) {
            throw new IllegalArgumentException("La hora no puede ser nula.");
        }
        if (hora.isBefore(LocalTime.of(20, 0)) || hora.isAfter(LocalTime.of(23, 59))) {
            throw new IllegalArgumentException("La hora debe estar entre las 20:00 y las 23:59.");
        }

        // Consulta la disponibilidad de la mesa
        for (Reserva reserva : listaReservasMesa) {
            if (reserva.getMesa().equals(mesa) && reserva.getFecha().equals(dia) && reserva.getHoraInicio().equals(hora)) {
                return false; // La mesa ya está reservada para esa fecha y hora
            }
        }
        //verificar si la mesa ha sido bloqueada por un evento
        /**
         for (Evento evento : listaEventos) {  // Supongo que tienes una lista de eventos que manejas en otro lado
         if (evento.getFecha().equals(dia) && evento.getHorainicio().equals(hora) && evento.contieneMesa(mesa)) {
         return false; // La mesa está bloqueada por un evento
         }
         }
         */
        return true;
    }

    /**
     * actualizarDisponibilidad: cambio el valor de la disponibilidad de la mesa debido a una cancelacion o una modificación en la reserva.
     * @param reservaOriginal: reserva que tenia x mesa a la cual se le va a actualizar su disponibilidad
     * @param nuevoDia: dia sobre el cual se va a plicar el cambio
     * @param nuevaHora: hora sobre la cual se va a aplicar el cambio
     * @param esCambioDeMesa: valor que confirma si la mesa se debe actualizar o no
     */
    // Método para actualizar la disponibilidad
    public void actualizarDisponibilidad(Reserva reservaOriginal, LocalDate nuevoDia, LocalTime nuevaHora, boolean esCambioDeMesa) {
        // Liberar la disponibilidad anterior
        if (reservaOriginal == null) {
            throw new IllegalArgumentException("La reserva no puede ser nula.");
        }

        listaReservasMesa.removeIf(reserva -> reserva.getFecha().equals(reservaOriginal.getFecha()) &&
                reserva.getHoraInicio().equals(reservaOriginal.getHoraInicio()));
        System.out.println("Se liberó la disponibilidad de la reserva original.");


        // Variables intermedias para fecha y hora
        LocalDate fechaReserva = (nuevoDia != null) ? nuevoDia : reservaOriginal.getFecha();
        LocalTime horaReserva = (nuevaHora != null) ? nuevaHora : reservaOriginal.getHoraInicio();

        if (fechaReserva.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("La fecha es incorrecta.");
        }
        // Validar que la nueva hora no sea inválida (puedes ajustar los horarios permitidos si es necesario)
        if (horaReserva.isBefore(LocalTime.of(20, 0)) || horaReserva.isAfter(LocalTime.of(23, 59))) {
            throw new IllegalArgumentException("La hora de la reserva debe estar entre las 20:00 y las 00:00.");//PARA QUE LAS RESERVAS TENGAN CIERTO LIMITE
        }

        boolean cambioFecha = nuevoDia != null && !nuevoDia.equals(reservaOriginal.getFecha());
        boolean cambioHora = nuevaHora != null && !nuevaHora.equals(reservaOriginal.getHoraInicio());

        if (esCambioDeMesa || cambioFecha || cambioHora) {
            if (!consultarDisponibilidad(this, fechaReserva, horaReserva)) {
                System.out.println("La mesa ya está ocupada en la fecha " + fechaReserva + " a las " + horaReserva);
                return; // Evitar seguir si la mesa ya está reservada
            }
        }
        Reserva nuevaReserva = new Reserva(reservaOriginal.getCliente(), this, fechaReserva, horaReserva, reservaOriginal.getComentarios());

        // Agregar la nueva reserva a la lista
        listaReservasMesa.add(nuevaReserva);

        System.out.println("Se asignó la nueva disponibilidad para el día " + fechaReserva + " a las " + horaReserva);
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

    private Ubicacion asignarUbicacion(int numeroMesa) {
        if (numeroMesa >= 1 && numeroMesa <= 15) {
            return Ubicacion.SALONPRINCIPAL;
        } else if (numeroMesa >= 16 && numeroMesa <= 26) {
            return Ubicacion.SEGUNDOPISO;
        } else if (numeroMesa >= 27 && numeroMesa <= 35) {
            return Ubicacion.TERRAZA;
        } else {
            throw new IllegalArgumentException("Número de mesa fuera de rango.");
        }
    }

    private int asignarCapacidad(int numeroMesa) {
        if (numeroMesa >= 1 && numeroMesa <= 15) {
            if (numeroMesa == 15) {
                return 8;
            } else if (numeroMesa % 3 == 0) {
                return 6;
            } else if (numeroMesa % 2 == 0) {
                return 4;
            } else {
                return 2;
            }
        } else if (numeroMesa >= 16 && numeroMesa <= 26) {
            if (numeroMesa == 26) {
                return 8;
            } else if (numeroMesa % 3 == 0) {
                return 6;
            } else if (numeroMesa % 2 == 0) {
                return 4;
            } else {
                return 2;
            }
        } else if (numeroMesa >= 27 && numeroMesa <= 35) {
            if (numeroMesa == 35) {
                return 8;
            } else if (numeroMesa % 3 == 0) {
                return 6;
            } else if (numeroMesa % 2 == 0) {
                return 4;
            } else {
                return 2;
            }
        } else {
            throw new IllegalArgumentException("Número de mesa fuera de rango.");
        }
    }
}
