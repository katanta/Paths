package edu.ntnu.mappe32.controller;

import edu.ntnu.mappe32.model.Game;
import edu.ntnu.mappe32.model.Item;
import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.model.action_related.Action;
import edu.ntnu.mappe32.model.action_related.InventoryAction;
import edu.ntnu.mappe32.model.goal_related.Goal;
import edu.ntnu.mappe32.model.story_related.Link;
import edu.ntnu.mappe32.model.story_related.Passage;
import edu.ntnu.mappe32.view.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static edu.ntnu.mappe32.view.PassageView.resizableMainFont;

public class PassageViewController {
    private final Stage stage;
    private Game game;
    private final Player originalPlayer;
    private final PassageView passageView;
    private static Passage currentPassage;
    private final List<Goal> completedGoals;
    private final ObservableList<Item> inventory;

    public PassageViewController(Stage stage, PassageView passageView, Game game) {
        this.stage = stage;
        this.passageView = passageView;
        this.originalPlayer = game.player().copyPlayer();
        this.game = game;
        this.inventory = FXCollections.observableArrayList(game.player().getInventory().keySet());
        completedGoals = new ArrayList<>();
        currentPassage = game.begin();
        updateScene();
        configureAllTopLeftButtonActions();
        passageView.getStoryTitle().setText(game.story().getTitle());
    }

    private void updateScene() {
        updatePlayerInfo();
        updateLinkButtons();
        updatePassageInfo();
        setItemsListViewActions();
        updateGameGoalsVBox();
    }

    private void setItemsListViewActions() {
        passageView.getItemsListView().setItems(inventory);
        passageView.getItemsListView().setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(null);
                setText(null);
                if (item != null && !empty) {
                    ItemCell cell = new ItemCell(item, game.player().getInventory());
                    setGraphic(cell.getHBox());
                    setTooltip(cell.getNewTooltip());
                }
            }
        });

        passageView.getItemsListView().setOnMouseClicked(mouseEvent -> {
            Item usedItem = passageView.getItemsListView().getSelectionModel().getSelectedItem();
            if (usedItem != null && usedItem.isUsable()) {
                usedItem.useItem(game.player());
                game.player().removeFromInventory(usedItem);

                if (game.player().getInventory().get(usedItem) == null)
                    inventory.remove(usedItem);

                updateRecentEventsPane(usedItem);
                updateScene();
                passageView.getItemsListView().refresh();
            }
        });
    }

    private void updatePlayerInfo() {
        if (game.player().getName().endsWith("s") || game.player().getName().endsWith("z") || game.player().getName().endsWith("x")) {
            passageView.getPlayerNameLabel().setText(game.player().getName() + "' stats");
        } else passageView.getPlayerNameLabel().setText(game.player().getName() + "'s stats");

        passageView.getPlayerGoldLabel().setText("GOLD: " + game.player().getGold());
        passageView.getPlayerHealthLabel().setText("HEALTH: " + game.player().getHealth());
        passageView.getPlayerScoreLabel().setText("SCORE: " + game.player().getScore());

        inventory.setAll(game.player().getInventory().keySet());
    }

    private void updatePassageInfo() {
        passageView.getPassageTitle().setText(currentPassage.getTitle());
        passageView.getPassageContent().setText(currentPassage.getContent());
    }

    private void updateLinkButtons() {
        passageView.getLinkButtonsVBox().getChildren().clear();
        currentPassage.getLinks().forEach(link -> {
            Button linkButton = new Button(link.getText());
            linkButton.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-border-width: 2px; -fx-border-color: #000000");
            linkButton.setFont(resizableMainFont(18));
            linkButton.setPrefSize(200, 50);
            linkButton.setMaxWidth(400);
            linkButton.setOnMouseClicked(mouseEvent -> {
                updateRecentEventsPane(link);
                currentPassage = game.go(link); //this.game can be used to update player information
                updateScene(); //make the buttons change the current active passage, changing the scene
            });
            linkButton.setOnMouseEntered(e -> linkButton.setStyle("-fx-background-color: #000000; -fx-text-fill: #ffffff"));
            linkButton.setOnMouseExited(e -> linkButton.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-border-width: 2px; -fx-border-color: #000000"));
            linkButton.setTooltip(new Tooltip(link.getText()));
            linkButton.getTooltip().setFont(resizableMainFont(20));
            passageView.getLinkButtonsVBox().getChildren().add(linkButton);
        });
    }

    private void updateGameGoalsVBox() {

        passageView.getGameGoalsVBox().getChildren().clear();

        for (Goal goal : game.goals()) {
            Label goalLabel = new Label(goal.getClass().getSimpleName() + ": " + goal);
            goalLabel.setFont(resizableMainFont(20));
            goalLabel.setTooltip(new Tooltip(goalLabel.getText()));
            goalLabel.getTooltip().setFont(resizableMainFont(14));
            goalLabel.setMaxWidth(300);
            goalLabel.setWrapText(true);
            goalLabel.setTextAlignment(TextAlignment.CENTER);

            Label completionStatus = new Label("INCOMPLETE!");
            completionStatus.setFont(resizableMainFont(16));
            completionStatus.setTooltip(new Tooltip("The goal above is incomplete!"));
            completionStatus.getTooltip().setFont(resizableMainFont(20));
            completionStatus.setTextFill(Color.RED);

            if ((goal.isFulfilled(game.player()) && !completedGoals.contains(goal))) {
                completedGoals.add(goal);
                game.goals().remove(goal);
                addNewGoalCompletionToEventsPane(goal);
                completionStatus.setText("GOAL COMPLETED!");
                completionStatus.setTextFill(Color.GREEN);
                completionStatus.getTooltip().setText("The goal above is complete!");
            } else if ((!goal.isFulfilled(game.player()) && completedGoals.contains(goal))
                    || goal.isFulfilled(game.player()) && completedGoals.contains(goal)) {
                completionStatus.setText("GOAL COMPLETED!");
                completionStatus.setTextFill(Color.GREEN);
                completionStatus.getTooltip().setText("The goal above is complete!");
            }
            VBox goalStatus = new VBox(goalLabel, completionStatus);
            goalStatus.setAlignment(Pos.CENTER);
            VBox.setMargin(goalStatus, new Insets(30, 0, 0, 0));
            passageView.getGameGoalsVBox().getChildren().add(goalStatus);
        }
    }

    private void setHomeButtonClickAction() {
        passageView.getHomeButton().setOnMouseClicked(e -> {
            FinalSplashScreenController finalSplashScreenController = new FinalSplashScreenController(stage, new FinalSplashScreenView());
        });
    }

    private void setRestartButtonClickAction() {
        passageView.getRestartButton().setOnMouseClicked(e -> {
            this.game = new Game(originalPlayer.copyPlayer(), game.story(), game.goals());
            completedGoals.clear();
            currentPassage = game.begin();
            passageView.getEventsVBox().getChildren().clear();
            updateScene();
        });
    }

    private void setHelpButtonClickAction() {
        passageView.getHelpButton().setOnMouseClicked(e -> {
            passageView.getTutorialImageView().toFront();
            passageView.getTutorialImageView().setVisible(true);
            passageView.getTutorialImageView().setOnMouseClicked(e1 -> {
                passageView.getTutorialImageView().toBack();
                passageView.getTutorialImageView().setVisible(false);
            });
        });
    }

    private void configureAllTopLeftButtonActions() {
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
            setHelpButtonClickAction();
            Tooltip tooltip = new Tooltip("Need help? Press me! :)");
            tooltip.setFont(resizableMainFont(18));
            Tooltip.install(passageView.getHelpButton(), tooltip);
        });
        passageView.getHelpButton().setOnMouseExited(e -> passageView.getHelpButton().setImage(normalHelpButton));
        //home button
        passageView.getHomeButton().setOnMouseEntered(e -> {
            passageView.getHomeButton().setImage(homeButtonHover.getImage());
            setHomeButtonClickAction();
            Tooltip tooltip = new Tooltip("Press this to go the home screen.");
            tooltip.setFont(resizableMainFont(18));
            Tooltip.install(passageView.getHomeButton(), tooltip);
        });
        passageView.getHomeButton().setOnMouseExited(e -> passageView.getHomeButton().setImage(normalHomeButton));
        //restart button
        passageView.getRestartButton().setOnMouseEntered(e -> {
            passageView.getRestartButton().setImage(restartButtonHover.getImage());
            setRestartButtonClickAction();
            Tooltip tooltip = new Tooltip("Press this to restart the story and revert " + game.player().getName() + " to their original stats.");
            tooltip.setMaxWidth(500);
            tooltip.setWrapText(true);
            tooltip.setFont(resizableMainFont(18));
            Tooltip.install(passageView.getRestartButton(), tooltip);
        });
        passageView.getRestartButton().setOnMouseExited(e -> passageView.getRestartButton().setImage(normalRestartButton));
        passageView.getRestartButton().setOnMouseReleased(e -> passageView.getRestartButton().setImage(normalRestartButton));
    }

    private void updateRecentEventsPane(Object linkOrItem) {

        if (!(linkOrItem instanceof Link) && !(linkOrItem instanceof Item))
            return;

        Text allEventText = new Text();
        allEventText.setFont(resizableMainFont(18));
        allEventText.setWrappingWidth(620);
        StringBuilder finalEventString = new StringBuilder();

        if (linkOrItem instanceof Link) {
            for (Action a : ((Link) linkOrItem).getActions()) {
                finalEventString.append(a.toEventString(game.player())).append("\n");
            }
        } else {
                finalEventString.append( "\n" + ((Item) linkOrItem).usageString(game.player()));
            for (Action a : ((Item) linkOrItem).getActions()) {
                finalEventString.append("\n" + a.toEventString(game.player()));
            }
        }
        allEventText.setText(finalEventString.toString());
        passageView.getEventsVBox().getChildren().add(0, allEventText);
    }

    private void addNewGoalCompletionToEventsPane(Goal newCompletedGoal) {

        Text goalCompletionText = new Text(game.player().getName() + " has completed a " + newCompletedGoal.getClass().getSimpleName() + ": " + newCompletedGoal + "!");
        goalCompletionText.setFont(resizableMainFont(18));
        goalCompletionText.setWrappingWidth(620);
        goalCompletionText.setFill(Color.GREEN);

        passageView.getEventsVBox().getChildren().add(0, goalCompletionText);
    }
}

