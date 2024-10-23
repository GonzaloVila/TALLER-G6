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

public class Administrador extends  Empleado {
    private Calendario calendario;
    private List<Evento> listaEventos;

    public Administrador() {
        super();
        this.listaEventos = new ArrayList<>();
    }

    public Administrador(List<Evento> listaEventos, int idempleado, String nombre, Permiso permiso, Rol rol, Calendario calendario) {
        super(idempleado, nombre, permiso, rol);
        this.calendario = calendario;
        this.listaEventos = listaEventos;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void agregarEventos(Evento evento){
        listaEventos.add(evento);
    }

    public Administrador(Calendario calendario) {
        this.calendario = calendario;
        this.listaEventos = new ArrayList<>(); // Inicializa la lista de eventos
    }


    @Override
    public String toString() {
        return "LogicaNegocio.Administrador{" +
                "calendario=" + calendario +
                '}';
    }

    public void guardarEmpleadoEnArchivo(Empleado empleado) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("empleados.txt", true))) {
            // Escribir encabezados solo si el archivo está vacío
            File file = new File("empleados.txt");
            if (file.length() == 0) {
                writer.write("Lista de Empleados");
                writer.newLine(); // Nueva línea para los datos
            }
            // Escribir los datos del empleado en una sola línea
            writer.write("ID: " + empleado.getIdempleado() +
                    ", Nombre: " + empleado.getNombre() +
                    ", Permiso: " + empleado.getPermiso() +
                    ", Rol: " + empleado.getRol());
            writer.newLine(); // Nueva línea para el próximo empleado
            System.out.println("Empleado guardado correctamente en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar el empleado: " + e.getMessage());
        }
    }

    public void crearCuentaEmpleado(int idEmpleado, String nombre, Permiso permiso, Rol rol) throws EmpleadoException {

        if (idExiste(idEmpleado)) {
            throw new EmpleadoException("El ID de empleado ya está en uso. Por favor, elija otro ID.");
        }
        validarID(idEmpleado);
        validarNombre(nombre);
        validarPermiso(permiso);
        validarRol(rol);

        setIdempleado(idEmpleado);
        setNombre(nombre);
        setPermiso(permiso);
        setRol(rol);
        Empleado nuevoEmpleado = new Empleado(idEmpleado, nombre, permiso, rol);
        guardarEmpleadoEnArchivo(nuevoEmpleado);

        System.out.println("Nueva cuenta creada para el empleado: " + getNombre());
    }

    private void validarNombre(String nombre) throws EmpleadoException {
        if (nombre == null || nombre.matches(".*\\d.*") || nombre.isEmpty()) {
            throw new EmpleadoException("El nombre no puede contener valores numéricos ni estar vacío.");
        }
    }

    private void validarID(int idempleado) throws EmpleadoException {
        if (idempleado <= 0) {
            throw new EmpleadoException("El ID del empleado debe ser un numero positivo");
        }
        String idEmpleadoStr = String.valueOf(idempleado);
        if (!idEmpleadoStr.matches("\\d+")) {
            throw new EmpleadoException("El ID del empleado solo debe contener dígitos numéricos.");
        }
        if (idempleado > 1_000_000) {
            throw new EmpleadoException("El ID del empleado es demasiado grande. Debe ser menor a 1,000,000.");
        }
        //agregar un metodo para saber si el id ya esta en uso
    }

    private void validarPermiso(Permiso permiso) throws EmpleadoException {
        if (permiso == null) {
            throw new EmpleadoException("El permiso no puede ser nulo");
        }
    }

    private void validarRol(Rol rol) throws EmpleadoException {
        if (rol == null) {
            throw new EmpleadoException("El rol no puede ser nulo");
        }
    }

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

    // Método para validar la entrada de hora
    private LocalTime validarHora(String horaStr) {
        horaStr = horaStr.trim(); // Eliminar espacios en blanco

        if (horaStr.isEmpty()) {
            throw new IllegalArgumentException("La hora no puede estar vacía.");
        }

        try {
            return LocalTime.parse(horaStr); // Intentar parsear la hora
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de hora no válido. Debe ser en el formato HH:mm.");
        }
    }


    public void eliminarEmpleadoDelArchivo(int idEmpleado) throws EmpleadoException {
        File file = new File("empleados.txt");
        File tempFile = new File("empleados_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            boolean encontrado = false;

            // Leer el archivo original línea por línea
            while ((linea = reader.readLine()) != null) {
                // Comprobar si la línea contiene el ID del empleado a eliminar
                if (linea.contains("ID: " + idEmpleado + ",")) {
                    encontrado = true; // Marcamos que hemos encontrado el empleado
                    continue; // No escribimos esta línea en el nuevo archivo
                }

                // Si no estamos en un registro a eliminar, escribir la línea en el archivo temporal
                writer.write(linea);
                writer.newLine(); // Nueva línea para el siguiente registro
            }

            if (!encontrado) {
                throw new EmpleadoException("No se encontró un empleado con el ID proporcionado en el archivo.");
            }

        } catch (IOException e) {
            throw new EmpleadoException("Error al eliminar el empleado: " + e.getMessage());
        }

        // Reemplazar el archivo original con el temporal
        if (!file.delete() || !tempFile.renameTo(file)) {
            throw new EmpleadoException("No se pudo actualizar el archivo de empleados.");
        }

        System.out.println("Empleado con ID " + idEmpleado + " eliminado del archivo.");
    }






    public static void eliminarCliente(String correo) throws Exception {
        File file = new File("clientes.txt");
        File tempFile = new File("clientes_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            boolean encontrado = false;

            // Leer el archivo original línea por línea
            while ((linea = reader.readLine()) != null) {
                // Comprobar si la línea contiene el correo del cliente a eliminar
                if (linea.contains("Correo: " + correo + ",")) {
                    encontrado = true; // Marcamos que hemos encontrado el cliente
                    continue; // No escribimos esta línea en el nuevo archivo
                }

                // Si no estamos en un registro a eliminar, escribir la línea en el archivo temporal
                writer.write(linea);
                writer.newLine(); // Nueva línea para el siguiente registro
            }

            if (!encontrado) {
                throw new Exception("No se encontró un cliente con el correo proporcionado en el archivo.");
            }

        } catch (IOException e) {
            throw new Exception("Error al eliminar el cliente: " + e.getMessage());
        }

        // Reemplazar el archivo original con el temporal
        if (!file.delete() || !tempFile.renameTo(file)) {
            throw new Exception("No se pudo actualizar el archivo de clientes.");
        }

        System.out.println("Cliente con correo " + correo + " eliminado del archivo.");
    }

}















/**
 * Definir horario del restaurante.
 *
 * @param inicio: hora de apertura del restaurante.
 * @param fin: hora de cierre del restaurante.
 */

    /**
     *
     * @param evento: Se establece un dia especial, que es un evento.
     */
    /**
     * public void configurarDiaEspecial(Evento evento) {
     * try {
     * // Verificar que el evento no sea nulo
     * if (evento == null) {
     * throw new Exception("El evento no puede ser nulo");
     * }
     * <p>
     * // Comprobar que la fecha y horas sean válidas
     * if (evento.getFecha() == null || evento.getHoraInicio() == null || evento.getHoraFin() == null) {
     * throw new Exception("La fecha y las horas del evento no pueden ser nulas");
     * }
     * <p>
     * // Validar que la hora de inicio sea antes de la hora de fin
     * if (evento.getHoraInicio().isAfter(evento.getHoraFin())) {
     * throw new Exception("La hora de inicio no puede ser después de la hora de fin");
     * }
     * <p>
     * // Comprobar si el horario del evento está disponible
     * Horario nuevoHorario = new Horario(evento.getFecha(), evento.getHoraInicio(), evento.getHoraFin());
     * if (!calendario.estaDisponible(nuevoHorario)) {
     * throw new Exception("El horario para el evento ya está ocupado");
     * }
     * <p>
     * // Si todo es válido, agregar el evento
     * listaEventos.add(evento);
     * System.out.println("Día especial configurado: " + evento.getNombre());
     * <p>
     * } catch (Exception e) {
     * System.out.println("Error al configurar el día especial: " + e.getMessage());
     * }
     * }
     */

