package edu.ntnu.mappe32;

public class HealthAction implements Action {
    private final int health;

    /**
     * Facilitates the creation of instances of the class HealthAction.
     * @param health , the amount of health that this action gives or takes, as int.
     * @since 0.1
     */
    public HealthAction(final int health) {
         this.health = health;
    }

    /**
     * Executes an action that affects a player's health.
     *
     * @param player The player affected by the action, as Player.
     * @since 0.1
     */
    @Override
    public void execute(Player player) {
        player.addHealth(this.health);
    }
}
