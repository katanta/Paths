package edu.ntnu.mappe32;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthGoalTest {
    Player player = null;
    @BeforeEach
    void setUp() {
        player = new Player("Olav", 150, 0, 300);
    }

    @Test
    void isFulfilledReturnsTrueWhenHealthGoalIsExceeded() {
        HealthGoal superPowered = new HealthGoal(125);
        assertTrue(superPowered.isFulfilled(player));
    }

    @Test
    void isFulfilledReturnsTrueWhenHealthGoalIsExactlyMet() {
        HealthGoal uberPowered = new HealthGoal(150);
        assertTrue(uberPowered.isFulfilled(player));
    }

    @Test
    void isFulfilledReturnsFalseWhenHealthGoalIsNotMet() {
        HealthGoal godLike = new HealthGoal(200);
        assertFalse(godLike.isFulfilled(player));
    }
}