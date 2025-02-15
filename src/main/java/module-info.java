module com.example.islandsurvivalgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens IslandSurvivalGame to javafx.fxml;
    exports IslandSurvivalGame;
}