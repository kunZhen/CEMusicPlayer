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

/**
 * VistaBibliotecaControlador se encarga de controlar los eventos y las acciones de la
 * ventana de bibliotecas del usuario.
 */
public class VistaBibliotecaControlador implements Initializable {
    @FXML
    private TextField addBibliotecaTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button refrescarButton;

    @FXML
    private TableView<Biblioteca> bibliotecaTableView;

    @FXML
    private Button deleteButton;

    @FXML
    private Button openButton;


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label labelNombre;


    private java.time.format.DateTimeFormatter DateTimeFormatter;

    private ObservableList<Biblioteca> bibliotecaObservableList;
    private ObservableList<Biblioteca> filtroBibliotecaO;

    //Crear la lista de las bibliotecas
    BibliotecaDoubleEndedLinkedList bibliotecaLista = new BibliotecaDoubleEndedLinkedList();
    private String nombreUsuario;
    private String nombreBiblioteca;
    private boolean presionar = true;


    /**
     * Inicializa el programa
     * @param url - hace referencia a la ubicación de la interfaz gráfica (el archivo fxml)
     * @param resourceBundle - para traducir textos o modificar otra información dependiente de la configuración regional
     */
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

    /**
     * Actualiza el nombre del usuario
     * @param nombreUsuario nombre del usuario
     */
    public void recibir (String nombreUsuario){
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Muestra en la tabla
     * @param event - interación del usuario con el programa por medio de clicks del mouse
     */
    @FXML
    void mostrar (ActionEvent event) {
        System.out.println("Entre al mostrar ");
        String salidaArchivo = "src/main/resources/Usuarios/" + this.nombreUsuario + "/Bibliotecas/infoBibliotecas.csv";

        if (presionar) { //si puedo presionar, entonces muestre
            presionar = false;
            BufferedReader reader = null;
            String line = "";

            bibliotecaObservableList = FXCollections.observableArrayList();

            try{
                reader = new BufferedReader(new FileReader(salidaArchivo));
                reader.readLine();

                while ((line = reader.readLine()) != null) {
                    String[] row = line.split(";");

                    Biblioteca newBiblioteca = new Biblioteca(row[0], Integer.parseInt(row[1]), row[2]);
                    bibliotecaObservableList.add(newBiblioteca);
                    bibliotecaTableView.getItems().add(newBiblioteca);
                    bibliotecaLista.insertLastBiblioteca(newBiblioteca); //agregar a la lista las bibliotecas
                    bibliotecaLista.displayBiblioteca();

                    // Imprime cada biblioteca creada
                    for (String index : row) {
                        System.out.printf("%-20s", index);

                    }

                    System.out.println();
                }
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                    System.out.println("Cierro el archivo csv de bibliotecas de: " + nombreUsuario);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            System.out.println("Ya se habia mostrado las bibliotecas");
        }

    }

    /**
     * Escribe en el archivo CSV para agregar las bibliotecas
     * @param newBiblioteca biblioteca nueva
     * @throws FileNotFoundException excepción que se produce cuando la dirección del archivo
     * no existe o no es accesible
     */
    private void escribirCSV(Biblioteca newBiblioteca) throws FileNotFoundException { // creditos para escritura de csv https://www.youtube.com/watch?v=J6oXEXVNNwo&feature=share&si=ELPmzJkDCLju2KnD5oyZMQ
        String salidaArchivo = "src/main/resources/Usuarios/" + this.nombreUsuario + "/Bibliotecas/infoBibliotecas.csv";
        boolean existe = new File (salidaArchivo).exists();

        System.out.println("Nombre usuario: " + nombreUsuario);

        try{
            System.out.println("Entra al try de escribirCSV");
            CsvWriter salidaCSV =  new CsvWriter(new FileWriter(salidaArchivo, true), ';'); //Crea el archivo

            int largo = 0;

            for (Biblioteca nB : bibliotecaObservableList) {
                if (bibliotecaLista.find(nB.getNombre()) != null) {
                    largo++;
                }
            }

            System.out.println("bibliotecaObservableList largo: " + largo);

            if (largo == 0) { //si no hay elementos en la lista, lo agrega de una vez
                salidaCSV.write(newBiblioteca.getNombre());
                salidaCSV.write("0");
                salidaCSV.write(newBiblioteca.getFecha());
                System.out.println("Se agrego la biblioteca al CSV");

                salidaCSV.endRecord(); //salto de línea
            } else {
                for (Biblioteca nB : bibliotecaObservableList) {
                    System.out.println("Entra al try de escribirCSV --> entra al for");

                    if (bibliotecaLista.find(nB.getNombre()) != null) {
                        System.out.println("Entra al try de escribirCSV --> entra al for --> encuentro el nombre");
                        if (bibliotecaLista.find(nB.getNombre()).equals(newBiblioteca.getNombre())) {
                            System.out.println("La biblioteca " + nB.getNombre() + "se encuentra en la lista");
                        } else {
                            System.out.println("Entra al try de escribirCSV --> entra al for --> entra al else para escribir el CSV");
                            salidaCSV.write(newBiblioteca.getNombre());
                            salidaCSV.write("0");
                            salidaCSV.write(newBiblioteca.getFecha());
                            System.out.println("Se agrego la biblioteca al CSV");

                            salidaCSV.endRecord(); //salto de línea
                            break;
                        }
                    }
                }
            }

            salidaCSV.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //conseguir las bibliotecas

    /**
     * Permite ejecutar la acción respectiva al botón que se presionó
     * @param event interación del usuario con el programa por medio de clicks del mouse
     * @throws FileNotFoundException excepción que se produce cuando la dirección del archivo
     * no existe o no es accesible
     */
    @FXML
    private void eventAction(ActionEvent event) throws FileNotFoundException {
        Object evt = event.getSource();

        if (evt.equals(addButton)) {

            LocalDate fechaActual = LocalDate.now(); //fecha
            System.out.println("Fecha actual: " + fechaActual);

            Biblioteca bibliotecaNew = new Biblioteca(addBibliotecaTextField.getText(), 0, fechaActual.toString());
            bibliotecaTableView.getItems().add(bibliotecaNew);
            addBibliotecaTextField.clear();

            escribirCSV(bibliotecaNew); //agrega al archivo CSV la nueva biblioteca

            bibliotecaLista.insertLastBiblioteca(bibliotecaNew); //agregar a la lista las bibliotecas
            bibliotecaLista.displayBiblioteca();


        } else if (evt.equals(deleteButton)) {
            Biblioteca eliminar = bibliotecaTableView.getSelectionModel().getSelectedItem(); //obtener el objeto por medio del tableView
            System.out.println("Se elimina: " + eliminar.getNombre());

            //this.nombreBiblioteca = eliminar.getNombre();

            bibliotecaLista.deleteSelectedBiblioteca(eliminar.getNombre()); //Elimino el objeto
            System.out.println("Se ha eliminado la biblioteca: " + eliminar.getNombre());
            bibliotecaLista.displayBiblioteca(); //Muestro la biblioteca

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


    /**
     *
     * @param url - hace referencia a la ubicación de la interfaz gráfica (el archivo fxml)
     * @param event interación del usuario con el programa por medio de clicks del mouse
     * @param nombreBiblioteca nombre de la biblioteca
     */
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