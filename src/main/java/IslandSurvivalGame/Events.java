package IslandSurvivalGame;

import java.util.Random;

public class Events {
    private Random random = new Random();

    // Generate a random event
    public String getRandomEvent(boolean hasShelter) {
        int eventChance = random.nextInt(100); // Generate a random number between 0 and 99

        if (eventChance < 20) { // 20% chance of a storm
            if (!hasShelter) {
                return "Storm! 🌩 Health decreased.";
            } else {
                return "Storm! 🌩 Shelter protected you.";
            }
        } else if (eventChance < 40) { // 20% chance of wildlife encounter
            if (random.nextBoolean()) { // 50% chance of harm or benefit
                return "Wildlife Encounter! 🐍 A snake bit you! Health decreased.";
            } else {
                return "Wildlife Encounter! 🐍 You found food!";
            }
        } else {
            return ""; // No event
        }
    }
}