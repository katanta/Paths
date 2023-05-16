package edu.ntnu.mappe32.model.goal_related;

import edu.ntnu.mappe32.model.Player;

public class ScoreGoal implements Goal {

    private final int minimumPoints;

    /**
     * This constructor facilitates the creation of instances of the class ScoreGoal.
     * @param minimumPoints Points required for a goal to be reached, as int.
     * @throws IllegalArgumentException when minimumPoints is negative.
     * @since 0.1
     */
    public ScoreGoal(final int minimumPoints) {
        if (minimumPoints < 0) {
            throw new IllegalArgumentException("Minimum score goal cannot be a negative amount.");
        }
        this.minimumPoints = minimumPoints;
    }

    /**
     * This method checks if the score goal is reached.
     * @param player The player a goal-check should be executed on, as Player.
     * @return Whether the player has fulfilled a goal, as boolean.
     * @since 0.1
     */
    @Override
    public boolean isFulfilled(Player player) {
        return player.getScore() >= this.minimumPoints;
    }

    public String toString() {
        return "Have a total score of " + minimumPoints;
    }
    @Override
    public String goalValue() {
        return String.valueOf(minimumPoints);
    }
    @Override
    public String goalType() {
        return "Score";
    }
}

