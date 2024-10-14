package LogicaNegocio;
import Ventanas.VentanaRegistrarEmpleado;
import java.time.LocalDate;
import java.time.LocalTime;


public class Main {
    public static void main(String[] args) {
        System.out.println("TALLER  GRUPO 6");
        Empleado empleado = new Empleado(15, "pablo", Permiso.MEDIO, Rol.Maitre);
        VentanaRegistrarEmpleado ventana = new VentanaRegistrarEmpleado();
        ventana.setVisible(true);

    }
}