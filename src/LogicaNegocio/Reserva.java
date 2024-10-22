package LogicaNegocio;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Reserva {
    private Integer idReserva;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private String comentarios;
    private LocalTime horaFinal;
    private Estado estado;
    private Cliente cliente;
    private Mesa mesa;
    private static ArrayList<Reserva> listaDeReservas = new ArrayList<>();
    private static int contadorReservas = 0; // Contador estático para generar ID únicos


    public Reserva(Integer idReserva, LocalDate fecha, LocalTime horaInicio, String comentarios, LocalTime horaFinal, Estado estado, Cliente cliente, Mesa mesa) {
        this.idReserva = ++contadorReservas;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.comentarios = comentarios;
        this.horaFinal = horaFinal;
        this.estado = estado;
        this.cliente = cliente;
        this.mesa = mesa;
    }
    public Reserva(Cliente cliente, Mesa mesa, LocalDate fecha, LocalTime horaInicio, String comentarios) {
        this.idReserva = ++contadorReservas; // ID autogenerado
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.comentarios = comentarios;
        this.horaFinal = null; // O puedes establecer un valor predeterminado
        this.estado = Estado.EN_CURSO; // O el estado que consideres adecuado
        this.cliente = cliente;
        this.mesa = mesa;
    }


    public Reserva(){
    }


    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public ArrayList<Reserva> getListaDeReservas(){return listaDeReservas;}

    public static int getContadorReservas() {   return contadorReservas;}


    @Override
    public String toString() {
        return "LogicaNegocio.Reserva{" +
                "cliente=" + cliente +
                ", idReserva=" + idReserva +
                ", fecha=" + fecha +
                ", horaInicio=" + horaInicio +
                ", comentarios='" + comentarios + '\'' +
                ", horaFinal=" + horaFinal +
                ", estado=" + estado +
                ", mesa=" + mesa +
                '}';
    }


    /**
     * realizarReserva: realiza una reserva a nombre de un cliente
     *
     * @param cliente    : nombre de quien realiza la reserva
     * @param dia        : dia en el que se realizó la reserva
     * @param horaInicio : comienzo de la reserva
     * @param mesa       : informacion de la mesa(número, ubicación y capacidad)
     */
    public void realizarReserva(Cliente cliente, LocalDate dia, LocalTime horaInicio, Mesa mesa, String comentarios){
        if (cliente == null){
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        //VALIDAR TARJETA CREIDTO
        if (horaFinal == null){
            horaFinal = horaInicio.plusHours(3);
        }
        if (!getMesa().consultarDisponibilidad(mesa, dia, horaInicio)){
            System.out.println("La mesa"+ mesa.getNumMesa() + "no se encuentra disponible para el día "+ dia + " a las"+  horaInicio);
            return; //EN REALIDAD VA UNA EXCEPCION
        }
        Reserva nuevaReserva = new Reserva(idReserva, dia, horaInicio, comentarios, null, Estado.RESERVADA, cliente, mesa);
        listaDeReservas.add(nuevaReserva);
        System.out.println("La reserva se realizó con éxito.");
        System.out.println("Nombre: "+ cliente.getNombre());
        System.out.println("Mesa: "+ mesa.getNumMesa());
        System.out.println("Día: "+ dia);
        System.out.println("Hora: "+ horaInicio);
        System.out.println("Número de identificación de la reserva: "+ idReserva);
    }

    /**
     * modificarReserva
     * @param idReserva: numero de identificacion de la reserva
     * @param nuevaMesa: nueva mesa en caso de que sea modificada, sino se conserva la misma
     * @param nuevaHora: nueva hora en caso de que sea modificada, sino se conserva la misma
     * @param nuevoDia: nuevo día en caso de que sea modifidicado, sino se conserva el mismo
     */
    public void modificarReserva(Integer idReserva, Mesa nuevaMesa, LocalTime nuevaHora, LocalDate nuevoDia) {
        // Buscar la reserva por ID
        Reserva reservaAModificar = null;
        for (Reserva reserva : listaDeReservas) {
            if (reserva.getIdReserva().equals(idReserva)) {
                reservaAModificar = reserva;
            }
        }
        if (horaFinal == null){
            horaFinal = horaInicio.plusHours(3);
        }

        if (reservaAModificar == null) {
            System.out.println("No se encontró una reserva con el ID: " + idReserva);
            return;
        }

        // Comprobar si se quiere cambiar la mesa
        boolean cambiarMesa = nuevaMesa != null && !nuevaMesa.equals(reservaAModificar.getMesa());

        // Actualizar la disponibilidad de la mesa (liberar y asignar nuevos valores)
        reservaAModificar.getMesa().actualizarDisponibilidad(reservaAModificar, nuevoDia, nuevaHora, cambiarMesa);

        // Actualizar los valores de la reserva
        if (nuevaHora != null && !nuevaHora.equals(reservaAModificar.getHoraInicio())) {
            reservaAModificar.setHoraInicio(nuevaHora); // Cambiar la hora
        }

        if (nuevoDia != null && !nuevoDia.equals(reservaAModificar.getFecha())) {
            reservaAModificar.setFecha(nuevoDia); // Cambiar el día
        }

        if (cambiarMesa) {
            reservaAModificar.setMesa(nuevaMesa); // Cambiar la mesa
        }

        System.out.println("Reserva modificada con éxito.");
    }



    /**
     * cancelarReserva
     * @param idReserva: número de indentificación de la reserva
     * @param cliente: cliente que cancela
     */

    public void cancelarReserva(int idReserva, Cliente cliente, Mesa mesa) {
        // Buscar la reserva por ID
        Reserva reservaACancelar = null;
        for (Reserva reserva : listaDeReservas) {
            if (reserva.getIdReserva().equals(idReserva)) {
                reservaACancelar = reserva;
                break; // Salir del bucle una vez encontrada la reserva
            }
        }

        if (reservaACancelar == null) {
            System.out.println("No se encontró una reserva con el ID: " + idReserva);
            return;
        }

        // Verificar que el cliente que cancela sea el que hizo la reserva
        if (!reservaACancelar.getCliente().equals(cliente)) {
            System.out.println("El cliente no coincide con la reserva. No se puede cancelar.");
            return;
        }

        // Actualizar la disponibilidad de la mesa (liberar la reserva cancelada)
        mesa.actualizarDisponibilidad(reservaACancelar, null, null, false);

        // Eliminar la reserva de la lista
        listaDeReservas.remove(reservaACancelar);

        // Actualizar el contador de cancelaciones del cliente
        cliente.incrementarContadorCancelaciones();

        // Confirmar la cancelación
        System.out.println("Reserva cancelada con éxito. ID Reserva: " + idReserva);
    }


    /**
     * filtrarMesa: filtra las mesas basandose en su capacidad y ubicación
     * @param capacidad: capacidad de comensales que soporta la mesa.
     * @param ubicacion: ubicación espacial en el restaurante
     * @return: lista de mesas que cumplan con los requerimientos pasados por parámetros
     */

    public ArrayList<Mesa> filtrarMesa(Integer capacidad, Ubicacion ubicacion){
        ArrayList<Mesa> mesasFiltradas = new ArrayList<>();

        for (Mesa mesa : mesa.getListaMesasUbicaciones()) {
            boolean cumpleCapacidad = (capacidad == null || mesa.getCapacidad() == capacidad);
            boolean cumpleUbicacion = (ubicacion == null || mesa.getUbicacion() == ubicacion); //VA UN EQUALS O ==
            if (cumpleCapacidad && cumpleUbicacion) {
                mesasFiltradas.add(mesa);
            }
        }
        return mesasFiltradas;
    }


    /**
     * realizarComentario: se le solicita al cliente que realice un comentario previo a la reserva con el objetivo
     * de conocer si cuenta con algunas alergias o enfermedades que no le permitan disfrutar de todos los alimentos
     * @return: comentario realizado por el cliente
     */
    public String realizarComentario(){
        return "";
    }

    //Puede ser:
    private boolean verificarHorario(LocalDate dia, LocalTime horaInicio) {
        return true;
    }
    //IDEA DE QUE PUEDE HABER UN MÉTODO QUE VERIFIQUE SI LOS HORARIOS QUE EL CLIENTE SELECCIONA PARA LA RESERVA
    //ESTEN DENTRO DE LOS HORARIOS EN LOS QUE EL RESTAURANTE TRABAJA O SE HAGA LA RESERVA 24HS ANTES.
}