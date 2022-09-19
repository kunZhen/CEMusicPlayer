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

    private Stage stage;
    private Scene scene;
    private Parent root;

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

        if (evt.equals(BotonIngresar)){
            if (!TextoUsuario.getText().isEmpty() && !TextoContrasena.getText().isEmpty()){ //Obtener los datos de ingreso
                String user = TextoUsuario.getText();
                String pass = TextoContrasena.getText();
                cargarArchivo(user,pass,event);

            }else {
                labelError.setText("Error: No puede dejar el espacio en blanco.");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public String cargarArchivo(String user, String pass, ActionEvent event){ //objetos de usuario, verificacion de usuario

        String fichero = "";
        System.out.println("entra a cargar archivo");
        String ruta = "src/main/resources/com/example/cemusicplayer/infoUsuariosPrueba.csv";
        UserLinkedList listausuario = new UserLinkedList();

        try {
            System.out.println("entra al try");
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            br.readLine(); //saltar la primera linea - los encabezados
            String linea = br.readLine();

            while (linea != null) { //objetos de usuario
                String[] row = linea.split(";");
                listausuario.insertFirst(row[0], row[1], row[2], row[3]);
                linea = br.readLine();

            } br.close();

            listausuario.print();

            if (listausuario.find(user,pass) == null) { //verifica usuario
                labelError.setText("Error: No se pudo ingresar con esos datos.");
            }else{
                CargarEscena("vistaBiblioteca.fxml", event); //Crear nueva ventana
            }
        } catch (IOException e) {
            System.err.println("Error al abrir el archivo");
        }
        return fichero;
    }

    public void CargarEscena(String url, Event event){
        try {
            root = FXMLLoader.load(getClass().getResource(url));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
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
