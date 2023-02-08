package edu.ntnu.mappe32;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    Player mutumbu;
    Player mufasa;
    Player pumba;

    List<String> pumbasTestInventory;
    List<String> mutumbusTestInventory;
    List<String> mufasasTestInventory;



    @BeforeEach
    void setup() {
        this.mutumbu = new Player("Mutumbu", 150, 0, 25);
        this.mufasa = new Player("Mufasa", 200, 50, 100);
        this.pumba = new Player("Pumba", 202, 40, 100);
        this.mutumbu.addToInventory("Tape");
        this.mutumbu.addToInventory("Lighter");
        this.mutumbu.addToInventory("Candle");
        this.mutumbu.addToInventory("Potato");
        this.mutumbu.addToInventory("Hammer");
        this.mutumbu.addToInventory("Dagger");
        this.mutumbu.addToInventory("Scarf");
        this.mufasa.addToInventory("Key");
        this.mufasa.addToInventory("Milk");
        this.mufasa.addToInventory("Chicken");
        this.mufasa.addToInventory("Pencil");
        this.mufasa.addToInventory("Wood");
        this.mufasa.addToInventory("Paper");
        this.pumba.addToInventory("N'Tofos");
        this.pumba.addToInventory("Orange");
        this.pumba.addToInventory("Apple");
        this.pumba.addToInventory("Machete");
        this.pumba.addToInventory("Knife");
        this.pumba.addToInventory("Paperclip");
        this.pumba.addToInventory("Rolex");
        this.pumba.addToInventory("Key");
        this.mutumbusTestInventory = new ArrayList<String>(Arrays.asList("Tape", "Lighter", "Candle", "Potato", "Hammer",
                "Dagger", "Scarf"));
        this.mufasasTestInventory = new ArrayList<String>(Arrays.asList("Key", "Milk", "Chicken", "Pencil", "Wood", "Paper"));
        this.pumbasTestInventory = new ArrayList<String>(Arrays.asList("N'Tofos", "Orange",
                "Apple", "Machete", "Knife", "Paperclip", "Rolex", "Key"));
    }
    @Test
    void getNameReturnsName() {
        assertEquals("Mutumbu", mutumbu.getName());
        assertEquals("Mufasa", mufasa.getName());
        assertEquals("Pumba", pumba.getName());
    }
    @Test
    void getScoreReturnsScore() {
        assertEquals(0, mutumbu.getScore());
        assertEquals(50, mufasa.getScore());
        assertEquals(40, pumba.getScore());
        
    }
    @Test
    void getHealthReturnsHealth() {
        assertEquals(150, mutumbu.getHealth());
        assertEquals(200, mufasa.getHealth());
        assertEquals(202, pumba.getHealth());
    }

    @Test
    void getGoldReturnsGold() {
        assertEquals(25, mutumbu.getGold());
        assertEquals(100, mufasa.getGold());
        assertEquals(100, pumba.getGold());
    }

    @Test
    void getInventoryReturnsInventoryInOriginalOrder() {
        assertEquals(mutumbusTestInventory, mutumbu.getInventory());
        assertEquals(mufasasTestInventory, mufasa.getInventory());
        assertEquals(pumbasTestInventory, pumba.getInventory());
    }
    @Test
    void addHealthAddsPositiveHealth() {
        mutumbu.addHealth(50);
        assertEquals(200, mutumbu.getHealth());
    }
    @Test
    void addGoldAddsGold() {
        mutumbu.addGold(100);
        mufasa.addGold(-100);
        pumba.addGold(-150);
        assertEquals(125, mutumbu.getGold());
        assertEquals(0, mufasa.getGold());
        assertEquals(-50,pumba.getGold());
    }
    @Test
    void addHealthDoesNotSetOrReduceHealthBelowZero() {
        mufasa.addHealth(-2 * mufasa.getHealth());
        assertEquals(0, mufasa.getHealth());
    }

    @Test
    void addInventoryAddsItemToInventory() {
        mutumbu.addToInventory("Glue");
        mufasa.addToInventory("Soup");
        pumba.addToInventory("Spaghetti");
        mutumbusTestInventory.add("Glue");
        mufasasTestInventory.add("Soup");
        pumbasTestInventory.add("Spaghetti");
        assertEquals(mutumbusTestInventory, mutumbu.getInventory());
        assertEquals(mufasasTestInventory, mufasa.getInventory());
        assertEquals(pumbasTestInventory, pumba.getInventory());
    }

    @Test
    void addScoreAddsScore() {
        mutumbu.addScore(20);
        mufasa.addScore(-120);
        pumba.addScore(-30);
        assertEquals(20, mutumbu.getScore());
        assertEquals(-70, mufasa.getScore());
        assertEquals(10, pumba.getScore());
    }
}
