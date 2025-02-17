module com.example.islandsurvivalgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens game to javafx.fxml;
    exports game;
}