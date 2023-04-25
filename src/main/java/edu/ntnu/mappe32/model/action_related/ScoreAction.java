package edu.ntnu.mappe32.model.action_related;

import edu.ntnu.mappe32.model.Player;

public class ScoreAction implements Action{

    private final int points;

    /**
     * Constructs objects of the ScoreAction class.
     * @param points , the minimum points required to meet the goal, as int.
     * @since 0.1
     */
    public ScoreAction(final int points) {
        this.points = points;
    }

    /**
     * Executes an action that affects player's score.
     * @param player The player affected by the action, as Player.
     * @since 0.1
     */
    @Override
    public void execute(Player player) {
        player.addScore(this.points);
    }

    @Override
    public String toString() {
        return "<Score " + points + ">";
    }
}
