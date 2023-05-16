package edu.ntnu.mappe32.view;

import edu.ntnu.mappe32.model.goal_related.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GoalCell extends ListCell<Goal> {

    public static final int FIT_HEIGHT = 20;
    public static final int FIT_WIDTH = 20;
    private final HBox hBox = new HBox(10);

    public GoalCell(Goal goal) {
        super();
        Pane pane = new Pane();
        ImageView icon = createIcon(goal);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(new Label(goal.goalValue(), pane));
        hBox.getChildren().addAll(icon, vBox, pane);
        hBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(pane, Priority.ALWAYS);
    }

    private static ImageView createIcon(Goal goal) {
        try {
            ImageView icon;
            if (goal instanceof InventoryGoal) {
                icon = new ImageView(
                        new Image(new FileInputStream("src/main/resources/img/inventory.png")));
                icon.setFitWidth(FIT_WIDTH);
                icon.setFitHeight(FIT_HEIGHT);
                return icon;
            } else if (goal instanceof ScoreGoal) {
                icon = new ImageView(
                        new Image(new FileInputStream("src/main/resources/img/score.png")));
                icon.setFitWidth(FIT_WIDTH + 6);
                icon.setFitHeight(FIT_HEIGHT + 3);
                return icon;
            } else if (goal instanceof HealthGoal) {
                icon = new ImageView(
                        new Image(new FileInputStream("src/main/resources/img/hp.png")));
                icon.setFitWidth(FIT_WIDTH);
                icon.setFitHeight(FIT_HEIGHT);
                return icon;
            } else if (goal instanceof GoldGoal) {
                icon = new ImageView(
                        new Image(new FileInputStream("src/main/resources/img/gold.png")));
                icon.setFitWidth(FIT_WIDTH + 6);
                icon.setFitHeight(FIT_HEIGHT + 2);
                return icon;
            }
            throw new IllegalArgumentException("No image that matches your goal");
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Image not found");
        }
    }
    public HBox getHBox() {
        return hBox;
    }
}

