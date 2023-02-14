package edu.ntnu.mappe32;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoldGoalTest {
    Player player = null;
    @BeforeEach
    void setUp() {
        player = new Player("Olav", 100, 0, 300);
    }

    @Test
    void isFulfilledReturnsTrueWhenGoldRequirementIsExceeded() {
        GoldGoal friendsRansom = new GoldGoal(150);
        assertTrue(friendsRansom.isFulfilled(player));
    }

    @Test
    void isFulfilledReturnsTrueWhenGoldRequirementIsExactlyMet() {
        GoldGoal yourRansom = new GoldGoal(300);
        assertTrue(yourRansom.isFulfilled(player));
    }

    @Test
    void isFulfilledReturnsFalseWhenGoldRequirementIsNotMet() {
        GoldGoal kingsRansom = new GoldGoal(1000);
        assertFalse(kingsRansom.isFulfilled(player));
    }


}