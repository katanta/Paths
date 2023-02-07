package edu.ntnu.mappe32;

public class ScoreAction implements Action{

    private final int points;

    public ScoreAction(final int points) {
        this.points = points;
    }

    @Override
    public void execute(Player player) {
        player.addScore(this.points);
    }


}
