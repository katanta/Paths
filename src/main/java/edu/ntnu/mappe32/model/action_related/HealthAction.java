package edu.ntnu.mappe32.model.action_related;

import edu.ntnu.mappe32.model.Player;

import java.io.Serializable;
import java.util.Objects;

public class HealthAction implements Action, Serializable {
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

    /**
     * A string that describes the action occurring to the player.
     *
     * @param player The player affected by the action, as Player.
     * @return a String that describes the action occurring to the player, as String.
     */
    @Override
    public String toEventString(Player player) {
        if (health < 0) {
            if (player.getHealth() + health <= 0) {
                return "Oh dear! You have died \n" + player.getName() + " has lost " + -health + " health and has perished!";
            } else return player.getName() + " has lost " + health + " health!";
        } else if (health > 0) return player.getName() + " has gained " + health + " health!";
        return null;
    }


    @Override
    public String toString() {
        return "<Health " + health + ">";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HealthAction that)) return false;
        return health == that.health;
    }

    @Override
    public int hashCode() {
        return Objects.hash(health);
    }
}
