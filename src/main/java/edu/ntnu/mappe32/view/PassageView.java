package edu.ntnu.mappe32.view;

import edu.ntnu.mappe32.FrontendUtils;
import edu.ntnu.mappe32.model.Item;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
    private Label playerNameLabel;
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
    private VBox eventsVBox;
    private ListView<Item> itemsListView;
    private ImageView tutorialImageView;

    public PassageView() {
        root = new StackPane();
        root.setStyle("-fx-border-width: 4px; -fx-border-color: #000000");
        infoFont = FrontendUtils.pixeloidSans(18);
        configurePassageInfo();
        configureLinkScrollPane();
        configureGameGoalsVBox();
        configureButtonsHBox();
        configurePlayerInfo();
        configureRecentEventsPane();
        setTestBackground();
        configureTutorialImageView();
        this.scene = new Scene(root, 1280, 720);
    }

    public void setTestBackground() {
        Image backgroundImage;
        try {
            backgroundImage = new Image(new FileInputStream("src/main/resources/img/scrollWallpaper.jpg"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        buttonsHBox.setBackground(new Background(background));
    }

    private void configureInventoryPane() {
        itemsVBox = new VBox();
        itemsVBox.setAlignment(Pos.TOP_LEFT);

        itemsListView = new ListView<>();
        itemsVBox.getChildren().add(itemsListView);
        itemsVBox.setStyle("-fx-border-color: black; -fx-border-width: 4px;");
    }

    private void configurePlayerInfo() {
        configurePlayerNameLabel();
        configurePlayerHealthLabel();
        configurePlayerScoreLabel();
        configurePlayerGoldLabel();
        configureInventoryPane();
        VBox playerInfo = new VBox(playerNameLabel, playerHealth, playerScore, playerGold, itemsVBox);
        playerInfo.getChildren().stream().limit(4).forEach(node -> VBox.setMargin(node, new Insets(0, 0, 0, 20)));
        playerInfo.setStyle("-fx-border-color: black; -fx-border-width: 4px;");
        playerInfo.setSpacing(40);
        playerInfo.setMaxWidth(320);
        playerInfo.setAlignment(Pos.TOP_CENTER);

        StackPane.setAlignment(playerInfo, Pos.TOP_RIGHT);
        root.getChildren().add(playerInfo);
    }

    private void configurePlayerScoreLabel() {
        ImageView scoreIcon = new ImageView(FrontendUtils.scoreImage());

        playerScoreLabel = new Label("");
        playerScoreLabel.setFont(infoFont);
        playerScoreLabel.setLabelFor(scoreIcon);
        scoreIcon.setFitHeight(70);
        scoreIcon.setFitWidth(70);
        playerScore = new HBox(scoreIcon, playerScoreLabel);
        playerScore.setAlignment(Pos.CENTER_LEFT);
        playerScore.setSpacing(20);
    }

    private void configurePlayerNameLabel() {
        playerNameLabel = new Label();
        playerNameLabel.setFont(FrontendUtils.pixeloidSans(25));
    }

    private void configurePlayerHealthLabel() {
        ImageView healthIcon = new ImageView(FrontendUtils.healthImage());
        playerHealthLabel = new Label("");
        playerHealthLabel.setFont(infoFont);
        playerHealthLabel.setLabelFor(healthIcon);
        healthIcon.setFitHeight(70);
        healthIcon.setFitWidth(70);
        playerHealth = new HBox(healthIcon, playerHealthLabel);
        playerHealth.setAlignment(Pos.CENTER_LEFT);
        playerHealth.setSpacing(20);
    }

    private void configurePlayerGoldLabel() {
        ImageView goldIcon = new ImageView(FrontendUtils.goldImage());
        playerGoldLabel = new Label("");
        playerGoldLabel.setFont(infoFont);
        playerGoldLabel.setLabelFor(goldIcon);
        goldIcon.setFitHeight(70);
        goldIcon.setFitWidth(70);
        playerGold = new HBox(goldIcon, playerGoldLabel);
        playerGold.setAlignment(Pos.CENTER_LEFT);
        playerGold.setSpacing(20);
    }

    private void configurePassageInfo() {
        storyTitle = new Label();
        storyTitle.setLabelFor(passageTitle);
        storyTitle.setMinSize(10, 10);
        storyTitle.setFont(FrontendUtils.pixeloidSansBold(45));
        passageTitle = new Text();
        passageTitle.setFont(FrontendUtils.pixeloidSans(28));

        ScrollPane passageContentScrollPane = new ScrollPane();
        passageContent = new Text();
        passageContent.setFont(FrontendUtils.pixeloidSans(18));
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
        linksScrollPane.setMaxSize(400, 200);
        linksScrollPane.setFitToWidth(true);
        passageInfo.getChildren().add(linksScrollPane);
        VBox.setMargin(linksScrollPane, new Insets(15, 0, 0, 0));
    }

    private void configureGameGoalsVBox() {
        gameGoalsVBox = new VBox();
        gameGoalsVBox.setMaxWidth(318);
        gameGoalsVBox.setMinWidth(318);
        gameGoalsVBox.setPrefHeight(598);
        ScrollPane goalScrollPane = new ScrollPane(gameGoalsVBox);
        gameGoalsVBox.setStyle("-fx-border-color: black; -fx-border-width: 4px;");
        StackPane.setAlignment(goalScrollPane, Pos.BOTTOM_LEFT);
        goalScrollPane.setMinWidth(320);
        goalScrollPane.setMaxWidth(320);
        goalScrollPane.setMaxHeight(600);
        goalScrollPane.setMinHeight(600);
        goalScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        root.getChildren().add(goalScrollPane);
    }

    private void configureButtonsHBox() {
        buttonsHBox = new HBox();
        buttonsHBox.setMaxWidth(319);
        buttonsHBox.setMaxHeight(720 - gameGoalsVBox.getPrefHeight());
        buttonsHBox.setStyle("-fx-border-color: black; -fx-border-width: 4px;");
        buttonsHBox.setAlignment(Pos.CENTER_LEFT);
        buttonsHBox.setSpacing(40);
        StackPane.setAlignment(buttonsHBox, Pos.TOP_LEFT);

        configureHelpButton();
        configureHomeButton();
        configureRestartButton();
        setButtonSizes(); //62 is as big as the buttons get, otherwise they somehow bypass the maxHeight of the buttons VBox
        root.getChildren().add(buttonsHBox);
    }

    private void configureHelpButton() {
        helpButton = new ImageView(FrontendUtils.helpButtonImage());
        helpButton.setFitHeight(50);
        helpButton.setFitWidth(50);
        HBox.setMargin(helpButton, new Insets(0, 0, 0, 20));
        buttonsHBox.getChildren().add(helpButton);
    }

    private void configureHomeButton() {
        homeButton = new ImageView(FrontendUtils.homeButtonImage());
        homeButton.setFitHeight(50);
        homeButton.setFitWidth(50);
        buttonsHBox.getChildren().add(homeButton);
    }

    private void configureRestartButton() {
        restartButton = new ImageView(FrontendUtils.restartImage());
        restartButton.setFitHeight(50);
        restartButton.setFitWidth(50);
        buttonsHBox.getChildren().add(restartButton);
    }

    private void setButtonSizes() {
        int buttonSize = 62;
        restartButton.setFitWidth(buttonSize);
        restartButton.setFitHeight(buttonSize);
        homeButton.setFitWidth(buttonSize);
        homeButton.setFitHeight(buttonSize);
        helpButton.setFitWidth(buttonSize);
        helpButton.setFitHeight(buttonSize);
    }

    private void configureRecentEventsPane() {
        eventsVBox = new VBox();
        eventsVBox.setSpacing(1.5);
        ScrollPane recentEventsPane = new ScrollPane(eventsVBox);
        recentEventsPane.setStyle("-fx-border-color: black; -fx-border-width: 4px;");
        recentEventsPane.setMaxHeight(195);
        recentEventsPane.setMinWidth(643);
        recentEventsPane.setMaxWidth(recentEventsPane.getMinWidth());
        StackPane.setAlignment(recentEventsPane, Pos.BOTTOM_CENTER);
        root.getChildren().add(recentEventsPane);
    }

    private void configureTutorialImageView() {
        try {
            tutorialImageView = new ImageView(new Image(new FileInputStream("src/main/resources/img/passageViewTutorial.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StackPane.setAlignment(tutorialImageView, Pos.CENTER);
        root.getChildren().add(tutorialImageView);
        tutorialImageView.toBack();
        tutorialImageView.setVisible(false);
    }

    public Label getPlayerNameLabel() {
        return playerNameLabel;
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

    public ImageView getHelpButton() {
        return helpButton;
    }

    public ImageView getHomeButton() {
        return homeButton;
    }

    public ImageView getRestartButton() {
        return restartButton;
    }

    public Scene getScene() {
        return scene;
    }

    public VBox getEventsVBox() {
        return eventsVBox;
    }

    public ListView<Item> getItemsListView() {
        return itemsListView;
    }

    public ImageView getTutorialImageView() {
        return tutorialImageView;
    }
}
