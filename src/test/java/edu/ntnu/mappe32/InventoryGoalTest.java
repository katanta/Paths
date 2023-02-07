package edu.ntnu.mappe32;

import edu.ntnu.mappe32.story_related.Link;
import edu.ntnu.mappe32.story_related.Passage;
import edu.ntnu.mappe32.story_related.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryGoalTest {
    @BeforeEach
    void setUp() {
        Player player = new Player("Olav", 100, 0, 25);
        player.addToInventory("Master Sword");
        player.addToInventory("Length of Rope");
        player.addToInventory("Legendary Ring of Fortifying");
        player.addToInventory("Potato");
        player.addToInventory("The King's Crown");
        player.addToInventory("Potion of Healing");
        Link l = new Link("a", "b");
        Passage p = new Passage("a", "b");
        Story s = new Story("test", p);
    }

    @Test
    void isFulfilledReturnsTrueWhenInventoryContainsMandatoryItemsToFulfillGoal() {
        Player player = new Player("Olav", 100, 0, 25);
        player.addToInventory("Master Sword");
        player.addToInventory("Length of Rope");
        player.addToInventory("Legendary Ring of Fortifying");
        player.addToInventory("Potato");
        player.addToInventory("The King's Crown");
        player.addToInventory("Potion of Healing");
        Link l = new Link("a", "b");
        Passage p = new Passage("a", "b");
        Story s = new Story("test", p);
        ArrayList<String> mandatoryItems = new ArrayList<String>();
        mandatoryItems.add("The King's Crown");
        InventoryGoal kingsCrown = new InventoryGoal(mandatoryItems);
        assertTrue(kingsCrown.isFulfilled(player));
    }
    //The first file for tests...
}
