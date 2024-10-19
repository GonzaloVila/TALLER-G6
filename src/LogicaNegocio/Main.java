package LogicaNegocio;
import InterfacesGraficas.VentanaRegistrarEmpleado;
import InterfacesGraficas.VentanaRegistrarReserva;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import LogicaNegocio.Horario;
import java.util.List;


import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
       /** //Integer idReserva, LocalDate fecha, LocalTime horaInicio, String comentarios, LocalTime horaFinal, Estado estado, Cliente cliente,
        Cliente cliente = new Cliente("Pablo Rd", "pablitoelmaskpito123@gmail.com", "123132", "lucasmireamor2");
        Mesa mesa = new Mesa(13, "Platea", 4);
        Reserva reserva = new Reserva(1234, LocalDate.of(2024, 10, 13), LocalTime.of(13, 0), "Sin comentarios",  LocalTime.of(15, 0), Estado.RESERVADA, cliente, mesa);
        System.out.println("TALLER  GRUPO 6");
        VentanaRegistrarReserva ventana = new VentanaRegistrarReserva(reserva);
        ventana.setVisible(true);
        */


       //Interfaz crearCuentaEmpleado
        Calendario calendario = new Calendario(); // Asegúrate de que esto no sea null
        Administrador administrador = new Administrador(calendario);
        VentanaRegistrarEmpleado ventana = new VentanaRegistrarEmpleado(administrador);
        ventana.setVisible(true);

       //interfaz AdministradorHorarios.
        /**Administrador administrador = new Administrador(new Calendario());
        SwingUtilities.invokeLater(() -> {
            VentanaEstablecerHorario ventana = new VentanaEstablecerHorario(administrador);
            ventana.setVisible(true);
        });*/
    }
}
