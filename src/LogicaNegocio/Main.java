package LogicaNegocio;

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
    }
}