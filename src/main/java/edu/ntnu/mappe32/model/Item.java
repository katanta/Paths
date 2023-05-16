package edu.ntnu.mappe32.model;

import edu.ntnu.mappe32.model.action_related.Action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Item {
    private final String itemName;
    //add itemtext here that describes what the item does or did?
    private final List<Action> actions;

    public Item(final String itemName, Action... actions) {
        this.itemName = itemName;
        this.actions = Arrays.stream(actions).toList();
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
        StringBuilder s = new StringBuilder(itemName + " ");
        actions.forEach(action -> s.append(action).append(" "));
        return s.toString();
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
