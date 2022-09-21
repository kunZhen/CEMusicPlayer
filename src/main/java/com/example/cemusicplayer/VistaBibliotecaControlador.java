package com.example.cemusicplayer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

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


        } catch (Exception e) {
            System.err.println("Error al abrir la biblioteca");
        }
    }

    private void escribirCSV(Biblioteca newBiblioteca) throws FileNotFoundException {
        String csv = "src/main/resources/Usuarios/csalazar/Bibliotecas/infoBibliotecas.csv";
        File csvFile = new File(csv);
        PrintWriter out = new PrintWriter(csvFile);

        out.printf("\n%s; %d\n", newBiblioteca.getNombre(),newBiblioteca.getCantidad(),newBiblioteca.getFecha());

        out.close();
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

        } else if (evt.equals(deleteButton)) {
            Biblioteca eliminar = bibliotecaTableView.getSelectionModel().getSelectedItem(); //obtener el objeto por medio del tableView
            System.out.println(eliminar.getNombre());

            bibliotecaLista.deleteSelectedBiblioteca(eliminar.getNombre()); //Elimino el objeto
            bibliotecaLista.displayBiblioteca();

            bibliotecaTableView.getItems().removeAll(bibliotecaTableView.getSelectionModel().getSelectedItem()); //lo elimino del tableView
        } else {
            System.err.println("Error al anadir/eliminar ");
        }
    }}