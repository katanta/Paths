package edu.ntnu.mappe32.model.goal_related;

import edu.ntnu.mappe32.model.Player;

/**
 * Goals are used to check the values of the attributes of a player.
 * Players can either fulfill or not fulfill a goal.
 * @version 0.1
 */
public interface Goal {
    /**
     * This method returns true if a partical requirement/goal in a game is met.
     * @param player The player being inspected to see if the requirements/goals are met.
     * @since 0.1
     */
    boolean isFulfilled(Player player);
}
