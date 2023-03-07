package edu.ntnu.mappe32.action_related;

import edu.ntnu.mappe32.Player;

public class InventoryAction implements Action{
    private final boolean add;
    private final String item;

    public InventoryAction(final String item, boolean add) {
        this.item = item;
        this.add = add;
    }

    //What should be the game mechanic when a player adds an item he already owns?
    //++ How are we going to handle removal of items from one's own inventory?

    /**
     * Executes an action that affects a players inventory.
     * @param player The player affected by the action, as Player.
     */
    @Override
    public void execute(Player player) {
        if (add) {
            player.addToInventory(item);
        }
        else {
            player.removeFromInventory(item);
        }
    }
}
