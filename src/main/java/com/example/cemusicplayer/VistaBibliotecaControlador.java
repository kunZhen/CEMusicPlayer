package com.example.cemusicplayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.ResourceBundle;

public class VistaBibliotecaControlador implements Initializable {
    @FXML
    private TableView<Biblioteca> bibliotecaTableView;
    @FXML
    private TableColumn<Biblioteca, String> nombreTableColumn;
    @FXML
    private TableColumn<Biblioteca, Integer> cantidadTableColumn;
    @FXML
    private TableColumn<Biblioteca, String> fechaTableColumn;
    @FXML
    private TextField addTextField;
    @FXML
    private TextField consultarTextField;
    @FXML
    private String fecha = "19/09/2022";
    @FXML
    private Integer cantidad = 3;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;

    @FXML
    private TextField addBibliotecaTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try { //columnas para el tableView
            nombreTableColumn.setCellValueFactory(new PropertyValueFactory<Biblioteca, String>("nombre"));
            cantidadTableColumn.setCellValueFactory(new PropertyValueFactory<Biblioteca, Integer>("cantidad"));
            fechaTableColumn.setCellValueFactory(new PropertyValueFactory<Biblioteca, String>("fecha"));


        } catch (Exception e) {
            System.err.println("Error al abrir la biblioteca");
        }
    }

    //conseguir las bibliotecas
    @FXML
    private void eventAction(ActionEvent event){
        Object evt = event.getSource();
        System.out.println("entra al action event");

        if (evt.equals(addButton)) {
            Biblioteca biblioteca = new Biblioteca(addTextField.getText(), cantidad, fecha);
            ObservableList<Biblioteca> bibliotecas = bibliotecaTableView.getItems();
            bibliotecas.add(biblioteca);
            bibliotecaTableView.setItems(bibliotecas);

        } else if (evt.equals(deleteButton)) {
            int selectedID = bibliotecaTableView.getSelectionModel().getSelectedIndex();
            bibliotecaTableView.getItems().remove(selectedID);

        } else {
            System.err.println("Error al anadir/eliminar ");
        }
    }}