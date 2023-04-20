package edu.ntnu.mappe32.action_related;

import edu.ntnu.mappe32.Item;
import edu.ntnu.mappe32.Player;

public class InventoryAction implements Action{
    private final boolean add;
    private final Item item;

    public InventoryAction(final Item item, boolean add) {
        this.item = item;
        this.add = add;
    }

    public boolean isAdding() {
        return add;
    }

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
            item.useItem(player);
        }
    }
}
