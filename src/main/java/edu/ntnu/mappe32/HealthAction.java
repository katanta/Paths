package edu.ntnu.mappe32;

public class HealthAction implements Action {

    private final int health;

    public HealthAction(final int health) {
         this.health = health;
    }


    @Override
    public void execute(Player player) {
        player.addHealth(this.health);
    }
}
