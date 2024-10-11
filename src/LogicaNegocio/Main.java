package LogicaNegocio;
import InterfacesGraficas.VentanaRegistrarReserva;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        //Integer idReserva, LocalDate fecha, LocalTime horaInicio, String comentarios, LocalTime horaFinal, Estado estado, Cliente cliente,
        Cliente cliente = new Cliente("Pablo Rd", "pablitoelmaskpito123@gmail.com", "123132", "lucasmireamor2");
        Mesa mesa = new Mesa(13, "Platea", 4);
        Reserva reserva = new Reserva(1234, LocalDate.of(2024, 10, 13), LocalTime.of(13, 0), "Sin comentarios",  LocalTime.of(15, 0), Estado.RESERVADA, cliente, mesa);
        System.out.println("TALLER  GRUPO 6");
        VentanaRegistrarReserva ventana = new VentanaRegistrarReserva(reserva);
        ventana.setVisible(true);

    }
}