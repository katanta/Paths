package edu.ntnu.mappe32;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    Player testPlayer;
    Player testPlayer1;
    Player testPlayer2;

    @BeforeEach
    void setup() {
        this.testPlayer = new Player("Mutumbu", 150, 0, 25);
        this.testPlayer1 = new Player("Mufasa", 200, 50, 100);
        this.testPlayer2 = new Player("Pumba", 202, 40,100);
    }

    @Test
    void getNameReturnsName() {
        assertEquals("Mutumbu", testPlayer.getName());
        assertEquals("Mufasa", testPlayer1.getName());
    }

}
