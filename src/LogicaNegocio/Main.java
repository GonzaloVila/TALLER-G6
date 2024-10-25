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
import InterfacesGraficas.RestauranteMapa;
import javafx.application.Application;
import InterfacesGraficas.VentanaRegistroCliente;
import InterfacesGraficas.VentanaIniciarSesion;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Integer idReserva, LocalDate fecha, LocalTime horaInicio, String comentarios, LocalTime horaFinal, Estado estado, Cliente cliente,
        Cliente cliente = new Cliente(" Pablo Rd", "pablitoelmaskpito123@gmail.com", "123132", "lucasmireamor2");
        // Inicializa todas las mesas
        Mesa.inicializarMesas();
        Reserva reserva = new Reserva();
        System.out.println("TALLER  GRUPO 6");
        VentanaRegistrarReserva ventana = new VentanaRegistrarReserva(reserva);
        ventana.setVisible(true);

        //new VentanaRegistroCliente();

       /** //Interfaz crearCuentaEmpleado
        Calendario calendario = new Calendario(); // Asegúrate de que esto no sea null
        Administrador administrador = new Administrador(calendario);
        VentanaRegistrarEmpleado ventana = new VentanaRegistrarEmpleado(administrador);
        ventana.setVisible(true);*/

       //interfaz AdministradorHorarios.
        /**Administrador administrador = new Administrador(new Calendario());
        SwingUtilities.invokeLater(() -> {
            VentanaEstablecerHorario ventana = new VentanaEstablecerHorario(administrador);
            ventana.setVisible(true);
        });*/

       // Application.launch(RestauranteMapa.class, args);
        //ArrayList<Cliente> listaClientes = new ArrayList<>();
        // Puedes añadir algunos clientes de prueba aquí si es necesario.


    }
}
