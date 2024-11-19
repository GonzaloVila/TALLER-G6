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
        //Panel de ingreso
        VentanaIniciarSesion ventanaIniciarSesion = new VentanaIniciarSesion();
        // Cargar reservas al inicio
        ArrayList<Reserva> listaReservas = Reserva.cargarReservasDesdeArchivo();
        // Inicializa todas las mesas
        Mesa.inicializarMesas();



    }
}
