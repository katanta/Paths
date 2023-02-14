package edu.ntnu.mappe32;

import java.util.List;

public class InventoryGoal implements Goal {
    private List<String> mandatoryItems;


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
     * @return whether player's inventory contains all the required items.
     * @since 0.1
     */
    @Override
    public boolean isFulfilled(Player player) {
        int mandatoryItemCount = (int) player.getInventory().stream().
                filter(item -> mandatoryItems.contains(item)).count();
        return mandatoryItemCount >= mandatoryItems.size();
    }
}

