package edu.ntnu.mappe32;

import edu.ntnu.mappe32.model.Item;
import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.model.action_related.HealthAction;
import edu.ntnu.mappe32.model.action_related.ScoreAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Player mutumbu;
    Player mufasa;
    Player pumba;

    List<Item> pumbasTestInventory;
    List<Item> mutumbusTestInventory;
    List<Item> mufasasTestInventory;



    @BeforeEach
    void setup() {
        this.mutumbu = new Player("Mutumbu", 150, 0, 25, new HashMap<>());
        this.mufasa = new Player("Mufasa", 200, 50, 100, new HashMap<>());
        this.pumba = new Player("Pumba", 202, 40, 100, new HashMap<>());
        this.mutumbu.addToInventory(new Item("Tape", new ScoreAction(10)));
        this.mutumbu.addToInventory(new Item("Lighter", new ScoreAction(10)));
        this.mutumbu.addToInventory(new Item("Candle", new ScoreAction(10)));
        this.mutumbu.addToInventory(new Item("Potato", new HealthAction(10)));
        this.mutumbu.addToInventory(new Item("Hammer", new ScoreAction(10)));
        this.mutumbu.addToInventory(new Item("Dagger", new ScoreAction(10)));
        this.mutumbu.addToInventory(new Item("Scarf", new HealthAction(10)));
        this.mufasa.addToInventory(new Item("Key", new ScoreAction(10)));
        this.mufasa.addToInventory(new Item("Milk", new HealthAction(10)));
        this.mufasa.addToInventory(new Item("Chicken", new HealthAction(50)));
        this.mufasa.addToInventory(new Item("Pencil", new ScoreAction(10)));
        this.mufasa.addToInventory(new Item("Wood", new ScoreAction(10)));
        this.mufasa.addToInventory(new Item("Paper", new ScoreAction(10)));
        this.pumba.addToInventory(new Item("N'Tofos", new ScoreAction(10)));
        this.pumba.addToInventory(new Item("Orange", new HealthAction(20)));
        this.pumba.addToInventory(new Item("Apple", new HealthAction(10)));
        this.pumba.addToInventory(new Item("Machete", new ScoreAction(10)));
        this.pumba.addToInventory(new Item("Knife", new ScoreAction(10)));
        this.pumba.addToInventory(new Item("Paperclip", new ScoreAction(10)));
        this.pumba.addToInventory(new Item("Rolex", new ScoreAction(10)));
        this.pumba.addToInventory(new Item("Key", new ScoreAction(10)));
        this.mutumbusTestInventory = new ArrayList<>(Arrays.asList(new Item("Tape", new ScoreAction(10)),
                new Item("Lighter", new ScoreAction(10)), new Item("Candle", new ScoreAction(10)),
                new Item("Potato", new HealthAction(10)), new Item("Hammer", new ScoreAction(10)),
                new Item("Dagger", new ScoreAction(10)), new Item("Scarf", new HealthAction(10))));

        this.mufasasTestInventory = new ArrayList<>(Arrays.asList(new Item("Key", new ScoreAction(10)),
        new Item("Milk", new HealthAction(10)),
        new Item("Chicken", new HealthAction(50)),
        new Item("Pencil", new ScoreAction(10)),
        new Item("Wood", new ScoreAction(10)),
        new Item("Paper", new ScoreAction(10))));

        this.pumbasTestInventory = new ArrayList<>(Arrays.asList(new Item("N'Tofos", new ScoreAction(10)),
        new Item("Orange", new HealthAction(20)),
        new Item("Apple", new HealthAction(10)),
        new Item("Machete", new ScoreAction(10)),
        new Item("Knife", new ScoreAction(10)),
        new Item("Paperclip", new ScoreAction(10)),
        new Item("Rolex", new ScoreAction(10)),
        new Item("Key", new ScoreAction(10))));
    }

    @DisplayName("constructor")
    @Nested
    class ConstructorTest {
        @DisplayName("throws IllegalArgumentException when player gold is less than zero")
        @Test
        void throwsIllegalArgumentExceptionWhenPlayerGoldIsLessThanZero() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Player("Name", 100, 100, -100, new HashMap<>()));

        }

        @DisplayName("throws IllegalArgumentException when player health is less than zero")
        @Test
        void throwsIllegalArgumentExceptionWhenPlayerHealthIsLessThanZero() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Player("Name", -100, 100, 100, new HashMap<>()));
        }
        @DisplayName("throws IllegalArgumentException when player gold is less than zero")
        @Test
        void throwsIllegalArgumentExceptionWhenPlayerScoreIsLessThanZero() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Player("Name", 100, -100, 100, new HashMap<>()));
        }
        @DisplayName("throws IllegalArgumentException when player name is null")
        @Test
        void throwsIllegalArgumentExceptionWhenPlayerNameIsNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Player(null, 100, 100, 100, new HashMap<>()));
        }
        @DisplayName("throws IllegalArgumentException when player name is blank")
        @Test
        void throwsIllegalArgumentExceptionWhenPlayerNameIsBlank() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Player("   ", 100, 100, 100, new HashMap<>()));
        }
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
        @DisplayName("does not throw IllegalArgumentException when player has just enough gold")
        @Test
        void doesNotThrowIllegalArgumentExceptionWhenPlayerHasEnoughGold() {
            assertDoesNotThrow(() -> mutumbu.addGold(-25));
        }
    }

    @DisplayName("getInventory()")
    @Nested
    class getInventoryTests {
        @DisplayName("returns inventory")
        @Test
        void getInventoryReturnsInventory() {
            assertTrue(mutumbusTestInventory.containsAll(mutumbu.getInventory().keySet()));
            assertTrue(mutumbu.getInventory().keySet().containsAll(mutumbusTestInventory));
            assertEquals(mutumbusTestInventory.size(), mutumbu.getInventory().keySet().size());

            assertTrue(mufasasTestInventory.containsAll(mufasa.getInventory().keySet()));
            assertTrue(mufasa.getInventory().keySet().containsAll(mufasasTestInventory));
            assertEquals(mufasasTestInventory.size(), mufasa.getInventory().keySet().size());

            assertTrue(pumbasTestInventory.containsAll(pumba.getInventory().keySet()));
            assertTrue(pumba.getInventory().keySet().containsAll(pumbasTestInventory));
            assertEquals(pumbasTestInventory.size(), pumba.getInventory().keySet().size());
        }
        @DisplayName("returns copy of inventory")
        @Test
        void getInventoryReturnsCopyOfInventory() {
            assertTrue(mutumbusTestInventory.containsAll(mutumbu.getInventory().keySet()));
            assertTrue(mutumbu.getInventory().keySet().containsAll(mutumbusTestInventory));
            assertEquals(mutumbusTestInventory.size(), mutumbu.getInventory().keySet().size());

            assertTrue(mufasasTestInventory.containsAll(mufasa.getInventory().keySet()));
            assertTrue(mufasa.getInventory().keySet().containsAll(mufasasTestInventory));
            assertEquals(mufasasTestInventory.size(), mufasa.getInventory().keySet().size());

            assertTrue(pumbasTestInventory.containsAll(pumba.getInventory().keySet()));
            assertTrue(pumba.getInventory().keySet().containsAll(pumbasTestInventory));
            assertEquals(pumbasTestInventory.size(), pumba.getInventory().keySet().size());


            mutumbusTestInventory.add(new Item("Item A", new ScoreAction(10)));
            mufasasTestInventory.add(new Item("Item B", new ScoreAction(10)));
            pumbasTestInventory.add(new Item("Item C", new ScoreAction(10)));

            assertFalse(mutumbu.getInventory().keySet().containsAll(mutumbusTestInventory));

            assertFalse(mufasa.getInventory().keySet().containsAll(mufasasTestInventory));

            assertFalse(pumba.getInventory().keySet().containsAll(pumbasTestInventory));
        }

    }
    @DisplayName("addToInventory()")
    @Nested
    class AddToInventoryTest {
        @DisplayName("adds a String to inventory")
        @Test
        void addToInventoryAddsItemToInventory() {
            mutumbu.addToInventory(new Item("Glue", new ScoreAction(10)));
            mufasa.addToInventory(new Item("Soup", new HealthAction(10)));
            pumba.addToInventory(new Item("Spaghetti", new HealthAction(10)));
            mutumbusTestInventory.add(new Item("Glue", new ScoreAction(10)));
            mufasasTestInventory.add(new Item("Soup", new HealthAction(10)));
            pumbasTestInventory.add(new Item("Spaghetti", new HealthAction(10)));
            System.out.println(mutumbusTestInventory);
            System.out.println(mutumbu.getInventory().keySet());

            assertTrue(mutumbusTestInventory.containsAll(mutumbu.getInventory().keySet()));
            assertTrue(mutumbu.getInventory().keySet().containsAll(mutumbusTestInventory));
            assertEquals(mutumbusTestInventory.size(), mutumbu.getInventory().keySet().size());

            assertTrue(mufasasTestInventory.containsAll(mufasa.getInventory().keySet()));
            assertTrue(mufasa.getInventory().keySet().containsAll(mufasasTestInventory));
            assertEquals(mufasasTestInventory.size(), mufasa.getInventory().keySet().size());

            assertTrue(pumbasTestInventory.containsAll(pumba.getInventory().keySet()));
            assertTrue(pumba.getInventory().keySet().containsAll(pumbasTestInventory));
            assertEquals(pumbasTestInventory.size(), pumba.getInventory().keySet().size());
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
