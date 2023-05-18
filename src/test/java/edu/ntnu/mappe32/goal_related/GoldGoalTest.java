package edu.ntnu.mappe32.goal_related;

import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.model.goal_related.GoldGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GoldGoalTest {
    Player player;
    @BeforeEach
    void setUp() {
        player = new Player("Olav", 100, 0, 300, new HashMap<>());
    }
    @DisplayName("isFulfilled()")
    @Nested
    class IsFulfilledTest {
        @DisplayName("returns true when gold requirement is exceeded")
        @Test
        void isFulfilledReturnsTrueWhenGoldRequirementIsExceeded() {
            GoldGoal friendsRansom = new GoldGoal(150);
            assertTrue(friendsRansom.isFulfilled(player));
        }
        @DisplayName("returns true when gold requirement is exactly met")
        @Test
        void isFulfilledReturnsTrueWhenGoldRequirementIsExactlyMet() {
            GoldGoal yourRansom = new GoldGoal(300);
            assertTrue(yourRansom.isFulfilled(player));
        }
        @DisplayName("returns false when gold requirement is not met")
        @Test
        void isFulfilledReturnsFalseWhenGoldRequirementIsNotMet() {
            GoldGoal kingsRansom = new GoldGoal(1000);
            assertFalse(kingsRansom.isFulfilled(player));
        }

    }
    @DisplayName("Constructor")
    @Nested
    class Constructor {
        @DisplayName("throws IllegalArgumentException when minimum gold is negative")
        @Test
        void goldGoalConstructorThrowsIllegalArgumentExceptionOnNegativeMinValue() {
            assertThrows(IllegalArgumentException.class, () -> new GoldGoal(-200));
        }
        @DisplayName("does not throw IllegalArgumentException when minimum gold is positive")
        @Test
        void goldGoalConstructorDoesNotThrowIllegalArgumentExceptionOnPositiveMinValue() {
            assertDoesNotThrow(() -> new GoldGoal(1));
        }
        @DisplayName("does not throw IllegalArgumentException when minimum gold is zero")
        @Test
        void goldGoalConstructorDoesNotThrowIllegalArgumentExceptionWhenMinValueIsZero() {
            assertDoesNotThrow(() -> new GoldGoal(0));
        }
    }
}