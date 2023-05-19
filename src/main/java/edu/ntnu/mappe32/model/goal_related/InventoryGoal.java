package edu.ntnu.mappe32.model.goal_related;

import edu.ntnu.mappe32.model.Item;
import edu.ntnu.mappe32.model.Player;
import javafx.application.Platform;

import java.util.HashMap;
import java.util.stream.Collectors;

public class InventoryGoal implements Goal {
    // List of mandatory items that a player needs to achieve an InventoryGoal
    private final HashMap<Item, Integer> mandatoryItems;
    /**
     * This constructor facilitates the creation of instances of the class InventoryGoal.
     * @param mandatoryItems items required to reach a particular goal, as a list of strings.
     * @since 0.1
     */
    public InventoryGoal(HashMap<Item, Integer> mandatoryItems) {
        if (mandatoryItems == null || mandatoryItems.isEmpty())
            throw new IllegalArgumentException("Mandatory items cannot be empty or null");

        if (mandatoryItems.containsValue(null))
            throw new IllegalArgumentException("A value cannot be null or zero");

        if (mandatoryItems.values().stream().anyMatch(value -> value < 1))
            throw new IllegalArgumentException("A value cannot be less than 1");

        if (mandatoryItems.containsKey(null))
            throw new IllegalArgumentException("A key cannot be null");

        this.mandatoryItems = mandatoryItems;
    }

    /**
     * This method checks a player's inventory to see if the invetory goal is met
     * For the goal to be met:
     *  (1) All the keys in mandatoryItems must exist in the player's inventory
     *  (2) All the values of the keys in mandatoryItems must be less than or equal to their
     *      conunter-part in the player's inventory
     * @param player The player being inspected to see if the requirements/goals are met.
     * @return Whether player's inventory meets the requirements of the goal
     * @since 0.1
     */
    @Override
    public boolean isFulfilled(Player player) {
        return mandatoryItems.keySet().stream()
                .allMatch(item -> player.getInventory().containsKey(item) &&
                        mandatoryItems.get(item) <= player.getInventory().get(item));
    }
    @Override
    public String goalValue() {
        return mandatoryItems.entrySet()
                .stream()
                .map(entry -> entry.getKey().getItemName() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));
    }
    @Override
    public String goalType() {
        return "Inventory";
    }
}

