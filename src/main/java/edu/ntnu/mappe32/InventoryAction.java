package edu.ntnu.mappe32;

public class InventoryAction implements Action{

    private final String item;

    public InventoryAction(final String item) {
        this.item = item;
    }

    //What should be the game mechanic when a player adds an item he already owns?
    @Override
    public void execute(Player player) {
        player.addToInventory(item);
    }

}
