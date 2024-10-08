
public class Reserva {
    private int idReserva;
    private int fecha;
    private int horainicio;
    private boolean disponibilidad;
    private String comentarios;
    private int cantcomensales;
    private int horafinal;
    private String estado;
    private Cliente cliente;
    private Mesa mesa;

    public Reserva(int idReserva, int fecha, int horainicio, boolean disponibilidad, String comentarios, int cantcomensales, int horafinal, String estado, Cliente cliente, Mesa mesa) {
        this.idReserva = idReserva;
        this.fecha = fecha;
        this.horainicio = horainicio;
        this.disponibilidad = disponibilidad;
        this.comentarios = comentarios;
        this.cantcomensales = cantcomensales;
        this.horafinal = horafinal;
        this.estado = estado;
        this.cliente = cliente;
        this.mesa = mesa;

    }


    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public int getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(int horainicio) {
        this.horainicio = horainicio;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getCantcomensales() {
        return cantcomensales;
    }

    public void setCantcomensales(int cantcomensales) {
        this.cantcomensales = cantcomensales;
    }

    public int getHorafinal() {
        return horafinal;
    }

    public void setHorafinal(int horafinal) {
        this.horafinal = horafinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
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


    @Override
    public String toString() {
        return "Reserva{" + "idReserva=" + idReserva + ", fecha=" + fecha + ", horainicio=" + horainicio + ", disponibilidad=" + disponibilidad + ", comentarios=" + comentarios + ", cantcomensales=" + cantcomensales + ", horafinal=" + horafinal + ", estado=" + estado + '}';
    }


}
