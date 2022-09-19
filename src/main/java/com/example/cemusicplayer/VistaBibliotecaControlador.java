package com.example.cemusicplayer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class VistaBibliotecaControlador implements Initializable {

    @FXML
    private TextField addBibliotecaTextField;

    @FXML
    private Button addButton;

    @FXML
    private TableView<?> bibliotecaTableView;

    @FXML
    private TableColumn<?, ?> cantidadColumn;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<?, ?> fechaColumn;

    @FXML
    private TableColumn<?, ?> nombreColumn;

    @FXML
    private Button openButton;

    @FXML
    private Text searchText;

    @FXML
    private TextField searchTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

