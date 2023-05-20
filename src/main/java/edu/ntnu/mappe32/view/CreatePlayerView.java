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

import static edu.ntnu.mappe32.view.PassageView.resizableMainFont;

public class CreatePlayerView {

    private final Scene scene;
    private HBox top;
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
    private Image infoButtonImage;
    private Image backButtonImage;
    private ImageView nextButton;
    private final Font statLabelFont;
    private final Font textFieldFont;
    public CreatePlayerView() {
        configureErrorCircleImage();

        statLabelFont = resizableMainFont(30);
        textFieldFont = Font.loadFont("file:src/main/resources/fonts/PixeloidSans.ttf", 20);
        configureTop();
        configureCenter();

        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setCenter(centerVBox);
        root.getChildren().add(backButton);
        this.scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add(getClass().getResource("/StyleSheets/DialogBoxStyleSheet.css").toExternalForm());

    }
    private void configureTop() {
        top = new HBox(50);

        try {
            backButtonImage = new Image(new FileInputStream("src/main/resources/img/restartButton.png"));
            backButtonHover = new Image(new FileInputStream("src/main/resources/img/restartButtonHover.png"));
            infoButtonImage = new Image(new FileInputStream("src/main/resources/img/info.png"));
            infoButtonHover = new Image(new FileInputStream("src/main/resources/img/info_hover.png"));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        backButton = new ImageView(backButtonImage);
        infoButton = new ImageView(infoButtonImage);
        backButton.setFitWidth(62);
        backButton.setFitHeight(62);
        backButton.setX(20);
        backButton.setY(20);
        backButton.setPickOnBounds(true);
        infoButton.setPickOnBounds(true);
        infoButton.setFitWidth(31);
        infoButton.setFitHeight(31);

        storyTitle = new Label();
        storyTitle.setFont(Font.loadFont("file:src/main/resources/fonts/PixeloidSansBold.ttf", 50));
        storyTitle.setMaxWidth(700);

        top.getChildren().addAll(storyTitle, infoButton);
        top.setAlignment(Pos.CENTER);

        top.setPadding(new Insets(20, 0,0,0));

    }

    private void configureCenter() {
        centerVBox = new VBox(50);
        centerVBox.setPadding(new Insets(30,0,0,0));
        centerVBox.setAlignment(Pos.TOP_CENTER);
        Label chooseYourStats = new Label("Choose Your Stats");
        chooseYourStats.setFont(resizableMainFont(40));
        chooseYourStats.setAlignment(Pos.CENTER);

        try {
            nextButton = new ImageView(new Image(new FileInputStream("src/main/resources/img/next_button.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        configurePlayerStatsGridPane();
        playerGridPane.setPadding(new Insets(0, 0,0,330));
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
        errorCircle.setFitWidth(20);
        errorCircle.setFitHeight(20);
        VBox errorCircleBox = new VBox(errorCircle);
        VBox.setMargin(errorCircle, new Insets(9.2,0,0, 0));
        return errorCircleBox;
    }
    private void configureErrorCircleImage() {
        try {
            errorCircleImage = new Image(new FileInputStream("src/main/resources/img/error-circle-solid-24.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private Label createInvalidErrorLabel(String errorMessage) {
        Label label = new Label(errorMessage);
        label.setStyle("-fx-text-fill: #D80D0D;");
        label.setFont(resizableMainFont(15));
        label.setAlignment(Pos.CENTER);
        return label;
    }
    private TextFormatter<TextFormatter.Change> maxNineCharactersFormatter() {
        Pattern pattern = Pattern.compile(".{0,9}");
        return new TextFormatter<>((change -> pattern.matcher(change.getControlNewText()).matches() ? change : null));
    }

    public Image getBackButtonImage() {
        return backButtonImage;
    }

    public Label getStoryTitle() {
        return storyTitle;
    }
    public ImageView getInfoButton() {
        return infoButton;
    }

    public Image getInfoButtonHover() {
        return infoButtonHover;
    }

    public Image getBackButtonHover() {
        return backButtonHover;
    }

    public ImageView getBackButton() {
        return backButton;
    }

    public Image getInfoButtonImage() {
        return infoButtonImage;
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
}
