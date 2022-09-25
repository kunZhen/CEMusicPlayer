package com.example.cemusicplayer;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class VistaBibliotecaControlador implements Initializable {
    @FXML
    private TextField addBibliotecaTextField;

    @FXML
    private Button addButton;

    @FXML
    private TableView<Biblioteca> bibliotecaTableView;

    @FXML
    private Button deleteButton;

    @FXML
    private Button openButton;

    @FXML
    private Text searchText;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label labelNombre;

    @FXML
    private TextField searchTextField;
    private java.time.format.DateTimeFormatter DateTimeFormatter;

    private ObservableList<Biblioteca> bibliotecaObservableList;
    private ObservableList<Biblioteca> filtroBibliotecaO;

    //Crear la lista de las bibliotecas
    BibliotecaDoubleEndedLinkedList bibliotecaLista = new BibliotecaDoubleEndedLinkedList();
    private String nombreUsuario;
    private String nombreBiblioteca;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try { //columnas para el tableView
            TableColumn nombreColumn = new TableColumn("Nombre");
            TableColumn cantColumn = new TableColumn("Cantidad");
            TableColumn fechaColumn = new TableColumn("Fecha");

            //se agregan las columnas a la table
            bibliotecaTableView.getColumns().addAll(nombreColumn, cantColumn, fechaColumn);

            //actualizar los valores de cada columna
            nombreColumn.setCellValueFactory(new PropertyValueFactory<Biblioteca, String>("nombre"));
            cantColumn.setCellValueFactory(new PropertyValueFactory<Biblioteca, Integer>("cantidad"));
            fechaColumn.setCellValueFactory(new PropertyValueFactory<Biblioteca, String>("fecha"));

            bibliotecaObservableList = FXCollections.observableArrayList();
            filtroBibliotecaO = FXCollections.observableArrayList();

            bibliotecaTableView.setItems(bibliotecaObservableList);

        } catch (Exception e) {
            System.err.println("Error al abrir la biblioteca");
        }
    }
    public void recibir (String nombreUsuario){
        this.nombreUsuario = nombreUsuario;
    }
    private void escribirCSV(Biblioteca newBiblioteca) throws FileNotFoundException { // creditos para escritura de csv https://www.youtube.com/watch?v=J6oXEXVNNwo&feature=share&si=ELPmzJkDCLju2KnD5oyZMQ
        String salidaArchivo = "src/main/resources/Usuarios/" + this.nombreUsuario + "/Bibliotecas/infoBibliotecas.csv";
        boolean existe = new File (salidaArchivo).exists();

        System.out.println("Nombre usuario: " + nombreUsuario);
        /*
        if (existe){ //Elimina el archivo en caso de que exista previamente
            File archivoBiblioteca = new File(salidaArchivo);
            archivoBiblioteca.delete();
        }*/

        try{
            CsvWriter salidaCSV =  new CsvWriter(new FileWriter(salidaArchivo, true), ';'); //Crea el archivo

            /*//Datos para identificar las columnas
            salidaCSV.write("Nombre");
            salidaCSV.write("cantidad de canciones");
            salidaCSV.write("fecha de creación");*/

            //salidaCSV.endRecord(); //deja de escribir

            for (Biblioteca nB : bibliotecaObservableList ) {

                if (bibliotecaLista.find(nB.getNombre()) != null) {
                    if (bibliotecaLista.find(nB.getNombre()).equals(nB.getNombre())) {
                        System.out.println("La biblioteca " + nB.getNombre() + "se encuentra en la lista");
                    }
                } else {
                    salidaCSV.write(nB.getNombre());
                    salidaCSV.write("0");
                    salidaCSV.write(nB.getFecha());

                    salidaCSV.endRecord(); //salto de línea
                }

            }
            salidaCSV.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //conseguir las bibliotecas
    @FXML
    private void eventAction(ActionEvent event) throws FileNotFoundException {
        Object evt = event.getSource();

        if (evt.equals(addButton)) {

            LocalDate fechaActual = LocalDate.now(); //fecha
            System.out.println(fechaActual);
            System.out.println("Fecha actual " + fechaActual);

            Biblioteca bibliotecaNew = new Biblioteca(addBibliotecaTextField.getText(), 0, fechaActual.toString());
            bibliotecaTableView.getItems().add(bibliotecaNew);
            addBibliotecaTextField.clear();

            escribirCSV(bibliotecaNew);

            bibliotecaLista.insertLastBiblioteca(bibliotecaNew); //agregar a la lista las bibliotecas
            bibliotecaLista.displayBiblioteca();

        } else if (evt.equals(deleteButton)) {
            Biblioteca eliminar = bibliotecaTableView.getSelectionModel().getSelectedItem(); //obtener el objeto por medio del tableView
            System.out.println("eliminar" + eliminar.getNombre());

            this.nombreBiblioteca = eliminar.getNombre();

            bibliotecaLista.deleteSelectedBiblioteca(eliminar.getNombre()); //Elimino el objeto
            bibliotecaLista.displayBiblioteca();

            bibliotecaTableView.getItems().removeAll(bibliotecaTableView.getSelectionModel().getSelectedItem()); //lo elimino del tableView

        }else if (evt.equals((openButton))){
            Biblioteca escoger = bibliotecaTableView.getSelectionModel().getSelectedItem(); //obtener el objeto por medio del tableView
            System.out.println("escoger " + escoger.getNombre());

            this.nombreBiblioteca = escoger.getNombre();

            CargarEscena("vistaPlaylist.fxml", event,nombreBiblioteca); //Crear nueva ventana

        } else {
            System.err.println("Error al anadir/eliminar ");
        }


    }

    private void filtrarBiblioteca (KeyEvent event){

        String filtroNombre = searchTextField.getText();

        if (filtroNombre.isEmpty()){
            this.bibliotecaTableView.setItems(bibliotecaObservableList);
        }else{
            filtroBibliotecaO.clear();

            for (Biblioteca b: bibliotecaObservableList){

                if(b.getNombre().toLowerCase().contains(filtroNombre.toLowerCase())){
                    filtroBibliotecaO.add(b);
                }
                bibliotecaTableView.setItems(filtroBibliotecaO);

            }
        }
    }

    public void CargarEscena(String url, Event event,String nombreBiblioteca){
        try {
            Object eventSource = event.getSource();
            Node sourceAsNode = (Node) eventSource ;
            Scene oldScene = sourceAsNode.getScene();
            Window window = oldScene.getWindow();
            Stage stage = (Stage) window ;
            stage.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaPlaylist.fxml"));
            root = loader.load();

            VistaPlaylistControlador vistaplaylist= loader.getController();
            vistaplaylist.conseguirNombreUsuarioBiblioteca(this.nombreUsuario,nombreBiblioteca);


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