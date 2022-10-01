package com.example.cemusicplayer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * VistaPlaylistControlador se encarga de controlar los eventos y las acciones de la
 * ventana del reproductor de canciones
 */
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

    /**
     * Inicializa el slider del volumen
     * @param url - hace referencia a la ubicación de la interfaz gráfica (el archivo fxml)
     * @param resourceBundle - para traducir textos o modificar otra información dependiente de la configuración regional
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        volumSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                mediaPlayer.setVolume(volumSlider.getValue()*0.01);
            }
        });
    }

    /**
     * Recibe el nombre de usuario y biblioteca de la clase VistaBibliotecaControlador
     * @param usuario usuario
     * @param biblioteca biblioteca
     */
    public void conseguirNombreUsuarioBiblioteca(String usuario, String biblioteca){
        this.nombreUsuario = usuario;
        this.nombreBibliteca = biblioteca;

        bibliotecaLabel.setText(this.nombreBibliteca);

    }

    /**
     * Permite ejecutar la acción respectiva al botón que se presionó
     * @param event interación del usuario con el programa
     */

    @FXML
    public void eventAction(ActionEvent event) {
        Object evt = event.getSource();

        if (evt.equals(favoriteButton)){
            Image myImage = new Image(getClass().getResourceAsStream("Icons/heartIMG.png"));
            favoriteImage.setImage(myImage);

            System.out.println("biblioteca " + this.nombreBibliteca);
            System.out.println("usuario " + this.nombreUsuario);

        }else if (evt.equals(nextButton)){
            mediaPlayer.stop();
            progressBar.setProgress(0); //progressBar en cero
            beginTimer();

            System.out.println(reproducirSong);

            Cancion reproducir = cancionLista.find(reproducirSong); //busca la cancion en la lista
            reproducirSong = reproducir.getNext().getNombre(); //consigue el nombre de la siguiente cancion

            media = new Media (cancionLista.find(reproducirSong).getFile().toURI().toString());//agrega la cancion al Media
            mediaPlayer = new MediaPlayer(media);

            mediaPlayer.play();

        }else if (evt.equals(playButton)){


            selectedSong = songsListView.getSelectionModel().getSelectedItem(); //obtiene la cancion del listView

            if (selectedSong != null) {
                mediaPlayer.stop();
                beginTimer();
                reproducirSong = selectedSong;

                Cancion reproducir = cancionLista.find(selectedSong); //busca la cancion en la lista
                labelSong.setText(reproducir.getNombre());
                media = new Media (reproducir.getFile().toURI().toString());//agrega la cancion al Media
                mediaPlayer = new MediaPlayer(media);

                mediaPlayer.play();
            }


        }else if (evt.equals(regresarButton)){
            mediaPlayer.stop();

            Cancion reproducir = cancionLista.find(reproducirSong); //busca la cancion en la lista
            reproducirSong = reproducir.getPrev().getNombre(); //consigue el nombre de la cancion anterior

            media = new Media (cancionLista.find(reproducirSong).getFile().toURI().toString());//agrega la cancion al Media
            mediaPlayer = new MediaPlayer(media);

            mediaPlayer.play();

        } else if (evt.equals(uploadButton)){

            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File("src/main/resources/Musica"));
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP3","*.mp3")); //solamente puede escoger .mp3
            File selectedCancion = fc.showOpenDialog(null);

            if (selectedCancion != null){
                Cancion cancion = new Cancion (selectedCancion.getName(), false, selectedCancion.getAbsolutePath(),selectedCancion); //nueva cancion
                cancionLista.addToCircularDoubleLinkedList(cancion);

                songsListView.getItems().add(cancion.getNombre()); //agregar una nueva cancion al listview

                cancionLista.display();
                cancionLista.displayPrevNext();
                if (media != null){
                    mediaPlayer.stop();
                }
                media = new Media (cancion.getFile().toURI().toString());//agrega la cancion al Media
                mediaPlayer =  new MediaPlayer(media);


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

    /**
     * Rellena la progress bar conforme se ejecuta la canción
     */
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

    /**
     * Detiene la reproducción de la progress bar
     */
    public void cancelTimer(){
        running = false;
        timer.cancel();
    }

}
