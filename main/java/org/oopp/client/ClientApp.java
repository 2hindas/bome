package org.oopp.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * This class is the entry point of the client application.
 */
public class ClientApp extends Application {

    /**
     * Starts the main window, and creates the object in the root window.
     *
     * @param stage Main stage of the program.
     * @throws Exception when one occurs.
     */
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());


        Font.loadFont(getClass().getResourceAsStream("fonts/Comfortaa/Comfortaa-Regular.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("fonts/Montserrat/Montserrat-Medium.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("fonts/Montserrat/Montserrat-Bold.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("fonts/Montserrat/Montserrat-Black.ttf"), 14);

        stage.setTitle("bome");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}