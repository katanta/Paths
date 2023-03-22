package goal_related;

import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.model.goal_related.ScoreGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreGoalTest {
    Player player = null;
    @BeforeEach
    void setUp() {
        player = new Player("Olav", 100, 1000, 300);
    }

    @Test
    void isFulfilledReturnsTrueWhenScoreGoalIsExceeded() {
        ScoreGoal masterGuardian = new ScoreGoal(800);
        assertTrue(masterGuardian.isFulfilled(player));
    }

    @Test
    void isFulfilledReturnsTrueWhenScoreGoalIsExactlyMet() {
        ScoreGoal masterGuardian2 = new ScoreGoal(1000);
        assertTrue(masterGuardian2.isFulfilled(player));
    }

    @Test
    void isFulfilledReturnsFalseWhenScoreGoalIsNotMet() {
        ScoreGoal globalElite = new ScoreGoal(1500);
        assertFalse(globalElite.isFulfilled(player));
    }

    @Test
    void scoreGoalConstructorThrowsIllegalArgumentExceptionOnNegativeMinValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            ScoreGoal rockBottom = new ScoreGoal(-200);
        });
    }
}