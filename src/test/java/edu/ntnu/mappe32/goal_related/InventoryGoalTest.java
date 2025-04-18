package edu.ntnu.mappe32.goal_related;

import edu.ntnu.mappe32.model.Item;
import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.model.action_related.HealthAction;
import edu.ntnu.mappe32.model.action_related.ScoreAction;
import edu.ntnu.mappe32.model.goal_related.InventoryGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryGoalTest {
    Player player;
    @BeforeEach
    void setUp() {
        player = new Player("Olav", 100, 0, 25, new HashMap<>());
        player.addToInventory(new Item("Master Sword", true, new ScoreAction(10)), 1);
        player.addToInventory(new Item("Length of Rope", true, new ScoreAction(10)), 1);
        player.addToInventory(new Item("Legendary Ring of Fortifying", true, new ScoreAction(10)), 1);
        player.addToInventory(new Item("Potato", true, new HealthAction(10)), 1);
        player.addToInventory(new Item("The King's Crown", true, new ScoreAction(10)), 1);
        player.addToInventory(new Item("Potion of Healing", true, new HealthAction(100)), 1);
    }

    @DisplayName("isFulFilled")
    @Nested
    class IsFulfilledTest {
        @DisplayName("returns true when inventory contains singular mandatory item to fulfill goal")
        @Test
        void isFulfilledReturnsTrueWhenInventoryContainsSingularMandatoryItemToFulfillGoal() {
            HashMap<Item, Integer> mandatoryItems = new HashMap<>();
            mandatoryItems.put(new Item("The King's Crown", true, new ScoreAction(10)), 1);
            InventoryGoal kingsCrown = new InventoryGoal(mandatoryItems);
            assertTrue(kingsCrown.isFulfilled(player));
        }
        @DisplayName("returns true when inventory contains multiple mandatory items to fulfill goal")
        @Test
        void isFulfilledReturnsTrueWhenInventoryContainsMultipleMandatoryItemsToFulfillGoal() {
            HashMap<Item, Integer> mandatoryItems = new HashMap<>();
            mandatoryItems.put(new Item("Length of Rope", true, new ScoreAction(10)), 1);
            mandatoryItems.put(new Item("Potion of Healing", true, new HealthAction(100)), 1);
            mandatoryItems.put(new Item("Potato", true, new HealthAction(10)), 1);
            InventoryGoal helpStranger = new InventoryGoal(mandatoryItems);
            assertTrue(helpStranger.isFulfilled(player));
        }
        @DisplayName("returns false when player inventory does not have an item")
        @Test
        void isFulfilledReturnsFalseWhenPlayerInventoryDoesNotHaveAnItem() {
            HashMap<Item, Integer> mandatoryItems = new HashMap<>();
            mandatoryItems.put(new Item("Potion of Healing", true, new HealthAction(100)), 1);
            mandatoryItems.put(new Item("The King's Crown", true, new ScoreAction(10)), 1);
            mandatoryItems.put(new Item("24-karat diamond", true, new ScoreAction(10)), 1);
            InventoryGoal impossibleTask = new InventoryGoal(mandatoryItems);
            assertFalse(impossibleTask.isFulfilled(player));
        }
        @DisplayName("returns false when player does not have enough of an item")
        @Test
        void isFulfilledReturnsFalseWhenPlayerInventoryDoesNotHaveEnoughOfAnItem() {
            HashMap<Item, Integer> mandatoryItems = new HashMap<>();
            mandatoryItems.put(new Item("Length of Rope", true, new ScoreAction(10)), 1);
            mandatoryItems.put(new Item("Potion of Healing", true, new HealthAction(100)), 1);
            mandatoryItems.put(new Item("Potato", true, new HealthAction(10)), 2);
            InventoryGoal helpStranger = new InventoryGoal(mandatoryItems);
            assertFalse(helpStranger.isFulfilled(player));
        }
    }
    @Nested
    @DisplayName("Constructor")
    class ConstructorTests {
        @Test
        @DisplayName("creates Inventory Goal when Mandatory Items is valid")
        void constructorCreatesAnInventoryGoalWhenMandatoryItemsIsValid() {

            HashMap<Item, Integer> mandatoryItems = new HashMap<>();
            mandatoryItems.put(new Item("Master Sword", true, new ScoreAction(10)), 1);
            mandatoryItems.put(new Item("Potato", true, new HealthAction(10)), 1);

            InventoryGoal goal = new InventoryGoal(mandatoryItems);

            assertNotNull(goal);
            assertEquals(mandatoryItems, goal.getMandatoryItems());
        }

        @Test
        @DisplayName("throws exception when Mandatory Items is null")
        void constructorThrowsWhenMandatoryItemsIsNull() {

            HashMap<Item, Integer> mandatoryItems = null;

            assertThrows(IllegalArgumentException.class, () -> new InventoryGoal(mandatoryItems));
        }

        @Test
        @DisplayName("throws exception when Mandatory Items is empty")
        void testConstructor_EmptyMandatoryItems_Negative() {

            HashMap<Item, Integer> mandatoryItems = new HashMap<>();

            assertThrows(IllegalArgumentException.class, () -> new InventoryGoal(mandatoryItems));
        }

        @Test
        @DisplayName("throws exception when Mandatory Items contains a null value")
        void constructorThrowsExceptionWhenMandatoryItemsContainsANullValue() {

            HashMap<Item, Integer> mandatoryItems = new HashMap<>();
            mandatoryItems.put(new Item("Master Sword", true, new ScoreAction(10)), 1);
            mandatoryItems.put(new Item("Potato", true, new HealthAction(10)), null);

            assertThrows(IllegalArgumentException.class, () -> new InventoryGoal(mandatoryItems));
        }

        @Test
        @DisplayName("throws exception when Mandatory Items contains a 0 value")
        void constructorThrowsExceptionWhenMandatoryItemsContainsA0Value() {

            HashMap<Item, Integer> mandatoryItems = new HashMap<>();
            mandatoryItems.put(new Item("Master Sword", true, new ScoreAction(10)), 1);
            mandatoryItems.put(new Item("Potato", true, new HealthAction(10)), 0);

            assertThrows(IllegalArgumentException.class, () -> new InventoryGoal(mandatoryItems));
        }

        @Test
        @DisplayName("throws exception when Mandatory Items contains a negative value")
        void constructorThrowsExceptionWhenMandatoryItemsContainsANegativeValue() {

            HashMap<Item, Integer> mandatoryItems = new HashMap<>();
            mandatoryItems.put(new Item("Master Sword", true, new ScoreAction(10)), 1);
            mandatoryItems.put(new Item("Potato", true, new HealthAction(10)), -2);

            assertThrows(IllegalArgumentException.class, () -> new InventoryGoal(mandatoryItems));
        }

        @Test
        @DisplayName("throws exception when Mandatory Items contains a null key")
        void constructorThrowsExceptionWhenMandatoryItemsContainsANullKey() {

            HashMap<Item, Integer> mandatoryItems = new HashMap<>();
            mandatoryItems.put(new Item("Master Sword", true, new ScoreAction(10)), 1);
            mandatoryItems.put(null, 2);

            assertThrows(IllegalArgumentException.class, () -> new InventoryGoal(mandatoryItems));
        }
    }
}
