package edu.ntnu.mappe32.model.goal_related;

import edu.ntnu.mappe32.model.Player;

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
     * @throws IllegalArgumentException when minimumGold is below zero.
     * @since 0.1
     */
    public GoldGoal(int minimumGold) {
        if (minimumGold < 0) {
            throw new IllegalArgumentException("Minimum gold goal cannot be a negative amount.");
        }
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


    public String toString() {
        return "Have a total of " + minimumGold + " gold";
    }


}
