package edu.ntnu.mappe32.model;

import edu.ntnu.mappe32.model.action_related.InsufficientGoldException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a Player.
 * A player and its attributes can be affected by a story.
 */
public class Player implements Serializable {
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
    private final Map<Item, Integer> inventory;
    /**
     * This constructor facilitates the creation of instances of the class Player.
     * The constructor throws IllegalArgumentExceptions
     * if negative values are entered in the parameters.
     * @param name Name of the player, as String.
     * @param health Health of the player, as int.
     * @param score Score of the player, as int.
     * @param gold Gold of the player, as int.
     */
    public Player(final String name, final int health,
                  final int score, final int gold, final Map<Item, Integer> inventory) throws IllegalArgumentException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Player must have a name");
        }
        if (health < 0) {
            throw new IllegalArgumentException("Health cannot be below zero");
        }
        if (score < 0) {
            throw new IllegalArgumentException("Score cannot be below zero");
        }
        if (gold < 0) {
            throw new IllegalArgumentException("Gold cannot be below zero");
        }
        if (inventory == null) {
            throw new IllegalStateException("Inventory of player cannot be null");
        }
        this.name = name;
        this.health = health;
        this.score = score;
        this.gold = gold;
        this.inventory = inventory;
    }

    public Player(PlayerBuilder playerBuilder) {
        this.name = playerBuilder.name;
        this.health = playerBuilder.health;
        this.gold = playerBuilder.gold;
        this.score = playerBuilder.score;
        this.inventory = playerBuilder.inventory;
    }

    public Player copyPlayer() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
            bos.close();
            byte[] byteData = bos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
            return (Player) new ObjectInputStream(bais).readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
        if (this.health + newHealthPoints > 0) {
            this.health += newHealthPoints;
            return;
        }
        this.health = 0;
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
        if (this.score + newScorePoints > 0) {
            this.score += newScorePoints;
            return;
        }
        this.score = 0;
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
     * If the new augend is negative and results in negative gold,
     * the method will throw an IllegalArguemntException.
     * @param newGoldPoints New gold points, as int.
     */
    public void addGold(final int newGoldPoints) {
        if (this.gold + newGoldPoints < 0) {
            throw new IllegalArgumentException("You do not have enough gold");
        }
        this.gold += newGoldPoints;
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
    public void addToInventory(final Item item, final int quantity) {
        if (quantity == 0)
            throw new IllegalArgumentException("Quantity cannot be zero");
        if (item == null)
            throw new IllegalArgumentException("Item cannot be null");
        inventory.merge(item, quantity, Integer::sum);
    }

    public void removeFromInventory(final Item item) {
        if (!inventory.containsKey(item)) {
            throw new IllegalArgumentException("The player does not have this item");
        }
        inventory.merge(item, -1, Integer::sum);

        if (inventory.get(item) == 0) {
            inventory.remove(item);
        }
    }

    /**
     * This method returns the inventory of a player.
     * @return Returns inventory as, List<String>
     */
    public Map<Item, Integer> getInventory() {
        return new HashMap<>(inventory);
    }

    /**
     * This is the builder class for Player, which makes it able to
     * instantiate a player without an inventory.
     * If gold or score is not set, it is
     */
    public static class PlayerBuilder {
        /**
         * Name of the player.
         */
        private final String name;
        /**
         * Health of a player.
         */
        private final int health;
        /**
         * Score of a player.
         */
        private int score = 0;
        /**
         * Gold of a player.
         */
        private int gold = 0;
        /**
         * Inventory of a player.
         */
        private Map<Item, Integer> inventory;

        public PlayerBuilder(String name, int health) {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Player must have a name");
            }
            if (health < 0) {
                throw new IllegalArgumentException("Health cannot be below zero");
            }
            this.name = name;
            this.health = health;
        }
        public PlayerBuilder inventory(Map<Item, Integer> inventory) {
            if (inventory == null || inventory.isEmpty())
                throw new IllegalArgumentException("Inventory cannot be empty or null");

            if (inventory.containsValue(null))
                throw new IllegalArgumentException("A value cannot be null or zero");

            if (inventory.values().stream().anyMatch(value -> value < 1))
                throw new IllegalArgumentException("A value cannot be less than 1");

            if (inventory.containsKey(null))
                throw new IllegalArgumentException("A key cannot be null");

            this.inventory = inventory;
            return this;
        }
        public PlayerBuilder score(int scorePoints) {
            if (score < 0)
                throw new IllegalArgumentException("Score cannot be below zero");

            this.score = scorePoints;
            return this;
        }

        public PlayerBuilder gold(int goldPoints) {
            if (gold < 0)
                throw new IllegalArgumentException("Gold cannot be below zero");

            this.gold = goldPoints;
            return this;
        }
        public Player build() {
            if (inventory == null)
                inventory = new HashMap<>();

            return new Player(this);
        }
    }
}
