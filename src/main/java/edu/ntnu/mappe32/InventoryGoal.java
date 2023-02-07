package edu.ntnu.mappe32;

import edu.ntnu.mappe32.story_related.Story;

import java.util.Iterator;
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

    @Override
    public void execute(Player player) {
        if(isFulfilled(player)) {
            //TODO: implement this
        }
    }
}
