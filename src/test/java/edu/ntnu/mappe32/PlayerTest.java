package edu.ntnu.mappe32;

import edu.ntnu.mappe32.model.Item;
import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.model.action_related.HealthAction;
import edu.ntnu.mappe32.model.action_related.ScoreAction;
import org.junit.jupiter.api.*;

import java.util.*;

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
        this.mutumbu.addToInventory(new Item("Tape", true, new ScoreAction(10)), 1);
        this.mutumbu.addToInventory(new Item("Lighter", true,new ScoreAction(10)), 1);
        this.mutumbu.addToInventory(new Item("Candle", true,new ScoreAction(10)), 1);
        this.mutumbu.addToInventory(new Item("Potato", true, new HealthAction(10)), 1);
        this.mutumbu.addToInventory(new Item("Hammer", true,new ScoreAction(10)), 1);
        this.mutumbu.addToInventory(new Item("Dagger", true,new ScoreAction(10)), 1);
        this.mutumbu.addToInventory(new Item("Scarf", true,new HealthAction(10)), 1);
        this.mufasa.addToInventory(new Item("Key", true,new ScoreAction(10)), 1);
        this.mufasa.addToInventory(new Item("Milk", true, new HealthAction(10)), 1);
        this.mufasa.addToInventory(new Item("Chicken", true, new HealthAction(50)), 1);
        this.mufasa.addToInventory(new Item("Pencil", true, new ScoreAction(10)), 1);
        this.mufasa.addToInventory(new Item("Wood", true, new ScoreAction(10)), 1);
        this.mufasa.addToInventory(new Item("Paper", true, new ScoreAction(10)), 1);
        this.pumba.addToInventory(new Item("N'Tofos", true, new ScoreAction(10)), 1);
        this.pumba.addToInventory(new Item("Orange", true, new HealthAction(20)), 1);
        this.pumba.addToInventory(new Item("Apple", true, new HealthAction(10)), 1);
        this.pumba.addToInventory(new Item("Machete", true, new ScoreAction(10)), 1);
        this.pumba.addToInventory(new Item("Knife", true, new ScoreAction(10)), 1);
        this.pumba.addToInventory(new Item("Paperclip", true, new ScoreAction(10)), 1);
        this.pumba.addToInventory(new Item("Rolex", true, new ScoreAction(10)), 1);
        this.pumba.addToInventory(new Item("Key", true, new ScoreAction(10)), 1);
        this.mutumbusTestInventory = new ArrayList<>(Arrays.asList(new Item("Tape", true, new ScoreAction(10)),
                new Item("Lighter", true, new ScoreAction(10)), new Item("Candle", true, new ScoreAction(10)),
                new Item("Potato", true, new HealthAction(10)), new Item("Hammer", true, new ScoreAction(10)),
                new Item("Dagger", true, new ScoreAction(10)), new Item("Scarf", true, new HealthAction(10))));

        this.mufasasTestInventory = new ArrayList<>(Arrays.asList(new Item("Key", true, new ScoreAction(10)),
        new Item("Milk", true, new HealthAction(10)),
        new Item("Chicken", true, new HealthAction(50)),
        new Item("Pencil", true, new ScoreAction(10)),
        new Item("Wood", true, new ScoreAction(10)),
        new Item("Paper", true, new ScoreAction(10))));

        this.pumbasTestInventory = new ArrayList<>(Arrays.asList(new Item("N'Tofos", true, new ScoreAction(10)),
        new Item("Orange", true, new HealthAction(20)),
        new Item("Apple", true, new HealthAction(10)),
        new Item("Machete", true, new ScoreAction(10)),
        new Item("Knife", true, new ScoreAction(10)),
        new Item("Paperclip", true, new ScoreAction(10)),
        new Item("Rolex", true, new ScoreAction(10)),
        new Item("Key", true, new ScoreAction(10))));
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


            mutumbusTestInventory.add(new Item("Item A", true, new ScoreAction(10)));
            mufasasTestInventory.add(new Item("Item B", true, new ScoreAction(10)));
            pumbasTestInventory.add(new Item("Item C", true, new ScoreAction(10)));

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
            mutumbu.addToInventory(new Item("Glue", true, new ScoreAction(10)), 1);
            mufasa.addToInventory(new Item("Soup", true, new HealthAction(10)), 1);
            pumba.addToInventory(new Item("Spaghetti", true, new HealthAction(10)), 1);
            mutumbusTestInventory.add(new Item("Glue", true, new ScoreAction(10)));
            mufasasTestInventory.add(new Item("Soup", true, new HealthAction(10)));
            pumbasTestInventory.add(new Item("Spaghetti", true, new HealthAction(10)));

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
            assertThrows(IllegalArgumentException.class, () -> mutumbu.addToInventory(null, 1));
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

    @DisplayName("removeFromInventory()")
    @Nested
    class RemoveFromInventoryTests {
        @DisplayName("removes valid item from inventory")
        @Test
        void removeFromInventoryRemovesValidItem() {
            Item item = new Item("Tape", true, new ScoreAction(10));
            mutumbu.removeFromInventory(item);

            assertFalse(mutumbu.getInventory().containsKey(item));
        }
        @DisplayName("throws exception when player does not have item")
        @Test
        void removeFromInventoryThrowsExceptionWhenPlayerDoesNotHaveItem() {
            Item invalidItem = new Item("Invalid Item", false, new ScoreAction(10));

            Assertions.assertThrows(IllegalArgumentException.class, () -> mutumbu.removeFromInventory(invalidItem));
        }
        @DisplayName("throws exception when item is null")
        @Test
        void removeFromInventoryThrowsExceptionWhenItemIsNull() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> mutumbu.removeFromInventory(null));
        }
        @DisplayName("decreases item quantity")
        @Test
        void removeFromInventoryItemQuantityDecreased() {
            Item item = new Item("Tape", true, new ScoreAction(10));

            int initialQuantity = mutumbu.getInventory().get(item);

            mutumbu.removeFromInventory(item);

            int updatedQuantity = mutumbu.getInventory().getOrDefault(item, 0);

            Assertions.assertEquals(initialQuantity - 1, updatedQuantity);
        }
        @DisplayName("removes item key when quantity is zero")
        @Test
        void removeFromInventoryRemovesItemWhenQuantityIsZero() {
            Item item = new Item("Tape", true, new ScoreAction(10));

            mutumbu.removeFromInventory(item);

            Assertions.assertFalse(mutumbu.getInventory().containsKey(item));
        }
    }
    @Nested
    @DisplayName("PlayerBuilder tests")
    class PlayerBuilderTest {

        @Test
        void testBuilderWithValidValues() {
            String name = "John";
            int health = 100;
            int score = 50;
            int gold = 1000;
            Map<Item, Integer> inventory = new HashMap<>();
            inventory.put(new Item("Sword", true, new HealthAction(-10)), 1);

            Player.PlayerBuilder builder = new Player.PlayerBuilder(name, health)
                    .score(score)
                    .gold(gold)
                    .inventory(inventory);

            Player player = builder.build();

            assertEquals(name, player.getName());
            assertEquals(health, player.getHealth());
            assertEquals(score, player.getScore());
            assertEquals(gold, player.getGold());
            assertEquals(inventory, player.getInventory());
        }

        @Test
        void testBuilderWithMissingValues() {
            String name = "John";
            int health = 100;

            Player.PlayerBuilder builder = new Player.PlayerBuilder(name, health);

            Player player = builder.build();

            assertEquals(name, player.getName());
            assertEquals(health, player.getHealth());
            assertEquals(0, player.getScore());
            assertEquals(0, player.getGold());
            assertTrue(player.getInventory().isEmpty());
        }

        @Test
        void testBuilderWithInvalidValues() {
            assertThrows(IllegalArgumentException.class, () -> new Player.PlayerBuilder(null, 100));
            assertThrows(IllegalArgumentException.class, () -> new Player.PlayerBuilder("", 100));
            assertThrows(IllegalArgumentException.class, () -> new Player.PlayerBuilder("John", -100));

            assertThrows(IllegalArgumentException.class, () -> new Player.PlayerBuilder("John", 100).inventory(null));

            Map<Item, Integer> invalidInventory = new HashMap<>();
            invalidInventory.put(new Item("Sword", true, new HealthAction(-10)), null);
            assertThrows(IllegalArgumentException.class, () -> new Player.PlayerBuilder("John", 100).inventory(invalidInventory));

            invalidInventory.clear();
            invalidInventory.put(new Item("Sword", true, new HealthAction(-10)), 0);
            assertThrows(IllegalArgumentException.class, () -> new Player.PlayerBuilder("John", 100).inventory(invalidInventory));

            invalidInventory.clear();
            invalidInventory.put(null, 1);
            assertThrows(IllegalArgumentException.class, () -> new Player.PlayerBuilder("John", 100).inventory(invalidInventory));

            invalidInventory.clear();
            invalidInventory.put(new Item("Sword", true, new HealthAction(-10)), -1);
            assertThrows(IllegalArgumentException.class, () -> new Player.PlayerBuilder("John", 100).inventory(invalidInventory));

            assertThrows(IllegalArgumentException.class, () -> new Player.PlayerBuilder("John", 100).score(-100));
            assertThrows(IllegalArgumentException.class, () -> new Player.PlayerBuilder("John", 100).gold(-100));
        }
    }
    @DisplayName("copyPlayer()")
    @Nested
    class CopyPlayerTest {
        @DisplayName("returns same player")
        @Test
        void testCopyReturnsSamePlayer() {
            // Create a player with initial values
            Player originalPlayer = new Player("Test Player", 100, 0, 0, new HashMap<>());
            originalPlayer.addToInventory(new Item("Item 1", true, new ScoreAction(10)), 5);
            originalPlayer.addToInventory(new Item("Item 2", true, new ScoreAction(10)), 3);

            // Create a copy of the player
            Player copiedPlayer = originalPlayer.copyPlayer();

            // Assert that the copied player is not the same object reference
            Assertions.assertNotSame(originalPlayer, copiedPlayer);

            // Assert that the copied player has the same values as the original player
            Assertions.assertEquals(originalPlayer.getName(), copiedPlayer.getName());
            Assertions.assertEquals(originalPlayer.getHealth(), copiedPlayer.getHealth());
            Assertions.assertEquals(originalPlayer.getScore(), copiedPlayer.getScore());
            Assertions.assertEquals(originalPlayer.getGold(), copiedPlayer.getGold());
            Assertions.assertEquals(originalPlayer.getInventory(), copiedPlayer.getInventory());
        }
    }
}
