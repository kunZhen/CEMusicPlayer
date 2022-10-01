module com.example.cemusicplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires com.google.gson;
    requires javacsv;
    requires javafx.media;
    requires com.fazecast.jSerialComm;
    requires jssc;


    opens com.example.cemusicplayer to javafx.fxml;
    exports com.example.cemusicplayer;
}