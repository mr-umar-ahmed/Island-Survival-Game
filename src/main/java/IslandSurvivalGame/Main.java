package IslandSurvivalGame;

import IslandSurvivalGame.Events;
import IslandSurvivalGame.GameLogic;
import IslandSurvivalGame.Resources;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private GameLogic gameLogic = new GameLogic();
    private Resources resources = new Resources();
    private Events events = new Events();

    @Override
    public void start(Stage primaryStage) {
        // Set up the UI and integrate the logic from GameLogic, Resources, and Events
    }

    public static void main(String[] args) {
        launch(args);
    }
}