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
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VistaPlaylistControlador implements Initializable {

    @FXML
    private Label bibliotecaLabel;

    @FXML
    private Button cycleButton;

    @FXML
    private ImageView cycleImage;

    @FXML
    private Button editButton;

    @FXML
    private Button favoriteButton;

    @FXML
    private ImageView favoriteImage;

    @FXML
    private Button garbageButton;

    @FXML
    private Label labelArtist;

    @FXML
    private Label labelSong;

    @FXML
    private Button nextButton;

    @FXML
    private ImageView nextImage;

    @FXML
    private Button playButton;

    @FXML
    private ImageView playImage;

    @FXML
    private Button playSelectedButton;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button regresarButton;

    @FXML
    private ImageView regresarImage;

    @FXML
    private Button returnButton;

    @FXML
    private ImageView returnImg;

    @FXML
    private ListView<String> songsListView;

    @FXML
    private ImageView trashImage;

    @FXML
    private Button uploadButton;

    @FXML
    private ImageView uploadImage;

    @FXML
    private Slider volumSlider;

    private Scene scene;
    private Parent root;

    private String nombreUsuario;
    private String nombreBibliteca;

    CancionCircularDoubleLinkedList cancionLista = new CancionCircularDoubleLinkedList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void conseguirNombreUsuarioBiblioteca(String usuario, String biblioteca){
        this.nombreUsuario = usuario;
        this.nombreBibliteca = biblioteca;

    }
    @FXML
    private void eventAction(ActionEvent event) {
        Object evt = event.getSource();

        if (evt.equals(returnButton)) {
            CargarEscena("vistaBiblioteca.fxml", event); //Crear nueva ventana

        }else if (evt.equals(favoriteButton)){
            Image myImage = new Image(getClass().getResourceAsStream("Icons/heartIMG.png"));
            favoriteImage.setImage(myImage);

            System.out.println("biblioteca " + this.nombreBibliteca);
            System.out.println("usuario " + this.nombreUsuario);

        }else if (evt.equals(nextButton)){

        }else if (evt.equals(playButton)){

        }else if (evt.equals(regresarButton)){

        } else if (evt.equals(uploadButton)){
            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File("src/main/resources/Musica"));
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP3","*.mp3"));
            File selectedCancion = fc.showOpenDialog(null);

            if (selectedCancion != null){
                songsListView.getItems().add(selectedCancion.getName()); //Buscar una nueva cancion

                Cancion cancion = new Cancion (selectedCancion.getName(), false, selectedCancion.getAbsolutePath());
                cancionLista.addToCircularDoubleLinkedList(cancion);

                cancionLista.display();
            }else{
                System.out.println("La cancion no es valida");
            }

        }else if (evt.equals(garbageButton)){

        }else if (evt.equals(cycleButton)){

        }else if (evt.equals(editButton)){

        }else if (evt.equals(playSelectedButton)){

        }
    }

    public void CargarEscena(String url, Event event){
        try {
            Object eventSource = event.getSource();
            Node sourceAsNode = (Node) eventSource ;
            Scene oldScene = sourceAsNode.getScene();
            Window window = oldScene.getWindow();
            Stage stage = (Stage) window ;
            stage.hide();

            root = FXMLLoader.load(getClass().getResource(url));
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
