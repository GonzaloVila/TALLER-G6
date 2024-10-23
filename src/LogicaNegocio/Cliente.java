package LogicaNegocio;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
public class Cliente {
    private String nombre;
    private String correo;
    private String numero;
    private String contrasenia;
    private ArrayList<Reserva> listaReservas;
    private static ArrayList<Cliente> listaClientes = new ArrayList<>();



    public Cliente(){
        this.listaReservas = new ArrayList<Reserva>();

    }


    public Cliente(String nombre, String correo, String numero){
        this.nombre = nombre;
        this.correo = correo;
        this.numero = numero;
    }

    public Cliente(String nombre, String correo, String numero, String contrasenia) {
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
<<<<<<< HEAD
     * @param cliente: ingresa un cliente con todos sus datos
     * @throws Exception si el nombre contiene números o el correo es inválido o ya está registrado
     */
    public void registrarCliente(Cliente cliente) throws Exception {
        Cliente nuevoCliente = new Cliente(nombre, correo, numero, contrasenia);

        try {
            // Validación del nombre
            if (cliente.getNombre().matches(".*\\d.*") || cliente.getNombre().isEmpty()) {
                throw new Exception("El nombre no puede contener valores numéricos ni estar vacio el campo.");
            }

            // Validación del correo (un ejemplo simple, puedes mejorarlo)
            if (!cliente.getCorreo().contains("@") || cliente.getCorreo().isEmpty() ) {
                throw new Exception("Correo electrónico inválido ni estar vacio el campo.");
            }

            // Verificar si el cliente ya está registrado
            for (Cliente c : listaClientes) {
                if (c.getCorreo().equals(cliente.getCorreo())) {
                    throw new Exception("El cliente ya está registrado .");
                }
            }
            listaClientes.add(cliente);
            cliente.guardarClienteEnArchivo();
            System.out.println("LogicaNegocio.Cliente registrado exitosamente: " + cliente.getNombre());
        } catch (Exception e) {
            System.out.println("Error al registrar cliente: " + e.getMessage());
        }
    }

    public void guardarClienteEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.txt", true))) {
            // Escribir encabezados solo si el archivo está vacío
            File file = new File("clientes.txt");
            if (file.length() == 0) {
                writer.write("Lista de Clientes");
                writer.newLine();
            }
            writer.write("Nombre: " + nombre +
                    ", Correo: " + correo +
                    ", Número: " + numero +
                    ", CONTRASEÑA: " + contrasenia);
            writer.newLine();
            System.out.println("Cliente guardado correctamente en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar el cliente: " + e.getMessage());
        }
    }



    /**
    *iniciarSesion: inicia sesion con una cuenta ya creada.
     * @param correo: correo del usuario
     * @param contrasenia: contraseña del usuario
    */

    public boolean iniciarSesion(String correo, String contrasenia){
        for (Cliente cliente : listaClientes) {
            if (cliente.getCorreo().equals(correo) && cliente.getContrasenia().equals(contrasenia)) {
                return true;
            }
        }
        return false;
    }
    /**
     *actualizarInfo: cambia informacion de la cuenta del cliente.
     * @param nuevoNombre: nuevo nombre a modificar
     * @param nuevoNumero: nuevo numero a modificar
     * @param nuevaContrasenia: nuevo contraseña a modificar
     */

    public void actualizarInfo(String nuevoNombre, String nuevoNumero, String nuevaContrasenia) {
        // Actualizar la información solo si los nuevos datos no son nulos o vacíos
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            this.nombre = nuevoNombre;
        }
        if (nuevoNumero != null && !nuevoNumero.isEmpty()) {
            this.numero = nuevoNumero;
        }
        if (nuevaContrasenia != null && !nuevaContrasenia.isEmpty()) {
            this.contrasenia = nuevaContrasenia;
        }
    }

    /**
     *actualizarInfo: cambia informacion de la cuenta del cliente.
     * @param correo: correo del usuario.
     * @param contrasenia: contraseña del usuario
     */


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
