package edu.ntnu.mappe32.view;

import edu.ntnu.mappe32.model.PathsFile;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CreatePlayerAndGoalsView {
    Scene scene;
    GridPane playerGridPane;
    Label storyTitleLabel;
    Label pathsFileLabel;
    Label playerConfigurationLabel;
    Label playerHealthLabel;
    Label playerGoldLabel;
    Label playerNameLabel;
    TextField playerNameTextField;
    TextField playerHealthTextField;
    TextField playerGoldTextField;
    Label addGoalLabel;
    PathsFile pathsFile;
    ToggleGroup goalTypeToggle;
    ToggleButton goldToggle;
    ToggleButton healthToggle;
    ToggleButton inventoryToggle;
    ToggleButton scoreToggle;
    TextField goalValueTextField;
    Label numberValueGoalLabel;
    ImageView playButton;


    public CreatePlayerAndGoalsView(PathsFile pathsFile) {
        this.pathsFile = pathsFile;
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

        HBox topBar = new HBox(10);
        topBar.getChildren().addAll(storyTitleLabel, pathsFileLabel);
        HBox goalTypeToggleBar = new HBox();
        goalTypeToggleBar.getChildren().addAll(goldToggle, healthToggle, inventoryToggle, scoreToggle   );
        HBox goalValueBar = new HBox(10);
        goalValueBar.getChildren().addAll(numberValueGoalLabel, goalValueTextField);
        VBox playerAndGoalConfigurationBox = new VBox(10);
        playerAndGoalConfigurationBox.getChildren().addAll(topBar,
                playerConfigurationLabel, playerGridPane, addGoalLabel,
                goalTypeToggleBar, goalValueBar, playButton);

        StackPane root = new StackPane();
        root.getChildren().addAll(playerAndGoalConfigurationBox);
        this.scene = new Scene(root, 1280, 720);

    }
    public void configureStoryTitleLabel() {
        this.storyTitleLabel = new Label(pathsFile.getStoryTitle());
        storyTitleLabel.setFont(new Font("Times New Roman", 40));
        HBox.setMargin(storyTitleLabel, new Insets(10));

    }
    public void configurePlayerNameLabel() {
        this.playerNameLabel = new Label("Player Name:");
        playerNameLabel.setFont(new Font("Times New Roman", 20));
    }
    public void configurePlayerHealthLabel() {
        this.playerHealthLabel = new Label("Player Health:");
        playerHealthLabel.setFont(new Font("Times New Roman", 20));
    }
    public void configurePlayerGoldLabel() {
        this.playerGoldLabel = new Label("Player Gold:");
        playerGoldLabel.setFont(new Font("Times New Roman", 20));
    }
    public void configureAddGoalLabel() {
        this.addGoalLabel = new Label("Add Goals:");
        addGoalLabel.setFont(new Font("Times New Roman", 20));
        VBox.setMargin(addGoalLabel, new Insets(10));
    }


    public void configureNumberValueGoalLabel() {
        this.numberValueGoalLabel = new Label();
        numberValueGoalLabel.setFont(new Font("Times New Roman", 20));
        numberValueGoalLabel.setManaged(false);
        numberValueGoalLabel.setVisible(false);
        HBox.setMargin(numberValueGoalLabel, new Insets(10));

    }
    public void configureGoalTypeToggleButton() {

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

    public void configurePlayerGridPane() {
        playerGridPane = new GridPane();
        playerGridPane.setHgap(10);
        playerGridPane.setVgap(10);
        playerGridPane.add(playerNameLabel,0,0);
        playerGridPane.add(playerGoldLabel,0,1);
        playerGridPane.add(playerHealthLabel,0,2);
        playerGridPane.add(playerNameTextField,1,0);
        GridPane.setHgrow(playerNameTextField, Priority.ALWAYS);
        playerGridPane.add(playerGoldTextField, 1,1);
        GridPane.setHgrow(playerGoldTextField, Priority.ALWAYS);
        playerGridPane.add(playerHealthTextField, 1,2);
        GridPane.setHgrow(playerHealthTextField, Priority.ALWAYS);
        VBox.setMargin(playerGridPane, new Insets(10));
    }
    public void configurePlayerNameTextField() {
        this.playerNameTextField = new TextField();
        playerNameTextField.setPromptText("Player Name");
        playerNameTextField.setMaxWidth(200);
    }
    public void configureGoalValueTextField() {
        this.goalValueTextField = new TextField();
        goalValueTextField.setPromptText("Enter the value of the goal");
        goalValueTextField.setMaxWidth(200);
        goalValueTextField.setVisible(false);
        goalValueTextField.setManaged(false);
        HBox.setMargin(goalValueTextField, new Insets(10));
    }
    public void configurePlayerGoldTextField() {
        this.playerGoldTextField = new TextField();
        playerGoldTextField.setPromptText("Player Gold");
        playerGoldTextField.setMaxWidth(200);
    }
    public void configurePlayerHealthTextField() {
        this.playerHealthTextField = new TextField();
        playerHealthTextField.setPromptText("Player Health");
        playerHealthTextField.setMaxWidth(200);
    }
    public void configurePlayerConfigurationLabel() {
        this.playerConfigurationLabel = new Label("Player Configuration");
        playerConfigurationLabel.setFont(new Font("Times New Roman", 20));
        VBox.setMargin(playerConfigurationLabel, new Insets(10));
    }
    public void configurePathsFileLabelInfo() {
        this.pathsFileLabel = new Label("Broken Links: "
                + pathsFile.getBrokenLinks() + "\nFile Path: "
                + pathsFile.getFilePath());
        this.pathsFileLabel.setMaxWidth(600);
        this.pathsFileLabel.setWrapText(true);
        HBox.setMargin(pathsFileLabel, new Insets(10));
    }
    public void configurePlayButton() {

        try {
            playButton = new ImageView(new Image(new FileInputStream("src/main/resources/img/play.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("play.png could not be fount in src/main/resources/img");
        }
        playButton.setPickOnBounds(true);
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
}
