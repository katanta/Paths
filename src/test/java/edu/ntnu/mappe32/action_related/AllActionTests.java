package edu.ntnu.mappe32.action_related;

import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.model.action_related.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class AllActionTests {
    Player player;

    @BeforeEach
    void setUp() {
        player = new Player("LeBron", 500, 500, 500);
    }
    @DisplayName("GoldAction accurately calculates and throws exception if going below zero")
    @Test
    void goldActionAccuratelyCalculatesAndThrowsExceptionIfGoingBelowZero() {
        Action plus500Gold = new GoldAction(500);
        plus500Gold.execute(player);
        assertEquals(1000, player.getGold());
        Action minus500Gold = new GoldAction(-500);
        minus500Gold.execute(player);
        assertEquals(500, player.getGold());
        Action impossibleDebt = new GoldAction(-6000000);
        assertThrows(IllegalArgumentException.class, () -> {
            impossibleDebt.execute(player);
        });
    }
    @DisplayName("HealthAction accurately calculates and does not go below zero")
    @Test
    void healthActionAccuratelyCalculatesAndDoesNotGoBelowZero() {
        Action plus500Health = new HealthAction(500);
        plus500Health.execute(player);
        assertEquals(1000, player.getHealth());
        Action minus500Health = new HealthAction(-500);
        minus500Health.execute(player);
        assertEquals(500, player.getHealth());
        Action die = new HealthAction(-600000);
        die.execute(player);
        assertEquals(0, player.getHealth());
    }
    @DisplayName("InventoryAction adds to inventory")
    @Test
    void inventoryActionAddsToInventory() {
        Action giveDiamond = new InventoryAction("10g Diamond");
        Action giveSandals = new InventoryAction("Fresh Pair of Birkenstocks");
        ArrayList<String> testInventory = new ArrayList<>();
        testInventory.add("10g Diamond");
        testInventory.add("Fresh Pair of Birkenstocks");
        giveDiamond.execute(player);
        giveSandals.execute(player);
        assertEquals(testInventory, player.getInventory());
    }

    @DisplayName("InventoryAction constructor throws IllegalArgumentException if item is blank or empty")
    @Test
    void inventoryActionConstructorThrowsIllegalArgumentExceptionIfItemNameIsBlankOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new InventoryAction("  "));
        assertThrows(IllegalArgumentException.class, () -> new InventoryAction(null));

    }
    @DisplayName("ScoreAction correctly calculates and does not go below zero")
    @Test
    void ScoreActionCorrectlyCalculatesAndDoesNotGoBelowZero() {
        Action wiltChamberlain = new ScoreAction(100);
        wiltChamberlain.execute(player);
        assertEquals(600, player.getScore());
        Action darkoMilicic = new ScoreAction(-100);
        darkoMilicic.execute(player);
        assertEquals(500, player.getScore());
        Action kristapsPorzingis = new ScoreAction(-1000000);
        kristapsPorzingis.execute(player);
        assertEquals(0, player.getScore());
    }
}