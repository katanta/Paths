package edu.ntnu.mappe32;

/**
 * Goal for minimum gold.
 * @author Kristians J.Matrevics
 * @version 0.1
 */
public class GoldGoal implements Goal{
    private final int minimumGold;

    /**
     * This constructor facilitates the creation of instances of the class GoldGoal.
     * @param minimumGold Gold required for a goal to be reached, as int.
     * @since 0.1
     */
    public GoldGoal(int minimumGold) {
        this.minimumGold = minimumGold;
    }

    /**
     * This method checks if the gold goal is reached.
     * @param player The player a goal-check should be executed on, as Player.
     * @return Whether the player has fulfilled this goal, as boolean.
     * @since 0.1
     */
    public boolean isFulfilled(Player player) {
        return player.getGold() >= minimumGold;
    }
}
