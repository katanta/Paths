package edu.ntnu.mappe32;

import java.util.ArrayList;
import java.util.Collection;
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
     * This constructor facilitates the creation of instances of the class Player.
     * The constructor throws IllegalArgumentExceptions if negartive values are entered in the parameters.
     * @param name Name of the player, as String.
     * @param health Health of the player, as int.
     * @param score Score of the player, as int.
     * @param gold Gold of the player, as int.
     */
    public Player(final String name, final int health,
                  final int score, final int gold) throws IllegalArgumentException {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Player must have a name");
        }
        if (health < 0) {
            throw new IllegalArgumentException("Health cannot be below zero");
        }
        if (score < 0) {
            throw new IllegalArgumentException("Score cannot be below zero");
        }
        if (gold < 0) {
            throw new IllegalArgumentException("Gold cannot be blow zero");
        }
        this.name = name;
        this.health = health;
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
        if (this.health + newHealthPoints < 0) {
            this.health = 0;
        } else {
            this.health += newHealthPoints;
        }
    }

    /**
     * This method returns the health of a player.
     * @return Health of player, as int.
     */
    public int getHealth() {
        return health;
    }

    /**
     * This method adds new score points to a player.
     * If the addition results in a negative value, score is set to 0 instead.
     * @param newScorePoints New score points, as int.
     */
    public void addScore(final int newScorePoints) {
        if (this.score + newScorePoints < 0) {
            this.score = 0;
        } else {
            this.score += newScorePoints;
        }
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
     * If the new augend is negative and results in negative gold, the method will throw an IllegalArguemntException.
     * @param newGoldPoints New gold points, as int.
     */
    public void addGold(final int newGoldPoints) throws IllegalArgumentException {
        if (this.gold + newGoldPoints < 0) {
            throw new IllegalArgumentException("You do not have enough points");
        } else {
            this.gold += newGoldPoints;
        }
    }

    /**
     * This method returns the gold a player.
     * @return Gold of a player, as int.
     */
    public int getGold() {
        return gold;
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
