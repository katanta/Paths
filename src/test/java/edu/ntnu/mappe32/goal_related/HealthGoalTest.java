package edu.ntnu.mappe32.goal_related;

import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.model.goal_related.HealthGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class HealthGoalTest {
    Player player;
    @BeforeEach
    void setUp() {
        player = new Player("Olav", 150, 0, 300, new HashMap<>());
    }

    @DisplayName("isFulfilled()")
    @Nested
    class IsFulfilledTest {
        @DisplayName("returns true when health goal is exceeded")
        @Test
        void isFulfilledReturnsTrueWhenHealthGoalIsExceeded() {
            HealthGoal superPowered = new HealthGoal(125);
            assertTrue(superPowered.isFulfilled(player));
        }
        @DisplayName("returns true when health goal is exactly met")
        @Test
        void isFulfilledReturnsTrueWhenHealthGoalIsExactlyMet() {
            HealthGoal uberPowered = new HealthGoal(150);
            assertTrue(uberPowered.isFulfilled(player));
        }

        @DisplayName("returns false when health goal is not met")
        @Test
        void isFulfilledReturnsFalseWhenHealthGoalIsNotMet() {
            HealthGoal godLike = new HealthGoal(200);
            assertFalse(godLike.isFulfilled(player));
        }

    }

    @DisplayName("constructor")
    @Nested
    class ConstructorTest {
        @DisplayName("throws IllegalArgumentException when minimum value is negative")
        @Test
        void healthGoalConstructorThrowsIllegalArgumentExceptionOnNegativeMinValue() {
            assertThrows(IllegalArgumentException.class, () ->  new HealthGoal(-200));
        }
        @DisplayName("does not throw IllegalArgumentException when minimum value is positive")
        @Test
        void healthGoalConstructorDoesNotThrowIllegalArgumentExceptionOnPositiveMinValue() {
            assertDoesNotThrow( () -> new HealthGoal(20));
        }
        @DisplayName("does not throw IllegalArgumentException when minimum value is zero")
        @Test
        void healthGoalConstructorDoesNotThrowIllegalArgumentExceptionWhenMinValueIsZero() {
            assertDoesNotThrow( () -> new HealthGoal(0));
        }


    }
}