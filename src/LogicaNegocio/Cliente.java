package LogicaNegocio;

import java.util.ArrayList;
public class Cliente {
    private String nombre;
    private String correo;
    private String numero;
    private String contrasenia;
    private ArrayList<Reserva> listaReservas;

    public Cliente() {
        this.listaReservas = new ArrayList<Reserva>();
    }
    public Cliente(String nombre, String correo, String numero, String contrasenia){
        this.nombre = nombre;
        this.correo = correo;
        this.numero = numero;
        this.contrasenia = contrasenia;
    }
    public Cliente(String nombre, String correo, String numero){
        this.nombre = nombre;
        this.correo = correo;
        this.numero = numero;
    }
    public Cliente(String nombre, String correo, String numero, String contrasenia, ArrayList<Reserva>listaReservas) {
        this.nombre = nombre;
        this.correo = correo;
        this.numero = numero;
        this.contrasenia = contrasenia;
        this.listaReservas = listaReservas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setListaReservas(ArrayList<Reserva>listaReservas){
        this.listaReservas = listaReservas;
    }
    public void agregarReserva(Reserva reserva){
        this.listaReservas.add(reserva);
    }


    @Override
    public String toString() {
        return "LogicaNegocio.Cliente{" + "nombre=" + nombre + ", correo=" + correo + ", numero=" + numero + ", contrasenia=" + contrasenia + '}';
    }

    /**
     * registrarCliente: crea una cuenta de un cliente
     * @param nombre: nombre del cliente
     * @param correo: correo del cliente
     * @param numero: numero del cliente
     * @param contrasenia: contrasenña del cliente
     */

    public void registarCliente(String nombre, String correo, String numero, String contrasenia){

    }
    /**
    *iniciarSesion: inicia sesion con una cuenta ya creada.
     * @param correo: correo del usuario
     * @param contrasenia: contraseña del usuario
    */
    public void iniciarSesion(String correo, String contrasenia){
    }
    /**
     *actualizarInfo: cambia informacion de la cuenta del cliente.
     * @param correo: correo del usuario.
     * @param contrasenia: contraseña del usuario
     */

    public void actualizarInfo(String correo, String contrasenia){
    }

    /**
     *recuperarContrasenia: se le enviá un mail al usuario con un enlace que le posibilite modificar su contraseña en caso de que se la olvide.
     * @param correo: correo del usuario
     */

    public void recuperarContrasenia(String correo){
    }

    /**
     * consultarHistorialReservas: el cliente consulta su historial de reservas
     * @param correo: correo del usuario
     * @param contrasenia: contraseña del usuario
     * @return: retorna una lista de las reservas históricas
     */
    public ArrayList<Reserva> consultarHistorialReservas(String correo, String contrasenia){

        return null;
    }

    /**
     * pagarXInasistencia: realiza un pago en caso de que el cliente no se presente a la reserva
     * @param estado: estado en el que se encuentra la reserva, define si el cliente asistió o no, si la reserva fue postergada o si está en curso.
     */
    public void pagarXInasistencia(Estado estado){

    }
}
