package com.example.cemusicplayer;

import com.csvreader.CsvWriter;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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

    private Media media;
    private MediaPlayer mediaPlayer;
    private String selectedSong;
    private File selectedCancion;
    private String reproducirSong;

    private Timer timer;
    private TimerTask task;
    private boolean running;

    CancionCircularDoubleLinkedList cancionLista = new CancionCircularDoubleLinkedList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        volumSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                mediaPlayer.setVolume(volumSlider.getValue()*0.01);
            }
        });
    }

    public void conseguirNombreUsuarioBiblioteca(String usuario, String biblioteca){
        this.nombreUsuario = usuario;
        this.nombreBibliteca = biblioteca;

        bibliotecaLabel.setText(this.nombreBibliteca);

    }
    /*
    private void escribirCSV(Cancion newCancion) throws FileNotFoundException {
        String salidaArchivo = "src/main/resources/Usuarios/" + this.nombreUsuario + "/Bibliotecas/infoCanciones.csv";
        boolean existe = new File (salidaArchivo).exists();

        try{
            CsvWriter salidaCSV =  new CsvWriter(new FileWriter(salidaArchivo, true), ';'); //Crea el archivo

            for (Cancion nB : cancionLista) {

                if (cancionLista.find(nB.getNombre()) != null) {
                    if (cancionLista.find(nB.getNombre()).equals(nB.getNombre())) {
                        System.out.println("La cancion " + nB.getNombre() + "se encuentra en la lista");
                    }
                } else {
                    salidaCSV.write(nB.getNombre());
                    salidaCSV.write(nB.getPath());
                    //salidaCSV.write(nB.getFile());

                    salidaCSV.endRecord(); //salto de línea
                }
            }
            salidaCSV.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    @FXML
    public void eventAction(ActionEvent event) {
        Object evt = event.getSource();

        if (evt.equals(returnButton)) {
            CargarEscena("vistaBiblioteca.fxml", event); //volver a biblioteca

        }else if (evt.equals(favoriteButton)){
            Image myImage = new Image(getClass().getResourceAsStream("Icons/heartIMG.png"));
            favoriteImage.setImage(myImage);

            System.out.println("biblioteca " + this.nombreBibliteca);
            System.out.println("usuario " + this.nombreUsuario);

        }else if (evt.equals(nextButton)){
            mediaPlayer.stop();
            progressBar.setProgress(0);
            beginTimer();

            Cancion reproducir = cancionLista.find(reproducirSong); //busca la cancion en la lista
            reproducirSong = (reproducir.getNext().getNombre());

            media = new Media (reproducir.getFile().toURI().toString());//agrega la cancion al Media
            mediaPlayer = new MediaPlayer(media);

            mediaPlayer.play();


        }else if (evt.equals(playButton)){
            mediaPlayer.stop();
            beginTimer();

            selectedSong = songsListView.getSelectionModel().getSelectedItem();//obtiene la cancion del listView
            reproducirSong = selectedSong;

            Cancion reproducir = cancionLista.find(selectedSong); //busca la cancion en la lista
            media = new Media (reproducir.getFile().toURI().toString());//agrega la cancion al Media
            mediaPlayer = new MediaPlayer(media);

            mediaPlayer.play();

        }else if (evt.equals(regresarButton)){
            mediaPlayer.stop();

            Cancion reproducir = cancionLista.find(reproducirSong); //busca la cancion en la lista
            reproducirSong = (reproducir.getPrev().getNombre());

            media = new Media (reproducir.getFile().toURI().toString());//agrega la cancion al Media
            mediaPlayer = new MediaPlayer(media);

            mediaPlayer.play();

        } else if (evt.equals(uploadButton)){

            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File("src/main/resources/Musica"));
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP3","*.mp3"));
            File selectedCancion = fc.showOpenDialog(null);

            if (selectedCancion != null){
                Cancion cancion = new Cancion (selectedCancion.getName(), false, selectedCancion.getAbsolutePath(),selectedCancion); //nueva cancion
                cancionLista.addToCircularDoubleLinkedList(cancion);

                songsListView.getItems().add(cancion.getNombre()); //agregar una nueva cancion al listview

                cancionLista.display();

                media = new Media (cancion.getFile().toURI().toString());//agrega la cancion al Media
                mediaPlayer = new MediaPlayer(media);
                /*
                try {
                    escribirCSV(cancion);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } */

                mediaPlayer.stop();

            }else{
                System.out.println("La cancion no es valida");
            }

        }else if (evt.equals(garbageButton)){
            mediaPlayer.stop();
            String eliminar = songsListView.getSelectionModel().getSelectedItem();

            cancionLista.delete(eliminar);

            songsListView.getItems().removeAll(songsListView.getSelectionModel().getSelectedItem());


        }else if (evt.equals(cycleButton)){

        }else if (evt.equals(editButton)){

        }else if (evt.equals(playSelectedButton)){

        }
    }
    public void beginTimer(){
        timer = new Timer();
        task = new TimerTask(){
            public void run(){
                running = true;
                double currentTimer =  mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                progressBar.setProgress(currentTimer/end);
                if (currentTimer/end == 1){
                    cancelTimer();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000 );
    }
    public void cancelTimer(){
        running = false;
        timer.cancel();
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
