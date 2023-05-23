package edu.ntnu.mappe32.model.action_related;

import edu.ntnu.mappe32.model.Item;
import edu.ntnu.mappe32.model.Player;

import java.io.Serializable;
import java.util.Objects;

public class InventoryAction implements Action, Serializable {
    private final boolean add;
    private final Item item;
    private final int quantity;

    /**
     * Facilitates the creation of instances of the class InventoryAction.
     *
     * @param item     The item of the InventoryAction, as Item.
     * @param add      Whether the InventoryAction, adds items or removes items, as boolean.
     * @param quantity The quantity of items to add or remove, as int.
     */
    public InventoryAction(final Item item, final boolean add, int quantity) {
        if (item == null)
            throw new IllegalArgumentException("Item cannot be null");
        this.item = item;
        this.add = add;
        this.quantity = quantity;
    }

    public boolean isAdding() {
        return add;
    }

    /**
     * Executes an action that affects a players inventory.
     *
     * @param player The player affected by the action, as Player.
     */
    @Override
    public void execute(Player player) {
        if (add)
            player.addToInventory(item, quantity);
        else {
            player.removeFromInventory(item, quantity);
            item.useItem(player);
        }
    }

    /**
     * A string that describes the action occurring to the player.
     *
     * @param player The player affected by the action, as Player.
     * @return a String that describes the action occurring to the player, as String.
     */
    @Override
    public String toEventString(Player player) {
        if (add) {
            return player.getName() + " has received an item: " + item.getItemName();
        } else {
            StringBuilder wholeEvent = new StringBuilder(player.getName() + " has lost their " + item.getItemName());
            item.getActions().forEach(action -> wholeEvent.append("\n" + action.toEventString(player)));
            return wholeEvent.toString();
        }
    }

    public boolean isAdd() {
        return add;
    }

    /**
     * This method returns whether it is possible to execute an InventoryAction on a player.
     * It is not possible to execute the action if the Player does not have enough of an Item.
     *
     * @param player Player to execute the InvetoryAction on, as Player.
     * @return Possibility of executing InventoryAction on a Player, as boolean.
     */
    public boolean isPossible(Player player) {
        if (add) return true;
        if (!player.getInventory().containsKey(item)) return false;
        return player.getInventory().get(item) >= quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "<Inventory " + this.add + " " + this.quantity + " " + this.item + ">";
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
