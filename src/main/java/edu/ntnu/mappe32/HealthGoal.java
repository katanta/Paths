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
     * This method decides which player a goal should be executed on.
     *
     * @param player The player a goal should be executed on, as Player.
     */
    @Override
    public void execute(Player player) {
        if (isFulFilled(player)) {
            // TODO: Implement
        }

    }

    /**
     * This method checks if the score goal is reached.
     * @param player The player a goal-check should be executed on, as Player.
     * @return Whether the player has fulfilled a goal, as boolean.
     */
    public boolean isFulFilled(Player player) {
        return player.getHealth() >= minimumHealth;
    }
}
