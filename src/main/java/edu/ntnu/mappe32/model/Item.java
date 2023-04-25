package edu.ntnu.mappe32;

import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.model.action_related.Action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Item {
    private final String itemName;
    //add itemtext here that describes what the item does or did?
    private final List<Action> actions;

    public Item(final String itemName) {
        this.itemName = itemName;
        this.actions = new ArrayList<>();
    }

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
}
