package LogicaNegocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * La clase Empleado representa a un empleado en el sistema de gestión del restaurante.
 * Cada empleado tiene un identificador único, un nombre, un correo electrónico,
 * una contraseña, un permiso de acceso, un rol y una lista de reservas asociadas.
 */
public class Empleado {
    private int idempleado;
    private String nombre;
    private String mail; // Correo electrónico del empleado
    private String contrasenia; // Contraseña del empleado
    private Permiso permiso;
    private Rol rol;
    private ArrayList<Reserva> listaReservas;

    /**
     * Constructor para crear un nuevo empleado con todos los atributos especificados.
     *
     * @param idempleado  El identificador único del empleado.
     * @param nombre      El nombre del empleado.
     * @param mail        El correo electrónico del empleado.
     * @param contrasenia La contraseña del empleado.
     * @param permiso     El permiso de acceso del empleado.
     * @param rol         El rol del empleado.
     */
    public Empleado(int idempleado, String nombre, String mail, String contrasenia, Permiso permiso, Rol rol) {
        this.idempleado = idempleado;
        this.nombre = nombre;
        this.mail = mail; // Inicialización del correo electrónico
        this.contrasenia = contrasenia; // Inicialización de la contraseña
        this.permiso = permiso;
        this.rol = rol;
        this.listaReservas = new ArrayList<>();
    }

    /**
     * Constructor vacío para crear un empleado sin atributos inicializados.
     */
    public Empleado() {
    }

    /**
     * Obtiene el identificador del empleado.
     *
     * @return El identificador del empleado.
     */
    public int getIdempleado() {
        return idempleado;
    }

    /**
     * Establece el identificador del empleado.
     *
     * @param idempleado El identificador a establecer.
     */
    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    /**
     * Obtiene el nombre del empleado.
     *
     * @return El nombre del empleado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del empleado.
     *
     * @param nombre El nombre a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el correo electrónico del empleado.
     *
     * @return El correo electrónico del empleado.
     */
    public String getMail() {
        return mail;
    }

    /**
     * Establece el correo electrónico del empleado.
     *
     * @param mail El correo electrónico a establecer.
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Obtiene la contraseña del empleado.
     *
     * @return La contraseña del empleado.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Establece la contraseña del empleado.
     *
     * @param contrasenia La contraseña a establecer.
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Obtiene el permiso de acceso del empleado.
     *
     * @return El permiso de acceso.
     */
    public Permiso getPermiso() {
        return permiso;
    }

    /**
     * Establece el permiso de acceso del empleado.
     *
     * @param permiso El permiso a establecer.
     */
    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    /**
     * Obtiene el rol del empleado.
     *
     * @return El rol del empleado.
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Establece el rol del empleado.
     *
     * @param rol El rol a establecer.
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * Obtiene la lista de reservas asociadas al empleado.
     *
     * @return La lista de reservas del empleado.
     */
    public ArrayList<Reserva> getListaReservas() {
        return listaReservas;
    }

    /**
     * Agrega una nueva reserva a la lista de reservas del empleado.
     *
     * @param reserva La reserva a agregar.
     */
    public void agregarReservas(Reserva reserva) {
        listaReservas.add(reserva);
    }

    /**
     * Obtiene las reservas del día actual.
     *
     * @return Una lista de reservas que corresponden al día actual.
     */
    public List<Reserva> obtenerReservasdelDia() {
        LocalDate fechahoy = LocalDate.now();
        return listaReservas.stream().filter(reserva -> reserva.getFecha().isEqual(fechahoy)).collect(Collectors.toList());
    }

    /**
     * Devuelve una representación en cadena del objeto Empleado.
     *
     * @return Una cadena con los detalles del empleado.
     */
    @Override
    public String toString() {
        return "LogicaNegocio.Empleado{" +
                "idempleado=" + idempleado +
                ", nombre='" + nombre + '\'' +
                ", mail='" + mail + '\'' +
                ", permiso=" + permiso +
                ", rol=" + rol +
                '}';
    }
}