package edu.ntnu.mappe32.action_related;

import edu.ntnu.mappe32.Player;

public class GoldAction implements Action {

    private final int gold;

    /**
     * Facilitates the creation of instances of the class GoldAction.
     * @param gold the amount of gold this action gives or takes, as int.
     * @since 0.1
     */
    public GoldAction(final int gold) {
        this.gold = gold;
    }

    /**
     * Executes an action that affects a player's gold count.
     *
     * @param player The player affected by the action, as Player.
     * @since 0.1
     */
    @Override
    public void execute(Player player) {
        player.addGold(this.gold);
    }

    @Override
    public String toString() {
        return "<gold " + gold + ">";
    }
}

