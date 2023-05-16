package edu.ntnu.mappe32.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class PassageView {
    private final Scene scene;
    private final Font infoFont;
    private final StackPane root;
    private VBox itemsVBox;
    private HBox playerHealth;
    private HBox playerScore;
    private HBox playerGold;
    private Label playerHealthLabel;
    private Label playerScoreLabel;
    private Label playerGoldLabel;
    private VBox passageInfo;
    private Label storyTitle;
    private Text passageTitle;
    private Text passageContent;
    private VBox linkButtonsVBox;
    private VBox gameGoalsVBox;
    private HBox buttonsHBox;
    private ImageView helpButton;
    private ImageView homeButton;
    private ImageView restartButton;

    public PassageView() {
        root = new StackPane();
        infoFont = resizableMainFont(14);
        configurePlayerInfo();
        configureInventoryPane();
        configurePassageInfo();
        configureLinkScrollPane();
        configureGameGoalsVBox();
        configureButtonsHBox();

        this.scene = new Scene(root, 1280, 720);
    }
    public static Font resizableMainFont(double fontSize) {
        return Font.loadFont("file:src/main/resources/fonts/PixeloidSans-JR6qo.ttf", fontSize);
    }
    private void configureInventoryPane() {
        ScrollPane inventoryPane = new ScrollPane();
        itemsVBox = new VBox();
        inventoryPane.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        itemsVBox.setMaxWidth(316);
        itemsVBox.setMaxHeight(350);
        itemsVBox.setAlignment(Pos.TOP_LEFT);
        itemsVBox.setSpacing(10);
        inventoryPane.setMaxSize(itemsVBox.getMaxWidth() + 4, itemsVBox.getMaxHeight() + 4);
        StackPane.setAlignment(inventoryPane, Pos.BOTTOM_RIGHT);
        inventoryPane.setContent(itemsVBox);
        root.getChildren().add(inventoryPane);
    }

    private void configurePlayerInfo() {
        configurePlayerHealthLabel();
        configurePlayerScoreLabel();
        configurePlayerGoldLabel();

        VBox playerInfo = new VBox(playerHealth, playerScore, playerGold);
        playerInfo.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        playerInfo.setSpacing(40);
        playerInfo.setMaxWidth(320);
        playerInfo.setAlignment(Pos.TOP_LEFT);

        VBox.setMargin(playerHealth, new Insets(40, 0, 0, 50));
        VBox.setMargin(playerScore, new Insets(0, 0, 0, 37));
        VBox.setMargin(playerGold, new Insets(0, 0, 0, 43));
        StackPane.setAlignment(playerInfo, Pos.TOP_RIGHT);
        root.getChildren().add(playerInfo);
    }

    private void configurePlayerScoreLabel() {
        ImageView scoreIcon;
        try {
            scoreIcon = new ImageView(new Image(new FileInputStream("src/main/resources/img/score.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        playerScoreLabel = new Label("");
        playerScoreLabel.setFont(infoFont);
        playerScoreLabel.setLabelFor(scoreIcon);
        scoreIcon.setFitHeight(67);
        scoreIcon.setFitWidth(67);
        playerScore = new HBox(scoreIcon, playerScoreLabel);
        playerScore.setAlignment(Pos.CENTER_LEFT);
        playerScore.setSpacing(20);
    }

    private void configurePlayerHealthLabel() {
        ImageView healthIcon;
        try {
            healthIcon = new ImageView(new Image(new FileInputStream("src/main/resources/img/hp.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        playerHealthLabel = new Label("");
        playerHealthLabel.setFont(infoFont);
        playerHealthLabel.setLabelFor(healthIcon);
        healthIcon.setFitHeight(50);
        healthIcon.setFitWidth(50);
        playerHealth = new HBox(healthIcon, playerHealthLabel);
        playerHealth.setAlignment(Pos.CENTER_LEFT);
        playerHealth.setSpacing(20);
    }

    private void configurePlayerGoldLabel() {
        ImageView goldIcon;
        try {
            goldIcon = new ImageView(new Image(new FileInputStream("src/main/resources/img/gold.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        playerGoldLabel = new Label("");
        playerGoldLabel.setFont(infoFont);
        playerGoldLabel.setLabelFor(goldIcon);
        goldIcon.setFitHeight(58);
        goldIcon.setFitWidth(60);
        playerGold = new HBox(goldIcon, playerGoldLabel);
        playerGold.setAlignment(Pos.CENTER_LEFT);
        playerGold.setSpacing(20);
    }

    private void configurePassageInfo() {
        storyTitle = new Label();
        storyTitle.setLabelFor(passageTitle);
        storyTitle.setMinSize(10, 10);
        storyTitle.setFont(resizableMainFont(45));
        passageTitle = new Text();
        passageTitle.setFont(resizableMainFont(28));

        ScrollPane passageContentScrollPane = new ScrollPane();
        passageContent = new Text();
        passageContent.setFont(resizableMainFont(18));
        passageContentScrollPane.setMaxWidth(600);
        passageContent.setWrappingWidth(passageContentScrollPane.getMaxWidth() - 15);
        passageContentScrollPane.setMaxHeight(180);
        passageContentScrollPane.setContent(passageContent);
        passageInfo = new VBox(storyTitle, passageTitle, passageContentScrollPane);
        VBox.setMargin(passageTitle, new Insets(0, 0, 25, 0));
        passageInfo.setAlignment(Pos.TOP_CENTER);
        root.getChildren().add(passageInfo);
    }

    private void configureLinkScrollPane() {
        ScrollPane linksScrollPane = new ScrollPane();
        linkButtonsVBox = new VBox();
        linkButtonsVBox.setAlignment(Pos.CENTER);
        linksScrollPane.setContent(linkButtonsVBox);
        linksScrollPane.setMaxSize(200, 500);
        linksScrollPane.setFitToWidth(true);
        passageInfo.getChildren().add(linksScrollPane);
        VBox.setMargin(linksScrollPane, new Insets(35, 0, 0, 0));
    }

    private void configureGameGoalsVBox() {
        gameGoalsVBox = new VBox();
        gameGoalsVBox.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
        gameGoalsVBox.setMaxWidth(320);
        gameGoalsVBox.setPrefHeight(600);
        gameGoalsVBox.setSpacing(50);
        ScrollPane goalScrollPane = new ScrollPane();
        root.getChildren().add(goalScrollPane);
        goalScrollPane.setContent(gameGoalsVBox);
        goalScrollPane.setMaxWidth(gameGoalsVBox.getMaxWidth() + 7);
        goalScrollPane.setMaxHeight(gameGoalsVBox.getPrefHeight() + 5);
        StackPane.setAlignment(goalScrollPane, Pos.BOTTOM_LEFT);
    }

    private void configureButtonsHBox() {
        buttonsHBox = new HBox();
        buttonsHBox.setMaxWidth(313);
        buttonsHBox.setMaxHeight(720 - gameGoalsVBox.getPrefHeight());
        buttonsHBox.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
        buttonsHBox.setAlignment(Pos.CENTER_LEFT);
        buttonsHBox.setSpacing(40);
        StackPane.setAlignment(buttonsHBox, Pos.TOP_LEFT);

        configureHelpButton();
        configureHomeButton();
        configureRestartButton();
        setButtonSizes(62); //62 is as big as the buttons get, otherwise they somehow bypass the maxHeight of the buttons VBox
        root.getChildren().add(buttonsHBox);
    }

    private void configureHelpButton() {
        try {
            helpButton = new ImageView(new Image(new FileInputStream("src/main/resources/img/helpButton.png")));
            //helpButtonHover = new ImageView(new Image(new FileInputStream("src/main/resources/img/helpButtonHover")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        helpButton.setFitHeight(50);
        helpButton.setFitWidth(50);
        buttonsHBox.setMargin(helpButton, new Insets(0, 0, 0, 20));
        buttonsHBox.getChildren().add(helpButton);
    }

    private void configureHomeButton() {
        try {
            homeButton = new ImageView(new Image(new FileInputStream("src/main/resources/img/homeButton.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        homeButton.setFitHeight(50);
        homeButton.setFitWidth(50);
        buttonsHBox.getChildren().add(homeButton);
    }

    private void configureRestartButton() {
        try {
            restartButton = new ImageView(new Image(new FileInputStream("src/main/resources/img/restartButton.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        restartButton.setFitHeight(50);
        restartButton.setFitWidth(50);
        buttonsHBox.getChildren().add(restartButton);
    }

    private void setButtonSizes(double buttonSize) {
        restartButton.setFitWidth(buttonSize);
        restartButton.setFitHeight(buttonSize);
        homeButton.setFitWidth(buttonSize);
        homeButton.setFitHeight(buttonSize);
        helpButton.setFitWidth(buttonSize);
        helpButton.setFitHeight(buttonSize);
    }
    public VBox getItemsVBox() {
        return itemsVBox;
    }

    public Label getPlayerGoldLabel() {
        return playerGoldLabel;
    }

    public Label getPlayerHealthLabel() {
        return playerHealthLabel;
    }

    public Label getPlayerScoreLabel() {
        return playerScoreLabel;
    }

    public Text getPassageContent() {
        return passageContent;
    }

    public Label getStoryTitle() {
        return storyTitle;
    }

    public Text getPassageTitle() {
        return passageTitle;
    }

    public VBox getLinkButtonsVBox() {
        return linkButtonsVBox;
    }

    public VBox getGameGoalsVBox() {
        return gameGoalsVBox;
    }

    public Scene getScene() {
        return scene;
    }
}
