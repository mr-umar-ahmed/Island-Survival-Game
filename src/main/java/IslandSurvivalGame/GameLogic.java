package IslandSurvivalGame;

public class GameLogic {
    private double hunger = 100;
    private double thirst = 100;
    private double health = 100;
    private int survivalTime = 0;
    private int score = 0;
    private int level = 1;
    private boolean isGameOver = false;

    // Decrease stats over time
    public void decreaseStats() {
        hunger = Math.max(0, hunger - 10); // Decrease hunger by 10%
        thirst = Math.max(0, thirst - 10); // Decrease thirst by 10%

        if (hunger <= 0 || thirst <= 0) {
            health = Math.max(0, health - 10); // Decrease health by 10%
        }

        survivalTime += 5; // Increase survival time by 5 seconds
        score += 5; // Increase score by 5 points every 5 seconds

        if (survivalTime % 60 == 0) {
            level++; // Level up every 60 seconds
        }
    }

    // Check if the game is over
    public boolean isGameOver() {
        return health <= 0;
    }

    // Getters for stats
    public double getHunger() {
        return hunger;
    }

    public double getThirst() {
        return thirst;
    }

    public double getHealth() {
        return health;
    }

    public int getSurvivalTime() {
        return survivalTime;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }
}