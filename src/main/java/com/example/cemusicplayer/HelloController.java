package com.example.cemusicplayer;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TextField TextoUsuario; //Objetos con el mismo ID que en scene builder

    @FXML
    private PasswordField TextoContrasena;

    @FXML
    private Button BotonIngresar;

    @FXML
    private void eventKey(KeyEvent event) {
        Object evt = event.getSource(); // en que nodo se posicionó el evento
        System.out.println("eventkey");
    }
    @FXML
    private void eventAction(ActionEvent event){
        Object evt = event.getSource();

        if (evt.equals(BotonIngresar)){
            System.out.println("Boton ingresar");
            if (!TextoUsuario.getText().isEmpty() && !TextoContrasena.getText().isEmpty()){
                String user = TextoUsuario.getText();
                String pass = TextoContrasena.getText();

                System.out.println("HOla");
                CargarEscena("vistaPrincipal.fxml", event); //Crear nueva ventana

            }else {
                System.out.println("Error");
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void CargarEscena(String url, Event event){
        try {
            Object eventSource = event.getSource();
            Node sourceAsNode = (Node) eventSource ;
            Scene oldScene = sourceAsNode.getScene();
            Window window = oldScene.getWindow();
            Stage stage = (Stage) window ;
            stage.hide();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("vistaPrincipal.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("CE Music Player!");
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Platform.exit();
                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }




}
