public class Empleado {
    private int idempleado;
    private String nombre;
    private Permiso permiso;
    private String rol;

    public Empleado(){};

    public Empleado(int idempleado, String nombre, Permiso permiso, String rol) {
        this.idempleado = idempleado;
        this.nombre = nombre;
        this.permiso = permiso;
        this.rol = rol;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Empleado{" + "idempleado=" + idempleado + ", nombre=" + nombre + ", permiso=" + permiso + ", rol=" + rol + '}';
    }

    //Metodos(probar)
    /*
    public void crearCuentaEmpleado (int idEmpleado, String nombre, Permiso permiso, Rol rol){
this.idEmpleado = idEmpleado;
this.nombre = nombre;
this.permiso = permiso;
this.rol = rol;}
System.out.println("Nueva cuenta creada para el empleado: " + nombre);
    }


public void accederAReservas(Reserva reserva) {
//LOGICA
}

 public void modificarReserva(Reserva reserva, Mesa nuevaMesa, LocalTime nuevaHora, LocalDate nuevaFecha) {
 //LOGICA
 }


    public void cancelarReserva(Reserva reserva, Cliente cliente){

    //LOGICA
    }


     */


    /**
     * crearCuentaEmpleado: crea una nueva cuenta a un empleado.
     */
    public void crearCuentaEmpleado(){}


    /**
     * accederAReservas: accede a una reserva.
     */
    public void accederAReservas(){}



    /**
     * modificarReserva: permite al empleado modificar una reserva.
     * */
    public void modificarReserva(){}


    /**
     * cancelarReserva: cancela una reserva.
     */
    public void cancelarReserva(){}


}