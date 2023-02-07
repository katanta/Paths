package edu.ntnu.mappe32;

public class GoldAction implements Action {

    private final int gold;

    public GoldAction(final int gold) {
        this.gold = gold;
    }

    @Override
    public void execute(Player player) {
        player.addGold(this.gold);
    }
}
