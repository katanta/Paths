package edu.ntnu.mappe32;

import edu.ntnu.mappe32.model.Item;
import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.model.action_related.Action;
import edu.ntnu.mappe32.model.action_related.HealthAction;
import edu.ntnu.mappe32.model.action_related.InventoryAction;
import edu.ntnu.mappe32.model.action_related.ScoreAction;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Item Test")
public class ItemTest {

    private Item item;

    @BeforeEach
    void setup() {
        Action action1 = new ScoreAction(100);
        Action action2 = new HealthAction(100);
        item = new Item("Sword", true, action1, action2);
    }

    @Nested
    @DisplayName("constructor")
    class ConstructorTests {
        @Test
        @DisplayName("creates items with actions")
        void testConstructorWithActions() {
            assertNotNull(item);
            assertEquals("Sword", item.getItemName());
            assertTrue(item.isUsable());

            List<Action> actions = item.getActions();
            assertEquals(2, actions.size());
            assertTrue(actions.contains(new ScoreAction(100)));
        }

        @Test
        @DisplayName("throws exception when item has no actions")
        void testConstructorWithoutActions() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> new Item("Sword", true));

            assertEquals("An item must have an action", exception.getMessage());
        }

        @Test
        @DisplayName("throws exception when an action is null")
        void testConstructorWithNullActions() {
            Action action = new ScoreAction(10);
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> new Item("Sword", true, action, null));

            assertEquals("Actions cannot contain null", exception.getMessage());
        }

        @Test
        @DisplayName("throws exception when an InventoryAction is passed")
        void testConstructorWithInventoryAction_Negative() {
            Action action = new InventoryAction(new Item("Item", true, new ScoreAction(10)), true, 1);
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> new Item("Sword", true, action));

            assertEquals("An item cannot contain an inventory action", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("useItem()")
    class UseItemTests {
        @Test
        @DisplayName("applies item's action on Player")
        void useItemAppliesItemsActionsOnPlayer() {
            Player player = new Player.PlayerBuilder("John", 100).score(100).build();
            item.useItem(player);

            assertEquals(200, player.getHealth());
            assertEquals(200, player.getScore());
        }
        @Test
        @DisplayName("throws exception when player is null")
        void useItemThrowsExceptionWhenPlayerIsNull() {
            assertThrows(NullPointerException.class, () -> item.useItem(null));
        }
    }
}
