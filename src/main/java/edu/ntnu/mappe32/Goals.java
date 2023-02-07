package edu.ntnu.mappe32;

/**
 * Goals are used to check the values of the attributes of a player.
 * Players can either fulfill or not fulfill a goal.
 */
public interface Goals {
    /**
     * This method decides which player a goal-check should be executed on.
     * @param player The player a goal should be executed on, as Player.
     */
    void execute(Player player);
}
