package com.example.cemusicplayer;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VistaLoginControlador implements Initializable {
    @FXML
    public Label labelError;
    @FXML
    private TextField TextoUsuario; //Objetos con el mismo ID que en scene builder

    @FXML
    private PasswordField TextoContrasena;

    @FXML
    private Button BotonIngresar;

    @FXML
    private void eventKey(KeyEvent event) {
        Object evt = event.getSource(); // en que nodo se posicionó el evento

        if (evt.equals(TextoUsuario)) {
            if (event.getCharacter().equals(" ")) { // No permite colocar espacios
                TextoUsuario.clear();
                labelError.setText("Error: No puede ingresar espacios.");
            }
        } else if (evt.equals(TextoContrasena)) {
            if (event.getCharacter().equals(" ")) {
                TextoContrasena.clear();
                labelError.setText("Error: No puede ingresar espacios.");
            }
        }

        System.out.println("eventkey"); //cada vez que se teclea muestra este print
    }
    @FXML
    private void eventAction(ActionEvent event){
        Object evt = event.getSource();

        //cargarArchivo();
        leerLineaCSV();

        if (evt.equals(BotonIngresar)){
            System.out.println("Boton ingresar");
            if (!TextoUsuario.getText().isEmpty() && !TextoContrasena.getText().isEmpty()){
                String user = TextoUsuario.getText();
                String pass = TextoContrasena.getText();

                System.out.println("HOla");
                CargarEscena("vistaPrincipal.fxml", event); //Crear nueva ventana

            }else {
                labelError.setText("Error: No puede dejar el espacio en blanco.");
            }
        }
    }

    /*
    public String cargarArchivo(){

        String fichero = "";
        System.out.println("entra a cargar archivo");
        String ruta = "src/main/resources/com/example/cemusicplayer/infoUsuariosPrueba.csv";

        try  {
            System.out.println("entra al try");
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea;

            while ((linea = br.readLine()) != null) {
                fichero += (linea+"/");

                 String[] row = fichero.split(";");

                Usuario usuario = new Usuario(row[0], row[1], row[2], row[3]);
                System.out.println("usuario");


            }
            //br.close();

        } catch (IOException e) {
            System.err.println("Error al abrir el archivo");
        }


        return fichero;
    } */

    public String leerLineaCSV(){
        String linea = "";
        String ruta = "src/main/resources/com/example/cemusicplayer/infoUsuariosPrueba.csv";
        System.out.println("entra a leer linea");
        try {
            BufferedReader bf = new BufferedReader(new FileReader(ruta));
            String bfRead;
            if ((bfRead = bf.readLine()) != null){
                linea = bfRead;
                String[] row = linea.split(";");

                Usuario usuario = new Usuario(row[0], row[1], row[2], row[3]);
                System.out.println(usuario);
            }
        } catch (Exception e){
                System.err.println("Error leyeno archivo csv");
            } return linea;
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
