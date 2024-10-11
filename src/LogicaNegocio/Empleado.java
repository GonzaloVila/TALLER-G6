package LogicaNegocio;

public class Empleado {
    private int idempleado;
    private String nombre;
    private Permiso permiso;

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

        return rol;
    }

        this.rol = rol;
    }

    @Override
    public String toString() {
        return "LogicaNegocio.Empleado{" + "idempleado=" + idempleado + ", nombre=" + nombre + ", permiso=" + permiso + ", rol=" + rol + '}';
    }


}
