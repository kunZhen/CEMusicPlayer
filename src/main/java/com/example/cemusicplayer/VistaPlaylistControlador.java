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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import com.fazecast.jSerialComm.*;
import jssc.*;
import jssc.SerialPort;
import jssc.SerialPortEvent;

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
     * Coloca el favorito en las canciones
     */
    @FXML
    public void favoriteButtonAction(){
        if (cancionLista.find(reproducirSong).isFavorite()){
            cancionLista.find(reproducirSong).setFavorite(false);
            Image myImage = new Image(getClass().getResourceAsStream("Icons/corazonIMG.png"));
            favoriteImage.setImage(myImage);
        }
        else{
            cancionLista.find(reproducirSong).setFavorite(true);
            Image myImage = new Image(getClass().getResourceAsStream("Icons/heartIMG.png"));
            favoriteImage.setImage(myImage);
        }
    }

    /**
     * Pasa a la siguiente canción
     */
    @FXML
    public void nextButtonAction() {
        mediaPlayer.stop();
        progressBar.setProgress(0); //progressBar en cero
        beginTimer();

        System.out.println(reproducirSong);

        Cancion reproducir = cancionLista.find(reproducirSong); //busca la cancion en la lista
        reproducirSong = reproducir.getNext().getNombre(); //consigue el nombre de la siguiente cancion

        media = new Media(cancionLista.find(reproducirSong).getFile().toURI().toString());//agrega la cancion al Media
        mediaPlayer = new MediaPlayer(media);

        if (cancionLista.find(reproducirSong).isFavorite()) { //muestra si es favorito o no
            Image myImage = new Image(getClass().getResourceAsStream("Icons/heartIMG.png"));
            favoriteImage.setImage(myImage);
        } else {
            Image myImage = new Image(getClass().getResourceAsStream("Icons/corazonIMG.png"));
            favoriteImage.setImage(myImage);
        }

        mediaPlayer.play();
    }

    /**
     * Reproduce la canción
     */
    @FXML
    public void playButtonAction(){
        selectedSong = songsListView.getSelectionModel().getSelectedItem();//obtiene la cancion del listView
            if (selectedSong != null) {
                mediaPlayer.stop();
                beginTimer();
                reproducirSong = selectedSong;

                Cancion reproducir = cancionLista.find(selectedSong); //busca la cancion en la lista
                labelSong.setText(reproducir.getNombre());
                media = new Media (reproducir.getFile().toURI().toString());//agrega la cancion al Media
                mediaPlayer = new MediaPlayer(media);
                System.out.println("favorito" + cancionLista.find(selectedSong).isFavorite());


                if (cancionLista.find(reproducirSong).isFavorite()){ //muestra si es favorito o no
                    Image myImage = new Image(getClass().getResourceAsStream("Icons/heartIMG.png"));
                    favoriteImage.setImage(myImage);
                }
                else{
                    Image myImage = new Image(getClass().getResourceAsStream("Icons/corazonIMG.png"));
                    favoriteImage.setImage(myImage);
                }

                mediaPlayer.play();

            }

        }

    /**
     * Reproduce la canción anterior
     */
        @FXML
    public void regresarButtonAction(){
        mediaPlayer.stop();

        Cancion reproducir = cancionLista.find(reproducirSong); //busca la cancion en la lista
        reproducirSong = reproducir.getPrev().getNombre(); //consigue el nombre de la cancion anterior

        media = new Media (cancionLista.find(reproducirSong).getFile().toURI().toString());//agrega la cancion al Media
        mediaPlayer = new MediaPlayer(media);

        if (cancionLista.find(reproducirSong).isFavorite()){ //muestra si es favorito o no
            Image myImage = new Image(getClass().getResourceAsStream("Icons/heartIMG.png"));
            favoriteImage.setImage(myImage);
        }
        else{
            Image myImage = new Image(getClass().getResourceAsStream("Icons/corazonIMG.png"));
            favoriteImage.setImage(myImage);
        }

        mediaPlayer.play();

        }

    /**
     * Permite subir canciones
     */
        @FXML
    public void UploadButtonAction(){

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

    }

    /**
     * Permite eliminar canciones
     */
    @FXML
    public void GarbageButtonAction() {
        mediaPlayer.stop();
        String eliminar = songsListView.getSelectionModel().getSelectedItem();

        cancionLista.delete(eliminar);

        songsListView.getItems().removeAll(songsListView.getSelectionModel().getSelectedItem());

    }


    @FXML
    public void cycleButtonAction(){

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

    /**
     * Inicializa el arduino
     */
    public void llamarArduino(){
        //arduino();
    }

    /**
     * Permite la comuniación con arduino
     */
    public void arduino(){
        System.out.println("arduino");
        SerialPort puerto = new SerialPort("COM4");
        try {
            puerto.openPort();
            puerto.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            puerto.addEventListener((SerialPortEvent event) -> {
                if (event.isRXCHAR()){
                    try {
                        String x = puerto.readString();
                        System.out.println(x + " x");
                        if (x.equals("p")){
                            System.out.println("entra a p");
                            playButtonAction();
                        }
                        if (x.equals("f")) {
                            favoriteButtonAction();
                        }
                        if (x.equals("r")){
                            regresarButtonAction();
                        }
                        if (x.equals("n")){
                            nextButtonAction();
                        }
                        if (x.equals("c")){
                            cycleButtonAction();
                        }
                    }
                    catch (SerialPortException e){
                        e.printStackTrace();
                    }
                }
            });
        }
        catch (SerialPortException e){
            e.printStackTrace();
        }
    }
}