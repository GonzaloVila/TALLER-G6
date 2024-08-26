public class Empleado {
    private int idempleado;
    private String nombre;
    private Permiso permiso;
    private String rol;

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


}
