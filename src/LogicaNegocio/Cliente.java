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

    public Cliente() {
        this.listaReservas = new ArrayList<>();
        cargarClientesDesdeArchivo(); // Cargar clientes al iniciar
    }

    public Cliente(String nombre, String correo, String numero) {
        this.nombre = nombre;
        this.correo = correo;
        this.numero = numero;
        this.listaReservas = new ArrayList<>();
    }

    public Cliente(String nombre, String correo, String numero, String contrasenia) {
        this.nombre = nombre;
        this.correo = correo;
        this.numero = numero;
        this.contrasenia = contrasenia;
        this.listaReservas = new ArrayList<>();
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

    public void setListaReservas(ArrayList<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }

    public void agregarReserva(Reserva reserva) {
        this.listaReservas.add(reserva);
    }
    public static ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public static void setListaClientes(ArrayList<Cliente> lista) {
        listaClientes = lista;
    }

    @Override
    public String toString() {
        return "LogicaNegocio.Cliente{" +
                "nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", numero='" + numero + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                '}';
    }

    /**
     * registrarCliente: crea una cuenta de un cliente
     * @param cliente: ingresa un cliente con todos sus datos
     * @throws Exception si el nombre contiene números o el correo es inválido o ya está registrado
     */
    public void registrarCliente(Cliente cliente) throws Exception {
        // Creación del nuevo cliente
        Cliente nuevoCliente = new Cliente(nombre, correo, numero, contrasenia);

        try {
            // Validación del nombre
            if (cliente.getNombre().matches(".*\\d.*") || cliente.getNombre().isEmpty()) {
                throw new Exception("El nombre no puede contener valores numéricos ni estar vacio el campo.");
            }

            // Validación del correo (un ejemplo simple, puedes mejorarlo)
            if (!cliente.getCorreo().contains("@") || cliente.getCorreo().isEmpty()) {
                throw new Exception("Correo electrónico inválido ni estar vacio el campo.");
            }

            // Verificar si el cliente ya está registrado
            for (Cliente c : listaClientes) {
                if (c.getCorreo().equals(cliente.getCorreo())) {
                    throw new Exception("El cliente ya está registrado.");
                }
            }

            // Agregar el nuevo cliente a la lista y guardarlo en el archivo
            listaClientes.add(cliente);
            cliente.guardarClienteEnArchivo();
            System.out.println("Cliente registrado exitosamente: " + cliente.getNombre());

            // Cargar nuevamente la lista de clientes desde el archivo
            listaClientes = cargarClientesDesdeArchivo();

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
                    ", Contraseña: " + contrasenia);
            writer.newLine();
            System.out.println("Cliente guardado correctamente en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar el cliente: " + e.getMessage());
        }
    }

    /**
     * Carga los clientes desde el archivo
     */
    public static ArrayList<Cliente> cargarClientesDesdeArchivo() {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("clientes.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Ignorar líneas que no son clientes
                if (linea.startsWith("Nombre: ")) {
                    String[] datos = linea.split(", ");
                    if (datos.length == 4) {
                        String nombre = datos[0].split(": ")[1];
                        String correo = datos[1].split(": ")[1];
                        String numero = datos[2].split(": ")[1];
                        String contrasenia = datos[3].split(": ")[1];

                        Cliente cliente = new Cliente(nombre, correo, numero, contrasenia);
                        listaClientes.add(cliente);
                    } else {
                        System.out.println("Formato incorrecto: " + linea); // Verifica el formato
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los clientes: " + e.getMessage());
        }
        System.out.println("Clientes cargados: " + listaClientes.size()); // Imprime el tamaño de la lista
        return listaClientes; // Devuelve la lista de clientes cargados
    }





    /**
     * inicarSesion: inicia sesión con una cuenta ya creada.
     * @param correo: correo del usuario
     * @param contrasenia: contraseña del usuario
     */
    public boolean iniciarSesion(String correo, String contrasenia) {
        // Asegúrate de que la lista de clientes esté actualizada
        listaClientes = cargarClientesDesdeArchivo(); // Actualiza la lista cada vez que inicias sesión

        for (Cliente cliente : listaClientes) {
            System.out.println("Verificando cliente: " + cliente.getCorreo()); // Para depuración
            if (cliente.getCorreo().equals(correo) && cliente.getContrasenia().equals(contrasenia)) {
                System.out.println("Inicio de sesión exitoso para: " + correo);
                return true; // Inicio de sesión exitoso
            }
        }
        System.out.println("Credenciales incorrectas para: " + correo);
        return false; // Credenciales incorrectas
    }

    /**
     * actualizarInfo: cambia la información de la cuenta del cliente.
     * @param nuevoNombre: nuevo nombre a modificar
     * @param nuevoNumero: nuevo número a modificar
     * @param nuevaContrasenia: nueva contraseña a modificar
     */
    public void actualizarInfo(String nuevoNombre, String nuevoNumero, String nuevaContrasenia) {
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
     * recuperarContrasenia: se le enviará un mail al usuario con un enlace que le posibilite modificar su contraseña en caso de que se la olvide.
     * @param correo: correo del usuario
     */
    public void recuperarContrasenia(String correo) {
        // Lógica para recuperar la contraseña
    }

    /**
     * consultarHistorialReservas: el cliente consulta su historial de reservas
     * @return: retorna una lista de las reservas históricas
     */
    public ArrayList<Reserva> consultarHistorialReservas() {
        return listaReservas; // Retorna el historial de reservas
    }

    /**
     * pagarXInasistencia: realiza un pago en caso de que el cliente no se presente a la reserva
     * @param estado: estado en el que se encuentra la reserva
     */
    public void pagarXInasistencia(Estado estado) {
        // Lógica de pago por inasistencia
    }
}
