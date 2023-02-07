package edu.ntnu.mappe32;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Player.
 * A player and its attributes can be affected by a story.
 */
public class Player {
    /**
     * Name of the player.
     */
    private final String name;
    /**
     * Health of a player.
     */
    private int health;
    /**
     * Score of a player.
     */
    private int score;
    /**
     * Gold of a player.
     */
    private int gold;
    /**
     * Inventory of a player.
     */
    private List<String> inventory;

    /**
     * This method instantiates an object of the class Player.
     * @param name Name of the player, as String.
     * @param health Health of the player, as int.
     * @param score Score of the player, as int.
     * @param gold Gold of the player, as int.
     */
    public Player(final String name, final int health,
                  final int score, final int gold) {
        this.name = name;
        addHealth(health);
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
     * This method adds new heath points to a player.
     * If the addition results in a negative value, health is set to 0 instead.
     * @param newHealthPoints New health points, as int.
     */
    public void addHealth(final int newHealthPoints) {
        if (newHealthPoints > 0) {
            this.health +=  newHealthPoints;
        } else if (this.health + newHealthPoints < 0) {
            this.health = 0;
        } else {
            this.health += newHealthPoints;
        }
    }

    /**
     * This method adds new score points to a player.
     * @param newScorePoints New score points, as int.
     */
    public void addScore(final int newScorePoints) {
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
    public void addGold(final int newGoldPoints) {
        this.gold += newGoldPoints;
    }

    /**
     * This method adds an item to a player's inventory.
     * @param item Item to be added, as String.
     */
    public void addToInventory(final String item) {
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
