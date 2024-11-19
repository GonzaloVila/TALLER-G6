/**
 * La clase Cliente representa a un cliente del sistema de un restaurante.
 * Esta clase almacena la información del cliente, gestiona sus reservas y proporciona
 * métodos para registrar, actualizar y recuperar datos del cliente.
 */
package LogicaNegocio;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cliente {
    private String nombre; // Nombre del cliente
    private String correo; // Correo electrónico del cliente
    private String numero; // Número de contacto del cliente
    private String contrasenia; // Contraseña del cliente
    public static final String CORREOADMIN = "admin@gmail.com";
    public static final String CONTRASEÑAADMIN = "ad";
    private ArrayList<Reserva> listaReservasClientes; // Lista de reservas del cliente
    private static ArrayList<Cliente> listaClientes = new ArrayList<>(); // Lista estática de todos los clientes
    private static int contadorCancelaciones = 0; // Contador de cancelaciones

    private TarjetaCredito tarjetaCredito; // Tarjeta de crédito del cliente

    /**
     * Constructor por defecto que inicializa la lista de reservas.
     */
    public Cliente() {
        this.listaReservasClientes = new ArrayList<>();
    }

    /**
     * Constructor que inicializa el cliente con nombre, correo y número.
     *
     * @param nombre El nombre del cliente.
     * @param correo El correo electrónico del cliente.
     * @param numero El número de contacto del cliente.
     */
    public Cliente(String nombre, String correo, String numero) {
        this.nombre = nombre;
        this.correo = correo;
        this.numero = numero;
        this.listaReservasClientes = new ArrayList<>();
    }

    /**
     * Constructor que inicializa el cliente con nombre, correo, número y contraseña.
     *
     * @param nombre El nombre del cliente.
     * @param correo El correo electrónico del cliente.
     * @param numero El número de contacto del cliente.
     * @param contrasenia La contraseña del cliente.
     */
    public Cliente(String nombre, String correo, String numero, String contrasenia) {
        this.nombre = nombre;
        this.correo = correo;
        this.numero = numero;
        this.contrasenia = contrasenia;
        this.listaReservasClientes = new ArrayList<>();
    }

    // Métodos de acceso y modificación

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

    public void setListaReservasClientes(ArrayList<Reserva> listaReservasClientes) {
        this.listaReservasClientes = listaReservasClientes;
    }
    public ArrayList<Reserva> getListaReservas(){
        return listaReservasClientes;
    }

    /**
     * Agrega una reserva a la lista de reservas del cliente.
     * @param reserva La reserva a agregar.
     */
    public void agregarReserva(Reserva reserva) {
        this.listaReservasClientes.add(reserva);
    }

    public static ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public static void setListaClientes(ArrayList<Cliente> lista) {
        listaClientes = lista;
    }

    /**
     * Incrementa el contador de cancelaciones.
     */
    public static void incrementarContadorCancelaciones(){
        contadorCancelaciones++;
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
     * Registra un nuevo cliente en el sistema.
     *
     * @param cliente El cliente a registrar con todos sus datos.
     * @throws Exception Si el nombre contiene números, el correo es inválido o ya está registrado.
     */
    public void registrarCliente(Cliente cliente) throws Exception {
        // Creación del nuevo cliente
        Cliente nuevoCliente = new Cliente(nombre, correo, numero, contrasenia);

        try {
            // Validación del nombre
            if (cliente.getNombre().matches(".*\\d.*") || cliente.getNombre().isEmpty()) {
                throw new Exception("El nombre no puede contener valores numéricos ni estar vacío el campo.");
            }

            // Validación del correo (un ejemplo simple, puedes mejorarlo)
            if (!cliente.getCorreo().contains("@") || cliente.getCorreo().isEmpty()) {
                throw new Exception("Correo electrónico inválido ni estar vacío el campo.");
            }

            // Verificar si el cliente ya está registrado
            for (Cliente c : listaClientes) {
                if (c.getCorreo().equals(cliente.getCorreo())) {
                    throw new Exception("El cliente ya está registrado.");
                }
            }

            // Agregar el nuevo cliente a la lista y guardarlo en el archivo
            listaClientes.add(cliente);
            cliente.guardarClienteEnArchivo(cliente);
            System.out.println("Cliente registrado exitosamente: " + cliente.getNombre());

        } catch (Exception e) {
            System.out.println("Error al registrar cliente: " + e.getMessage());
        }
    }

    /**
     * Guarda los datos del cliente en un archivo.
     * @param clienteGuardar El cliente cuyos datos se van a guardar.
     */
    public void guardarClienteEnArchivo(Cliente clienteGuardar) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.txt", true))) {
            // Escribir encabezados solo si el archivo está vacío
            File file = new File("clientes.txt");
            if (file.length() == 0) {
                writer.write("Lista de Clientes");
                writer.newLine();
            }
            writer.write("Correo: " + clienteGuardar.getCorreo() +
                    ", Nombre: " + clienteGuardar.getNombre() +
                    ", Número: " + clienteGuardar.getNumero() +
                    ", Contraseña: " + clienteGuardar.getContrasenia());
            writer.newLine();
            System.out.println("Cliente guardado correctamente en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar el cliente: " + e.getMessage());
        }
    }

    /**
     * Verifica si las credenciales proporcionadas corresponden a las del administrador.
     *
     * @param correo El correo electrónico ingresado.
     * @param contrasenia La contraseña ingresada.
     * @return true si el correo y la contraseña coinciden con las credenciales del administrador,
     *         false en caso contrario.
     */
    public static boolean esAdministrador(String correo, String contrasenia) {
        return CORREOADMIN.equals(correo) && CONTRASEÑAADMIN.equals(contrasenia);
    }

    /**
     * Verifica si las credenciales proporcionadas corresponden a un empleado registrado.
     * Lee la lista de empleados desde el archivo y compara el correo y la contraseña
     * proporcionados con los datos de cada empleado.
     *
     * @param correo El correo electrónico ingresado.
     * @param contrasenia La contraseña ingresada.
     * @return true si las credenciales coinciden con las de un empleado registrado, false en caso contrario.
     */
    public static boolean esEmpleado(String correo, String contrasenia) {
        List<String> empleados = cargarEmpleadosDesdeArchivo(); // Carga los empleados
        for (String empleado : empleados) {
            // Suponiendo que la línea se ve así: "ID: 1, Nombre: pablo, Email: pablord@gmail.com, Contraseña: pablord, Permiso: BAJO, Rol: Mesero"
            String[] datos = empleado.split(", "); // Separa por comas y espacios

            String email = null;
            String password = null;

            // Itera sobre los datos para encontrar el correo y la contraseña
            for (String dato : datos) {
                if (dato.startsWith("Email: ")) {
                    email = dato.split(": ")[1]; // Toma el valor del email
                } else if (dato.startsWith("Contraseña: ")) {
                    password = dato.split(": ")[1]; // Toma el valor de la contraseña
                }
            }

            // Comprueba si el correo y la contraseña coinciden
            if (email != null && password != null && email.equals(correo) && password.equals(contrasenia)) {
                return true; // Si hay coincidencia
            }
        }
        return false; // Si no se encontró un empleado que coincida
    }

    /**
     * Carga los clientes desde el archivo.
     * @return Una lista de clientes cargados desde el archivo.
     */
    public static ArrayList<Cliente> cargarClientesDesdeArchivo() {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("clientes.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("Correo: ")) {
                    String[] datos = linea.split(", ");
                    if (datos.length == 4) {
                        String correo = datos[0].split(": ")[1];
                        String nombre = datos[1].split(": ")[1];
                        String numero = datos[2].split(": ")[1];
                        String contrasenia = datos[3].split(": ")[1];

                        Cliente cliente = new Cliente(nombre, correo, numero, contrasenia);
                        listaClientes.add(cliente);
                    } else {
                        System.out.println("Formato incorrecto: " + linea);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los clientes: " + e.getMessage());
        }
        System.out.println("Clientes cargados: " + listaClientes.size());
        return listaClientes;
    }

    /**
     * Carga la lista de empleados desde el archivo "empleados.txt".
     * Cada línea del archivo representa a un empleado y se añade a la lista.
     *
     * @return Una lista de cadenas que representan a cada empleado, cargada desde el archivo.
     */
    public static List<String> cargarEmpleadosDesdeArchivo() {
        List<String> empleados = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("empleados.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                empleados.add(linea);
                System.out.println("Empleado cargado: " + linea); // Verifica que se cargue correctamente
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    /**
     * Inicia sesión con una cuenta ya creada.
     *
     * @param correo El correo del usuario.
     * @param contrasenia La contraseña del usuario.
     * @return true si las credenciales son correctas, false en caso contrario.
     */
    public boolean iniciarSesion(String correo, String contrasenia) {
        if(CORREOADMIN.equals(correo) && CONTRASEÑAADMIN.equals(contrasenia)){
            esAdministrador(correo, contrasenia);
        }
        for (Cliente cliente : listaClientes) {
            System.out.println("Verificando cliente: " + cliente.getCorreo()); // Para depuración
            if (cliente.getCorreo().equals(correo) && cliente.getContrasenia().equals(contrasenia)) {
                System.out.println("Inicio de sesión exitoso para: " + correo);
                System.out.println(cliente.toString());
                return true; // Inicio de sesión exitoso
            }
        }
        System.out.println("Credenciales incorrectas para: " + correo);
        return false; // Credenciales incorrectas
    }

    /**
     * actualizarInfo: Cambia la información de la cuenta del cliente.
     *
     * @param cliente: objeto Cliente que contiene la información del cliente a modificar.
     * @param nuevoNombre: nuevo nombre a modificar. Si es null o vacío, no se realizará ningún cambio.
     * @param nuevoNumero: nuevo número a modificar. Si es null o vacío, no se realizará ningún cambio.
     * @param nuevaContrasenia: nueva contraseña a modificar. Si es null o vacío, no se realizará ningún cambio.
     */
    public void actualizarInfo(Cliente cliente, String nuevoNombre, String nuevoNumero, String nuevaContrasenia) {
        System.out.println("Cliente antes de sobrescribir: " + cliente.toString());

        if (nuevoNombre != null && !nuevoNombre.isEmpty() && !nuevoNombre.equalsIgnoreCase(cliente.getNombre())) {
            cliente.setNombre(nuevoNombre);
        }
        if (nuevoNumero != null && !nuevoNumero.isEmpty()) {
            cliente.setNumero(nuevoNumero);
        }
        if (nuevaContrasenia != null && !nuevaContrasenia.isEmpty()) {
            cliente.setContrasenia(nuevaContrasenia);
        }
        cliente.setCorreo(cliente.getCorreo()); // No hay cambio en el correo, se mantiene.

        sobrescribirArchivoClientes(cliente);
    }

    /**
     * sobrescribirArchivoClientes: Sobrescribe el archivo de clientes con la información actualizada de un cliente.
     * @param clienteSobreEscrito: objeto Cliente que contiene la información actualizada del cliente.
     */
    public void sobrescribirArchivoClientes(Cliente clienteSobreEscrito) {
        String archivoClientes = "clientes.txt";
        List<String> lineasArchivo = new ArrayList<>();

        System.out.println("Cliente sobrescrito: " + clienteSobreEscrito.toString()); // chequeo

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoClientes))) {
            String linea;
            boolean clienteEncontrado = false;

            while ((linea = reader.readLine()) != null) {
                if (linea.contains("Correo: " + clienteSobreEscrito.getCorreo())) {
                    // Modificar la línea correspondiente al cliente
                    lineasArchivo.add("Correo: " + clienteSobreEscrito.getCorreo() +
                            ", Nombre: " + clienteSobreEscrito.getNombre() +
                            ", Número: " + clienteSobreEscrito.getNumero() +
                            ", Contraseña: " + clienteSobreEscrito.getContrasenia());
                    clienteEncontrado = true; // Se encontró y actualizó el cliente
                } else {
                    lineasArchivo.add(linea);
                }
            }

            if (!clienteEncontrado) {
                System.err.println("No se encontró el cliente con correo: " + clienteSobreEscrito.getCorreo());
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de clientes: " + e.getMessage());
        }

        // Sobrescribir el archivo con las líneas actualizadas
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoClientes))) {
            for (String nuevaLinea : lineasArchivo) {
                writer.write(nuevaLinea);
                writer.newLine();
            }
            System.out.println("Archivo actualizado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de clientes: " + e.getMessage());
        }
    }

    /**
     * recuperarContrasenia: Envía un correo al usuario con un enlace para modificar su contraseña en caso de que la haya olvidado.
     * @param correo: correo del usuario al que se le enviará el enlace de recuperación.
     */
    public void recuperarContrasenia(String correo) {
        MailAutomatico mail = new MailAutomatico();
        mail.enviar_Mail_Recuperacion(correo);
    }

    /**
     * consultarHistorialReservas: Permite al cliente consultar su historial de reservas.
     * @return: Retorna una lista de las reservas históricas del cliente.
     */
    public ArrayList<Reserva> consultarHistorialReservas() {
        ArrayList<Reserva> historialReservas = new ArrayList<>();

        String nombreCliente = this.getNombre();
        if (nombreCliente == null || nombreCliente.trim().isEmpty()) {
            System.err.println("Error: El nombre del cliente es nulo o vacío.");
            return historialReservas; // Retornar una lista vacía si el nombre es nulo
        }

        String nombreBuscado = nombreCliente.trim();

        try (BufferedReader reader = new BufferedReader(new FileReader("reservas.txt"))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                System.out.println("Leyendo línea: " + linea);

                // Procesar la línea para obtener el nombre
                String nombreReserva = obtenerNombreDeLinea(linea);
                if (nombreReserva != null && nombreReserva.equals(nombreBuscado)) {
                    System.out.println("Coincidencia encontrada para: " + nombreBuscado);
                    try {
                        Reserva reserva = procesarLineaReserva(linea);
                        if (reserva != null) {
                            historialReservas.add(reserva);
                        }
                    } catch (Exception e) {
                        System.err.println("Error al procesar la línea: " + linea);
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de reservas: " + e.getMessage());
        }

        System.out.println("Historial de reservas para el cliente " + this.getNombre() + ":");
        for (Reserva reserva : historialReservas) {
            System.out.println(reserva);
        }

        return historialReservas;
    }

    /**
     * procesarLineaReserva: Procesa una línea del archivo de reservas y crea un objeto Reserva.
     * @param linea: línea del archivo que contiene la información de la reserva.
     * @return: Retorna un objeto Reserva si la línea es válida; null en caso contrario.
     */
    public Reserva procesarLineaReserva(String linea) {
        try {
            // Dividir la línea en campos usando ", " como delimitador
            String[] partes = linea.split(", ");

            // Validar que se encontraron las partes esperadas
            if (partes.length < 5) {
                System.err.println("Formato de línea incorrecto: " + linea);
                return null;
            }

            // Extraer y procesar cada campo, con validación de formato y mensajes de depuración
            int id = Integer.parseInt(obtenerValorCampo(partes[0], "ID Reserva"));
            String nombre = obtenerValorCampo(partes[1], "Nombre");
            LocalDate fecha = LocalDate.parse(obtenerValorCampo(partes[4], "Fecha"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // Ajuste en el procesamiento del campo Hora
            String horaTexto = obtenerValorCampoHora(partes[5]);
            LocalTime hora = LocalTime.parse(horaTexto, DateTimeFormatter.ofPattern("HH:mm"));

            int mesa = Integer.parseInt(obtenerValorCampo(partes[6], "Mesa"));

            // Crear y retornar el objeto Reserva
            return new Reserva(this, new Mesa(mesa), fecha, null, hora);

        } catch (Exception e) {
            System.err.println("Error al procesar la línea: " + linea);
            e.printStackTrace();
            return null; // Retorna null si hay algún error en el procesamiento
        }
    }

    /**
     * obtenerValorCampo: Método auxiliar para obtener el valor de un campo en formato "Campo: Valor".
     * @param campo: campo en formato "Campo: Valor".
     * @param nombreCampo: nombre del campo para mensajes de error.
     * @return: Retorna el valor del campo; cadena vacía si el formato es incorrecto.
     */
    private String obtenerValorCampo(String campo, String nombreCampo) {
        int indice = campo.indexOf(":");
        if (indice != -1 && indice + 1 < campo.length()) {
            return campo.substring(indice + 1).trim(); // Obtiene el valor después de ":"
        } else {
            System.err.println("Error: Formato incorrecto para el campo '" + nombreCampo + "' en: " + campo);
            return ""; // Retorna cadena vacía si no está en el formato correcto
        }
    }

    /**
     * obtenerValorCampoHora: Método auxiliar específico para obtener el valor de "Hora".
     * @param campo: campo que contiene la hora en formato "Hora: Valor".
     * @return: Retorna el valor de la hora; cadena vacía si el formato es incorrecto.
     */
    private String obtenerValorCampoHora(String campo) {
        // Buscar "Hora" y extraer el valor después del espacio
        if (campo.startsWith("Hora: ")) {
            return campo.substring(5).trim(); // Extraer después de "Hora "
        } else {
            System.err.println("Error: Formato incorrecto para el campo 'Hora' en: " + campo);
            return ""; // Retorna cadena vacía si no está en el formato correcto
        }
    }

    /**
     * obtenerNombreDeLinea: Método para extraer el nombre del cliente de una línea.
     * @param linea: línea del archivo que contiene la información del cliente.
     * @return: Retorna el nombre del cliente si se encuentra; null en caso contrario.
     */
    private String obtenerNombreDeLinea(String linea) {
        // Asume que la línea tiene el formato "Nombre: Juan, Otros datos..."
        String[] partes = linea.split(", ");
        for (String parte : partes) {
            if (parte.startsWith("Nombre: ")) {
                return parte.split(": ")[1]; // Retorna solo el nombre
            }
        }
        return null; // Retorna null si no se encuentra un nombre
    }

    /**
     * pagarXInasistencia: Realiza un pago en caso de que el cliente no se presente a la reserva.
     * @param estado: estado en el que se encuentra la reserva.
     */
    public void pagarXInasistencia(Estado estado) {
        double montoInasistencia = 50.0; // Monto fijo por inasistencia
        int fechaActual = 20231025;

        for (Reserva reserva : listaReservasClientes) {
            if (reserva.getEstado() == Estado.NO_ASISTIO) {
                Pago pagoInasistencia = new Pago(montoInasistencia, fechaActual, this.tarjetaCredito);
                System.out.println("Cobro por inasistencia realizado para el cliente: " + nombre);
                System.out.println("Detalles del pago: " + pagoInasistencia.toString());
            }
        }
    }
}