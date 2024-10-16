package LogicaNegocio;

import java.time.*;
import java.util.ArrayList;

public class Reporte {
    private String repototal;
    private ArrayList<Cliente> clientes;

    public Reporte(String repototal, ArrayList<Cliente> clientes) {
        this.repototal = repototal;
        this.clientes = clientes;
    }

    public String getRepototal() {
        return repototal;
    }

    public void setRepototal(String repototal) {
        this.repototal = repototal;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return "Reporte [repototal=" + repototal + "]";
    }

    /**
     * detalleReservasFuturas: Detalla las futuras reservas de un determinado cliente
     * @param c: Lista de clientes
     * @return: Lista de reservas futuras
     */
    public ArrayList<Reserva> detalleReservasFuturas(ArrayList<Cliente> c){
        return null;

    }

    /**
     * historial_Reserva_PorCliente: Reservas realizadas por un cliente durante toda su vida
     * @param c: Lista de clientes
     * @return: Lista de reservas historial del cliente
     */
    public ArrayList<Reserva> historial_Reserva_PorCliente(ArrayList<Cliente> c){
        return null;
    }


    /**
     * max_Asistencia_Cliente: Cliente que ha realizado mayor cantidad de reservas y ha asistido a ellas
     * @param c: Lista de clientes
     * @return: Cliente
     */
    public Cliente max_Asistencia_Cliente(ArrayList<Cliente> c){
        return null;
    }

    /**
     * cliente_malo_inasistencia: Clientes que han realizado reservas pero no han asistido en el último año
     * @param c: Lista de clientes
     * @return: Lista de clientes que no han asistido
     */
    public ArrayList<Cliente> cliente_malo_inasistencia(ArrayList<Cliente> c){
        return null;
    }

    /**
     * reservaRealizadas: Reservas realizadas detallando mesa, fecha, hora de comienzo de la ocupación
     *                    y de finalización de la misma, cliente y cantidad de comensales que puede albergar
     *                    en un rango de fechas dado.
     * @param c: Cliente
     * @param m: Mesa
     * @param f: Fecha
     * @param can_comen: Cantidad de comensales
     * @param hi: Hora inicio
     * @param hf: Hora fin
     * @return: Lista de reservas realizadas
     */
    public ArrayList<Reserva> reservaRealizadas(Cliente c, Mesa m, LocalDate f, int can_comen, LocalTime hi, LocalTime hf){
        return null;

    }

    /**
     * Periodo_de_tiempo_en_que_Mas_Comensales_hay: Análisis de períodos de tiempo en que existe mayor
     *                                              concurrencia de comensales
     * @return fecha del periodo
     */
    public String Periodo_de_tiempo_en_que_Mas_Comensales_hay(){
        return null;
    }

    /**
     * exportar_Reporte: Metodo para exportar reporte
     */
    public void exportar_Reporte(){
    }
}