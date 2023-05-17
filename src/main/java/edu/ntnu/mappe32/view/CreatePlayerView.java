package edu.ntnu.mappe32.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;

public class CreatePlayerView {

    private final Scene scene;
    private HBox storyTitleBox;
    private ImageView backButton;
    private Image backButtonHover;
    private ImageView infoButton;
    private Image infoButtonHover;
    private Label storyTitle;
    private VBox centerVBox;
    private GridPane playerGridPane;
    private HBox invalidHealthBox;
    private HBox invalidNameBox;
    private HBox invalidGoldBox;
    private Label playerNameLabel;
    private Label playerHealthLabel;
    private Label playerGoldLabel;
    private TextField playerNameTextField;
    private TextField playerHealthTextField;
    private TextField playerGoldTextField;
    private Image errorCircleImage;
    private final Font statLabelFont;
    private final Font textFieldFont;
    public CreatePlayerView() {
        statLabelFont = resizableMainFont(30);
        textFieldFont = Font.loadFont("file:src/main/resources/fonts/PixeloidSans-JR6qo.ttf", 20);
        configureTop();
        configureCenter();

        BorderPane root = new BorderPane();
        root.getChildren().add(backButton);
        root.setTop(storyTitleBox);
        root.setCenter(centerVBox);
        this.scene = new Scene(root, 1080, 720);
    }
    public static Font resizableMainFont(double fontSize) {
        return Font.loadFont("file:src/main/resources/fonts/PixeloidSans-JR6qo.ttf", fontSize);
    }
    private void configureTop() {
        storyTitleBox = new HBox(50);

        try {
            backButton = new ImageView(new Image(new FileInputStream("src/main/resources/img/restartButton.png")));
            backButtonHover = new Image(new FileInputStream("src/main/resources/img/restartButtonHover.png"));
            infoButton = new ImageView(new Image(new FileInputStream("src/main/resources/img/info.png")));
            infoButtonHover = new Image(new FileInputStream("src/main/resources/img/info_hover.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        backButton.setFitWidth(62);
        backButton.setFitHeight(62);
        backButton.setX(20);
        backButton.setY(20);
        infoButton.setFitWidth(31);
        infoButton.setFitHeight(31);

        storyTitle = new Label();
        storyTitle.setFont(Font.loadFont("file:src/main/resources/fonts/PixeloidSansBold-GOjpP.ttf", 50));
        storyTitle.setMaxWidth(700);

        storyTitleBox.getChildren().addAll(storyTitle, infoButton);
        storyTitleBox.setAlignment(Pos.CENTER);

        storyTitleBox.setPadding(new Insets(20, 0,0,0));

    }

    private void configureCenter() {
        centerVBox = new VBox(10);
        centerVBox.setPadding(new Insets(30,0,0,0));
        centerVBox.setAlignment(Pos.TOP_CENTER);
        Label chooseYourStats = new Label("Choose Your Stats");
        chooseYourStats.setFont(resizableMainFont(40));
        chooseYourStats.setAlignment(Pos.CENTER);

        ImageView nextButton;
        try {
            nextButton = new ImageView(new Image(new FileInputStream("src/main/resources/img/next_button.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        configurePlayerStatsGridPane();
        playerGridPane.setPadding(new Insets(0, 0,0,220));
        playerGridPane.setAlignment(Pos.TOP_CENTER);
        centerVBox.getChildren().addAll(chooseYourStats, playerGridPane, nextButton);

    }

    private void configurePlayerStatsGridPane() {
        configureInvalidInputBoxes();
        configureRow0();
        configureRow2();
        configureRow1();
        playerGridPane = new GridPane();
        playerGridPane.setHgap(10);
        playerGridPane.setVgap(30);
        playerGridPane.add(playerNameLabel,0,0);
        playerGridPane.add(playerHealthLabel,0,1);
        playerGridPane.add(playerGoldLabel,0,2);
        playerGridPane.add(playerNameTextField,1,0);
        playerGridPane.add(playerHealthTextField, 1,1);
        playerGridPane.add(playerGoldTextField, 1,2);
        playerGridPane.add(invalidNameBox, 2,0);
        playerGridPane.add(invalidHealthBox,2,1);
        playerGridPane.add(invalidGoldBox, 2, 2);
        VBox.setMargin(playerGridPane, new Insets(20,0,0,0));
    }
    private void configureInvalidInputBoxes() {

        String invalidLabelStyle = "-fx-text-fill: #D80D0D;" + "-fx-font-size: 13;";

        Label invalidGoldLabel = new Label("Invalid gold (Integers over 0 only)");
        invalidGoldLabel.setStyle(invalidLabelStyle);
        invalidGoldLabel.setAlignment(Pos.CENTER);
        invalidGoldBox = new HBox(5);
        invalidGoldBox.getChildren().addAll(createErrorCircle(), invalidGoldLabel);
        invalidGoldBox.setAlignment(Pos.CENTER_LEFT);
        invalidGoldBox.setVisible(false);

        Label invalidHealthLabel = new Label("Invalid health (Integers over 0 only)");
        invalidHealthLabel.setStyle(invalidLabelStyle);
        invalidHealthLabel.setAlignment(Pos.CENTER);
        invalidHealthBox = new HBox(5);
        invalidHealthBox.getChildren().addAll(createErrorCircle(), invalidHealthLabel);
        invalidHealthBox.setAlignment(Pos.CENTER_LEFT);
        invalidHealthBox.setVisible(false);

        Label invalidNameLabel = new Label("Invalid name");
        invalidNameLabel.setStyle(invalidLabelStyle);
        invalidNameLabel.setAlignment(Pos.CENTER);
        invalidNameBox = new HBox(5);
        invalidNameBox.getChildren().addAll(createErrorCircle(), invalidNameLabel);
        invalidNameBox.setAlignment(Pos.CENTER_LEFT);
        invalidNameBox.setVisible(false);
    }

    private void configureRow0() {
        this.playerNameTextField = new TextField();
        playerNameTextField.setPromptText("Player Name");
        playerNameTextField.setMaxWidth(170);
        playerNameTextField.setFont(textFieldFont);
        this.playerNameLabel = new Label("Name:");
        playerNameLabel.setFont(statLabelFont);
    }
    private void configureRow1() {
        this.playerGoldTextField = new TextField();
        playerGoldTextField.setPromptText("Player Gold");
        playerGoldTextField.setMaxWidth(170);
        playerGoldTextField.setFont(textFieldFont);
        playerGoldTextField.setTextFormatter(maxNineCharactersFormatter());
        this.playerGoldLabel = new Label("Gold:");
        playerGoldLabel.setFont(statLabelFont);
    }
    private void configureRow2() {
        this.playerHealthTextField = new TextField();
        playerHealthTextField.setPromptText("Player Health");
        playerHealthTextField.setMaxWidth(170);
        playerHealthTextField.setFont(textFieldFont);
        playerHealthTextField.setTextFormatter(maxNineCharactersFormatter());
        this.playerHealthLabel = new Label("Health:");
        playerHealthLabel.setFont(statLabelFont);
    }
    private VBox createErrorCircle() {
        ImageView errorCircle = new ImageView(errorCircleImage);
        errorCircle.setFitWidth(13);
        errorCircle.setFitHeight(13);
        VBox errorCircleBox = new VBox(errorCircle);
        errorCircleBox.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(errorCircle, new Insets(6,0,0, 0));
        return errorCircleBox;
    }
    private void configureErrorCircleImage() {
        try {
            errorCircleImage = new Image(new FileInputStream("src/main/resources/img/error-circle-solid-24.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private TextFormatter<TextFormatter.Change> maxNineCharactersFormatter() {
        Pattern pattern = Pattern.compile(".{0,9}");
        return new TextFormatter<>((change -> pattern.matcher(change.getControlNewText()).matches() ? change : null));
    }

    public Label getStoryTitle() {
        return storyTitle;
    }

    public Scene getScene() {
        return scene;
    }
}
