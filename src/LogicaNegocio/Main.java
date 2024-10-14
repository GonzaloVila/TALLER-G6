package LogicaNegocio;
<<<<<<< HEAD

import InterfacesGraficas.VentanaActualizarInfo;
import InterfacesGraficas.VentanaIniciarSesion;
import InterfacesGraficas.VentanaRegistroCliente;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        System.out.println("TALLER  GRUPO 6");
        ArrayList<Cliente> listaClientes = new ArrayList<>();

        //new VentanaRegistroCliente();

        // Agregar algunos clientes a la lista para probar
        listaClientes.add(new Cliente("Juan", "juan@example.com", "123456", "password"));
        listaClientes.add(new Cliente("Ana", "ana@example.com", "789012", "1234"));

        // Mostrar la ventana de inicio de sesión
        //new VentanaIniciarSesion(listaClientes);

        // Mostrar la ventana de actualización de información
        new VentanaActualizarInfo(listaClientes);
=======
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

>>>>>>> 8d43a089b5a3a434da9e33bd4ca2a9e58f80ae1c
    }
}