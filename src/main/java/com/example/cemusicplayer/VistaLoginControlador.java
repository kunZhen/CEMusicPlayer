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

/**
 *  VistaLoginControlador se encarga de controlar los eventos y las acciones de
 *  la ventana Login.
 */
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

    /**
     * Interacción del programa por medio del teclado
     * @param event interación del usuario con el programa
     */
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

        System.out.println("Se ha tecleado"); //cada vez que se teclea muestra este print
    }

    /**
     * Permite ejecutar la acción respectiva al botón que se presionó
     * @param event interación del usuario con el programa
     */
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

    /**
     * Inicializa el programa
     * @param url - hace referencia a la ubicación de la interfaz gráfica (el archivo fxml)
     * @param resourceBundle - para traducir textos o modificar otra información dependiente de la configuración regional
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    /**
     * Crea la lista de usuarios y valida el usuario con la contraseña
     * @param user usuario
     * @param pass contraseña
     * @param event interación del usuario con el programa
     * @return
     */
    public String cargarArchivo(String user, String pass, ActionEvent event){ //objetos de usuario, verificacion de usuario

        String fichero = "";
        System.out.println("Carga el archivo de infousuarios.csv");
        String ruta = "src/main/resources/com/example/cemusicplayer/infoUsuarios.csv";
        UserLinkedList listausuario = new UserLinkedList();

        try {
            System.out.println("Entra al try a crear los usuarios en la lista");
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            br.readLine(); //saltar la primera linea - los encabezados
            String linea = br.readLine();

            while (linea != null) { //objetos de usuario
                String[] row = linea.split(";");
                listausuario.insertFirst(row[0], row[1], row[2], row[3]);
                linea = br.readLine();

            } br.close();


            if (listausuario.find(user,pass) == null) { //verifica usuario
                labelError.setText("Error: No se pudo ingresar con esos datos.");
            }else{
                CargarEscena("vistaBiblioteca.fxml", event,user); //Crear nueva ventana
            }
        } catch (IOException e) {
            System.err.println("Error al abrir el archivo");
        }
        return fichero;
    }

    /**
     * Carga la siguiente escena --> biblioteca
     * @param url - hace referencia a la ubicación de la interfaz gráfica (el archivo fxml)
     * @param event interación del usuario con el programa
     * @param user usuario
     */
    public void CargarEscena(String url, Event event, String user){
        try {
            Object eventSource = event.getSource();
            Node sourceAsNode = (Node) eventSource ;
            Scene oldScene = sourceAsNode.getScene();
            Window window = oldScene.getWindow();
            Stage stage = (Stage) window ;
            stage.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaBiblioteca.fxml")); //creditos de pasar datos de un controlador a otro https://www.youtube.com/watch?v=wxhGKR3PQpo&feature=share&si=ELPmzJkDCLju2KnD5oyZMQ
            root = loader.load();
            
            VistaBibliotecaControlador vistablioteca = loader.getController();
            vistablioteca.recibir(user);
            
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setResizable(false);
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
