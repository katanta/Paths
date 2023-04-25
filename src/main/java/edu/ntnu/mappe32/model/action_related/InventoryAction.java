package edu.ntnu.mappe32.model.action_related;

import edu.ntnu.mappe32.model.Player;

public class InventoryAction implements Action{

    private final String item;

    public InventoryAction(final String item) {
        if (item == null || item.isBlank()) {
            throw new IllegalArgumentException("The item must have a name");
        }
        this.item = item;
    }

    //What should be the game mechanic when a player adds an item he already owns?
    //++ How are we going to handle removal of items from one's own inventory?

    /**
     * Executes an action that affects a players inventory.
     * @param player The player affected by the action, as Player.
     */
    @Override
    public void execute(Player player) {
        player.addToInventory(item);
    }

    @Override
    public String toString() {
        return "<Inventory " + item + ">";
    }
}
