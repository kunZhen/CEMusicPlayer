package com.example.cemusicplayer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

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

    @FXML
    private TextField searchTextField;
    private java.time.format.DateTimeFormatter DateTimeFormatter;

    private ObservableList<Biblioteca> bibliotecaObservableList;
    private ObservableList<Biblioteca> filtroBibliotecaO;

    //Crear la lista de las bibliotecas
    BibliotecaDoubleEndedLinkedList bibliotecaLista = new BibliotecaDoubleEndedLinkedList();



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

    private void escribirCSV(Biblioteca newBiblioteca) throws FileNotFoundException { // creditos para escritura de csv https://www.youtube.com/watch?v=J6oXEXVNNwo&feature=share&si=ELPmzJkDCLju2KnD5oyZMQ
        String salidaArchivo = "src/main/resources/Usuarios/csalazar/Bibliotecas/infoBibliotecas.csv";
        boolean existe = new File (salidaArchivo).exists();

        if (existe){ //Elimina el archivo en caso de que exista previamente
            File archivoBiblioteca = new File(salidaArchivo);
            archivoBiblioteca.delete();
        }
        try{
            CsvWriter salidaCSV =  new CsvWriter(new FileWriter(salidaArchivo, true), ','); //Crea el archivo

            //Datos para identificar las columnas
            salidaCSV.write("Nombre");
            salidaCSV.write("cantidad de canciones");
            salidaCSV.write("fecha de creación");

            salidaCSV.endRecord(); //deja de escribir

            for (Biblioteca nB : bibliotecaObservableList ){
                salidaCSV.write(nB.getNombre());
                salidaCSV.write("0");
                salidaCSV.write(nB.getFecha());

                salidaCSV.endRecord();
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
        System.out.println("entra al action event");

        if (evt.equals(addButton)) {
            System.out.println("Entra al AddButton");

            LocalDate fechaActual = LocalDate.now(); //fecha
            System.out.println(fechaActual);
            System.out.println("Fecha actual " + fechaActual);

            Biblioteca bibliotecaNew = new Biblioteca(addBibliotecaTextField.getText(), 0, fechaActual.toString());
            bibliotecaTableView.getItems().add(bibliotecaNew);
            addBibliotecaTextField.clear();

            escribirCSV(bibliotecaNew);

            bibliotecaLista.insertLastBiblioteca(bibliotecaNew); //agregar a la lista las bibliotecas
            bibliotecaLista.displayBiblioteca();

            Biblioteca b = new Biblioteca(addBibliotecaTextField.getText(), 0, fechaActual.toString());

        } else if (evt.equals(deleteButton)) {
            Biblioteca eliminar = bibliotecaTableView.getSelectionModel().getSelectedItem(); //obtener el objeto por medio del tableView
            System.out.println(eliminar.getNombre());

            bibliotecaLista.deleteSelectedBiblioteca(eliminar.getNombre()); //Elimino el objeto
            bibliotecaLista.displayBiblioteca();

            bibliotecaTableView.getItems().removeAll(bibliotecaTableView.getSelectionModel().getSelectedItem()); //lo elimino del tableView
        }else if (evt.equals((openButton))){
            //escribirCSV(bibliotecaNew);

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
}