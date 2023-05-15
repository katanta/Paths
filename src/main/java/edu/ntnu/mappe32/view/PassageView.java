package edu.ntnu.mappe32.view;

import edu.ntnu.mappe32.controller.Game;
import edu.ntnu.mappe32.model.Item;
import edu.ntnu.mappe32.model.goal_related.Goal;
import edu.ntnu.mappe32.model.story_related.Passage;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class PassageView {
    StackPane root;
    Scene scene;
    Text passageContent;
    Label storyTitle;
    Text passageTitle;
    Game game;
    Passage currentPassage;
    VBox linkButtons;
    Label playerHealthLabel;
    Label playerScoreLabel;
    Label playerGoldLabel;
    ScrollPane inventory;
    private ScrollPane goalScrollPane;

    public PassageView(Game game) {
        this.game = game;
    }

    public static Font resizableMainFont(double fontSize) {
        return Font.loadFont("file:src/main/resources/fonts/PixeloidSans-JR6qo.ttf", fontSize);
    }
    public void configurePassageContent() throws FileNotFoundException {
        currentPassage = game.begin();
        root = new StackPane();
        scene = new Scene(root, 1280, 720, Color.DARKGREY);
        storyTitle = new Label(game.getStory().getTitle());
        storyTitle.setLabelFor(passageTitle);
        storyTitle.setMinSize(10, 10);
        storyTitle.setFont(resizableMainFont(45));
        passageTitle = new Text(currentPassage.getTitle());
        passageTitle.setFont(resizableMainFont(28));
        ScrollPane passageScrollPane = new ScrollPane();
        passageContent = new Text(currentPassage.getContent() + " ejsdhfkjadshfkjdsahfksdfkjahsdkjhaskjhaksljhkjsdahvkljashvjasdhvjashvjashfjashfkjasfhjashfjkSHDFJKSHFJSHFJSDHFJHSDFKJHASDFHASDF@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@222");
        passageContent.setFont(resizableMainFont(18));
        passageScrollPane.setMaxWidth(600);
        passageContent.setWrappingWidth(passageScrollPane.getMaxWidth() - 15);
        passageScrollPane.setMaxHeight(180);
        passageScrollPane.setContent(passageContent);
        VBox center = new VBox(storyTitle, passageTitle, passageScrollPane);
        center.setMargin(passageTitle, new Insets(0, 0, 25, 0));
        center.setAlignment(Pos.TOP_CENTER);
        root.getChildren().add(center);

        //Configure initial links
        ScrollPane linksScrollPane = new ScrollPane();
        linkButtons = new VBox();
        game.begin().getLinks().forEach(link -> {
            Button linkButton = new Button(link.getText());
            linkButton.setFont(resizableMainFont(18));
            linkButton.setPrefSize(200, 40);
            linkButton.setMaxWidth(400);
            linkButton.setOnMouseClicked(mouseEvent -> {
                currentPassage = game.go(link); //this.game can be used to update player information
                updateScene(); //make the buttons change the current active passage, changing the scene
                updateGoals();
                createInventory();
            });
            linkButton.setTooltip(new Tooltip(link.getText()));
            linkButton.getTooltip().setFont(resizableMainFont(12));
            linkButtons.getChildren().add(linkButton);
        });
        linkButtons.setAlignment(Pos.CENTER);
        linksScrollPane.setContent(linkButtons);
        linksScrollPane.setMaxSize(200, 500);
        linksScrollPane.setFitToWidth(true);
        center.getChildren().add(linksScrollPane);
        center.setMargin(linksScrollPane, new Insets(35, 0, 0, 0));


        Font infoFont = resizableMainFont(14);
        //setup health indicator
        ImageView healthIcon = new ImageView(new Image(new FileInputStream("src/main/resources/img/hp.png")));
        playerHealthLabel = new Label("HEALTH: " + game.getPlayer().getHealth());
        playerHealthLabel.setFont(infoFont);
        playerHealthLabel.setLabelFor(healthIcon);
        healthIcon.setFitHeight(50);
        healthIcon.setFitWidth(50);
        HBox playerHealth = new HBox(healthIcon, playerHealthLabel);
        playerHealth.setAlignment(Pos.CENTER_LEFT);
        playerHealth.setSpacing(20);

        //setup score indicator
        ImageView scoreIcon = new ImageView(new Image(new FileInputStream("src/main/resources/img/score.png")));
        playerScoreLabel = new Label("SCORE: " + game.getPlayer().getScore());
        playerScoreLabel.setFont(infoFont);
        playerScoreLabel.setLabelFor(scoreIcon);
        scoreIcon.setFitHeight(67);
        scoreIcon.setFitWidth(67);
        HBox playerScore = new HBox(scoreIcon, playerScoreLabel);
        playerScore.setAlignment(Pos.CENTER_LEFT);
        playerScore.setSpacing(20);

        //setup gold indicator
        ImageView goldIcon = new ImageView(new Image(new FileInputStream("src/main/resources/img/gold.png")));
        playerGoldLabel = new Label("GOLD: " + game.getPlayer().getGold());
        playerGoldLabel.setFont(infoFont);
        playerGoldLabel.setLabelFor(goldIcon);
        goldIcon.setFitHeight(58);
        goldIcon.setFitWidth(60);
        HBox playerGold = new HBox(goldIcon, playerGoldLabel);
        playerGold.setAlignment(Pos.CENTER_LEFT);
        playerGold.setSpacing(20);

        //Set all player info, (score, hp, and gold)
        VBox playerInfo = new VBox(playerHealth, playerScore, playerGold);
        playerInfo.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        Label infoTitle = new Label(game.getPlayer().getName() + "'s status");
        VBox.setMargin(infoTitle, new Insets(30, 0, 0, 0));
        playerInfo.setSpacing(40);
        playerInfo.setMaxWidth(320);
        playerInfo.setAlignment(Pos.TOP_LEFT);

        VBox.setMargin(playerHealth, new Insets(40, 0, 0, 50));
        VBox.setMargin(playerScore, new Insets(0, 0, 0, 37));
        VBox.setMargin(playerGold, new Insets(0, 0, 0, 43));
        StackPane.setAlignment(playerInfo, Pos.TOP_RIGHT);
        root.getChildren().add(playerInfo);

        //Set up goals
        updateGoals();
        createInventory();
    }

    public Scene getScene() throws FileNotFoundException {
        configurePassageContent();
        return scene;
    }

    /**
     * Updates the scene based on the current active passage. The link buttons
     * receive an action that changes the current passage, and executes this method once again.
     */
    public void updateScene() {
        linkButtons.getChildren().clear();
        passageContent.setText(currentPassage.getContent());
        passageTitle.setText(currentPassage.getTitle());
        playerGoldLabel.setText("GOLD: " + game.getPlayer().getGold());
        playerHealthLabel.setText("HEALTH: " + game.getPlayer().getHealth());
        playerScoreLabel.setText("SCORE: " + game.getPlayer().getScore());
        //TODO: this method CAN be split up into smaller parts, e.g. update score, health and gold labels separately from here
        currentPassage.getLinks().forEach(link -> {
            Button linkButton = new Button(link.getText());
            linkButton.setFont(resizableMainFont(18));
            linkButton.setPrefSize(200, 40);
            linkButton.setTooltip(new Tooltip(link.getText()));
            linkButton.getTooltip().setFont(resizableMainFont(12));
            linkButton.setOnMouseClicked(mouseEvent -> {this.currentPassage = game.go(link);
                updateScene();
                updateGoals();
                createInventory();
            });
            linkButtons.getChildren().add(linkButton);
        });
    }
    public void updateGoals() {
        VBox gameGoals = new VBox();
        gameGoals.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
        gameGoals.setMaxWidth(320);
        gameGoals.setMinHeight(718);
        gameGoals.setSpacing(50);

        for (Goal g : game.getGoals()) {
            Label goalLabel = new Label(g.getClass().getSimpleName() + ": " + g);
            goalLabel.setFont(resizableMainFont(16));
            goalLabel.setTooltip(new Tooltip(goalLabel.getText()));
            goalLabel.getTooltip().setFont(resizableMainFont(14));
            Label completionStatus = new Label("INCOMPLETE!");
            completionStatus.setFont(resizableMainFont(16));
            completionStatus.setTooltip(new Tooltip("The goal above is incomplete!"));
            completionStatus.getTooltip().setFont(resizableMainFont(20));
            completionStatus.setTextFill(Color.RED);
            if (g.isFulfilled(game.getPlayer())) {
                completionStatus.setText("GOAL COMPLETED!");
                completionStatus.setTextFill(Color.GREEN);
                completionStatus.getTooltip().setText("The goal above is complete!");
            }
            VBox goalStatus = new VBox(goalLabel, completionStatus);
            goalStatus.setAlignment(Pos.TOP_CENTER);
            gameGoals.getChildren().add(goalStatus);
            goalScrollPane = new ScrollPane();
            root.getChildren().add(goalScrollPane);
            goalScrollPane.setContent(gameGoals);
            goalScrollPane.setMaxWidth(gameGoals.getMaxWidth() + 5);
            StackPane.setAlignment(goalScrollPane, Pos.TOP_LEFT);
        }
    }
    public void createInventory() {
        inventory = new ScrollPane();
        VBox items = new VBox();
        inventory.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        items.setMaxWidth(316);
        items.setMaxHeight(350);
        items.setAlignment(Pos.TOP_LEFT);
        items.setSpacing(10);
        inventory.setMaxSize(items.getMaxWidth() + 4, items.getMaxHeight() + 4);
        for (Map.Entry<Item, Integer> item : game.getPlayer().getInventory().entrySet()) {
            Label itemLabel = new Label(item.getValue() + "x "  +  item.getKey().getItemName());
            itemLabel.setFont(resizableMainFont(18));
            items.getChildren().add(itemLabel);
        }
        StackPane.setAlignment(inventory, Pos.BOTTOM_RIGHT);
        inventory.setContent(items);
        root.getChildren().add(inventory);
    }
}
