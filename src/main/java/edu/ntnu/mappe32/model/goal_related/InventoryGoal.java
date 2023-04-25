package edu.ntnu.mappe32.model.goal_related;

import edu.ntnu.mappe32.model.Item;
import edu.ntnu.mappe32.model.Player;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryGoal implements Goal {
    // List of mandatory items that a player needs to achieve an InventoryGoal
    private final List<String> mandatoryItems;
    /**
     * This constructor facilitates the creation of instances of the class InventoryGoal.
     * @param mandatoryItems items required to reach a particular goal, as a list of strings.
     * @since 0.1
     */
    //TODO: The current iteration of InventoryGoal does not take into account if a player has met quantity requirements
    public InventoryGoal(List<String> mandatoryItems) {
        if (mandatoryItems == null || mandatoryItems.isEmpty()) {
            throw new IllegalArgumentException("Mandatory items cannot be empty or null");
        }
        this.mandatoryItems = mandatoryItems;
    }

    /**
     * This method checks a player's inventory to see if the item goal is met.
     * @param player The player being inspected to see if the requirements/goals are met.
     * @return Whether  player's inventory contains all the required items.
     * @since 0.1
     */
    @Override
    public boolean isFulfilled(Player player) {
        HashSet<String> itemNames = (HashSet<String>) player.getInventory().keySet().stream().map(Item::getItemName).collect(Collectors.toSet());

        return itemNames.containsAll(mandatoryItems);
    }
}

