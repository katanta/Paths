package edu.ntnu.mappe32;

public class HealthGoal implements Goal {

    private final int minimumHealth;

    /**
     * This constructor facilitates the creation of instances of the class HealthGoal.
     * @param minimumHealth Points required for a goal to be reached, as int.
     */
    public HealthGoal(final int minimumHealth) {
        this.minimumHealth = minimumHealth;
    }

    /**
     * This method checks if the score goal is reached.
     * @param player The player a goal-check should be executed on, as Player.
     * @return Whether the player has fulfilled a goal, as boolean.
     */
    public boolean isFulfilled(Player player) {
        return player.getHealth() >= minimumHealth;
    }
}
