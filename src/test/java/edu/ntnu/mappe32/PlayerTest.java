package edu.ntnu.mappe32;

import edu.ntnu.mappe32.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Player mutumbu;
    Player mufasa;
    Player pumba;

    List<String> pumbasTestInventory;
    List<String> mutumbusTestInventory;
    List<String> mufasasTestInventory;



    @BeforeEach
    void setup() {
        this.mutumbu = new Player("Mutumbu", 150, 0, 25);
        this.mufasa = new Player("Mufasa", 200, 50, 100);
        this.pumba = new Player("Pumba", 202, 40, 100);
        this.mutumbu.addToInventory("Tape");
        this.mutumbu.addToInventory("Lighter");
        this.mutumbu.addToInventory("Candle");
        this.mutumbu.addToInventory("Potato");
        this.mutumbu.addToInventory("Hammer");
        this.mutumbu.addToInventory("Dagger");
        this.mutumbu.addToInventory("Scarf");
        this.mufasa.addToInventory("Key");
        this.mufasa.addToInventory("Milk");
        this.mufasa.addToInventory("Chicken");
        this.mufasa.addToInventory("Pencil");
        this.mufasa.addToInventory("Wood");
        this.mufasa.addToInventory("Paper");
        this.pumba.addToInventory("N'Tofos");
        this.pumba.addToInventory("Orange");
        this.pumba.addToInventory("Apple");
        this.pumba.addToInventory("Machete");
        this.pumba.addToInventory("Knife");
        this.pumba.addToInventory("Paperclip");
        this.pumba.addToInventory("Rolex");
        this.pumba.addToInventory("Key");
        this.mutumbusTestInventory = new ArrayList<String>(Arrays.asList("Tape", "Lighter", "Candle", "Potato", "Hammer",
                "Dagger", "Scarf"));
        this.mufasasTestInventory = new ArrayList<String>(Arrays.asList("Key", "Milk", "Chicken", "Pencil", "Wood", "Paper"));
        this.pumbasTestInventory = new ArrayList<String>(Arrays.asList("N'Tofos", "Orange",
                "Apple", "Machete", "Knife", "Paperclip", "Rolex", "Key"));
    }
    @DisplayName("getName() returns name")
    @Test
    void getNameReturnsName() {
        assertEquals("Mutumbu", mutumbu.getName());
        assertEquals("Mufasa", mufasa.getName());
        assertEquals("Pumba", pumba.getName());
    }
    @DisplayName("getScore() returns score")
    @Test
    void getScoreReturnsScore() {
        assertEquals(0, mutumbu.getScore());
        assertEquals(50, mufasa.getScore());
        assertEquals(40, pumba.getScore());
        
    }
    @DisplayName("getHealth() returns health")
    @Test
    void getHealthReturnsHealth() {
        assertEquals(150, mutumbu.getHealth());
        assertEquals(200, mufasa.getHealth());
        assertEquals(202, pumba.getHealth());
    }
    @DisplayName("getGold() returns gold")
    @Test
    void getGoldReturnsGold() {
        assertEquals(25, mutumbu.getGold());
        assertEquals(100, mufasa.getGold());
        assertEquals(100, pumba.getGold());
    }

    @DisplayName("addHealth()")
    @Nested
    public class AddHealthTest {
        @DisplayName("adds health")
        @Test
        void addHealthAddsHealth() {
            mutumbu.addHealth(50);
            assertEquals(200, mutumbu.getHealth());
        }
        @DisplayName("does not reduce health below zero")
        @Test
        void addHealthDoesNotReduceHealthBelowZero() {
            mufasa.addHealth(-2 * mufasa.getHealth());
            assertEquals(0, mufasa.getHealth());
        }
    }
    @DisplayName("addGold()")
    @Nested
    class AddGoldTest {
        @DisplayName("adds gold")
        @Test
        void addGoldAddsGold() {
            mutumbu.addGold(100);
            mufasa.addGold(-100);
            pumba.addGold(-100);
            assertEquals(125, mutumbu.getGold());
            assertEquals(0, mufasa.getGold());
            assertEquals(0,pumba.getGold());
        }
        @DisplayName("throws IllegalArgumentException if player does not have enough gold")
        @Test
        void addGoldThrowsIllegalArgumentExceptionIfPlayerDoesNotHaveEnoughGold() {
            assertThrows(IllegalArgumentException.class, () -> mutumbu.addGold(-10000));
            assertThrows(IllegalArgumentException.class, () -> mufasa.addGold(-100000000));
            assertThrows(IllegalArgumentException.class, () -> pumba.addGold(-10000));
        }
    }

    @DisplayName("getInventory()")
    @Nested
    class getInventoryTests {
        @DisplayName("returns inventory")
        @Test
        void getInventoryReturnsInventory() {
            assertEquals(mutumbusTestInventory, mutumbu.getInventory());
            assertEquals(mufasasTestInventory, mufasa.getInventory());
            assertEquals(pumbasTestInventory, pumba.getInventory());
        }
        @DisplayName("returns copy of inventory")
        @Test
        void getInventoryReturnsCopyOfInventory() {
            assertEquals(mutumbusTestInventory, mutumbu.getInventory());
            assertEquals(mufasasTestInventory, mufasa.getInventory());
            assertEquals(pumbasTestInventory, pumba.getInventory());
            mutumbusTestInventory.add("A");
            mufasasTestInventory.add("B");
            pumbasTestInventory.add("C");
            assertNotEquals(mutumbusTestInventory, mutumbu.getInventory());
            assertNotEquals(mufasasTestInventory, mufasa.getInventory());
            assertNotEquals(pumbasTestInventory, pumba.getInventory());
        }

    }
    @DisplayName("addToInventory()")
    @Nested
    class AddToInventoryTest {
        @DisplayName("adds a String to inventory")
        @Test
        void addToInventoryAddsItemToInventory() {
            mutumbu.addToInventory("Glue");
            mufasa.addToInventory("Soup");
            pumba.addToInventory("Spaghetti");
            mutumbusTestInventory.add("Glue");
            mufasasTestInventory.add("Soup");
            pumbasTestInventory.add("Spaghetti");
            assertEquals(mutumbusTestInventory, mutumbu.getInventory());
            assertEquals(mufasasTestInventory, mufasa.getInventory());
            assertEquals(pumbasTestInventory, pumba.getInventory());
        }
        @DisplayName("throws IllegalArgumentException when item is blank")
        @Test
        void throwsIllegalArguementExceptionWhenItemIsBlank() {
            assertThrows(IllegalArgumentException.class, () -> mutumbu.addToInventory("   "));
        }
        @DisplayName("throws IllegalArgumentException when item is null")
        @Test
        void throwsIllegalArguementExceptionWhenItemIsNull() {
            assertThrows(IllegalArgumentException.class, () -> mutumbu.addToInventory(null));
        }
    }
    @DisplayName("addScore()")
    @Nested
    public class AddScoreTest {
        @DisplayName("adds score")
        @Test
        void addScoreAddsScore() {
            mutumbu.addScore(20);
            mufasa.addScore(100);
            pumba.addScore(-30);
            assertEquals(20, mutumbu.getScore());
            assertEquals(150, mufasa.getScore());
            assertEquals(10, pumba.getScore());
        }
        @DisplayName("does not reduce score below zero")
        @Test
        void addScoreDoesNotReduceScoreBelowZero() {
            mutumbu.addScore(-51);
            mufasa.addScore(-100);
            pumba.addScore(-1000);
            assertEquals(0, mutumbu.getScore());
            assertEquals(0, mufasa.getScore());
            assertEquals(0, pumba.getScore());
        }
    }
}
