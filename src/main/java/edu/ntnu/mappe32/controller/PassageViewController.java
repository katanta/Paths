package edu.ntnu.mappe32.controller;

import edu.ntnu.mappe32.model.Game;
import edu.ntnu.mappe32.model.Item;
import edu.ntnu.mappe32.model.goal_related.Goal;
import edu.ntnu.mappe32.model.story_related.Passage;
import edu.ntnu.mappe32.view.PassageView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Map;

import static edu.ntnu.mappe32.view.PassageView.resizableMainFont;

public class PassageViewController {

    private final Game game;
    private final PassageView passageView;
    private static Passage currentPassage;

    public PassageViewController(Stage stage, PassageView passageView, Game game) {
        this.passageView = passageView;
        this.game = game;
        currentPassage = game.begin();
        updateScene();
        passageView.getStoryTitle().setText(game.story().getTitle());
    }

    private void updateScene() {
        updatePlayerInfo();
        updateLinkButtons();
        updatePassageInfo();
        updateInventoryPane();
        updateGameGoalsVBox();
    }

    private void updateInventoryPane() {
        passageView.getItemsVBox().getChildren().clear();
        for (Map.Entry<Item, Integer> item : game.player().getInventory().entrySet()) {
            Label itemLabel = new Label(item.getValue() + "x " + item.getKey().getItemName());
            itemLabel.setFont(resizableMainFont(18));
            passageView.getItemsVBox().getChildren().add(itemLabel);
        }
    }

    private void updatePlayerInfo() {
        passageView.getPlayerGoldLabel().setText("GOLD: " + game.player().getGold());
        passageView.getPlayerHealthLabel().setText("HEALTH: " + game.player().getHealth());
        passageView.getPlayerScoreLabel().setText("SCORE: " + game.player().getScore());
    }

    private void updatePassageInfo() {
        passageView.getPassageTitle().setText(currentPassage.getTitle());
        passageView.getPassageContent().setText(currentPassage.getContent());

    }

    private void updateLinkButtons() {
        passageView.getLinkButtonsVBox().getChildren().clear();
        currentPassage.getLinks().forEach(link -> {
            Button linkButton = new Button(link.getText());
            linkButton.setFont(resizableMainFont(18));
            linkButton.setPrefSize(200, 40);
            linkButton.setMaxWidth(400);
            linkButton.setOnMouseClicked(mouseEvent -> {
                currentPassage = game.go(link); //this.game can be used to update player information
                updateScene(); //make the buttons change the current active passage, changing the scene
            });
            linkButton.setTooltip(new Tooltip(link.getText()));
            linkButton.getTooltip().setFont(resizableMainFont(12));
            passageView.getLinkButtonsVBox().getChildren().add(linkButton);
        });
    }

    private void updateGameGoalsVBox() {
        passageView.getGameGoalsVBox().getChildren().clear();

        for (Goal goal : game.goals()) {

            Label goalLabel = new Label(goal.getClass().getSimpleName() + ": " + goal);
            goalLabel.setFont(resizableMainFont(16));
            goalLabel.setTooltip(new Tooltip(goalLabel.getText()));
            goalLabel.getTooltip().setFont(resizableMainFont(14));

            Label completionStatus = new Label("INCOMPLETE!");
            completionStatus.setFont(resizableMainFont(16));
            completionStatus.setTooltip(new Tooltip("The goal above is incomplete!"));
            completionStatus.getTooltip().setFont(resizableMainFont(20));
            completionStatus.setTextFill(Color.RED);

            if (goal.isFulfilled(game.player())) {
                completionStatus.setText("GOAL COMPLETED!");
                completionStatus.setTextFill(Color.GREEN);
                completionStatus.getTooltip().setText("The goal above is complete!");
            }

            VBox goalStatus = new VBox(goalLabel, completionStatus);
            goalStatus.setAlignment(Pos.TOP_CENTER);
            passageView.getGameGoalsVBox().getChildren().add(goalStatus);
        }
    }
}

