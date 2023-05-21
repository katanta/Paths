package edu.ntnu.mappe32.action_related;

import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.model.action_related.GoldAction;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("GoldAction Test")
class GoldActionTest {

    private Player player;

    @BeforeEach
    void setup() {
        player = new Player.PlayerBuilder("John", 100).build();
    }

    @Nested
    @DisplayName("execute()")
    class ExecuteTests {
        @Test
        @DisplayName("adds Gold")
        void testExecute_AddGold_Positive() {
            GoldAction addAction = new GoldAction(100);
            addAction.execute(player);

            assertEquals(100, player.getGold());
        }

        @Test
        @DisplayName("reduces Gold")
        void testExecute_ReduceGold_Positive() {
            // Set the player's gold to a value greater than the reduction
            player.addGold(200);

            GoldAction reduceAction = new GoldAction(-50);
            reduceAction.execute(player);

            assertEquals(150, player.getGold());
        }
    }
}