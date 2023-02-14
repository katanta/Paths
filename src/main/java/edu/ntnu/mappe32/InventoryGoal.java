package edu.ntnu.mappe32;

import java.util.List;

public class InventoryGoal implements Goal {
    private List<String> mandatoryItems;

    public InventoryGoal(List<String> mandatoryItems) {
        this.mandatoryItems = mandatoryItems;
    }

    public boolean isFulfilled(Player player) {
        int mandatoryItemCount = (int) player.getInventory().stream().
                filter(item -> mandatoryItems.contains(item)).count();
        return mandatoryItemCount >= mandatoryItems.size();
    }
}

