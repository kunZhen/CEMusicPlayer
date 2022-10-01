package com.example.cemusicplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Clase principal
 */
public class HelloApplication extends Application {

    /**
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("vistaLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("CE Music Player!");
        stage.setResizable(false);
        Image icon = new Image(getClass().getResourceAsStream("Icons/musicPlayerIcon.png"));
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}