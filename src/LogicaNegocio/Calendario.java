package LogicaNegocio;

import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.time.format.DateTimeParseException;

/**
 * Clase que representa un calendario que contiene información sobre los días,
 * horarios y administradores, así como el tipo de día.
 */
public class Calendario {
    private ArrayList<Horario> dias; // Lista de horarios por día
    private TipoDeDia tipoDeDia; // Tipo de día (ej. laboral, festivo)
    private List<Administrador> administradores; // Lista de administradores asociados al calendario
    private List<Horario> horarios; // Lista de horarios del calendario

    /**
     * Constructor por defecto que inicializa el calendario con listas vacías.
     */
    public Calendario() {
        this.administradores = new ArrayList<>();
        this.horarios = new ArrayList<>();
    }

    /**
     * Constructor que permite inicializar el calendario con administradores, días,
     * horarios y tipo de día específicos.
     *
     * @param administradores Lista de administradores asociados al calendario.
     * @param dias Lista de horarios por día.
     * @param horarios Lista de horarios del calendario.
     * @param tipoDeDia Tipo de día (ej. laboral, festivo).
     */
    public Calendario(List<Administrador> administradores, ArrayList<Horario> dias, List<Horario> horarios, TipoDeDia tipoDeDia) {
        this.administradores = administradores;
        this.dias = dias;
        this.horarios = horarios;
        this.tipoDeDia = tipoDeDia;
    }

    /**
     * Obtiene la lista de días (horarios).
     *
     * @return Lista de horarios por día.
     */
    public ArrayList<Horario> getDias() {
        return dias;
    }

    /**
     * Establece la lista de días (horarios).
     *
     * @param dias Lista de horarios por día a establecer.
     */
    public void setDias(ArrayList<Horario> dias) {
        this.dias = dias;
    }

    /**
     * Obtiene el tipo de día.
     *
     * @return Tipo de día.
     */
    public TipoDeDia getTipoDeDia() {
        return tipoDeDia;
    }

    /**
     * Establece el tipo de día.
     *
     * @param tipoDeDia Tipo de día a establecer.
     */
    public void setTipoDeDia(TipoDeDia tipoDeDia) {
        this.tipoDeDia = tipoDeDia;
    }

    /**
     * Obtiene la lista de administradores asociados al calendario.
     *
     * @return Lista de administradores.
     */
    public List<Administrador> getAdministradores() {
        return administradores;
    }

    /**
     * Establece la lista de administradores.
     *
     * @param administradores Lista de administradores a establecer.
     */
    public void setAdministradores(List<Administrador> administradores) {
        this.administradores = administradores;
    }

    /**
     * Obtiene la lista de horarios del calendario.
     *
     * @return Lista de horarios.
     */
    public List<Horario> getHorarios() {
        return horarios;
    }

    /**
     * Establece la lista de horarios.
     *
     * @param horarios Lista de horarios a establecer.
     */
    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    /**
     * Devuelve una representación en cadena del calendario.
     *
     * @return Representación en cadena del calendario.
     */
    @Override
    public String toString() {
        return "LogicaNegocio.Calendario{" +
                "administradores=" + administradores +
                ", dias=" + dias +
                ", tipoDeDia=" + tipoDeDia +
                ", horarios=" + horarios +
                '}';

    }

    /**
     * Guarda los horarios en un archivo especificado.
     * Este metodo escribe cada horario en el archivo en el formato:
     * "fecha,horaInicio,horaFin", donde:
     * - fecha es la fecha del horario.
     * - horaInicio es la hora de inicio del horario.
     * - horaFin es la hora de fin del horario.
     *
     * @param nombreArchivo El nombre del archivo donde se guardarán los horarios.
     */
    public void guardarHorariosEnArchivo(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Horario horario : horarios) {
                writer.write(horario.getDia() + "," + horario.getHoraInicio() + "," + horario.getHoraFin());
                writer.newLine(); // Nueva línea para cada horario
            }
            System.out.println("Horarios guardados correctamente en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar horarios: " + e.getMessage());
        }
    }

    /**
     * Carga los horarios desde un archivo especificado.
     *
     * Este metodo lee los horarios del archivo en el formato:
     * "fecha,horaInicio,horaFin" y los convierte en objetos de la clase Horario.
     *
     * @param nombreArchivo El nombre del archivo desde el cual se cargarán los horarios.
     * @return Una lista de objetos Horario cargados desde el archivo.
     */
    public List<Horario> cargarHorariosDesdeArchivo(String nombreArchivo) {
        List<Horario> horarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                LocalDate dia = LocalDate.parse(partes[0]);
                LocalTime horaInicio = LocalTime.parse(partes[1]);
                LocalTime horaFin = LocalTime.parse(partes[2]);
                horarios.add(new Horario(dia, horaInicio, horaFin));
            }
            System.out.println("Horarios cargados correctamente desde " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al cargar horarios: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Error al parsear la fecha u hora: " + e.getMessage());
        }
        return horarios;
    }
}