package LogicaNegocio;

import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.time.format.DateTimeParseException;


public class Calendario {
    private ArrayList<Horario> dias;
    private TipoDeDia tipoDeDia;
    private List<Administrador> administradores;
    private List<Horario> horarios;

    public Calendario() {
        this.administradores = new ArrayList<>();
        this.horarios = new ArrayList<>();
    }

    public Calendario(List<Administrador> administradores, ArrayList<Horario> dias, List<Horario> horarios, TipoDeDia tipoDeDia) {
        this.administradores = administradores;
        this.dias = dias;
        this.horarios = horarios;
        this.tipoDeDia = tipoDeDia;
    }

    public ArrayList<Horario> getDias() {
        return dias;
    }

    public void setDias(ArrayList<Horario> dias) {
        this.dias = dias;
    }

    public TipoDeDia getTipoDeDia() {
        return tipoDeDia;
    }

    public void setTipoDeDia(TipoDeDia tipoDeDia) {
        this.tipoDeDia = tipoDeDia;
    }

    public List<Administrador> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(List<Administrador> administradores) {
        this.administradores = administradores;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    @Override
    public String toString() {
        return "LogicaNegocio.Calendario{" +
                "administradores=" + administradores +
                ", dias=" + dias +
                ", tipoDeDia=" + tipoDeDia +
                ", horarios=" + horarios +
                '}';
    }

    public void guardarHorariosEnArchivo(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Horario horario : horarios) {
                // Escribe la fecha, la hora de inicio y la hora de fin en el formato correcto
                writer.write(horario.getDia() + "," + horario.getHoraInicio() + "," + horario.getHoraFin());
                writer.newLine(); // Nueva línea para cada horario
            }
            System.out.println("Horarios guardados correctamente en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar horarios: " + e.getMessage());
        }
    }

    public List<Horario> cargarHorariosDesdeArchivo(String nombreArchivo) {
        List<Horario> horarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("horarios.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                LocalDate dia = LocalDate.parse(partes[0]); // Asegúrate de que el primer elemento sea una fecha en el formato correcto
                LocalTime horaInicio = LocalTime.parse(partes[1]); // Segundo elemento es hora de inicio
                LocalTime horaFin = LocalTime.parse(partes[2]); // Tercer elemento es hora de fin
                horarios.add(new Horario(dia, horaInicio, horaFin)); // Agregar el nuevo horario a la lista
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