package LogicaNegocio;

public class Empleado {
    private int idempleado;
    private String nombre;
    private Permiso permiso;
    private Rol rol;

    public Empleado(){};

    public Empleado(int idempleado, String nombre, Permiso permiso, Rol rol) {
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "LogicaNegocio.Empleado{" + "idempleado=" + idempleado + ", nombre=" + nombre + ", permiso=" + permiso + ", rol=" + rol + '}';
    }



    /**
     * crearCuentaEmpleado: crea una nueva cuenta a un empleado.
     */

    public void crearCuentaEmpleado(int idEmpleado, String nombre, Permiso permiso, Rol rol) {
        try {
            // Validación del nombre
            if (nombre == null || nombre.matches(".*\\d.*") || nombre.isEmpty()) {
                throw new Exception("El nombre no puede contener valores numéricos ni estar vacío.");
            }

            // Validación del ID del empleado
            if (idEmpleado <= 0) {
                throw new Exception("El ID del empleado debe ser un número positivo.");
            }

            // Verificar si el permiso es nulo
            if (permiso == null) {
                throw new Exception("El permiso no puede ser nulo.");
            }

            // Verificar si el rol es nulo
            if (rol == null) {
                throw new Exception("El rol no puede ser nulo.");
            }

            // Asignar valores al nuevo empleado
            this.idempleado = idEmpleado;
            this.nombre = nombre;
            this.permiso = permiso;
            this.rol = rol; // Guardar el nombre del rol como String

            // Mostrar mensaje de éxito
            System.out.println("Nueva cuenta creada para el empleado: " + this.nombre);

        } catch (Exception e) {
            // Manejo de excepciones
            System.out.println("Error al crear la cuenta del empleado: " + e.getMessage());
        }
    }

}





