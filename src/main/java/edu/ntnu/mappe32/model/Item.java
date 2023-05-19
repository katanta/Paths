package edu.ntnu.mappe32.model;

import edu.ntnu.mappe32.model.action_related.Action;
import edu.ntnu.mappe32.model.action_related.InventoryAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Item implements Serializable {
    private final String itemName;
    private final boolean usable;
    private final List<Action> actions;

    public Item(final String itemName, final boolean usable, Action... actions) throws IllegalArgumentException {
        if (actions == null ||actions.length == 0)
            throw new IllegalArgumentException("An item must have an action");

        if (Arrays.stream(actions).anyMatch(Objects::isNull))
            throw new IllegalArgumentException("Actions cannot contain null");

        if (Arrays.stream(actions).anyMatch(action -> action instanceof InventoryAction))
            throw new IllegalArgumentException("An item cannot contain an inventory action");

        this.itemName = itemName;
        this.actions = Arrays.stream(actions).toList();
        this.usable = usable;
    }

    public String getItemName() {
        return itemName;
    }

    public List<Action> getActions() {
        return new ArrayList<>(actions);
    }

    public void addAction(Action action) {
        this.actions.add(action);
    }

    public void useItem(Player player) {
        actions.forEach(action -> action.execute(player));
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(itemName + " " + usable + " ");
        actions.forEach(action -> s.append(action).append(" "));
        return s.toString();
    }
    private boolean isUsable() {
        return usable;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return itemName.equals(item.itemName) && actions.equals(item.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, actions);
    }
}
