package edu.ntnu.mappe32;

/**
 * Goals are used to check the values of the attributes of a player.
 * Players can either fulfill or not fulfill a goal.
 */
public interface Goal {
    /**
     * This method returns true if a partical requirement/goal in a game is met.
     * @param player The player being inspected to see if the requirements/goals are met.
     */
    boolean isFulfilled(Player player);
}
