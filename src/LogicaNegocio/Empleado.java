package LogicaNegocio;
<<<<<<< HEAD

=======
import java.util.ArrayList;
>>>>>>> 8d43a089b5a3a434da9e33bd4ca2a9e58f80ae1c
public class Empleado {
    private int idempleado;
    private String nombre;
    private Permiso permiso;
    private Rol rol;
    private ArrayList<Reserva> listaReservas;

    public Empleado(int idempleado, String nombre, Permiso permiso, Rol rol ){
        this.idempleado = idempleado;
        this.nombre = nombre;
        this.permiso = permiso;
        this.rol = rol;
        this.listaReservas = new ArrayList<>();
    }
    public Empleado(){
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public Rol getRol() {return rol;}

    public void setRol(Rol rol) {this.rol = rol;}

    public ArrayList<Reserva> getListaReservas() {return listaReservas;}

    public void agregarReservas(Reserva reserva){listaReservas.add(reserva);}

    @Override
    public String toString() {
        return "LogicaNegocio.Empleado{" + "idempleado=" + idempleado + ", nombre=" + nombre + ", permiso=" + permiso + ", rol=" + rol + '}';
    }

<<<<<<< HEAD
=======
    public void crearCuentaEmpleado(Integer idEmpleado, String nombre, Permiso permiso, Rol rol){

    }

>>>>>>> 8d43a089b5a3a434da9e33bd4ca2a9e58f80ae1c
}
