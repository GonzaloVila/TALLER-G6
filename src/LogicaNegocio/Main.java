package LogicaNegocio;
import Ventanas.AdministradorGUI;
import Ventanas.VentanaRegistrarEmpleado;
import java.time.LocalDate;
import java.time.LocalTime;


public class Main {
    public static void main(String[] args) {
        System.out.println("TALLER  GRUPO 6");
        Administrador admin = new Administrador();
        new AdministradorGUI(admin);
        System.out.println("hola");
    }
}