package edu.ntnu.mappe32.goal_related;

import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.model.goal_related.ScoreGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreGoalTest {
    Player player;
    @BeforeEach
    void setUp() {
        player = new Player("Olav", 100, 1000, 300);
    }
    @DisplayName("isFulFilled()")
    @Nested
    class IsFulfilledTest {
        @DisplayName("returns true when score goal is exceeded")
        @Test
        void isFulfilledReturnsTrueWhenScoreGoalIsExceeded() {
            ScoreGoal masterGuardian = new ScoreGoal(800);
            assertTrue(masterGuardian.isFulfilled(player));
        }
        @DisplayName("returns true when score goal is exactly met")
        @Test
        void isFulfilledReturnsTrueWhenScoreGoalIsExactlyMet() {
            ScoreGoal masterGuardian2 = new ScoreGoal(1000);
            assertTrue(masterGuardian2.isFulfilled(player));
        }
        @DisplayName("returns false when score goal is not met")
        @Test
        void isFulfilledReturnsFalseWhenScoreGoalIsNotMet() {
            ScoreGoal globalElite = new ScoreGoal(1500);
            assertFalse(globalElite.isFulfilled(player));
        }
    }

    @DisplayName("constructor")
    @Nested
    class ConstructorTest {
        @DisplayName("throws IllegalArgumentException on negative minimum value")
        @Test
        void scoreGoalConstructorThrowsIllegalArgumentExceptionOnNegativeMinValue() {
            assertThrows(IllegalArgumentException.class, () -> new ScoreGoal(-200));
        }
        @DisplayName("does not throw IllegalArgumentException on positive minimum value")
        @Test
        void constructorDoesNotThrowIllegalArgumentExceptionOnPositiveMinValue() {
            assertDoesNotThrow(() -> new ScoreGoal(2));
        }
        @DisplayName("does not throw IllegalArgumentException when minimum value is zero")
        @Test
        void constructorDoesNotThrowIllegalArgumentExceptionWhenMinValueIsZero() {
            assertDoesNotThrow(() -> new ScoreGoal(0));
        }
    }
}