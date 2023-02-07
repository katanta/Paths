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
        Iterator<String> it = player.getInventory().iterator();
        int i = 0;
        while (it.hasNext()) {
            i = 0;
            while (i < mandatoryItems.size()) {
                if (it.next().equals(mandatoryItems.get(i))) {
                    mandatoryItems.remove(i);
                }
                i++;
            }
            it.next();
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
