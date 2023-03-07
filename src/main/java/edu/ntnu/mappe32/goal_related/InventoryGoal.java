package edu.ntnu.mappe32.goal_related;

import edu.ntnu.mappe32.Player;

import java.util.List;

public class InventoryGoal implements Goal {
    // List of mandatory items that a player needs to achieve an InventoryGoal
    private final List<String> mandatoryItems;
    /**
     * This constructor facilitates the creation of instances of the class InventoryGoal.
     * @param mandatoryItems items required to reach a particular goal, as a list of strings.
     * @since 0.1
     */
    public InventoryGoal(List<String> mandatoryItems) {
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
        int mandatoryItemCount = (int) player.getInventory().keySet().stream().
                filter(mandatoryItems::contains).count();
        return mandatoryItemCount == mandatoryItems.size();
    }
}

