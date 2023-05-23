package edu.ntnu.mappe32.view;

import edu.ntnu.mappe32.FrontendUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.regex.Pattern;


public class CreatePlayerView {

    private final Scene scene;
    private HBox top;
    private ImageView backButton;
    private ImageView infoButton;
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
    private ImageView nextButton;
    private final Font statLabelFont;
    private final Font textFieldFont;
    private ImageView helpButton;
    private HBox helpAndBackButtonHBox;
    private ImageView tutorialImageView;
    private final BorderPane root;

    public CreatePlayerView() {
        this.root = new BorderPane();
        statLabelFont = FrontendUtils.pixeloidSans(30);
        textFieldFont = FrontendUtils.pixeloidSans(20);
        configureTop();
        configureCenter();

        root.setTop(top);
        root.setCenter(centerVBox);
        root.getChildren().add(helpAndBackButtonHBox);
        this.scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/StyleSheets/DialogBoxStyleSheet.css")).toExternalForm());

    }

    private void configureTop() {
        top = new HBox(50);

        infoButton = new ImageView(FrontendUtils.infoImage());
        infoButton.setPickOnBounds(true);
        infoButton.setFitWidth(31);
        infoButton.setFitHeight(31);
        configureHelpAndBackButton();
        helpAndBackButtonHBox.getChildren().addAll(backButton, helpButton);

        storyTitle = new Label();
        storyTitle.setFont(FrontendUtils.pixeloidSansBold(50));
        storyTitle.setMaxWidth(700);

        top.getChildren().addAll(storyTitle, infoButton);
        top.setAlignment(Pos.CENTER);

        top.setPadding(new Insets(20, 0, 0, 0));

    }

    private void configureHelpAndBackButton() {
        helpAndBackButtonHBox = new HBox(10);
        helpAndBackButtonHBox.setAlignment(Pos.TOP_LEFT);
        helpAndBackButtonHBox.setPadding(new Insets(20, 0, 0, 20));
        helpButton = new ImageView(FrontendUtils.helpButtonImage());
        helpButton.setFitWidth(62);
        helpButton.setFitHeight(62);
        helpButton.setPickOnBounds(true);
        backButton = new ImageView(FrontendUtils.restartImage());
        backButton.setFitWidth(62);
        backButton.setFitHeight(62);
        backButton.setPickOnBounds(true);
    }

    private void configureCenter() {
        centerVBox = new VBox(50);
        centerVBox.setPadding(new Insets(30, 0, 0, 0));
        centerVBox.setAlignment(Pos.TOP_CENTER);
        Label chooseYourStats = new Label("Choose Your Stats");
        chooseYourStats.setFont(FrontendUtils.pixeloidSans(40));
        chooseYourStats.setAlignment(Pos.CENTER);

        nextButton = new ImageView(FrontendUtils.nextImage());
        configurePlayerStatsGridPane();
        configureTutorialImageView();
        playerGridPane.setPadding(new Insets(0, 0, 0, 330));
        playerGridPane.setAlignment(Pos.TOP_CENTER);
        nextButton.setPickOnBounds(true);
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
        playerGridPane.add(playerNameLabel, 0, 0);
        playerGridPane.add(playerHealthLabel, 0, 1);
        playerGridPane.add(playerGoldLabel, 0, 2);
        playerGridPane.add(playerNameTextField, 1, 0);
        playerGridPane.add(playerHealthTextField, 1, 1);
        playerGridPane.add(playerGoldTextField, 1, 2);
        playerGridPane.add(invalidNameBox, 2, 0);
        playerGridPane.add(invalidHealthBox, 2, 1);
        playerGridPane.add(invalidGoldBox, 2, 2);
        VBox.setMargin(playerGridPane, new Insets(20, 0, 0, 0));
    }

    private void configureInvalidInputBoxes() {

        Label invalidGoldLabel = createInvalidErrorLabel("Invalid gold (Integers over 0 only)");
        invalidGoldBox = new HBox(5);
        invalidGoldBox.getChildren().addAll(createErrorCircle(), invalidGoldLabel);
        invalidGoldBox.setAlignment(Pos.CENTER_LEFT);
        invalidGoldBox.setVisible(false);

        Label invalidHealthLabel = createInvalidErrorLabel("Invalid health (Integers over 0 only)");
        invalidHealthBox = new HBox(5);
        invalidHealthBox.getChildren().addAll(createErrorCircle(), invalidHealthLabel);
        invalidHealthBox.setAlignment(Pos.CENTER_LEFT);
        invalidHealthBox.setVisible(false);

        Label invalidNameLabel = createInvalidErrorLabel("Invalid name");
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
        playerNameTextField.setTextFormatter(restrictTextFieldFormatter(14));
        this.playerNameLabel = new Label("Name:");
        playerNameLabel.setFont(statLabelFont);
    }

    private void configureRow1() {
        this.playerGoldTextField = new TextField();
        playerGoldTextField.setPromptText("Player Gold");
        playerGoldTextField.setMaxWidth(170);
        playerGoldTextField.setFont(textFieldFont);
        playerGoldTextField.setTextFormatter(restrictTextFieldFormatter(9));
        this.playerGoldLabel = new Label("Gold:");
        playerGoldLabel.setFont(statLabelFont);
    }

    private void configureRow2() {
        this.playerHealthTextField = new TextField();
        playerHealthTextField.setPromptText("Player Health");
        playerHealthTextField.setMaxWidth(170);
        playerHealthTextField.setFont(textFieldFont);
        playerHealthTextField.setTextFormatter(restrictTextFieldFormatter(9));
        this.playerHealthLabel = new Label("Health:");
        playerHealthLabel.setFont(statLabelFont);
    }

    private VBox createErrorCircle() {
        ImageView errorCircle = new ImageView(FrontendUtils.errorCircleImage());
        errorCircle.setFitWidth(20);
        errorCircle.setFitHeight(20);
        VBox errorCircleBox = new VBox(errorCircle);
        VBox.setMargin(errorCircle, new Insets(9.2, 0, 0, 0));
        return errorCircleBox;
    }

    private Label createInvalidErrorLabel(String errorMessage) {
        Label label = new Label(errorMessage);
        label.setStyle("-fx-text-fill: #D80D0D;");
        label.setFont(FrontendUtils.pixeloidSans(15));
        label.setAlignment(Pos.CENTER);
        return label;
    }

    private TextFormatter<TextFormatter.Change> restrictTextFieldFormatter(int characters) {
        Pattern pattern = Pattern.compile(".{0," + characters + "}");
        return new TextFormatter<>((change -> pattern.matcher(change.getControlNewText()).matches() ? change : null));
    }

    private void configureTutorialImageView() {
        try {
            tutorialImageView = new ImageView(new Image(new FileInputStream("src/main/resources/img/createPlayerTutorial.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BorderPane.setAlignment(tutorialImageView, Pos.CENTER);
        root.getChildren().add(tutorialImageView);
        tutorialImageView.toBack();
        tutorialImageView.setVisible(false);
    }

    public Label getStoryTitle() {
        return storyTitle;
    }

    public ImageView getInfoButton() {
        return infoButton;
    }

    public ImageView getBackButton() {
        return backButton;
    }

    public Scene getScene() {
        return scene;
    }

    public TextField getPlayerNameTextField() {
        return playerNameTextField;
    }

    public TextField getPlayerHealthTextField() {
        return playerHealthTextField;
    }

    public TextField getPlayerGoldTextField() {
        return playerGoldTextField;
    }

    public HBox getInvalidHealthBox() {
        return invalidHealthBox;
    }

    public HBox getInvalidNameBox() {
        return invalidNameBox;
    }

    public HBox getInvalidGoldBox() {
        return invalidGoldBox;
    }

    public ImageView getNextButton() {
        return nextButton;
    }

    public ImageView getHelpButton() {
        return helpButton;
    }


    public ImageView getTutorialImageView() {
        return tutorialImageView;
    }
}
