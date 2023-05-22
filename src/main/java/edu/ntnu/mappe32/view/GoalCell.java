package edu.ntnu.mappe32.view;

import edu.ntnu.mappe32.ViewUtils;
import edu.ntnu.mappe32.model.goal_related.*;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

public class GoalCell extends ListCell<Goal> {

    public static final int FIT_HEIGHT = 30;
    public static final int FIT_WIDTH = 30;
    private final HBox hBox = new HBox(20);

    public GoalCell(Goal goal) {
        super();
        Pane pane = new Pane();
        ImageView icon = createIcon(goal);

        Label label = new Label(goal.goalValue());
        Font font  = ViewUtils.pixeloidSans(25);
        label.setFont(font);

        if (goal instanceof InventoryGoal)
            label.setFont(ViewUtils.pixeloidSans(10));
        hBox.getChildren().addAll(icon, label, pane);

        hBox.setPadding(new Insets(0,0,0,5));
        HBox.setHgrow(pane, Priority.ALWAYS);
    }

    private static ImageView createIcon(Goal goal) {
            ImageView icon;
            if (goal instanceof InventoryGoal) {
                icon = new ImageView(ViewUtils.inventoryImage());
                icon.setFitWidth(FIT_WIDTH);
                icon.setFitHeight(FIT_HEIGHT);
                return icon;
            } else if (goal instanceof ScoreGoal) {
                icon = new ImageView(ViewUtils.scoreImage());
                icon.setFitWidth(FIT_WIDTH);
                icon.setFitHeight(FIT_HEIGHT);
                return icon;
            } else if (goal instanceof HealthGoal) {
                icon = new ImageView(ViewUtils.healthImage());
                icon.setFitWidth(FIT_WIDTH);
                icon.setFitHeight(FIT_HEIGHT);
                return icon;
            } else if (goal instanceof GoldGoal) {
                icon = new ImageView(ViewUtils.goldImage());
                icon.setFitWidth(FIT_WIDTH);
                icon.setFitHeight(FIT_HEIGHT);
                return icon;
            }
            throw new IllegalArgumentException("No image that matches your goal");
    }
    public HBox getHBox() {
        return hBox;
    }
}

