package edu.ntnu.idatt2001.groups.group32;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Player.
 * A player and its attributes can be affected by a story.
 */
public class Player {
    // Name of the player.
    private final String name;
    // Health of a player.
    private int health;
    //Score of a player.
    private int score;
    // Gold of a player.
    private int gold;
    private List<String> inventory;

    public Player(String name, int health, int score, int gold) {
        this.name = name;
        if (health > 0) {
            this.health = health;
        } else {
            throw new IllegalArgumentException("Health cannot dip below zero!");
        }
        this.score = score;
        this.gold = gold;
        inventory = new ArrayList<String>();
    }

    /**
     * This method returns the name a player.
     * @return Name of player as String
     */
    public String getName() {
        return name;
    }

    /**
     * THis method adds new heath points to a player.
     * @param newHealthPoints New health points, as int.
     */
    public void addHealth(int newHealthPoints) {
        this.health += newHealthPoints;
    }

    /**
     * This method adds new score points to a player:
     * @param newScorePoints New score points, as int.
     */
    public void addScore(int newScorePoints) {
        this.score += newScorePoints;
    }

    /**
     * This method returns the score of a player.
     * @return Score of a player, as int.
     */
    public int getScore() {
        return score;
    }

    /**
     * This method adds new gold points to the player.
     * @param newGoldPoints New gold points, as int.
     */
    public void addGold(int newGoldPoints) {
        this.gold += newGoldPoints;
    }

    /**
     * This method adds an item to a player's inventory.
     * @param item Item to be added, as String.
     */
    public void addToInventory(String item) {
       inventory.add(item);
    }

    /**
     * This method returns the inventory of a player.
     * @return Returns inventory as, List<String>
     */
    public List<String> getInventory() {
        return inventory;
    }
}
