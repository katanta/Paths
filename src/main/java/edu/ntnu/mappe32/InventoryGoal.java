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
        Iterator<String> it = mandatoryItems.iterator();
        while (it.hasNext()) {
            if (it.equals(player.getInventory()))
        }
        for(String item : mandatoryItems) {
            for(String playerItem : player.getInventory()) {
                if(item.equals(playerItem)) {
                    mandatoryItems.remove(item);
                }
            }
        }
        return mandatoryItems.isEmpty();
    }

    @Override
    public void execute(Player player) {
        if(isFulfilled(player)) {
            //TODO: implement this
        }
    }
}
