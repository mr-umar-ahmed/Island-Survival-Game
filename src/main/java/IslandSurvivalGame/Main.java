package IslandSurvivalGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.Random;

public class Main extends Application {

    // Variables to track resources
    private int wood = 0;
    private int food = 0;
    private int water = 0;

    // Variables to track survival stats
    private double hunger = 100; // Starts at 100%
    private double thirst = 100; // Starts at 100%
    private double health = 100; // Starts at 100%

    // Variables to track tools and shelter
    private boolean hasAxe = false; // Player starts without an axe
    private boolean hasShelter = false; // Player starts without a shelter

    // Variables for scoring and levels
    private int survivalTime = 0; // Tracks how long the player survives (in seconds)
    private int score = 0; // Tracks the player's score
    private int level = 1; // Tracks the current level

    // Variable to track if the game is over
    private boolean isGameOver = false;

    // High Score Tracking
    private int highScore = 0;

    @Override
    public void start(Stage primaryStage) {
        // Load the high score from a file
        highScore = loadHighScore();

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

        Button eatFoodButton = new Button("Eat Food 🍽️"); // Consume food
        Button drinkWaterButton = new Button("Drink Water 🥤"); // Consume water

        Button craftAxeButton = new Button("Craft Axe ⚒️"); // Craft an axe
        Button craftShelterButton = new Button("Craft Shelter 🏠"); // Craft a shelter

        Button restartButton = new Button("Restart Game 🔄"); // Restart button

        Label inventoryLabel = new Label("Inventory:");
        Label woodCountLabel = new Label("Wood: 0");
        Label foodCountLabel = new Label("Food: 0");
        Label waterCountLabel = new Label("Water: 0");
        Label toolLabel = new Label("Tools: None"); // Display tools
        Label eventLabel = new Label(""); // Display random events
        Label scoreLabel = new Label("Score: 0"); // Display score
        Label levelLabel = new Label("Level: 1"); // Display level
        Label highScoreLabel = new Label("High Score: " + highScore); // Display high score
        Label gameOverLabel = new Label(""); // Game Over message

        // Style the labels
        gameOverLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: red; -fx-font-weight: bold;");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Event Listeners for Resource Collection
        collectWoodButton.setOnAction(e -> {
            if (!isGameOver) { // Only allow actions if the game is not over
                if (hasAxe) {
                    wood += 2; // Collect wood faster with an axe
                } else {
                    wood++;
                }
                woodCountLabel.setText("Wood: " + wood);
            }
        });

        collectFoodButton.setOnAction(e -> {
            if (!isGameOver) { // Only allow actions if the game is not over
                food++;
                foodCountLabel.setText("Food: " + food);
            }
        });

        collectWaterButton.setOnAction(e -> {
            if (!isGameOver) { // Only allow actions if the game is not over
                water++;
                waterCountLabel.setText("Water: " + water);
            }
        });

        // Event Listeners for Consuming Resources
        eatFoodButton.setOnAction(e -> {
            if (!isGameOver && food > 0) { // Only allow actions if the game is not over
                food--;
                hunger = Math.min(100, hunger + 20); // Restore 20% hunger
                foodCountLabel.setText("Food: " + food);
                hungerBar.setProgress(hunger / 100);
            }
        });

        drinkWaterButton.setOnAction(e -> {
            if (!isGameOver && water > 0) { // Only allow actions if the game is not over
                water--;
                thirst = Math.min(100, thirst + 20); // Restore 20% thirst
                waterCountLabel.setText("Water: " + water);
                thirstBar.setProgress(thirst / 100);
            }
        });

        // Event Listener for Crafting an Axe
        craftAxeButton.setOnAction(e -> {
            if (!isGameOver && wood >= 5 && !hasAxe) { // Only allow actions if the game is not over
                wood -= 5; // Deduct wood
                hasAxe = true; // Player now has an axe
                woodCountLabel.setText("Wood: " + wood);
                toolLabel.setText("Tools: Axe ⚒️"); // Update tools label
                System.out.println("You crafted an axe!");
            }
        });

        // Event Listener for Crafting a Shelter
        craftShelterButton.setOnAction(e -> {
            if (!isGameOver && wood >= 10 && !hasShelter) { // Only allow actions if the game is not over
                wood -= 10; // Deduct wood
                hasShelter = true; // Player now has a shelter
                woodCountLabel.setText("Wood: " + wood);
                toolLabel.setText("Tools: Axe ⚒️, Shelter 🏠"); // Update tools label
                System.out.println("You crafted a shelter!");
            }
        });

        // Restart Button Logic
        restartButton.setOnAction(e -> {
            // Reset all variables
            wood = 0;
            food = 0;
            water = 0;
            hunger = 100;
            thirst = 100;
            health = 100;
            hasAxe = false;
            hasShelter = false;
            survivalTime = 0;
            score = 0;
            level = 1;
            isGameOver = false;

            // Reset UI elements
            woodCountLabel.setText("Wood: 0");
            foodCountLabel.setText("Food: 0");
            waterCountLabel.setText("Water: 0");
            toolLabel.setText("Tools: None");
            eventLabel.setText("");
            scoreLabel.setText("Score: 0");
            levelLabel.setText("Level: 1");
            gameOverLabel.setText("");

            // Reset progress bars
            hungerBar.setProgress(1.0);
            thirstBar.setProgress(1.0);
            healthBar.setProgress(1.0);

            // Restart the game loop
            new Thread(() -> {
                Random random = new Random();
                while (true) {
                    try {
                        Thread.sleep(5000); // Decrease stats every 5 seconds
                        if (isGameOver) break; // Stop the game loop if the game is over

                        survivalTime += 5; // Increase survival time by 5 seconds
                        score += 5; // Increase score by 5 points every 5 seconds
                        scoreLabel.setText("Score: " + score);

                        // Level up every 60 seconds (12 iterations of 5 seconds)
                        if (survivalTime % 60 == 0) {
                            level++;
                            levelLabel.setText("Level: " + level);
                            System.out.println("You reached Level " + level + "!");
                        }

                        hunger = Math.max(0, hunger - 10); // Decrease hunger by 10%
                        thirst = Math.max(0, thirst - 10); // Decrease thirst by 10%

                        // If hunger or thirst reaches zero, decrease health
                        if (hunger <= 0 || thirst <= 0) {
                            health = Math.max(0, health - 10); // Decrease health by 10%
                        }

                        // Random Events
                        int eventChance = random.nextInt(100); // Generate a random number between 0 and 99
                        if (eventChance < 20 + (level * 5)) { // Increased chance of storms as level increases
                            eventLabel.setText("Event: Storm! 🌩️");
                            if (!hasShelter) {
                                health = Math.max(0, health - 20); // Lose 20% health during a storm without a shelter
                                System.out.println("You were caught in a storm without a shelter! Health decreased.");
                            } else {
                                System.out.println("Your shelter protected you from the storm!");
                            }
                        } else if (eventChance < 40 + (level * 5)) { // Increased chance of wildlife encounters
                            eventLabel.setText("Event: Wildlife Encounter! 🐍");
                            if (random.nextBoolean()) { // 50% chance of harm or benefit
                                health = Math.max(0, health - 10); // Harm: Lose 10% health
                                System.out.println("A snake bit you! Health decreased.");
                            } else {
                                food += 5; // Benefit: Find food
                                foodCountLabel.setText("Food: " + food);
                                System.out.println("You found food during the wildlife encounter!");
                            }
                        } else {
                            eventLabel.setText(""); // No event
                        }

                        // Update Progress Bars
                        hungerBar.setProgress(hunger / 100);
                        thirstBar.setProgress(thirst / 100);
                        healthBar.setProgress(health / 100);

                        // Check for Game Over
                        if (health <= 0) {
                            isGameOver = true; // Mark the game as over
                            gameOverLabel.setText("GAME OVER! You survived for " + survivalTime + " seconds and scored " + score + " points.");

                            // Save the high score
                            if (score > highScore) {
                                highScore = score;
                                highScoreLabel.setText("High Score: " + highScore);
                                saveHighScore(highScore);
                            }

                            System.out.println("Game Over! You survived for " + survivalTime + " seconds and scored " + score + " points.");
                            break; // Exit the game loop
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
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
                eatFoodButton,
                drinkWaterButton,
                craftAxeButton,
                craftShelterButton,
                restartButton,
                inventoryLabel,
                woodCountLabel,
                foodCountLabel,
                waterCountLabel,
                toolLabel,
                eventLabel,
                scoreLabel,
                levelLabel,
                highScoreLabel,
                gameOverLabel
        );

        // Style the layout
        root.setStyle("-fx-padding: 20; -fx-background-color: #87CEEB; -fx-alignment: center;");

        // Create the scene
        Scene scene = new Scene(root, 300, 400); // Width: 300px, Height: 400px

        // Set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to save the high score to a file
    private void saveHighScore(int highScore) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("highscore.txt"))) {
            writer.write(String.valueOf(highScore));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load the high score from a file
    private int loadHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader("highscore.txt"))) {
            String line = reader.readLine();
            return line != null ? Integer.parseInt(line) : 0;
        } catch (IOException e) {
            return 0; // Default high score if file doesn't exist
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}