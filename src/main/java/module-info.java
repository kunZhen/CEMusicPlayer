module com.example.cemusicplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires com.google.gson;


    opens com.example.cemusicplayer to javafx.fxml;
    exports com.example.cemusicplayer;
}