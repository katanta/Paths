package edu.ntnu.mappe32;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryGoalTest {
    Player player = null;
    @BeforeEach
    void setUp() {
        player = new Player("Olav", 100, 0, 25);
        player.addToInventory("Master Sword");
        player.addToInventory("Length of Rope");
        player.addToInventory("Legendary Ring of Fortifying");
        player.addToInventory("Potato");
        player.addToInventory("The King's Crown");
        player.addToInventory("Potion of Healing");
    }

    @Test
    void isFulfilledReturnsTrueWhenInventoryContainsSingularMandatoryItemToFulfillGoal() {
        ArrayList<String> mandatoryItems = new ArrayList<>();
        mandatoryItems.add("The King's Crown");
        InventoryGoal kingsCrown = new InventoryGoal(mandatoryItems);
        assertTrue(kingsCrown.isFulfilled(player));
    }

    @Test
    void IsFulfilledReturnsTrueWhenInventoryContainsMultipleMandatoryItemsToFulfillGoal() {
        ArrayList<String> mandatoryItems = new ArrayList<>();
        mandatoryItems.add("Length of Rope");
        mandatoryItems.add("Potion of Healing");
        mandatoryItems.add("Potato");
        InventoryGoal helpStranger = new InventoryGoal(mandatoryItems);
        assertTrue(helpStranger.isFulfilled(player));
    }

}
