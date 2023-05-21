package edu.ntnu.mappe32.action_related;

import edu.ntnu.mappe32.model.Item;
import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.model.action_related.InventoryAction;
import edu.ntnu.mappe32.model.action_related.ScoreAction;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("InventoryAction Test")
class InventoryActionTest {

    private Player player;
    private Item item;

    @BeforeEach
    void setup() {
        player = new Player.PlayerBuilder("John", 100).build();
        item = new Item("Sword", true, new ScoreAction(10));
    }

    @Nested
    @DisplayName("execute()")
    class ExecuteTests {
        @Test
        @DisplayName("adds Item to Inventory")
        void testExecuteAddItemPositive() {
            InventoryAction addAction = new InventoryAction(item, true, 1);
            addAction.execute(player);

            assertTrue(player.getInventory().containsKey(item));
        }

        @Test
        @DisplayName("removes Item from Inventory and Uses It")
        void testExecute_RemoveItemAndUse_Positive() {

            player.addToInventory(item, 1);

            InventoryAction removeAction = new InventoryAction(item, false, 1);
            removeAction.execute(player);

            assertFalse(player.getInventory().containsKey(item));
            assertEquals(10, player.getScore());
        }

        @Test
        @DisplayName("execute throws exception when item is null")
        void testExecute_NullItem_Negative() {
            assertThrows(IllegalArgumentException.class, () -> new InventoryAction(null, true, 1));
        }
    }
}