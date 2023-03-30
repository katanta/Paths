package goal_related;

import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.model.goal_related.InventoryGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryGoalTest {
    Player player;
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

    @DisplayName("isFulFilled")
    @Nested
    class IsFulfilledTest {
        @DisplayName("returns true when inventory contains singular mandatory item to fulfill goal")
        @Test
        void isFulfilledReturnsTrueWhenInventoryContainsSingularMandatoryItemToFulfillGoal() {
            ArrayList<String> mandatoryItems = new ArrayList<>();
            mandatoryItems.add("The King's Crown");
            InventoryGoal kingsCrown = new InventoryGoal(mandatoryItems);
            assertTrue(kingsCrown.isFulfilled(player));
        }
        @DisplayName("returns true when inventory contains multiple mandatory items to fulfill goal")
        @Test
        void IsFulfilledReturnsTrueWhenInventoryContainsMultipleMandatoryItemsToFulfillGoal() {
            ArrayList<String> mandatoryItems = new ArrayList<>();
            mandatoryItems.add("Length of Rope");
            mandatoryItems.add("Potion of Healing");
            mandatoryItems.add("Potato");
            InventoryGoal helpStranger = new InventoryGoal(mandatoryItems);
            assertTrue(helpStranger.isFulfilled(player));
        }
        @DisplayName("returns false when inventory does not meet requirements")
        @Test
        void isFulfilledReturnsFalseWhenInventoryDoesNotMeetRequirements() {
            ArrayList<String> mandatoryItems = new ArrayList<>();
            mandatoryItems.add("Potion of Healing");
            mandatoryItems.add("The King's Crown");
            mandatoryItems.add("24-karat diamond");
            InventoryGoal impossibleTask = new InventoryGoal(mandatoryItems);
            assertFalse(impossibleTask.isFulfilled(player));
        }
    }
    @DisplayName("constructor")
    @Nested
    class ConstructorTest {
        @DisplayName("throws IllegalArgumentException when mandatoryItems is null")
        @Test
        void constructorThrowsIllegalArgumentExceptionWhenMandatoryItemsIsNull() {
            assertThrows(IllegalArgumentException.class, () -> new InventoryGoal(null));
        }
        @DisplayName("throws IllegalArgumentException when mandatoryItems is empty")
        @Test
        void constructorThrowsIllegalArgumentExceptionWhenMandatoryItemsIsEmpty() {
            assertThrows(IllegalArgumentException.class, () -> new InventoryGoal(new ArrayList<>()));
        }
        @DisplayName("does not throw IllegalArgumentException when mandatory items contains elements")
        @Test
        void doesNotThrowIllegalArgumentExceptionWhenMandatoryItemsContainsElements() {
            ArrayList<String> mandatoryItems = new ArrayList<>();
            mandatoryItems.add("Potion of Healing");
            mandatoryItems.add("The King's Crown");
            mandatoryItems.add("24-karat diamond");
            assertDoesNotThrow(() -> new InventoryGoal(mandatoryItems));
        }
    }
}
