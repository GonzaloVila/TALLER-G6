package InterfacesGraficas;

import javax.swing.*;
import java.awt.*;

/**
 * Clase RestauranteMapa que extiende JPanel y crea la interfaz gráfica para representar el mapa del restaurante.
 */
public class RestauranteMapa extends JPanel {

    /**
     * Constructor de la clase RestauranteMapa.
     * Configura el diseño del panel principal y añade el menú principal.
     */
    public RestauranteMapa() {
        setLayout(null);

        // Crear menú principal
        JPanel menuPrincipal = new JPanel();
        menuPrincipal.setLayout(new GridLayout(3, 1, 20, 20));
        menuPrincipal.setBounds(50, 50, 300, 150);

        // Crear botones para los distintos salones
        JButton btnSalonPrincipal = new JButton("Salón Principal");
        JButton btnTerraza = new JButton("Terraza");
        JButton btnSegundoPiso = new JButton("Segundo Piso");

        // Añadir los botones al menú principal
        menuPrincipal.add(btnSalonPrincipal);
        menuPrincipal.add(btnTerraza);
        menuPrincipal.add(btnSegundoPiso);

        // Añadir el menú principal al panel
        add(menuPrincipal);

        // Crear eventos para los botones
        btnSalonPrincipal.addActionListener(e -> mostrarSalon(this, "Salón Principal", new Color(173, 216, 230), 1, 15));
        btnTerraza.addActionListener(e -> mostrarSalon(this, "Terraza", new Color(144, 238, 144), 26, 35));
        btnSegundoPiso.addActionListener(e -> mostrarSalon(this, "Segundo Piso", new Color(255, 160, 122), 16, 25));
    }

    /**
     * Método para mostrar el salón seleccionado.
     *
     * @param panel Panel en el que se mostrará el salón.
     * @param salon Nombre del salón a mostrar.
     * @param color Color de fondo del salón.
     * @param inicio Número de mesa inicial.
     * @param fin Número de mesa final.
     */
    private void mostrarSalon(JPanel panel, String salon, Color color, int inicio, int fin) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();

        // Crear salón
        JPanel salonPanel = new JPanel();
        salonPanel.setBackground(color);
        salonPanel.setBounds(50, 50, 700, 500);
        salonPanel.setLayout(null);
        panel.add(salonPanel);

        // Crear texto del salón
        JLabel textoSalon = new JLabel(salon);
        textoSalon.setFont(new Font("Verdana", Font.PLAIN, 24));
        textoSalon.setBounds(60, 20, 300, 30);
        salonPanel.add(textoSalon);

        // Crear mesas en el salón
        for (int i = inicio; i <= fin; i++) {
            int x = 70 + (i - inicio) % 5 * 140; // Ajustar espaciado horizontal
            int y = 90 + (i - inicio) / 5 * 140; // Ajustar espaciado vertical
            JPanel mesa = new JPanel();
            mesa.setBackground(new Color(245, 222, 179));
            mesa.setBounds(x, y, 100, 100);
            JLabel textoMesa = new JLabel(String.valueOf(i));
            textoMesa.setFont(new Font("Verdana", Font.PLAIN, 18));
            mesa.add(textoMesa);
            salonPanel.add(mesa);
        }

        // Crear baños
        JPanel banos = new JPanel();
        banos.setBackground(new Color(169, 169, 169));
        banos.setBounds(600, 400, 80, 80);
        JLabel textoBanos = new JLabel("Baños");
        textoBanos.setFont(new Font("Verdana", Font.PLAIN, 18));
        textoBanos.setForeground(Color.WHITE);
        banos.add(textoBanos);
        salonPanel.add(banos);

        // Botón para volver al menú principal
        JButton botonVolver = new JButton("Volver al Menú Principal");
        botonVolver.setBounds(10, 10, 180, 30);
        botonVolver.addActionListener(e -> {
            panel.removeAll();
            crearPanelRestaurante(panel); // Crear de nuevo el menú principal
            panel.revalidate();
            panel.repaint();
        });
        salonPanel.add(botonVolver);

        panel.revalidate();
        panel.repaint();
    }

    /**
     * Método para crear el panel del restaurante.
     *
     * @param panel Panel en el que se añadirá el menú principal.
     */
    private void crearPanelRestaurante(JPanel panel) {
        panel.setLayout(null);

        // Crear menú principal
        JPanel menuPrincipal = new JPanel();
        menuPrincipal.setLayout(new GridLayout(3, 1, 20, 20));
        menuPrincipal.setBounds(50, 50, 300, 150);

        // Crear botones para los distintos salones
        JButton btnSalonPrincipal = new JButton("Salón Principal");
        JButton btnTerraza = new JButton("Terraza");
        JButton btnSegundoPiso = new JButton("Segundo Piso");

        // Añadir los botones al menú principal
        menuPrincipal.add(btnSalonPrincipal);
        menuPrincipal.add(btnTerraza);
        menuPrincipal.add(btnSegundoPiso);

        // Añadir el menú principal al panel
        panel.add(menuPrincipal);

        // Crear eventos para los botones
        btnSalonPrincipal.addActionListener(e -> mostrarSalon(panel, "Salón Principal", new Color(173, 216, 230), 1, 15));
        btnTerraza.addActionListener(e -> mostrarSalon(panel, "Terraza", new Color(144, 238, 144), 26, 35));
        btnSegundoPiso.addActionListener(e -> mostrarSalon(panel, "Segundo Piso", new Color(255, 160, 122), 16, 25));
    }
}
