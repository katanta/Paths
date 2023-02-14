package edu.ntnu.mappe32;

public class HealthGoal implements Goal {

    private final int minimumHealth;

    /**
     * This constructor facilitates the creation of instances of the class HealthGoal.
     * @param minimumHealth Points required for a goal to be reached, as int.
     * @since 0.1
     */
    public HealthGoal(final int minimumHealth) {
        this.minimumHealth = minimumHealth;
    }

    /**
     * This method checks if the health goal is reached.
     * @param player The player a goal-check should be executed on, as Player.
     * @return Whether the player has fulfilled the health, as boolean.
     * @since 0.1
     */
    public boolean isFulfilled(Player player) {
        return player.getHealth() >= minimumHealth;
    }
}
