package com.example.cemusicplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("vistaLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("CE Music Player!");
        stage.setScene(scene);
        stage.show();
        /*
        UserLinkedList listausuario = new UserLinkedList();

        listausuario.insertFirst("monicaegm", "egm");
        listausuario.insertFirst("mongranados", "stfn");
        listausuario.insertFirst("granadosmon", "forza");*/


    }

    public static void main(String[] args) {
        launch();
    }
}