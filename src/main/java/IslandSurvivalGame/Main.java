package IslandSurvivalGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    // Variables to track resources
    private int wood = 0;
    private int food = 0;
    private int water = 0;

    @Override
    public void start(Stage primaryStage) {
        // Set the title of the game window
        primaryStage.setTitle("Island Survival Game");

        // Create UI Elements
        Label titleLabel = new Label("Island Survival Game");
        ProgressBar hungerBar = new ProgressBar(1.0); // Hunger starts at 100%
        ProgressBar thirstBar = new ProgressBar(1.0); // Thirst starts at 100%
        ProgressBar healthBar = new ProgressBar(1.0); // Health starts at 100%

        Button collectWoodButton = new Button("Collect Wood 🌳");
        Button collectFoodButton = new Button("Collect Food 🍎");
        Button collectWaterButton = new Button("Collect Water 💧");

        Label inventoryLabel = new Label("Inventory:");
        Label woodCountLabel = new Label("Wood: 0");
        Label foodCountLabel = new Label("Food: 0");
        Label waterCountLabel = new Label("Water: 0");

        // Event Listeners for Resource Collection
        collectWoodButton.setOnAction(e -> {
            wood++;
            woodCountLabel.setText("Wood: " + wood);
        });

        collectFoodButton.setOnAction(e -> {
            food++;
            foodCountLabel.setText("Food: " + food);
        });

        collectWaterButton.setOnAction(e -> {
            water++;
            waterCountLabel.setText("Water: " + water);
        });

        // Layout (Vertical Box)
        VBox root = new VBox(10); // 10px spacing between elements
        root.getChildren().addAll(
                titleLabel,
                hungerBar,
                thirstBar,
                healthBar,
                collectWoodButton,
                collectFoodButton,
                collectWaterButton,
                inventoryLabel,
                woodCountLabel,
                foodCountLabel,
                waterCountLabel
        );

        // Style the layout
        root.setStyle("-fx-padding: 20; -fx-background-color: #87CEEB; -fx-alignment: center;");

        // Create the scene
        Scene scene = new Scene(root, 300, 400); // Width: 300px, Height: 400px

        // Set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}