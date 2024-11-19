package LogicaNegocio;

import Excepciones.EmpleadoException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeParseException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;

/**
 * Clase que representa a un administrador en el sistema, que hereda de la clase Empleado.
 * Un administrador puede gestionar un calendario y eventos asociados.
 */
public class Administrador extends Empleado {
    // Atributos
    private Calendario calendario; // Calendario del administrador
    private List<Evento> listaEventos; // Lista de eventos gestionados por el administrador

    /**
     * Constructor por defecto que inicializa la lista de eventos.
     */
    public Administrador() {
        super();
        this.listaEventos = new ArrayList<>();
    }

    /**
     * Constructor que inicializa un Administrador con los atributos especificados.
     *
     * @param listaEventos Lista de eventos a gestionar por el administrador
     * @param idempleado   Identificador del empleado
     * @param nombre       Nombre del empleado
     * @param mail         Correo electrónico del empleado
     * @param contrasenia  Contraseña del empleado
     * @param permiso      Permiso del empleado
     * @param rol          Rol del empleado
     * @param calendario   Calendario asociado al administrador
     */
    public Administrador(List<Evento> listaEventos, int idempleado, String nombre, String mail, String contrasenia, Permiso permiso, Rol rol, Calendario calendario) {
        super(idempleado, nombre, mail, contrasenia, permiso, rol);
        this.calendario = calendario;
        this.listaEventos = listaEventos;
    }

    /**
     * Obtiene el calendario del administrador.
     *
     * @return Calendario del administrador
     */
    public Calendario getCalendario() {
        return calendario;
    }

    /**
     * Establece un nuevo calendario para el administrador.
     *
     * @param calendario Nuevo calendario a establecer
     */
    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    /**
     * Obtiene la lista de eventos gestionados por el administrador.
     *
     * @return Lista de eventos
     */
    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    /**
     * Agrega un nuevo evento a la lista de eventos del administrador.
     *
     * @param evento Evento a agregar
     */
    public void agregarEventos(Evento evento) {
        listaEventos.add(evento);
    }

    /**
     * Constructor que inicializa el administrador con un calendario.
     *
     * @param calendario Calendario a asociar al administrador
     */
    public Administrador(Calendario calendario) {
        this.calendario = calendario;
        this.listaEventos = new ArrayList<>();
    }

    /**
     * Devuelve una representación en forma de cadena del administrador.
     *
     * @return Cadena que representa al administrador
     */
    @Override
    public String toString() {
        return "LogicaNegocio.Administrador{" +
                "calendario=" + calendario +
                '}';
    }


    /**
     * Guarda los datos de un empleado en un archivo de texto.
     *
     * @param empleado El empleado cuyos datos se desean guardar.
     */
    public void guardarEmpleadoEnArchivo(Empleado empleado) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("empleados.txt", true))) {
            File file = new File("empleados.txt");
            if (file.length() == 0) {
                writer.write("Lista de Empleados");
                writer.newLine(); // Nueva línea para los datos
            }
            writer.write("ID: " + empleado.getIdempleado() +
                    ", Nombre: " + empleado.getNombre() +
                    ", Email: " + empleado.getMail() +
                    ", Contraseña: " + empleado.getContrasenia() +
                    ", Permiso: " + empleado.getPermiso() +
                    ", Rol: " + empleado.getRol());
            writer.newLine();
            System.out.println("Empleado guardado correctamente en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar el empleado: " + e.getMessage());
        }
    }

    /**
     * Crea una cuenta para un nuevo empleado con los datos proporcionados.
     *
     * @param idEmpleado  El ID del nuevo empleado.
     * @param nombre      El nombre del nuevo empleado.
     * @param mail        El correo electrónico del nuevo empleado.
     * @param contrasenia La contraseña del nuevo empleado.
     * @param permiso     El permiso asignado al nuevo empleado.
     * @param rol         El rol asignado al nuevo empleado.
     * @throws EmpleadoException Si el ID del empleado ya está en uso o no es válido.
     */
    public void crearCuentaEmpleado(int idEmpleado, String nombre, String mail, String contrasenia, Permiso permiso, Rol rol) throws EmpleadoException {
        if (idExiste(idEmpleado)) {
            throw new EmpleadoException("El ID de empleado ya está en uso. Por favor, elija otro ID.");
        }
        validarID(idEmpleado);
        validarNombre(nombre);
        validarCorreo(mail); // Nueva validación para el correo
        validarPermiso(permiso);
        validarRol(rol);

        setIdempleado(idEmpleado);
        setNombre(nombre);
        setPermiso(permiso);
        setRol(rol);
        Empleado nuevoEmpleado = new Empleado(idEmpleado, nombre, mail, contrasenia, permiso, rol); // Crear el nuevo empleado con correo y contraseña
        guardarEmpleadoEnArchivo(nuevoEmpleado);

        System.out.println("Nueva cuenta creada para el empleado: " + getNombre());
    }

    /**
     * Valida el nombre del empleado.
     *
     * @param nombre El nombre a validar.
     * @throws EmpleadoException Si el nombre es nulo, vacío o contiene valores numéricos.
     */
    private void validarNombre(String nombre) throws EmpleadoException {
        if (nombre == null || nombre.matches(".*\\d.*") || nombre.isEmpty()) {
            throw new EmpleadoException("El nombre no puede contener valores numéricos ni estar vacío.");
        }
    }

    /**
     * Valida el correo electrónico del empleado.
     *
     * @param correo El correo a validar.
     * @throws EmpleadoException Si el correo es nulo o no tiene un formato válido.
     */
    private void validarCorreo(String correo) throws EmpleadoException {
        if (correo == null || !correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new EmpleadoException("El correo no es válido.");
        }
    }

    /**
     * Valida el ID del empleado.
     *
     * @param idempleado El ID a validar.
     * @throws EmpleadoException Si el ID es menor o igual a cero, no es numérico o es demasiado grande.
     */
    private void validarID(int idempleado) throws EmpleadoException {
        if (idempleado <= 0) {
            throw new EmpleadoException("El ID del empleado debe ser un número positivo.");
        }
        String idEmpleadoStr = String.valueOf(idempleado);
        if (!idEmpleadoStr.matches("\\d+")) {
            throw new EmpleadoException("El ID del empleado solo debe contener dígitos numéricos.");
        }
        if (idempleado > 1_000_000) {
            throw new EmpleadoException("El ID del empleado es demasiado grande. Debe ser menor a 1,000,000.");
        }
    }

    /**
     * Valida el permiso del empleado.
     *
     * @param permiso El permiso a validar.
     * @throws EmpleadoException Si el permiso es nulo.
     */
    private void validarPermiso(Permiso permiso) throws EmpleadoException {
        if (permiso == null) {
            throw new EmpleadoException("El permiso no puede ser nulo.");
        }
    }

    /**
     * Valida el rol del empleado.
     *
     * @param rol El rol a validar.
     * @throws EmpleadoException Si el rol es nulo.
     */
    private void validarRol(Rol rol) throws EmpleadoException {
        if (rol == null) {
            throw new EmpleadoException("El rol no puede ser nulo.");
        }
    }

    /**
     * Verifica si el ID de un empleado ya existe en el archivo.
     *
     * @param idEmpleado El ID del empleado a verificar.
     * @return true si el ID ya existe, false en caso contrario.
     */
    public boolean idExiste(int idEmpleado) {
        try (BufferedReader reader = new BufferedReader(new FileReader("empleados.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.contains("ID: " + idEmpleado + ",")) {
                    return true; // El ID ya existe
                }
            }
        } catch (IOException e) {
            System.out.println("Error al verificar el ID: " + e.getMessage());
        }
        return false; // El ID no existe
    }

    /**
     * Establece horarios fijos para la semana en el calendario.
     *
     * @param inicioSemanaStr      Hora de inicio para la semana (formato HH:mm).
     * @param finSemanaStr         Hora de fin para la semana (formato HH:mm).
     * @param inicioFinDeSemanaStr Hora de inicio para el fin de semana (formato HH:mm).
     * @param finFinDeSemanaStr    Hora de fin para el fin de semana (formato HH:mm).
     */
    public void establecerHorariosFijos(String inicioSemanaStr, String finSemanaStr, String inicioFinDeSemanaStr, String finFinDeSemanaStr) {
        // Validar y establecer horarios en el calendario
        LocalTime inicioSemana = validarHora(inicioSemanaStr);
        LocalTime finSemana = validarHora(finSemanaStr);
        LocalTime inicioFinDeSemana = validarHora(inicioFinDeSemanaStr);
        LocalTime finFinDeSemana = validarHora(finFinDeSemanaStr);

        calendario.getHorarios().clear();

        // Establecer horarios de Domingo a Jueves
        for (int i = 0; i < 5; i++) {
            LocalDate dia = LocalDate.now().with(java.time.DayOfWeek.SUNDAY).plusDays(i);
            calendario.getHorarios().add(new Horario(dia, inicioSemana, finSemana));
        }

        // Establecer horarios de Viernes y Sábado
        for (int i = 5; i < 7; i++) {
            LocalDate dia = LocalDate.now().with(java.time.DayOfWeek.SUNDAY).plusDays(i);
            calendario.getHorarios().add(new Horario(dia, inicioFinDeSemana, finFinDeSemana));
        }

        // Guardar en archivo
        calendario.guardarHorariosEnArchivo("horarios.txt");
    }

    /**
     * Metodo para validar la entrada de hora:
     *
     * @param horaStr La hora en formato String a validar (formato HH:mm).
     * @return La hora validada como un objeto LocalTime.
     * @throws IllegalArgumentException Si la hora está vacía o no tiene un formato válido.
     */
    private LocalTime validarHora(String horaStr) {
        horaStr = horaStr.trim();

        if (horaStr.isEmpty()) {
            throw new IllegalArgumentException("La hora no puede estar vacía.");
        }

        try {
            return LocalTime.parse(horaStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de hora no válido. Debe ser en el formato HH:mm.");
        }
    }

    /**
     * Elimina un empleado del archivo de empleados.
     *
     * @param idEmpleado El ID del empleado a eliminar.
     * @throws EmpleadoException Si no se encuentra el empleado con el ID proporcionado o si ocurre un error al acceder al archivo.
     */
    public void eliminarEmpleadoDelArchivo(int idEmpleado) throws EmpleadoException {
        File file = new File("empleados.txt");
        File tempFile = new File("empleados_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            boolean encontrado = false;
            while ((linea = reader.readLine()) != null) {
                if (linea.contains("ID: " + idEmpleado + ",")) {
                    encontrado = true;
                    continue;
                }
                writer.write(linea);
                writer.newLine();
            }
            if (!encontrado) {
                throw new EmpleadoException("No se encontró un empleado con el ID proporcionado en el archivo.");
            }
        } catch (IOException e) {
            throw new EmpleadoException("Error al eliminar el empleado: " + e.getMessage());
        }
        if (!file.delete() || !tempFile.renameTo(file)) {
            throw new EmpleadoException("No se pudo actualizar el archivo de empleados.");
        }

        System.out.println("Empleado con ID " + idEmpleado + " eliminado del archivo.");
    }

    /**
     * Elimina un cliente del archivo de clientes basado en su correo electrónico.
     *
     * @param correo El correo electrónico del cliente a eliminar.
     * @throws Exception Si no se encuentra un cliente con el correo proporcionado o si ocurre un error al acceder al archivo.
     */
    public void eliminarCliente(String correo) throws Exception {
        File file = new File("clientes.txt");
        File tempFile = new File("clientes_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            boolean encontrado = false;
            while ((linea = reader.readLine()) != null) {
                if (linea.contains("Correo: " + correo + ",")) {
                    encontrado = true;
                    continue;
                }
                writer.write(linea);
                writer.newLine();
            }

            if (!encontrado) {
                throw new Exception("No se encontró un cliente con el correo proporcionado en el archivo.");
            }

        } catch (IOException e) {
            throw new Exception("Error al eliminar el cliente: " + e.getMessage());
        }

        if (!file.delete() || !tempFile.renameTo(file)) {
            throw new Exception("No se pudo actualizar el archivo de clientes.");
        }

        System.out.println("Cliente con correo " + correo + " eliminado del archivo.");
    }
}