package LogicaNegocio;

import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Reporte {
    private static LocalDate fechaActual = LocalDate.now();

    private ArrayList<Cliente> clientes;

    public Reporte(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    /**
     * detalleReservasFuturas: Detalla las futuras reservas de un determinado cliente.
     * @param c: Cliente determinado
     * @return: Lista de reservas futuras
     */
    public ArrayList<Reserva> detalleReservasFuturas(Cliente c){
        ArrayList<Reserva> reservasFuturas = new ArrayList<>();

        for(Cliente cl: this.clientes){
            if(cl==c){
                for(Reserva r: c.consultarHistorialReservas()){
                    if(fechaActual.isBefore(r.getFecha())){
                        reservasFuturas.add(r);
                    }
                }
            }
        }
        return reservasFuturas;
    }

    /**
     * historialReservaPorCliente: Reservas realizadas por un cliente durante toda su vida.
     * @param c: Cliente determinado
     * @return: Lista de reservas historial del cliente
     */
    public ArrayList<Reserva> historialReservaPorCliente(Cliente c) {
        for (Cliente cl : clientes) {
            if (cl == c) {
                return cl.consultarHistorialReservas();
            }
        }
        return null;
    }


    /**
     * maxAsistenciaCliente: Cliente que ha realizado mayor cantidad de reservas y ha asistido a ellas.
     * @return: Cliente
     */
    public Cliente maxAsistenciaCliente() {
        int cantMaxReservas = 0;
        Cliente clienteMayorReservas = null;

        for (Cliente c : this.clientes) {
            int contador = 0;
            for (Reserva r : c.consultarHistorialReservas()) {
                if (r.getEstado().equals(Estado.FINALIZADA)) {
                    contador++;
                }
            }
            if (cantMaxReservas < contador) {
                cantMaxReservas = contador;
                clienteMayorReservas = c;
            }
        }

        return clienteMayorReservas;

    }

    /**
     * clienteMaloInasistencia: Clientes que han realizado reservas pero no han asistido en el último año.
     * @return: Lista de clientes que no han asistido a sus reservas en el último año
     */
    public ArrayList<Cliente> clienteMaloInasistencia(){

        LocalDate haceUnAnio = fechaActual.minusYears(1); //   23/10/23
        HashSet<Cliente> clientesNoAsistidos = new HashSet<Cliente>();

        for(Cliente c: this.clientes){
            for(Reserva r: c.consultarHistorialReservas()){
                if(r.getEstado().equals(Estado.NO_ASISTIO) && r.getFecha().isAfter(haceUnAnio)){
                    clientesNoAsistidos.add(c);
                    break;
                }
            }
        }
        return new ArrayList<>(clientesNoAsistidos);

    }

    /**
     * reservaRealizadas: Reservas realizadas detallando mesa, fecha, hora de comienzo de la ocupación
     *                    y de finalización de la misma, cliente y cantidad de comensales que puede albergar
     *                    en un rango de fechas dado.
     * @param f1: Primer fecha del rango de fechas
     * @param f2: Segunda fecha del rango de fechas
     * @return: Lista de reservas detalladas
     */
    public ArrayList<Reserva> reservaRealizadas(LocalDate f1, LocalDate f2){
        ArrayList<Reserva> reservasEnRango = new ArrayList<>();

        for(Cliente c: clientes){
            for(Reserva r: c.consultarHistorialReservas()){
                if(r.getFecha().isAfter(f1) && r.getFecha().isBefore(f2)){
                    reservasEnRango.add(r);
                }
            }
        }

        return reservasEnRango;
    }

    /**
     * analisisConcurrenciaPorEstacion: Análisis de períodos de tiempo en que existe mayor concurrencia de comensales.
     * Recorre todas las reservas de los clientes y calcula el total de comensales por estacion.
     *
     * @return HashMap que contiene estación (clave) y total de comensales (valor)
     */
    public Map<String, Integer> analisisConcurrenciaPorEstacion(){
        Map<String, Integer> concurrenciaPorEstacion = new HashMap<>();

        for(Cliente c: this.clientes){
            for(Reserva r: c.consultarHistorialReservas()){
                String estacion = getEstacion(r.getFecha());
                int comensales = r.getMesa().getCapacidad();

                concurrenciaPorEstacion.put(estacion, concurrenciaPorEstacion.getOrDefault(estacion, 0)+ comensales);
            }
        }
        return concurrenciaPorEstacion;
    }

    /**
     * getEstacion: Determinar la estación del año basada en la fecha de la reserva.
     *
     * @param fecha: fecha de la reserva
     * @return El nombre de la estacion del año correspondiente (Verano, Otoño, invierno, Primavera)
     */
    private String getEstacion(LocalDate fecha){
        Month mes= fecha.getMonth();

        switch(mes){
            case DECEMBER: case JANUARY: case FEBRUARY:
                return "Verano";
            case MARCH: case APRIL: case MAY:
                return "Otoño";
            case JUNE: case JULY: case AUGUST:
                return "Invierno";
            case SEPTEMBER: case OCTOBER: case NOVEMBER:
                return "Primavera";
            default:
                return "Desconocida";
        }
    }
}