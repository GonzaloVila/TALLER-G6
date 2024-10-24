package InterfacesGraficas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RestauranteMapa extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Crear la ventana principal
        primaryStage.setTitle("Mapa del Restaurante");
        primaryStage.setScene(createMainMenuScene(primaryStage));
        primaryStage.show();
    }

    private Scene createMainMenuScene(Stage primaryStage) {
        VBox mainMenu = new VBox(20);
        Button btnSalonPrincipal = new Button("Ir al Salón Principal");
        Button btnTerraza = new Button("Ir a la Terraza");
        Button btnSegundoPiso = new Button("Ir al Segundo Piso");

        btnSalonPrincipal.setOnAction(e -> primaryStage.setScene(createSalonPrincipalScene(primaryStage)));
        btnTerraza.setOnAction(e -> primaryStage.setScene(createTerrazaScene(primaryStage)));
        btnSegundoPiso.setOnAction(e -> primaryStage.setScene(createSegundoPisoScene(primaryStage)));

        mainMenu.getChildren().addAll(btnSalonPrincipal, btnTerraza, btnSegundoPiso);
        return new Scene(mainMenu, 300, 200);
    }

    private Scene createSalonPrincipalScene(Stage primaryStage) {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: lightgray;"); // Cambiar color de fondo

        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(3);
        shadow.setOffsetY(3);
        shadow.setColor(Color.GRAY);

        // Salón Principal
        Rectangle salonPrincipal = new Rectangle(50, 50, 700, 500); // Aumentar tamaño
        salonPrincipal.setArcWidth(20);
        salonPrincipal.setArcHeight(20);
        salonPrincipal.setFill(Color.LIGHTBLUE);
        salonPrincipal.setEffect(shadow);
        pane.getChildren().add(salonPrincipal);

        Text salonText = new Text(60, 70, "Salón Principal");
        salonText.setFont(Font.font("Verdana", 20)); // Aumentar tamaño de fuente
        pane.getChildren().add(salonText);

        // Mesas en el salón principal
        Image imagenMesa = new Image("imagenes/gratis-png-dibujos-animados-mesa-thumbnail.png");
        for (int i = 1; i <= 15; i++) {
            int x = 70 + (i - 1) % 5 * 140; // Ajustar espaciado
            int y = 120 + (i - 1) / 5 * 100; // Ajustar espaciado
            ImageView mesaImageView = new ImageView(imagenMesa);
            mesaImageView.setX(x);
            mesaImageView.setY(y);
            mesaImageView.setFitWidth(120); // Aumentar tamaño de las mesas
            mesaImageView.setFitHeight(80);  // Aumentar tamaño de las mesas
            pane.getChildren().add(mesaImageView);
        }

        // Botón para volver al menú principal
        Button backButton = new Button("Volver al Menú Principal");
        backButton.setOnAction(e -> primaryStage.setScene(createMainMenuScene(primaryStage)));
        backButton.setLayoutX(10);
        backButton.setLayoutY(10);
        pane.getChildren().add(backButton);

        return new Scene(pane, 800, 600);
    }

    private Scene createTerrazaScene(Stage primaryStage) {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: lightgray;"); // Cambiar color de fondo

        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(3);
        shadow.setOffsetY(3);
        shadow.setColor(Color.GRAY);

        // Terraza
        Rectangle terraza = new Rectangle(50, 50, 700, 500); // Aumentar tamaño
        terraza.setArcWidth(20);
        terraza.setArcHeight(20);
        terraza.setFill(Color.LIGHTGREEN);
        terraza.setEffect(shadow);
        pane.getChildren().add(terraza);

        Text terrazaText = new Text(60, 70, "Terraza");
        terrazaText.setFont(Font.font("Verdana", 20)); // Aumentar tamaño de fuente
        pane.getChildren().add(terrazaText);

        // Mesas en la terraza
        Image imagenMesa = new Image("imagenes/gratis-png-dibujos-animados-mesa-thumbnail.png");
        for (int i = 1; i <= 15; i++) {
            int x = 70 + (i - 1) % 5 * 140; // Ajustar espaciado
            int y = 120 + (i - 1) / 5 * 100; // Ajustar espaciado
            ImageView mesaImageView = new ImageView(imagenMesa);
            mesaImageView.setX(x);
            mesaImageView.setY(y);
            mesaImageView.setFitWidth(120); // Aumentar tamaño de las mesas
            mesaImageView.setFitHeight(80);  // Aumentar tamaño de las mesas
            pane.getChildren().add(mesaImageView);
        }

        // Botón para volver al menú principal
        Button backButton = new Button("Volver al Menú Principal");
        backButton.setOnAction(e -> primaryStage.setScene(createMainMenuScene(primaryStage)));
        backButton.setLayoutX(10);
        backButton.setLayoutY(10);
        pane.getChildren().add(backButton);

        return new Scene(pane, 800, 600);
    }

    private Scene createSegundoPisoScene(Stage primaryStage) {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: lightgray;"); // Cambiar color de fondo

        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(3);
        shadow.setOffsetY(3);
        shadow.setColor(Color.GRAY);

        // Salón del Segundo Piso
        Rectangle salonSegundoPiso = new Rectangle(50, 50, 700, 500); // Aumentar tamaño
        salonSegundoPiso.setArcWidth(20);
        salonSegundoPiso.setArcHeight(20);
        salonSegundoPiso.setFill(Color.LIGHTCORAL);
        salonSegundoPiso.setEffect(shadow);
        pane.getChildren().add(salonSegundoPiso);

        Text segundoPisoText = new Text(60, 70, "Salón del Segundo Piso");
        segundoPisoText.setFont(Font.font("Verdana", 20)); // Aumentar tamaño de fuente
        pane.getChildren().add(segundoPisoText);

        // Mesas en el salón del segundo piso
        Image imagenMesa = new Image("imagenes/gratis-png-dibujos-animados-mesa-thumbnail.png");
        for (int i = 1; i <= 15; i++) {
            int x = 70 + (i - 1) % 5 * 140; // Ajustar espaciado
            int y = 120 + (i - 1) / 5 * 100; // Ajustar espaciado
            ImageView mesaImageView = new ImageView(imagenMesa);
            mesaImageView.setX(x);
            mesaImageView.setY(y);
            mesaImageView.setFitWidth(120); // Aumentar tamaño de las mesas
            mesaImageView.setFitHeight(80);  // Aumentar tamaño de las mesas
            pane.getChildren().add(mesaImageView);
        }

        // Botón para volver al menú principal
        Button backButton = new Button("Volver al Menú Principal");
        backButton.setOnAction(e -> primaryStage.setScene(createMainMenuScene(primaryStage)));
        backButton.setLayoutX(10);
        backButton.setLayoutY(10);
        pane.getChildren().add(backButton);

        return new Scene(pane, 800, 600);
    }


}
