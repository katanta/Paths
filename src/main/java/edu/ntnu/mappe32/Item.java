package edu.ntnu.mappe32;

import edu.ntnu.mappe32.action_related.Action;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private final String itemName;
    private final List<Action> actions;

    public Item(final String itemName) {
        this.itemName = itemName;
        this.actions = new ArrayList<>();
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
}
