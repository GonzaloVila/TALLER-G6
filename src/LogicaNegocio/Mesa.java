package LogicaNegocio;

import Excepciones.ReservaException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Clase Mesa representa una mesa en el restaurante.
 * Cada mesa tiene un número, una ubicación, una capacidad y una lista de reservas asociadas.
 * Además, se mantiene una lista estática de todas las mesas y sus ubicaciones en el restaurante.
 */
public class Mesa {
    private Integer numMesa;
    private Ubicacion ubicacion;
    private Integer capacidad;
    private ArrayList<Reserva> listaReservasMesa;
    private static ArrayList<Mesa> listaMesasUbicaciones = new ArrayList<>();

    /**
     * Constructor por defecto que inicializa la lista de reservas de la mesa.
     */
    public Mesa() {
        this.listaReservasMesa = new ArrayList<>();
    }

    /**
     * Constructor que asigna un número de mesa específico.
     * También asigna la ubicación y capacidad en base al número de mesa.
     *
     * @param numMesa Número de la mesa.
     */
    public Mesa(Integer numMesa) {
        this.numMesa = numMesa;
        this.ubicacion = asignarUbicacion(numMesa);
        this.capacidad = asignarCapacidad(numMesa);
        this.listaReservasMesa = new ArrayList<>();
    }

    /**
     * Inicializa una lista de mesas con números consecutivos del 1 al 35.
     */
    public static void inicializarMesas() {
        for (int i = 1; i <= 35; i++) {
            listaMesasUbicaciones.add(new Mesa(i));
        }
    }

    public Integer getNumMesa() {return numMesa;}

    public void setNumMesa(Integer numMesa) {this.numMesa = numMesa;}

    public Ubicacion getUbicacion() {return ubicacion;}

    public void setUbicacion(Ubicacion ubicacion) {this.ubicacion = ubicacion;}

    public Integer getCapacidad() {return capacidad;}

    public void setCapacidad(Integer capacidad) {this.capacidad = capacidad;}

    public ArrayList<Reserva> getListaReservasMesa() {return listaReservasMesa;}

    public static ArrayList<Mesa> getListaMesasUbicaciones() {return listaMesasUbicaciones;}


    /**
     * Agrega una reserva a la lista de reservas de la mesa si no existe ya.
     *
     * @param reserva La reserva a agregar.
     */
    public void agregarReserva(Reserva reserva) {
        if (!this.listaReservasMesa.contains(reserva)) {
            this.listaReservasMesa.add(reserva);
        } else {
            System.out.println("La reserva ya existe en la lista. ");
        }
    }

    /**
     * Elimina una reserva de la lista de reservas de la mesa.
     *
     * @param reserva La reserva a eliminar.
     */
    public void quitarReserva(Reserva reserva) {
        if (this.listaReservasMesa.remove(reserva)) {
            System.out.println("Reserva eliminada con éxito.");
        } else {
            System.out.println("No se encontró la reserva en la lista.");
        }
    }



    @Override
    public String toString() {
        return "LogicaNegocio.Mesa{" + "numMesa=" + numMesa + ", ubicacion=" + ubicacion + ", capacidad=" + capacidad + '}';
    }


    /**
     * Consulta si la mesa está disponible en una fecha y hora especificadas.
     *
     * @param mesaDisponibilidad La mesa a consultar.
     * @param dia Día de la reserva.
     * @param horaComienzo Hora de inicio de la reserva.
     * @return true si la mesa está disponible, false si está ocupada.
     * @throws IllegalArgumentException si alguno de los parámetros es inválido.
     */
    public boolean consultarDisponibilidad(Mesa mesaDisponibilidad, LocalDate dia, LocalTime horaComienzo) {
        if (mesaDisponibilidad == null) {
            throw new IllegalArgumentException("La mesa no puede ser nula.");
        }
        if (dia == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }
        if (dia.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha es incorrecta.");
        }
        if (horaComienzo == null) {
            throw new IllegalArgumentException("La hora no puede ser nula.");
        }
        if (horaComienzo.isBefore(LocalTime.of(20, 0)) || horaComienzo.isAfter(LocalTime.of(23, 59))) {
            throw new IllegalArgumentException("La hora debe estar entre las 20:00 y las 23:59.");
        }
        mesaDisponibilidad.listaReservasMesa = Reserva.cargarReservasDesdeArchivo();

        // Calcular la hora final sumando 3 horas a la hora de inicio
        LocalTime horaFinal = horaComienzo.plusHours(3);

        // Consultar la disponibilidad de la mesa
        for(Reserva reserva: listaReservasMesa){
            if(reserva.getMesa().getNumMesa().equals(mesaDisponibilidad.getNumMesa())){
                if(reserva.getFecha().isEqual(dia)){
                    if(reserva.getHoraInicio().equals(horaComienzo)){
                        return false;
                    }
                }
            }
        }

        return true;
    }


    /**
     * Actualiza la disponibilidad de la mesa en base a cambios en una reserva.
     *
     * @param reservaOriginal Reserva original a actualizar.
     * @param nuevoDia Nuevo día para la reserva.
     * @param nuevaHora Nueva hora para la reserva.
     * @param esCambioDeMesa Indica si hay un cambio de mesa.
     * @throws ReservaException si la hora de la reserva no está en el horario permitido.
     */
    public void actualizarDisponibilidad(Reserva reservaOriginal, LocalDate nuevoDia, LocalTime nuevaHora, boolean esCambioDeMesa) throws ReservaException {
        if (reservaOriginal == null) {
            throw new IllegalArgumentException("La reserva no puede ser nula.");
        }

        listaReservasMesa.removeIf(reserva -> reserva.getFecha().equals(reservaOriginal.getFecha()) &&
                reserva.getHoraInicio().equals(reservaOriginal.getHoraInicio()));

        // Variables intermedias para fecha y hora
        LocalDate fechaReserva = (nuevoDia != null) ? nuevoDia : reservaOriginal.getFecha();
        LocalTime horaReserva = (nuevaHora != null) ? nuevaHora : reservaOriginal.getHoraInicio();

        LocalTime horaFinal = horaReserva.plusHours(3);

        if (fechaReserva.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("La fecha es incorrecta.");
        }
        // Validar que la nueva hora no sea inválida (puedes ajustar los horarios permitidos si es necesario)
        if (horaReserva.isBefore(LocalTime.of(20, 0)) || horaReserva.isAfter(LocalTime.of(23, 59))) {
            throw new ReservaException("La hora de la reserva debe estar entre las 20:00 y las 00:00.");
        }

        boolean cambioFecha = false;
        boolean cambioHora = false;

        if(nuevoDia != null && !nuevoDia.equals(reservaOriginal.getFecha())){
            cambioFecha = true;
        }
        if(nuevaHora != null && nuevaHora.equals(reservaOriginal.getHoraInicio())){
            cambioHora = true;
        }

        if (esCambioDeMesa || cambioFecha || cambioHora) {
            System.out.println("Entró a cambiar la disponibilidad");
            if (!consultarDisponibilidad(this, fechaReserva, horaReserva)) {
                System.out.println("La mesa ya está ocupada en la fecha " + fechaReserva + " a las " + horaReserva);
                return;
            }
        }
        Reserva nuevaReserva = new Reserva(reservaOriginal.getCliente(), this, fechaReserva, horaReserva, reservaOriginal.getComentarios());

        // Agregar la nueva reserva a la lista
        listaReservasMesa.add(nuevaReserva);

        System.out.println("Se asignó la nueva disponibilidad para el día " + fechaReserva + " a las " + horaReserva);
    }

    /**
     * Método para bloquear una mesa en una fecha y hora especificadas.
     * Si la mesa no está disponible en ese horario, se considera bloqueada.
     *
     * @param mesa La mesa que se desea bloquear.
     * @param dia El día en el cual se quiere bloquear la mesa.
     * @param hora La hora en la cual se quiere bloquear la mesa.
     * @throws IllegalStateException si la mesa ya está ocupada en la fecha y hora especificadas
     *                               o si no se puede bloquear por otras razones.
     */
    public void bloquearMesa(Mesa mesa, LocalDate dia, LocalTime hora) {
        LocalTime horaaFin = hora.plusHours(3);
        if (!consultarDisponibilidad(mesa, dia, hora)) {
            throw new IllegalStateException("La mesa ha sido bloqueada.");
        } else {
            throw new IllegalStateException("No se puede bloquear.");
        }
    }

    /**
     * Asigna una ubicación a la mesa en base al número de mesa.
     *
     * @param numeroMesa Número de la mesa.
     * @return La ubicación asignada.
     * @throws IllegalArgumentException si el número de mesa es inválido.
     */
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

    /**
     * Asigna la capacidad a la mesa en función de su número.
     *
     * @param numeroMesa Número de la mesa.
     * @return La capacidad de la mesa.
     * @throws IllegalArgumentException si el número de mesa es inválido.
     */
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