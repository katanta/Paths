package edu.ntnu.mappe32.view;

import edu.ntnu.mappe32.model.PathsFile;
import edu.ntnu.mappe32.model.goal_related.Goal;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;

public class CreatePlayerAndGoalsView {
    private final Scene scene;
    private GridPane playerGridPane;
    private Label storyTitleLabel;
    private Label pathsFileLabel;
    private Label playerConfigurationLabel;
    private Label playerHealthLabel;
    private Label playerGoldLabel;
    private Label playerNameLabel;
    private TextField playerNameTextField;
    private TextField playerHealthTextField;
    private TextField playerGoldTextField;
    private Label addGoalLabel;
    private final PathsFile pathsFile;
    private ToggleGroup goalTypeToggle;
    private ToggleButton goldToggle;
    private ToggleButton healthToggle;
    private ToggleButton inventoryToggle;
    private ToggleButton scoreToggle;
    private TextField goalValueTextField;
    private Label numberValueGoalLabel;
    private ImageView playButton;
    private ListView<Goal> goalsListView;
    private Button addGoalButton;
    private HBox invalidNameBox;
    private HBox invalidGoldBox;
    private HBox invalidHealthBox;
    private Image errorCircleImage;
    private final Font labelFont = new Font("Times New Roman", 16);


    public CreatePlayerAndGoalsView(PathsFile pathsFile) {
        this.pathsFile = pathsFile;
        configureErrorCircleImage();
        configureStoryTitleLabel();
        configurePathsFileLabelInfo();
        configurePlayerConfigurationLabel();
        configurePlayerNameTextField();
        configurePlayerGoldTextField();
        configurePlayerHealthTextField();
        configurePlayerGoldLabel();
        configurePlayerHealthLabel();
        configurePlayerNameLabel();
        configurePlayerGridPane();
        configureAddGoalLabel();
        configureGoalTypeToggleButton();
        configureGoalValueTextField();
        configureNumberValueGoalLabel();
        configurePlayButton();
        configureGoalsListView();
        configureAddGoalButton();

        VBox topBar = new VBox(10);
        topBar.getChildren().addAll(storyTitleLabel, pathsFileLabel);
        HBox goalTypeToggleBar = new HBox();
        goalTypeToggleBar.getChildren().addAll(goldToggle, healthToggle, inventoryToggle, scoreToggle);
        HBox goalValueBar = new HBox();
        goalValueBar.getChildren().addAll(numberValueGoalLabel, goalValueTextField, addGoalButton);
        VBox playerAndGoalConfigurationBox = new VBox(10);
        playerAndGoalConfigurationBox.getChildren().addAll(
                playerConfigurationLabel, playerGridPane, addGoalLabel,
                goalTypeToggleBar, goalValueBar, playButton, goalsListView);

        BorderPane root = new BorderPane();
        root.setCenter(playerAndGoalConfigurationBox);
        VBox vBox = new VBox();
        vBox.setPrefWidth(400);
        root.setTop(topBar);
        topBar.setAlignment(Pos.CENTER);
        root.setLeft(vBox);
        this.scene = new Scene(root, 1280, 720);

    }
    private void configureErrorCircleImage() {
        try {
            errorCircleImage = new Image(new FileInputStream("src/main/resources/img/error-circle-solid-24.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void configureStoryTitleLabel() {
        this.storyTitleLabel = new Label(pathsFile.getStoryTitle());
        storyTitleLabel.setFont(labelFont);
        HBox.setMargin(storyTitleLabel, new Insets(10));

    }
    private void configureAddGoalButton() {
        this.addGoalButton = new Button("Add Goal");
        addGoalButton.setVisible(false);
        addGoalButton.setManaged(false);
        addGoalButton.setTooltip(new Tooltip("Press to add goal"));
        HBox.setMargin(addGoalButton, new Insets(10));
        System.out.println(pathsFile.getStory().getItems());

    }
    private void configurePlayerNameLabel() {
        this.playerNameLabel = new Label("Player Name:");
        playerNameLabel.setFont(labelFont);
    }
    private void configurePlayerHealthLabel() {
        this.playerHealthLabel = new Label("Player Health:");
        playerHealthLabel.setFont(labelFont);
    }
    private void configurePlayerGoldLabel() {
        this.playerGoldLabel = new Label("Player Gold:");
        playerGoldLabel.setFont(labelFont);
    }
    private void configureAddGoalLabel() {
        this.addGoalLabel = new Label("Add Goals:");
        addGoalLabel.setFont(labelFont);
        VBox.setMargin(addGoalLabel, new Insets(10));
    }


    private void configureNumberValueGoalLabel() {
        this.numberValueGoalLabel = new Label();
        numberValueGoalLabel.setFont(labelFont);
        numberValueGoalLabel.setManaged(false);
        numberValueGoalLabel.setVisible(false);
        HBox.setMargin(numberValueGoalLabel, new Insets(10));

    }
    private void configureGoalTypeToggleButton() {

        this.goalTypeToggle = new ToggleGroup();
        goldToggle = new ToggleButton("Gold");
        goldToggle.setToggleGroup(goalTypeToggle);
        goldToggle.setPrefWidth(75);
        HBox.setMargin(goldToggle, new Insets(5));
        healthToggle = new ToggleButton("Health");
        healthToggle.setToggleGroup(goalTypeToggle);
        healthToggle.setPrefWidth(75);
        HBox.setMargin(healthToggle, new Insets(5));
        inventoryToggle = new ToggleButton("Inventory");
        inventoryToggle.setToggleGroup(goalTypeToggle);
        inventoryToggle.setPrefWidth(75);
        HBox.setMargin(inventoryToggle, new Insets(5));
        scoreToggle = new ToggleButton("Score");
        scoreToggle.setToggleGroup(goalTypeToggle);
        scoreToggle.setPrefWidth(75);
        HBox.setMargin(scoreToggle, new Insets(5));
    }

    private void configurePlayerGridPane() {
        configureInvalidInputBoxes();
        playerGridPane = new GridPane();
        playerGridPane.setHgap(10);
        playerGridPane.setVgap(10);
        playerGridPane.add(playerNameLabel,0,0);
        playerGridPane.add(playerHealthLabel,0,1);
        playerGridPane.add(playerGoldLabel,0,2);
        playerGridPane.add(playerNameTextField,1,0);
        playerGridPane.add(playerHealthTextField, 1,1);
        playerGridPane.add(playerGoldTextField, 1,2);
        playerGridPane.add(invalidNameBox, 2,0);
        playerGridPane.add(invalidHealthBox,2,1);
        playerGridPane.add(invalidGoldBox, 2, 2);
        VBox.setMargin(playerGridPane, new Insets(10));
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
    private void configurePlayerNameTextField() {
        this.playerNameTextField = new TextField();
        playerNameTextField.setPromptText("Player Name");
        playerNameTextField.setMaxWidth(200);
    }
    private void configureGoalValueTextField() {
        this.goalValueTextField = new TextField();
        goalValueTextField.setPromptText("Enter the value of the goal");
        goalValueTextField.setMaxWidth(200);
        goalValueTextField.setVisible(false);
        goalValueTextField.setManaged(false);
        goalValueTextField.setTextFormatter(maxNineCharactersFormatter());
        HBox.setMargin(goalValueTextField, new Insets(10));
    }
    private void configurePlayerGoldTextField() {
        this.playerGoldTextField = new TextField();
        playerGoldTextField.setPromptText("Player Gold");
        playerGoldTextField.setMaxWidth(200);
        playerGoldTextField.setTextFormatter(maxNineCharactersFormatter());
    }
    private void configurePlayerHealthTextField() {
        this.playerHealthTextField = new TextField();
        playerHealthTextField.setPromptText("Player Health");
        playerHealthTextField.setMaxWidth(200);
        playerHealthTextField.setTextFormatter(maxNineCharactersFormatter());
    }
    private void configurePlayerConfigurationLabel() {
        this.playerConfigurationLabel = new Label("Player Configuration");
        playerConfigurationLabel.setFont(labelFont);
        VBox.setMargin(playerConfigurationLabel, new Insets(10));
    }
    private void configurePathsFileLabelInfo() {
        this.pathsFileLabel = new Label("Broken Links: "
                + pathsFile.getBrokenLinks() + "\nFile Path: "
                + pathsFile.getFilePath());
        this.pathsFileLabel.setMaxWidth(600);
        this.pathsFileLabel.setWrapText(true);
        HBox.setMargin(pathsFileLabel, new Insets(10));
    }
    private void configurePlayButton() {
        try {
            playButton = new ImageView(new Image(new FileInputStream("src/main/resources/img/play.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("play.png could not be fount in src/main/resources/img");
        }
        playButton.setPickOnBounds(true);
    }
    private void configureGoalsListView() {
        this.goalsListView = new ListView<>();
        goalsListView.setPrefWidth(100);
        goalsListView.setPrefHeight(300);
        goalsListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Goal goal, boolean empty) {
                super.updateItem(goal, empty);
                if (goal == null || empty) {
                    setText("");
                    setStyle("");
                } else {
                    GoalCell cell = new GoalCell(goal);
                    setGraphic(cell.getHBox());
                    setTooltip(new Tooltip("Click to remove '" + goal.goalType() + " Goal: " + goal.goalValue() + "'"));
                }
                }
        });
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
    private TextFormatter<TextFormatter.Change> maxNineCharactersFormatter() {
        Pattern pattern = Pattern.compile(".{0,9}");
        return new TextFormatter<>((change -> pattern.matcher(change.getControlNewText()).matches() ? change : null));
    }
    public Label getNumberValueGoalLabel() {
        return numberValueGoalLabel;
    }

    public ToggleButton getGoldToggle() {
        return goldToggle;
    }

    public ToggleGroup getGoalTypeToggle() {
        return goalTypeToggle;
    }

    public ToggleButton getScoreToggle() {
        return scoreToggle;
    }

    public ToggleButton getHealthToggle() {
        return healthToggle;
    }

    public TextField getGoalValueTextField() {
        return goalValueTextField;
    }

    public ToggleButton getInventoryToggle() {
        return inventoryToggle;
    }

    public ImageView getPlayButton() {
        return playButton;
    }

    public Scene getScene() {
        return scene;
    }
    public TextField getPlayerGoldTextField() {
        return playerGoldTextField;
    }

    public TextField getPlayerHealthTextField() {
        return playerHealthTextField;
    }

    public TextField getPlayerNameTextField() {
        return playerNameTextField;
    }

    public Button getAddGoalButton() {
        return addGoalButton;
    }

    public ListView<Goal> getGoalsListView() {
        return goalsListView;
    }
    public HBox getInvalidGoldBox() {
        return invalidGoldBox;
    }
    public HBox getInvalidNameBox() {
        return invalidNameBox;
    }
    public HBox getInvalidHealthBox() {
        return invalidHealthBox;
    }
}
