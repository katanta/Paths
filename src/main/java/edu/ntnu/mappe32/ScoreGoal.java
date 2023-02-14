package edu.ntnu.mappe32;

public class ScoreGoal implements Goal {

    private final int minimumPoints;

    /**
     * This constructor facilitates the creation of instances of the class ScoreGoal.
     * @param minimumPoints Points required for a goal to be reached, as int.
     */
    ScoreGoal(final int minimumPoints) {
        this.minimumPoints = minimumPoints;
    }

    /**
     * This method checks if the score goal is reached.
     * @param player The player a goal-check should be executed on, as Player.
     * @return Whether the player has fulfilled a goal, as boolean.
     */
    public boolean isFulfilled(Player player) {
        return player.getScore() >= this.minimumPoints;
    }
}

