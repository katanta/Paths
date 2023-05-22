package edu.ntnu.mappe32.view;

import edu.ntnu.mappe32.ViewUtils;
import edu.ntnu.mappe32.model.Item;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

import java.util.Map;

public class ItemCell extends ListCell<Item> {

    private final Map<Item, Integer> inventory;

    private final HBox hBox;
    private final Item item;
    private Tooltip tooltip;

    public ItemCell(Item item, Map<Item, Integer> inventory) {
        super();
        this.item = item;
        this.inventory = inventory;
        this.hBox = new HBox();
        configureTooltip();
        configureHBox();
    }

    private void configureHBox() {
        Label itemInfo = new Label();
        itemInfo.setMaxWidth(262.5);
        itemInfo.setFont(ViewUtils.pixeloidSans(15));
        HBox.setMargin(itemInfo, new Insets(6));

        String quantity = String.valueOf(inventory.get(item));
        itemInfo.setText(quantity + "x " + item.getItemName());

        if (!item.isUsable())
            itemInfo.setStyle("-fx-text-fill: grey;");
        hBox.getChildren().add(itemInfo);
    }

    private void configureTooltip() {
        String toolTipText = "Click to use: " + item.getItemName();

        if (!item.isUsable())
            toolTipText = "You cannot use this item!";


        tooltip = ViewUtils.createTooltip(toolTipText, 12);

        tooltip.setFont(ViewUtils.pixeloidSansBold(12));
    }

    public Tooltip getNewTooltip() {
        return tooltip;
    }

    public HBox getHBox() {
        return hBox;
    }
}
