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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        configureTopLeftButtonHovering();
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
    
    private void configureTopLeftButtonHovering() {
        ImageView helpButtonHover;
        ImageView homeButtonHover;
        ImageView restartButtonHover;
        try {
            helpButtonHover = new ImageView(new Image(new FileInputStream("src/main/resources/img/helpButtonHover.png")));
            homeButtonHover = new ImageView(new Image(new FileInputStream("src/main/resources/img/homeButtonHover.png")));
            restartButtonHover = new ImageView(new Image(new FileInputStream("src/main/resources/img/restartButtonHover.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        final Image normalHelpButton = passageView.getHelpButton().getImage();
        final Image normalHomeButton = passageView.getHomeButton().getImage();
        final Image normalRestartButton = passageView.getRestartButton().getImage();
        //help button
        passageView.getHelpButton().setOnMouseEntered(e -> {
            passageView.getHelpButton().setImage(helpButtonHover.getImage());
            Tooltip tooltip = new Tooltip("Need help? Press me! :)");
            tooltip.setFont(resizableMainFont(18));
            Tooltip.install(passageView.getHelpButton(), tooltip);
        });
        passageView.getHelpButton().setOnMouseExited(e -> {
            passageView.getHelpButton().setImage(normalHelpButton);
        });
        //home button
        passageView.getHomeButton().setOnMouseEntered(e -> {
            passageView.getHomeButton().setImage(homeButtonHover.getImage());
            Tooltip tooltip = new Tooltip("Press this to go the home screen.");
            tooltip.setFont(resizableMainFont(18));
            Tooltip.install(passageView.getHomeButton(), tooltip);
        });
        passageView.getHomeButton().setOnMouseExited(e -> {
                passageView.getHomeButton().setImage(normalHomeButton);
        });
        //restart button
        passageView.getRestartButton().setOnMouseEntered(e -> {
            passageView.getRestartButton().setImage(restartButtonHover.getImage());
            Tooltip tooltip = new Tooltip("Press this to restart the story and revert " + game.player().getName() + " to their original stats.");
            tooltip.setMaxWidth(500);
            tooltip.setWrapText(true);
            tooltip.setFont(resizableMainFont(18));
            Tooltip.install(passageView.getRestartButton(), tooltip);
        });
        passageView.getRestartButton().setOnMouseExited(e -> {
            passageView.getRestartButton().setImage(normalRestartButton);

        });

    }


}

