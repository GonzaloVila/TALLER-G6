package LogicaNegocio;

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
     * @param cliente: nombre de quien realiza la reserva
     * @param dia: dia en el que se realizó la reserva
     * @param horaInicio: comienzo de la reserva
     * @param mesa: informacion de la mesa(número, ubicación y capacidad)
     */
    public void realizarReserva(Cliente cliente, LocalDate dia, LocalTime horaInicio, Mesa mesa, String comentarios){
        if (cliente == null){
            throw new IllegalArgumentException("El cliente no puede ser nulo");
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
    public void modificarReserva(Integer idReserva, Mesa nuevaMesa, LocalTime nuevaHora, LocalDate nuevoDia){
    }

    /**
     * cancelarReserva
     * @param idReserva: número de indentificación de la reserva
     * @param cliente: cliente que cancela
     */
    public void cancelarReserva(Integer idReserva, Cliente cliente, Mesa mesa){}

    /**
     * filtrarMesa: filtra las mesas basandose en su capacidad y ubicación
     * @param capacidad: capacidad de comensales que soporta la mesa.
     * @param ubicacion: ubicación espacial en el restaurante
     * @return: lista de mesas que cumplan con los requerimientos pasados por parámetros
     */
    public ArrayList<Mesa> filtrarMesa(Integer capacidad, String ubicacion){
        return null;
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
    //IDEA DE LUCAS DE QUE PUEDE HABER UN MÉTODO QUE VERIFIQUE SI LOS HORARIOS QUE EL CLIENTE SELECCIONA PARA LA RESERVA
    //ESTEN DENTRO DE LOS HORARIOS EN LOS QUE EL RESTAURANTE TRABAJA O SE HAGA LA RESERVA 24HS ANTES.
}
