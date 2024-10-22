package InterfacesGraficas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RestauranteMapa extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        // Sombras para los elementos
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(3);
        shadow.setOffsetY(3);
        shadow.setColor(Color.GRAY);

        // Salón Principal
        Rectangle salonPrincipal = new Rectangle(50, 50, 300, 200);
        salonPrincipal.setArcWidth(20);
        salonPrincipal.setArcHeight(20);
        salonPrincipal.setFill(Color.LIGHTBLUE);
        salonPrincipal.setEffect(shadow);
        pane.getChildren().add(salonPrincipal);

        Text salonText = new Text(60, 70, "Salón Principal");
        salonText.setFont(Font.font("Verdana", 16));
        pane.getChildren().add(salonText);

        // Baños del salón principal
        Rectangle banosSalonPrincipal = new Rectangle(280, 180, 60, 60);
        banosSalonPrincipal.setArcWidth(10);
        banosSalonPrincipal.setArcHeight(10);
        banosSalonPrincipal.setFill(Color.DARKGRAY);
        banosSalonPrincipal.setEffect(shadow);
        Text banosSalonPrincipalText = new Text(285, 200, "Baños");
        banosSalonPrincipalText.setFont(Font.font("Verdana", 14));
        banosSalonPrincipalText.setFill(Color.WHITE);
        pane.getChildren().addAll(banosSalonPrincipal, banosSalonPrincipalText);

        // Mesas en el salón principal
        for (int i = 1; i <= 15; i++) {
            int x = 60 + (i - 1) % 5 * 50;
            int y = 100 + (i - 1) / 5 * 40;
            Rectangle mesa = new Rectangle(x, y, 30, 30);
            mesa.setFill(Color.BEIGE);
            mesa.setArcWidth(10);
            mesa.setArcHeight(10);
            mesa.setEffect(shadow);
            Text mesaText = new Text(x + 5, y + 20, String.valueOf(i));
            pane.getChildren().addAll(mesa, mesaText);
        }

        // Terraza
        Rectangle terraza = new Rectangle(400, 50, 300, 200);
        terraza.setArcWidth(20);
        terraza.setArcHeight(20);
        terraza.setFill(Color.LIGHTGREEN);
        terraza.setEffect(shadow);
        pane.getChildren().add(terraza);

        Text terrazaText = new Text(410, 70, "Terraza");
        terrazaText.setFont(Font.font("Verdana", 16));
        pane.getChildren().add(terrazaText);

        // Baños de la terraza
        Rectangle banosTerraza = new Rectangle(620, 180, 60, 60);
        banosTerraza.setArcWidth(10);
        banosTerraza.setArcHeight(10);
        banosTerraza.setFill(Color.DARKGRAY);
        banosTerraza.setEffect(shadow);
        Text banosTerrazaText = new Text(625, 200, "Baños");
        banosTerrazaText.setFont(Font.font("Verdana", 14));
        banosTerrazaText.setFill(Color.WHITE);
        pane.getChildren().addAll(banosTerraza, banosTerrazaText);

        // Mesas en la terraza
        for (int i = 26; i <= 35; i++) {
            int x = 410 + (i - 26) % 5 * 50;
            int y = 100 + (i - 26) / 5 * 40;
            Rectangle mesa = new Rectangle(x, y, 30, 30);
            mesa.setFill(Color.BEIGE);
            mesa.setArcWidth(10);
            mesa.setArcHeight(10);
            mesa.setEffect(shadow);
            Text mesaText = new Text(x + 5, y + 20, String.valueOf(i));
            pane.getChildren().addAll(mesa, mesaText);
        }

        // Salón del Segundo Piso
        Rectangle salonSegundoPiso = new Rectangle(50, 300, 650, 200);
        salonSegundoPiso.setArcWidth(20);
        salonSegundoPiso.setArcHeight(20);
        salonSegundoPiso.setFill(Color.LIGHTCORAL);
        salonSegundoPiso.setEffect(shadow);
        pane.getChildren().add(salonSegundoPiso);

        Text segundoPisoText = new Text(60, 320, "Salón del Segundo Piso");
        segundoPisoText.setFont(Font.font("Verdana", 16));
        pane.getChildren().add(segundoPisoText);

        // Baños del segundo piso
        Rectangle banosSegundoPiso = new Rectangle(620, 430, 60, 60);
        banosSegundoPiso.setArcWidth(10);
        banosSegundoPiso.setArcHeight(10);
        banosSegundoPiso.setFill(Color.DARKGRAY);
        banosSegundoPiso.setEffect(shadow);
        Text banosSegundoPisoText = new Text(625, 450, "Baños");
        banosSegundoPisoText.setFont(Font.font("Verdana", 14));
        banosSegundoPisoText.setFill(Color.WHITE);
        pane.getChildren().addAll(banosSegundoPiso, banosSegundoPisoText);

        // Mesas en el salón del segundo piso
        for (int i = 16; i <= 25; i++) {
            int x = 60 + (i - 16) % 5 * 50;
            int y = 370 + (i - 16) / 5 * 40;
            Rectangle mesa = new Rectangle(x, y, 30, 30);
            mesa.setFill(Color.BEIGE);
            mesa.setArcWidth(10);
            mesa.setArcHeight(10);
            mesa.setEffect(shadow);
            Text mesaText = new Text(x + 5, y + 20, String.valueOf(i));
            pane.getChildren().addAll(mesa, mesaText);
        }

        // Escaleras entre el primer y segundo piso
        Rectangle escaleras = new Rectangle(200, 260, 120, 30);
        escaleras.setArcWidth(10);
        escaleras.setArcHeight(10);
        escaleras.setFill(Color.BROWN);
        escaleras.setEffect(shadow);
        Text escalerasText = new Text(210, 280, "Escaleras");
        escalerasText.setFont(Font.font("Verdana", 14));
        escalerasText.setFill(Color.WHITE);
        pane.getChildren().addAll(escaleras, escalerasText);

        // Salida de Seguridad sin la "mancha roja"
        Rectangle salidaSeguridad = new Rectangle(650, 50, 30, 30);
        salidaSeguridad.setArcWidth(10);
        salidaSeguridad.setArcHeight(10);
        salidaSeguridad.setFill(Color.RED);
        salidaSeguridad.setEffect(shadow);
        Text salidaText = new Text(655, 70, "Salida");
        salidaText.setFont(Font.font("Verdana", 14));
        salidaText.setFill(Color.WHITE);
        pane.getChildren().addAll(salidaSeguridad, salidaText);

        // Configurar la escena
        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setTitle("Mapa del Restaurante");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}