package edu.ntnu.mappe32.model.action_related;

import edu.ntnu.mappe32.model.Item;
import edu.ntnu.mappe32.model.Player;

import java.util.Objects;

public class InventoryAction implements Action{
    private final boolean add;
    private final Item item;

    public InventoryAction(final Item item, final boolean add) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
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

    @Override
    public String toString() {
        return "<Inventory " + this.add + " " + this.item + ">";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventoryAction that)) return false;
        return add == that.add && item.equals(that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(add, item);
    }
}
