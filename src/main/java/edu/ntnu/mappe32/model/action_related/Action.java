package edu.ntnu.mappe32.model.action_related;

import edu.ntnu.mappe32.model.Player;

/**
 * An action represents a future change to the state of the player.
 * This includes changes in the players score, gold, health and inventory.
 */
public interface Action {

    /**
     * Executes the action on a player.
     *
     * @param player The player affected by the action, as Player.
     */
    void execute(Player player);

    /**
     * A string that describes the action occurring to the player.
     *
     * @param player The player affected by the action, as Player.
     * @return a String that describes the action occurring to the player, as String.
     */
    String toEventString(Player player);

    boolean equals(Object o);
}
