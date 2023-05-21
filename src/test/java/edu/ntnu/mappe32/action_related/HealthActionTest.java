package edu.ntnu.mappe32.action_related;

import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.model.action_related.HealthAction;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("HealthAction Test")
class HealthActionTest {

    private Player player;

    @BeforeEach
    void setup() {
        player = new Player.PlayerBuilder("John", 100).build();
    }

    @Nested
    @DisplayName("execute()")
    class ExecuteTests {
        @Test
        @DisplayName("adds health")
        void testExecute_AddHealth_Positive() {
            HealthAction addAction = new HealthAction(10);
            addAction.execute(player);

            assertEquals(110, player.getHealth());
        }

        @Test
        @DisplayName("reduces health")
        void testExecute_ReduceHealth_Positive() {
            // Set the player's health to a value greater than the reduction
            player.addHealth(50);

            HealthAction reduceAction = new HealthAction(-20);
            reduceAction.execute(player);

            assertEquals(130, player.getHealth());
        }
    }
}
