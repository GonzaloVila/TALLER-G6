package InterfacesGraficas;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import LogicaNegocio.Cliente;
import LogicaNegocio.Mesa;
import LogicaNegocio.Reporte;
import LogicaNegocio.Reserva;
import LogicaNegocio.Formato;
import com.itextpdf.layout.element.Table;

public class VentanaReporte extends JFrame {
    private final Reporte reporte;
    private JComboBox<Cliente> clienteComboBox_HRF;  // ComboBox para seleccionar un Cliente
    private JComboBox<Cliente> clienteComboBox_HR;  // ComboBox para seleccionar un Cliente
    private Formato formato;

    public VentanaReporte(Reporte reporte) {
        this.reporte = reporte;

        setTitle("Reportes del Restaurante");
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    public void initUI() {
        //Panel con pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        //Pestaña para HISTORIAL DE RESERVAS FUTURAS
        JPanel panel_HistorialReservasFuturas = crearPanelHistorialReservasFuturas();
        tabbedPane.addTab("Reservas futuras", panel_HistorialReservasFuturas);

        //Pestaña para HISTORIAL DE RESERVAS TOTAL
        JPanel panel_HistorialReservas = crearPanelHistorialReservas();
        tabbedPane.addTab("Historial de Reservas", panel_HistorialReservas);

        //Pestaña para MAYOR CANTIDAD DE RESERVAS ASISTIDAS
        JPanel panel_MayorCantidadReservas = crearPanelMayorCantidadReservas();
        tabbedPane.add("Reservas asistidas", panel_MayorCantidadReservas);

        //Pestaña para RESERVAS NO ASISTIDAS ULTIMO AÑO
        JPanel panel_ReservasNoAsistidas = crearPanelReservasNoAsistidas();
        tabbedPane.add("Reservas no asistidas", panel_ReservasNoAsistidas);

        //Pestaña para RESERVAS REALIZADAS EN UN RANGO DE FECHAS DADO
        JPanel panel_ReservasRangoFechas = crearPanelReservasRangoFechas();
        tabbedPane.add("Reservas en rango", panel_ReservasRangoFechas);

        //Pestaña para ANALISIS DE CONCURRENCIA DE LOS COMENSALES
        JPanel panel_AnalisisConcurrenciaComensales = crearPanelAnalisisConcurrenciaComensales();
        tabbedPane.add("Analisis de concurrencia", panel_AnalisisConcurrenciaComensales);

        add(tabbedPane);
    }

    //Creacion PANEL - Analisis de concurrencia de los comensales
    private JPanel crearPanelAnalisisConcurrenciaComensales() {
        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(new JLabel("Analisis de concurrencia de los comensales"));

        // Crear la tabla con el modelo de datos
        String[] columnas = {"Estación", "Total de Comensales"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modeloTabla);

        for (Map.Entry<String, Integer> entry : reporte.analisisConcurrenciaPorEstacion().entrySet()) {
            modeloTabla.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }

        JScrollPane scrollPane = new JScrollPane(tabla);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Crear botón para exportar a PDF
        JButton button_ExportarPDF_AnalisisConcurrencia = new JButton("Exportar a PDF");
        panel.add(button_ExportarPDF_AnalisisConcurrencia);
        // Crear botón para exportar a EXCEL
        JButton button_ExportarEXCEL_AnalisisConcurrencia = new JButton("Exportar a EXCEL");
        panel.add(button_ExportarEXCEL_AnalisisConcurrencia);

        //ACCION BOTON EXPORTAR
        accionBtn_exportar_AnalisisConcurrenciaComensales(button_ExportarPDF_AnalisisConcurrencia, tabla, Formato.PDF);
        accionBtn_exportar_AnalisisConcurrenciaComensales(button_ExportarEXCEL_AnalisisConcurrencia, tabla, Formato.EXCEL);

        return panel;
    }

    //Creacion PANEL - Reservas realizadas en un rango de fechas
    private JPanel crearPanelReservasRangoFechas() {
        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(new JLabel("Reservas realizadas en un rango de fechas"));
        panel.add(new JLabel("Seleccione un rango: "));

        JTextField fechaInicioField = new JTextField("YYYY-MM-DD", 10);
        JTextField fechaFinField = new JTextField("YYYY-MM-DD", 10);

        // Aplica el comportamiento de placeholder en ambos campos
        addPlaceholder(fechaInicioField, "YYYY-MM-DD");
        addPlaceholder(fechaFinField, "YYYY-MM-DD");

        JButton button_ReservasRangoFechas = new JButton("Generar Reporte");

        String[] columnas = {"Mesa", "Fecha", "Inicio", "Fin", "Cliente", "Comensales"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        JTable tablaReservas = new JTable(modeloTabla);

        JPanel fechasPanel = new JPanel();
        fechasPanel.add(new JLabel("Fecha Inicio:"));
        fechasPanel.add(fechaInicioField);
        fechasPanel.add(new JLabel("Fecha Fin:"));
        fechasPanel.add(fechaFinField);
        fechasPanel.add(button_ReservasRangoFechas);

        panel.add(fechasPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(tablaReservas), BorderLayout.CENTER);

        // Boton exportar PDF
        JButton button_ExportarPDF_RangoFechas = new JButton("Exportar a PDF");
        button_ExportarPDF_RangoFechas.setEnabled(false);
        panel.add(button_ExportarPDF_RangoFechas);

        // Boton exportar EXCEL
        JButton button_ExportarEXCEL_RangoFechas = new JButton("Exportar a EXCEL");
        button_ExportarEXCEL_RangoFechas.setEnabled(false);
        panel.add(button_ExportarEXCEL_RangoFechas);

        //ACCION BOTON - Reserva en rango de fechas
        button_ReservasRangoFechas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate fechaInicio = LocalDate.parse(fechaInicioField.getText(), formatter);
                    LocalDate fechaFin = LocalDate.parse(fechaFinField.getText(), formatter);

                    List<Reserva> reservas = reporte.reservaRealizadas(fechaInicio, fechaFin);

                    modeloTabla.setRowCount(0);

                    for (Reserva reserva : reservas) {
                        Mesa mesa = reserva.getMesa();
                        Cliente cliente = reserva.getCliente();
                        Object[] fila = {
                                mesa.getNumMesa(),
                                reserva.getFecha().format(formatter),
                                reserva.getHoraInicio(),
                                reserva.getHoraFinal(),
                                cliente.getNombre(),
                                mesa.getCapacidad()
                        };
                        modeloTabla.addRow(fila);
                    }


                    if (reservas.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No se encontraron reservas en el rango de fechas dado.");
                    }else{
                        button_ExportarPDF_RangoFechas.setEnabled(true);
                        button_ExportarEXCEL_RangoFechas.setEnabled(true);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al procesar las fechas. Asegúrese de usar el formato YYYY-MM-DD.");
                }
            }
        });

        //ACCION BOTON EXPORTE PDF
        button_ExportarPDF_RangoFechas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarPDF_ReservasRangoFechas(tablaReservas);
            }
        });

        //ACCION BOTON EXPORTE EXCEL
        button_ExportarEXCEL_RangoFechas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarEXCEL_ReservasRangoFechas(tablaReservas);
            }
        });

        return panel;
    }

    //Creacion PANEL - Reservas no asistidas en el último año
    private JPanel crearPanelReservasNoAsistidas() {
        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(new JLabel("Clientes que han realizado reservas pero no han asistido en el último año "));

        JButton button_ReservasNoAsistidas = new JButton("Generar");
        JTextArea resultadoArea = new JTextArea(2, 10);
        resultadoArea.setEditable(false);

        panel.add(new JScrollPane(resultadoArea), BorderLayout.CENTER);
        panel.add(button_ReservasNoAsistidas, BorderLayout.SOUTH);

        //Botones EXPORTAR
        JButton button_exportarPDF_ReservasNoAsistidas = new JButton("Exportar PDF");
        panel.add(button_exportarPDF_ReservasNoAsistidas);

        JButton button_exportarEXCEL_ReservasNoAsistidas = new JButton("Exportar EXCEL");
        panel.add(button_exportarEXCEL_ReservasNoAsistidas);

        //ACCIONES BOTONES
        accionBtn_ReservasNoAsistidas(button_ReservasNoAsistidas, resultadoArea);
        accionBtn_exportar_ReservasNoAsistidas(button_exportarPDF_ReservasNoAsistidas, Formato.PDF);
        accionBtn_exportar_ReservasNoAsistidas(button_exportarEXCEL_ReservasNoAsistidas, Formato.EXCEL);


        return panel;
    }

    //Creacion PANEL - Mayor cantidad de Reservas Asistidas
    private JPanel crearPanelMayorCantidadReservas() {
        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(new JLabel("Cliente con más reservas asistidas"));

        JButton button_MayorCantidadReservas = new JButton("Generar");
        JTextArea resultadoArea = new JTextArea(2, 10);
        resultadoArea.setEditable(false);

        panel.add(new JScrollPane(resultadoArea), BorderLayout.CENTER);
        panel.add(button_MayorCantidadReservas, BorderLayout.SOUTH);

        //Botones de exporte
        JButton button_exportarPDF_MayorCantidadReservas = new JButton("Exportar PDF");
        panel.add(button_exportarPDF_MayorCantidadReservas);

        JButton button_exportarEXCEL_MayorCantidadReservas = new JButton("Exportar EXCEL");
        panel.add(button_exportarEXCEL_MayorCantidadReservas);

        //Acciones de botones
        accionBtn_mayorCantidadReservas(button_MayorCantidadReservas, resultadoArea);

        accionBtn_exportar_MayorCantidadReservas(button_exportarPDF_MayorCantidadReservas, Formato.PDF);
        accionBtn_exportar_MayorCantidadReservas(button_exportarEXCEL_MayorCantidadReservas, Formato.EXCEL);


        return panel;
    }

    //Creacion PANEL - Historial Reservas
    private JPanel crearPanelHistorialReservas() {
        //JComboBox para la lista de clientes
        clienteComboBox_HR = new JComboBox<>();

        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(new JLabel("Seleccione un cliente para ver historial de reservas: "));

        // JComboBox para lista de clientes
        cargarClientes_HR();
        panel.add(clienteComboBox_HR, BorderLayout.EAST);

        // Boton GENERAR
        JButton boton_HistorialReservas = new JButton("Generar");
        panel.add(boton_HistorialReservas, BorderLayout.SOUTH);

        //Boton EXPORTAR
        JButton boton_ExportarPDF_HistorialReservas = new JButton("Exportar a PDF");
        panel.add(boton_ExportarPDF_HistorialReservas);

        JButton boton_ExportarEXCEL_HistorialReservas = new JButton("Exportar a EXCEL");
        panel.add(boton_ExportarEXCEL_HistorialReservas);

        //ACCIONES BOTONES
        accionBtn_exportar_HistorialReservas(boton_ExportarPDF_HistorialReservas, Formato.PDF);
        accionBtn_HistorialReservas(boton_HistorialReservas);
        accionBtn_exportar_HistorialReservas(boton_ExportarEXCEL_HistorialReservas, Formato.EXCEL);

        return panel;
    }

    //Creacion PANEL - Historial Reservas Futuras
    private JPanel crearPanelHistorialReservasFuturas() {
        //JComboBox para la lista de clientes
        clienteComboBox_HRF = new JComboBox<>();

        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(new JLabel("Seleccione un cliente para ver reservas futuras: "));

        // JComboBox para lista de clientes
        cargarClientes_HRF();
        panel.add(clienteComboBox_HRF, BorderLayout.EAST);

        // Boton y evento
        JButton boton_HistorialReservasFuturas = new JButton("Generar");
        panel.add(boton_HistorialReservasFuturas, BorderLayout.SOUTH);

        //Boton EXPORTAR
        JButton boton_ExportarPDF_HistorialReservasFuturas = new JButton("Exportar PDF");
        panel.add(boton_ExportarPDF_HistorialReservasFuturas);

        JButton boton_ExportarEXCEL_HistorialReservasFuturas = new JButton("Exportar a EXCEL");
        panel.add(boton_ExportarEXCEL_HistorialReservasFuturas);

        //ACCIONES BOTONES
        accionBtn_exportar_HistorialReservasFuturas(boton_ExportarPDF_HistorialReservasFuturas, Formato.PDF);
        accionBtn_HistorialReservasFuturas(boton_HistorialReservasFuturas);
        accionBtn_exportar_HistorialReservasFuturas(boton_ExportarEXCEL_HistorialReservasFuturas, Formato.EXCEL);

        return panel;
    }


    // CARGAR - Clientes JComboBox - Historial Reservas
    private void cargarClientes_HR() {
        List<Cliente> clientes = reporte.getClientes(); // Obtener la lista de clientes del reporte
        for (Cliente cliente : clientes) {
            clienteComboBox_HR.addItem(cliente); // Añadir cada cliente al JComboBox
        }
    }
    // CARGAR - Clientes JComboBox - Historial Reservas Futuras
    private void cargarClientes_HRF() {
        List<Cliente> clientes = reporte.getClientes(); // Obtener la lista de clientes del reporte
        for (Cliente cliente : clientes) {
            clienteComboBox_HRF.addItem(cliente); // Añadir cada cliente al JComboBox
        }
    }


    // MOSTRAR - Historial Reservas futuras
    private void mostrarReservasFuturas(ArrayList<Reserva> reservasFuturas) {
        if (reservasFuturas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay reservas futuras para este cliente.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Reserva reserva : reservasFuturas) {
                sb.append("Fecha: ").append(reserva.getFecha())
                        .append(", Mesa: ").append(reserva.getMesa().getNumMesa())
                        .append(", Comensales: ").append(reserva.getMesa().getCapacidad())
                        .append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Reservas Futuras", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    //MOSTRAR - Historial Reservas
    private void mostrarHistorialReservas(List<Reserva> historialReservas) {
        if (historialReservas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay historial de reservas");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Reserva reserva : historialReservas) {
                sb.append("Fecha: ").append(reserva.getFecha())
                        .append(", Mesa: ").append(reserva.getMesa().getNumMesa())
                        .append(", Comensales: ").append(reserva.getMesa().getCapacidad())
                        .append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Historial de reservas", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Agregar el texto de fondo (placeholder) al campo de texto
    private static void addPlaceholder(JTextField textField, String placeholder) {
        // Establecer el texto inicial como el placeholder
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);

        // Añadir el FocusListener para manejar el foco
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Si el texto es igual al placeholder, limpiar el campo al obtener foco
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Si el campo está vacío al perder foco, volver a mostrar el placeholder
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }


    // EXPORTAR PDF - AnalisisConcurrenciaComensales
    private static void exportarPDF_AnalisisConcurrenciaComensales(JTable tabla) {
        //Elige locacion para guardar el archivo
        JFileChooser ruta_seleccion = new JFileChooser();
        ruta_seleccion.setDialogTitle("Guardar PDF");
        int usuario_seleccion = ruta_seleccion.showSaveDialog(null);

        if (usuario_seleccion == JFileChooser.APPROVE_OPTION) {
            try {
                // Obtiene ruta seleccionada por el usuario
                String ruta_archivo = ruta_seleccion.getSelectedFile().getAbsolutePath();
                if (!ruta_archivo.endsWith(".pdf")) {
                    ruta_archivo += ".pdf";
                }

                // Crea el archivo PDF
                PdfWriter writer = new PdfWriter(new FileOutputStream(ruta_archivo));
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf);

                // Añade titulo
                document.add(new Paragraph("Análisis de concurrencia de comensales"));

                // Genera el contenido del PDF
                //Crea la tabla
                float[] medidaColumna = {200f, 200f};
                Table tabla_pdf = new Table(medidaColumna);

                // Añade encabezado al PDF
                tabla_pdf.addHeaderCell("Estación");
                tabla_pdf.addHeaderCell("Total de comensales");

                // Obtiene el modelo de la tabla
                TableModel modelo = tabla.getModel();
                int filas = modelo.getRowCount();
                int columnas = modelo.getColumnCount();

                // Añade las filas al PDF
                for (int f = 0; f < filas; f++) {
                    for (int c = 0; c < columnas; c++) {
                        tabla_pdf.addCell(modelo.getValueAt(f, c).toString());
                    }
                }

                document.add(tabla_pdf);
                document.close();
                writer.close();

                JOptionPane.showMessageDialog(null, "PDF exportado exitosamente.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al exportar el PDF.");
            }
        }
    }
    //EXPORTAR PDF - Historial de reservas
    private static void exportarPDF_HistorialReservas(Cliente clienteSeleccionado) {
        //Elige locacion para guardar el archivo
        JFileChooser ruta_seleccion = new JFileChooser();
        ruta_seleccion.setDialogTitle("Guardar PDF");
        int usuario_seleccion = ruta_seleccion.showSaveDialog(null);

        if (usuario_seleccion == JFileChooser.APPROVE_OPTION) {
            try {
                // Obtiene ruta seleccionada por el usuario
                String ruta_archivo = ruta_seleccion.getSelectedFile().getAbsolutePath();
                if (!ruta_archivo.endsWith(".pdf")) {
                    ruta_archivo += ".pdf";
                }

                // Crea el archivo PDF
                PdfWriter writer = new PdfWriter(new FileOutputStream(ruta_archivo));
                PdfDocument pdf = new PdfDocument(writer);
                Document documento = new Document(pdf);

                // Añadir título y nombre del cliente
                documento.add(new Paragraph("Historial de Reservas").setBold().setFontSize(14));
                documento.add(new Paragraph("Cliente: " + clienteSeleccionado.getNombre()).setFontSize(12));

                float[] medidaColumna = {100f,100f,100f};
                Table pdfTable = new Table(medidaColumna);

                pdfTable.addHeaderCell("Fecha");
                pdfTable.addHeaderCell("N° Mesa");
                pdfTable.addHeaderCell("Comensales");

                for(Reserva r: clienteSeleccionado.consultarHistorialReservas()){
                    pdfTable.addCell(r.getFecha().toString());
                    pdfTable.addCell(r.getMesa().getNumMesa().toString());
                    pdfTable.addCell(r.getMesa().getCapacidad().toString());
                }

                documento.add(pdfTable);
                documento.close();
                writer.close();

                JOptionPane.showMessageDialog(null, "PDF exportado exitosamente!");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al crear el archivo PDF: " + e.getMessage());
            }
        }
    }
    //EXPORTAR PDF - Historial de reservas futuras
    private static void exportarPDF_HistorialReservasFuturas(Cliente clienteSeleccionado, Reporte reporte){
        //Elige locacion para guardar el archivo
        JFileChooser ruta_seleccion = new JFileChooser();
        ruta_seleccion.setDialogTitle("Guardar PDF");
        int usuario_seleccion = ruta_seleccion.showSaveDialog(null);

        if (usuario_seleccion == JFileChooser.APPROVE_OPTION) {
            try {
                // Obtiene ruta seleccionada por el usuario
                String ruta_archivo = ruta_seleccion.getSelectedFile().getAbsolutePath();
                if (!ruta_archivo.endsWith(".pdf")) {
                    ruta_archivo += ".pdf";
                }

                // Crea el archivo PDF
                PdfWriter writer = new PdfWriter(new FileOutputStream(ruta_archivo));
                PdfDocument pdf = new PdfDocument(writer);
                Document documento = new Document(pdf);

                // Añadir título y nombre del cliente
                documento.add(new Paragraph("Historial de Reservas Futuras").setBold().setFontSize(14));
                documento.add(new Paragraph("Cliente: " + clienteSeleccionado.getNombre()).setFontSize(12));

                float[] medidaColumna = {100f,100f,100f};
                Table pdfTable = new Table(medidaColumna);

                pdfTable.addHeaderCell("Fecha");
                pdfTable.addHeaderCell("N° Mesa");
                pdfTable.addHeaderCell("Comensales");

                for(Reserva r: reporte.detalleReservasFuturas(clienteSeleccionado)){
                    pdfTable.addCell(r.getFecha().toString());
                    pdfTable.addCell(r.getMesa().getNumMesa().toString());
                    pdfTable.addCell(r.getMesa().getCapacidad().toString());
                }

                documento.add(pdfTable);
                documento.close();
                writer.close();

                JOptionPane.showMessageDialog(null, "PDF exportado exitosamente!");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al crear el archivo PDF: " + e.getMessage());
            }
        }
    }
    //EXPORTAR PDF - Reserva rango fechas
    private static void exportarPDF_ReservasRangoFechas(JTable tabla) {
        //Elige locacion para guardar el archivo
        JFileChooser ruta_seleccion = new JFileChooser();
        ruta_seleccion.setDialogTitle("Guardar PDF");
        int usuario_seleccion = ruta_seleccion.showSaveDialog(null);

        if (usuario_seleccion == JFileChooser.APPROVE_OPTION) {
            try {
                // Obtiene ruta seleccionada por el usuario
                String ruta_archivo = ruta_seleccion.getSelectedFile().getAbsolutePath();
                if (!ruta_archivo.endsWith(".pdf")) {
                    ruta_archivo += ".pdf";
                }

                // Crea el archivo PDF
                PdfWriter writer = new PdfWriter(new FileOutputStream(ruta_archivo));
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf);

                // Añade titulo
                document.add(new Paragraph("Reservas en un rango de fechas"));

                float[] medidaColumna = {100f, 100f, 100f, 100f, 100f, 100f};
                Table pdfTable = new Table(medidaColumna);

                pdfTable.addHeaderCell("Mesa");
                pdfTable.addHeaderCell("Fecha");
                pdfTable.addHeaderCell("Inicio");
                pdfTable.addHeaderCell("Fin");
                pdfTable.addHeaderCell("Cliente");
                pdfTable.addHeaderCell("Comensales");

                //Obtiene modelo del JTable para leer los datos
                TableModel modelo = tabla.getModel();
                int filas = modelo.getRowCount();
                int col = modelo.getColumnCount();

                for (int f = 0; filas > f; f++) {
                    for (int c = 0; col > c; c++) {
                        pdfTable.addCell(modelo.getValueAt(f, c).toString());
                    }
                }
                document.add(pdfTable);
                document.close();
                writer.close();

                JOptionPane.showMessageDialog(null, "PDF exportado exitosamente!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al exportar el PDF.");
            }
        }
    }
    //EXPORTAR PDF - Reservas no asistidas
    private static void exportarPDF_ReservasNoAsistidas(ArrayList<Cliente> clientes){
        //Elige locacion para guardar el archivo
        JFileChooser ruta_seleccion = new JFileChooser();
        ruta_seleccion.setDialogTitle("Guardar PDF");
        int usuario_seleccion = ruta_seleccion.showSaveDialog(null);

        if (usuario_seleccion == JFileChooser.APPROVE_OPTION) {
            try {
                // Obtiene ruta seleccionada por el usuario
                String ruta_archivo = ruta_seleccion.getSelectedFile().getAbsolutePath();
                if (!ruta_archivo.endsWith(".pdf")) {
                    ruta_archivo += ".pdf";
                }

                // Crea el archivo PDF
                PdfWriter writer = new PdfWriter(new FileOutputStream(ruta_archivo));
                PdfDocument pdf = new PdfDocument(writer);
                Document documento = new Document(pdf);

                // Añadir título y nombre del cliente
                documento.add(new Paragraph("Clientes que han realizado reservas, ").setBold().setFontSize(14));
                documento.add(new Paragraph("pero no han asistido a ellas en el ÚLTIMO AÑO. ").setBold().setFontSize(14));

                float[] medidaColumna = {140f,160f,120f};
                Table pdfTable = new Table(medidaColumna);

                pdfTable.addHeaderCell("Nombre del cliente");
                pdfTable.addHeaderCell("Correo del cliente");
                pdfTable.addHeaderCell("Número del cliente");

                for(Cliente c: clientes){
                    pdfTable.addCell(c.getNombre());
                    pdfTable.addCell(c.getCorreo());
                    pdfTable.addCell(c.getNumero());
                }

                documento.add(pdfTable);
                documento.close();
                writer.close();

                JOptionPane.showMessageDialog(null, "PDF exportado exitosamente!");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al crear el archivo PDF: " + e.getMessage());
            }
        }
    }
    //EXPORTAR PDF - Mayor cantidad de reservas
    private static void exportarPDF_mayorCantidadReservas(Cliente c){
        //Elige locacion para guardar el archivo
        JFileChooser ruta_seleccion = new JFileChooser();
        ruta_seleccion.setDialogTitle("Guardar PDF");
        int usuario_seleccion = ruta_seleccion.showSaveDialog(null);

        if (usuario_seleccion == JFileChooser.APPROVE_OPTION) {
            try {
                // Obtiene ruta seleccionada por el usuario
                String ruta_archivo = ruta_seleccion.getSelectedFile().getAbsolutePath();
                if (!ruta_archivo.endsWith(".pdf")) {
                    ruta_archivo += ".pdf";
                }

                // Crea el archivo PDF
                PdfWriter writer = new PdfWriter(new FileOutputStream(ruta_archivo));
                PdfDocument pdf = new PdfDocument(writer);
                Document documento = new Document(pdf);

                // Añadir título y nombre del cliente
                documento.add(new Paragraph("Cliente con más RESERVAS ASISTIDAS ").setBold().setFontSize(14));

                float[] medidaColumna = {140f,160f,120f};
                Table pdfTable = new Table(medidaColumna);

                pdfTable.addHeaderCell("Nombre del cliente");
                pdfTable.addHeaderCell("Correo del cliente");
                pdfTable.addHeaderCell("Número del cliente");


                pdfTable.addCell(c.getNombre());
                pdfTable.addCell(c.getCorreo());
                pdfTable.addCell(c.getNumero());


                documento.add(pdfTable);
                documento.close();
                writer.close();

                JOptionPane.showMessageDialog(null, "PDF exportado exitosamente!");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al crear el archivo PDF: " + e.getMessage());
            }
        }
    }

    // ACCION BOTON - Mayor cantidad de reservas
    private void accionBtn_mayorCantidadReservas(JButton b, JTextArea t){
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamar al metodo maxAsistenciaCliente() del reporte
                Cliente clienteConMasReservas = reporte.maxAsistenciaCliente();

                // Mostrar los resultados en el JTextArea
                if (clienteConMasReservas != null) {
                    t.setText("Cliente: " + clienteConMasReservas.getNombre());
                } else {
                    t.setText("No hay información de clientes con reservas asistidas.");
                }
            }
        });
    }
    //ACCION BOTON - Historial de reservas
    private void accionBtn_HistorialReservas(JButton b){
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Cliente clienteSeleccionado = (Cliente) clienteComboBox_HR.getSelectedItem();
                if (clienteSeleccionado != null) {
                    List<Reserva> historialReservas = reporte.historialReservaPorCliente(clienteSeleccionado);
                    mostrarHistorialReservas(historialReservas); // Metodo que muestra el resultado en la interfaz
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un cliente válido.");
                }
            }
        });
    }
    //ACCION BOTON - Historial Reservas Futuras
    private void accionBtn_HistorialReservasFuturas(JButton b){
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente clienteSeleccionado = (Cliente) clienteComboBox_HRF.getSelectedItem();
                if (clienteSeleccionado != null) {
                    // Llamar al método detalleReservasFuturas con el cliente seleccionado
                    ArrayList<Reserva> reservasFuturas = reporte.detalleReservasFuturas(clienteSeleccionado);
                    mostrarReservasFuturas(reservasFuturas); // Método que muestra el resultado en la interfaz
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un cliente válido.");

                }
            }
        });
    }
    //ACCION BOTON - Reservas no asistidas en el último año
    private void accionBtn_ReservasNoAsistidas(JButton b, JTextArea text){
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Cliente> clientesNoAsistidos = reporte.clienteMaloInasistencia();

                if (clientesNoAsistidos.size() != 0) {
                    StringBuilder sb = new StringBuilder();
                    for (Cliente c : clientesNoAsistidos) {
                        sb.append("Nombre: ").append(c.getNombre()).append("\n");
                    }
                    text.setText(sb.toString());

                } else {
                    text.setText("No hay información de clientes que no han asistido en el último año");
                }
            }
        });
    }

    //ACCION BOTON EXPORTE- Analisis Concurrencia Comensales
    private void accionBtn_exportar_AnalisisConcurrenciaComensales(JButton b, JTable t, Formato f){
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(f.equals(Formato.PDF)){
                    exportarPDF_AnalisisConcurrenciaComensales(t);
                }else if(f.equals(Formato.EXCEL)){
                    exportarEXCEL_AnalisisConcurrenciaComensales(t);
                }
            }
        });
    }
    //ACCION BOTON EXPORTE - Mayor cantidad de reservas
    private void accionBtn_exportar_MayorCantidadReservas(JButton b, Formato f){
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = reporte.maxAsistenciaCliente();
                if(cliente != null){ //MODIFIQUE PARA PROBAR EXCEL (cliente == null)
                    JOptionPane.showMessageDialog(null, "No hay información de clientes con reservas asistidas.");
                }else{
                    if(f.equals(Formato.PDF)){
                        exportarPDF_mayorCantidadReservas(cliente);
                    }else if(f.equals(Formato.EXCEL)){
                        JOptionPane.showMessageDialog(null, "Deberia exportar :V");
                        exportarEXCEL_mayorCantidadReservas(cliente);
                    }

                }
            }
        });
    }
    //ACCION BOTON EXPORTE - Historial de Reservas
    private void accionBtn_exportar_HistorialReservas(JButton b, Formato f){
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente clienteSeleccionado = (Cliente) clienteComboBox_HR.getSelectedItem();
                if(clienteSeleccionado.consultarHistorialReservas().isEmpty()){
                    JOptionPane.showMessageDialog(null, "No hay historial de reservas");
                }else {
                    if (clienteSeleccionado != null) {
                        if(f.equals(Formato.PDF)){
                            exportarPDF_HistorialReservas(clienteSeleccionado);
                        }else if(f.equals(Formato.EXCEL)){
                            exportarEXCEL_HistorialReservas(clienteSeleccionado);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione un cliente válido.");
                    }
                }
            }
        });
    }
    //ACCION BOTON EXPORTE - Historial Reservas Futuras
    private void accionBtn_exportar_HistorialReservasFuturas(JButton b, Formato f){
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente clienteSeleccionado = (Cliente) clienteComboBox_HRF.getSelectedItem();
                if(reporte.detalleReservasFuturas(clienteSeleccionado).isEmpty()){
                    JOptionPane.showMessageDialog(null, "No hay historial de reservas futuras");
                }else{
                    if (clienteSeleccionado != null) {
                        if(f.equals(Formato.PDF)){
                            exportarPDF_HistorialReservasFuturas(clienteSeleccionado, reporte);
                        }else if(f.equals(Formato.EXCEL)){
                            exportarEXCEL_HistorialReservasFuturas(clienteSeleccionado, reporte);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione un cliente válido.");
                    }
                }
            }
        });
    }
    //ACCION BOTON EXPORTE - Reservas no asistidas en el ultimo año
    private void accionBtn_exportar_ReservasNoAsistidas(JButton b, Formato f){
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Cliente> clientesNoAsistidos = reporte.clienteMaloInasistencia();

                if (clientesNoAsistidos.size() != 0) {
                    if (f.equals(Formato.PDF)) {
                        exportarPDF_ReservasNoAsistidas(clientesNoAsistidos);
                    } else if (f.equals(Formato.EXCEL)){
                        exportarEXCEL_ReservasNoAsistidas(clientesNoAsistidos);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"No hay información de clientes que no han asistido en el último año");
                }
            }
        });
    }

    //ACCION BOTON EXPORTE - Reserva rango fechas SIN USO
    private void accionBtn_exportarPDF_ReservasRangoFechas(JButton b, JTable tabla){
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarPDF_ReservasRangoFechas(tabla);
            }
        });
    }



    //EXPORTAR EXCEL - Analisis Concurrencia Comensales
    private static void exportarEXCEL_AnalisisConcurrenciaComensales(JTable tabla){
        Workbook libro = new XSSFWorkbook();
        Sheet sheet = libro.createSheet("Analisis de Concurrencia");

        // Crear la primera fila con el título
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Analisis Concurrencia Comensales");

        // Estilo para el título
        CellStyle titleStyle = libro.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        Font titleFont = libro.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 14);
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);

        // Combinar las celdas para el título (por ejemplo, de la columna A a la C)
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

        // Crear la fila de encabezado en la segunda fila
        TableModel model = tabla.getModel();
        Row headerRow = sheet.createRow(1);

        for (int col = 0; col < model.getColumnCount(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(model.getColumnName(col));

            // Estilo para el encabezado
            CellStyle headerStyle = libro.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font headerFont = libro.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            cell.setCellStyle(headerStyle);
        }

        // Llenar los datos de la tabla en el archivo Excel, comenzando en la tercera fila
        for (int row = 0; row < model.getRowCount(); row++) {
            Row excelRow = sheet.createRow(row + 2); // +2 para saltar el título y el encabezado
            for (int col = 0; col < model.getColumnCount(); col++) {
                Cell cell = excelRow.createCell(col);
                Object value = model.getValueAt(row, col);
                if (value instanceof Number) {
                    cell.setCellValue(((Number) value).doubleValue());
                } else {
                    cell.setCellValue(value.toString());
                }
            }
        }

        // Ajustar el tamaño de las columnas
        for (int i = 0; i < model.getColumnCount(); i++) {
            sheet.autoSizeColumn(i);
        }

        guardar_Excel(libro);
    }

    //EXPORTAR EXCEL - Mayor cantidad de reservas
    private static void exportarEXCEL_mayorCantidadReservas(Cliente cliente){
        Workbook libro= new XSSFWorkbook();
        Sheet sheet = libro.createSheet("Reporte de Cliente con mayor cantidad de reservas y asistido");

        // Crear la primera fila con el título
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Cliente que ha realizado mayor cantidad de reservas y ha asistido a ellas");

        // Estilo para el título
        CellStyle titleStyle = libro.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        Font titleFont = libro.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 14);
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);

        // Combinar las celdas para el título (por ejemplo, de la columna A a la C)
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

        // Crear la segunda fila con encabezados de columna
        Row headerRow = sheet.createRow(2);
        headerRow.createCell(0).setCellValue("Nombre del Cliente");
        headerRow.createCell(1).setCellValue("Correo Electrónico");
        headerRow.createCell(2).setCellValue("Número de Teléfono");

        // Estilo para el encabezado
        CellStyle headerStyle = libro.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = libro.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        for (int i = 0; i < 3; i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Crear una fila con los datos del cliente
        Row dataRow = sheet.createRow(3);
        dataRow.createCell(0).setCellValue(cliente.getNombre());  // Nombre
        dataRow.createCell(1).setCellValue(cliente.getCorreo());  // Correo
        dataRow.createCell(2).setCellValue(cliente.getNumero());  // Número de teléfono

        // Ajustar el tamaño de las columnas
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);

        guardar_Excel(libro);
    }

    //EXPORTAR EXCEL - Reservas No Asistidas
    private static void exportarEXCEL_ReservasNoAsistidas(ArrayList<Cliente> clientes){
        Workbook libro= new XSSFWorkbook();
        Sheet sheet = libro.createSheet("Reporte Clientes que no asistieron a las reservas el último año");

        // Crear la primera fila con el título
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Clientes que han realizado reservas pero no han asistido en el último año: ");

        // Estilo para el título
        CellStyle titleStyle = libro.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        Font titleFont = libro.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 14);
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);

        // Combinar las celdas para el título (por ejemplo, de la columna A a la C)
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

        // Crear la segunda fila con encabezados de columna
        Row headerRow = sheet.createRow(2);
        headerRow.createCell(0).setCellValue("Nombre del Cliente");
        headerRow.createCell(1).setCellValue("Correo Electrónico");
        headerRow.createCell(2).setCellValue("Número de Teléfono");

        // Estilo para el encabezado
        CellStyle headerStyle = libro.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = libro.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        for (int i = 0; i < 3; i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        int i=3;
        for(Cliente c: clientes){
            Row dataRow = sheet.createRow(i);
            dataRow.createCell(0).setCellValue(c.getNombre());
            dataRow.createCell(1).setCellValue(c.getCorreo());
            dataRow.createCell(2).setCellValue(c.getNumero());

            // Ajustar el tamaño de las columnas
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            i++;
        }
        guardar_Excel(libro);
    }

    //EXPORTAR EXCEL - Historial Reservas
    private static void exportarEXCEL_HistorialReservas(Cliente cliente){
        Workbook libro= new XSSFWorkbook();
        Sheet sheet = libro.createSheet("Historial de Reservas");

        // Crear la primera fila con el título
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Historial de reservas del cliente: " + cliente.getNombre());


        // Estilo para el título
        CellStyle titleStyle = libro.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        Font titleFont = libro.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 14);
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);

        // Combinar las celdas para el título (por ejemplo, de la columna A a la C)
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

        // Crear la segunda fila con encabezados de columna
        Row headerRow = sheet.createRow(2);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Fecha");
        headerRow.createCell(2).setCellValue("N° Mesa");
        headerRow.createCell(3).setCellValue("Comensales");

        // Estilo para el encabezado
        CellStyle headerStyle = libro.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = libro.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        for (int i = 0; i < 3; i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        //Datos de las reservas
        int i=3;
        for(Reserva r: cliente.consultarHistorialReservas()){
            Row dataRow = sheet.createRow(i);
            dataRow.createCell(0).setCellValue(r.getIdReserva());
            dataRow.createCell(1).setCellValue(r.getFecha());
            dataRow.createCell(2).setCellValue(r.getMesa().getNumMesa());
            dataRow.createCell(3).setCellValue(r.getMesa().getNumMesa());

            // Ajustar el tamaño de las columnas
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            i++;
        }
        guardar_Excel(libro);
    }

    //EXPORTAR EXCEL - Historial Reservas Futuras
    private static void exportarEXCEL_HistorialReservasFuturas(Cliente cliente, Reporte reporte){
        Workbook libro= new XSSFWorkbook();
        Sheet sheet = libro.createSheet("Historial de Reservas Futuras");

        // Crear la primera fila con el título
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Historial de reservas futuras del cliente: " + cliente.getNombre());


        // Estilo para el título
        CellStyle titleStyle = libro.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        Font titleFont = libro.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 14);
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);

        // Combinar las celdas para el título (por ejemplo, de la columna A a la C)
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

        // Crear la segunda fila con encabezados de columna
        Row headerRow = sheet.createRow(2);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Fecha");
        headerRow.createCell(2).setCellValue("N° Mesa");
        headerRow.createCell(3).setCellValue("Comensales");

        // Estilo para el encabezado
        CellStyle headerStyle = libro.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = libro.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        for (int i = 0; i < 3; i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        //Datos de las reservas
        int i=3;
        for(Reserva r: reporte.detalleReservasFuturas(cliente)){
            Row dataRow = sheet.createRow(i);
            dataRow.createCell(0).setCellValue(r.getIdReserva());
            dataRow.createCell(1).setCellValue(r.getFecha());
            dataRow.createCell(2).setCellValue(r.getMesa().getNumMesa());
            dataRow.createCell(3).setCellValue(r.getMesa().getNumMesa());

            // Ajustar el tamaño de las columnas
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            i++;
        }

        guardar_Excel(libro);
    }

    //EXPORTAR EXCEL - Reserva rango fechas
    private static void exportarEXCEL_ReservasRangoFechas(JTable tabla){
        Workbook libro = new XSSFWorkbook();
        Sheet sheet = libro.createSheet("Reserva en rango de fechas");

        // Crear la primera fila con el título
        Row titulo = sheet.createRow(0);
        Cell titleCell = titulo.createCell(0);
        titleCell.setCellValue("Reserva en rango de fechas");

        // Estilo para el título
        CellStyle estiloTitulo = libro.createCellStyle();
        estiloTitulo.setAlignment(HorizontalAlignment.CENTER);
        Font fuenteTitulo = libro.createFont();
        fuenteTitulo.setBold(true);
        fuenteTitulo.setFontHeightInPoints((short) 14);
        estiloTitulo.setFont(fuenteTitulo);
        titleCell.setCellStyle(estiloTitulo);

        // Combinar las celdas para el título (por ejemplo, de la columna A a la C)
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

        // Crear la fila de encabezado en la segunda fila
        TableModel modelo = tabla.getModel();
        Row headerRow = sheet.createRow(1);

        for (int col = 0; col < modelo.getColumnCount(); col++) {
            Cell celda = headerRow.createCell(col);
            celda.setCellValue(modelo.getColumnName(col));

            // Estilo para el encabezado
            CellStyle estiloCabezera = libro.createCellStyle();
            estiloCabezera.setAlignment(HorizontalAlignment.CENTER);
            estiloCabezera.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            estiloCabezera.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font cabezeraFuente = libro.createFont();
            cabezeraFuente.setBold(true);
            estiloCabezera.setFont(cabezeraFuente);
            celda.setCellStyle(estiloCabezera);
        }

        // Llenar los datos de la tabla en el archivo Excel, comenzando en la tercera fila
        for (int row = 0; row < modelo.getRowCount(); row++) {
            Row excelRow = sheet.createRow(row + 2); // +2 para saltar el título y el encabezado
            for (int col = 0; col < modelo.getColumnCount(); col++) {
                Cell cell = excelRow.createCell(col);
                Object value = modelo.getValueAt(row, col);
                if (value instanceof Number) {
                    cell.setCellValue(((Number) value).doubleValue());
                } else {
                    cell.setCellValue(value.toString());
                }
            }
        }

        // Ajustar el tamaño de las columnas
        for (int i = 0; i < modelo.getColumnCount(); i++) {
            sheet.autoSizeColumn(i);
        }

        guardar_Excel(libro);
    }



    //FUNCION GUARDAR ARCHIVO EXCEL
    private static void guardar_Excel(Workbook libro){
        // Abrir el diálogo para elegir la ubicación del archivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Excel");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                // Obtener la ruta seleccionada por el usuario
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.endsWith(".xlsx")) {
                    filePath += ".xlsx";
                }

                // Guardar el archivo Excel en la ruta seleccionada
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    libro.write(fileOut);
                    JOptionPane.showMessageDialog(null, "Archivo Excel exportado exitosamente!");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al crear el archivo Excel: " + e.getMessage());
            } finally {
                try {
                    libro.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}