package islandsurvival;

public class Resources {
    private int wood = 0;
    private int food = 0;
    private int water = 0;
    private boolean hasAxe = false;
    private boolean hasShelter = false;

    // Collect resources
    public void collectWood() {
        if (hasAxe) {
            wood += 2; // Collect wood faster with an axe
        } else {
            wood++;
        }
    }

    public void collectFood() {
        food++;
    }

    public void collectWater() {
        water++;
    }

    // Consume resources
    public boolean eatFood() {
        if (food > 0) {
            food--;
            return true; // Successfully consumed food
        }
        return false; // Not enough food
    }

    public boolean drinkWater() {
        if (water > 0) {
            water--;
            return true; // Successfully consumed water
        }
        return false; // Not enough water
    }

    // Craft tools and shelters
    public boolean craftAxe() {
        if (wood >= 5 && !hasAxe) {
            wood -= 5;
            hasAxe = true;
            return true; // Successfully crafted an axe
        }
        return false; // Not enough wood or already has an axe
    }

    public boolean craftShelter() {
        if (wood >= 10 && !hasShelter) {
            wood -= 10;
            hasShelter = true;
            return true; // Successfully crafted a shelter
        }
        return false; // Not enough wood or already has a shelter
    }

    // Getters for resources
    public int getWood() {
        return wood;
    }

    public int getFood() {
        return food;
    }

    public int getWater() {
        return water;
    }

    public boolean hasAxe() {
        return hasAxe;
    }

    public boolean hasShelter() {
        return hasShelter;
    }
}