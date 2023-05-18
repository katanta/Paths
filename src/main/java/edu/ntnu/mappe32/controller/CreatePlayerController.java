package edu.ntnu.mappe32.controller;

import edu.ntnu.mappe32.model.PathsFile;
import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.view.CreatePlayerView;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;
import java.util.regex.Pattern;

import static edu.ntnu.mappe32.view.PassageView.resizableMainFont;

public class CreatePlayerController {

    private final CreatePlayerView view;
    private final PathsFile pathsFile;
    private final Stage stage;

    public CreatePlayerController(CreatePlayerView view, Stage stage, PathsFile pathsFile) {
        this.stage = stage;
        this.view = view;
        this.pathsFile = pathsFile;
        setActions();
        view.getStoryTitle().setText(pathsFile.getStoryTitle());
        stage.setScene(view.getScene());
    }
    private void setActions() {

        // Set info button tooltip and change image
        Tooltip tooltip = createTooltip("Click here to see Story information...", 18);
        Tooltip.install(view.getInfoButton(), tooltip);
        view.getInfoButton().setOnMouseEntered(mouseEvent -> view.getInfoButton().setImage(view.getInfoButtonHover()));
        view.getInfoButton().setOnMouseExited(mouseEvent -> view.getInfoButton().setImage(view.getInfoButtonImage()));
        showInformationBox();

        // Set back button tooltip and change image
        Tooltip backButtonTooltip = createTooltip("Click to go back to Select Story", 18);
        Tooltip.install(view.getBackButton(), backButtonTooltip);
        view.getBackButton().setOnMouseEntered(mouseEvent -> view.getBackButton().setImage(view.getBackButtonHover()));
        view.getBackButton().setOnMouseExited(mouseEvent -> view.getBackButton().setImage(view.getBackButtonImage()));

        // Set action for when next button is pressed
        view.getNextButton().setOnMouseClicked(mouseEvent -> goToCreateGoals());

        Tooltip nextButtonTooltip = createTooltip("Click to continue", 18);
        Tooltip.install(view.getNextButton(), nextButtonTooltip);

        view.getPlayerHealthTextField().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                goToCreateGoals();
            }
        });
        view.getPlayerGoldTextField().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                goToCreateGoals();
            }
        });
        view.getPlayerNameTextField().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                goToCreateGoals();
            }
        });
    }
    private Tooltip createTooltip(String text, int fontSize) {
        Tooltip tooltip = new Tooltip(text);
        tooltip.setShowDelay(Duration.ZERO);
        tooltip.setShowDuration(Duration.INDEFINITE);
        tooltip.setFont(resizableMainFont(fontSize));
        return tooltip;
    }

    /**
     * This method sends the user to CreateGoalsView
     */
    private void goToCreateGoals() {
        if (validateGoldInput() & validateHealthInput() & validatePlayerNameInput()) {
            Player player = createPlayer();
        }
    }

    /**
     * This method creates a player based on the input from the textfields.
     * @return The game's player based on the input, as Player
     */
    private Player createPlayer() {
        int playerGold = 0;

        if (!view.getPlayerGoldTextField().getText().isBlank())
            playerGold = Integer.parseInt(view.getPlayerGoldTextField().getText());

        int playerHealth = Integer.parseInt(view.getPlayerHealthTextField().getText());
        String playerName = view.getPlayerNameTextField().getText();
        return new Player.PlayerBuilder(playerName, playerHealth).gold(playerGold).build();
    }

    /**
     * This method sets border color of textfields to red
     * @param textField Input textfield, as TextField
     */
    private void setInvalidTextFieldStyle(TextField textField) {
        String borderColor = "red;";
        String borderWidth = "1px;";
        textField.setStyle("-fx-border-color: " + borderColor +
                "-fx-border-width: " + borderWidth);
    }

    /**
     * This method validates the input of the playerHealthTextField
     * It returns false if the input contains other characters than numbers or is empty
     * Otherwise, it returns true
     * @return false if input contains other characters than numbers or is empty, as boolean
     *         true otherwise, as boolean
     */
    private boolean validateHealthInput() {
        String numbersOnlyRegex = "^[0-9]+$";
        if (!Pattern.matches(numbersOnlyRegex, view.getPlayerHealthTextField().getText())
        ) {
            setInvalidTextFieldStyle(view.getPlayerHealthTextField());
            view.getInvalidHealthBox().setVisible(true);
            return false;
        }
        view.getPlayerHealthTextField().setStyle(null);
        view.getInvalidHealthBox().setVisible(false);
        return true;
    }

    /**
     * This method validates the input of the playerNameTextField
     * It returns false if the input is blank
     * Otherwise, it returns true
     * @return false if input is blank, as boolean
     *         true otherwise, as boolean
     */
    private boolean validatePlayerNameInput() {
        if (view.getPlayerNameTextField().getText().isBlank()) {
            setInvalidTextFieldStyle(view.getPlayerNameTextField());
            view.getInvalidNameBox().setVisible(true);
            return false;
        }
        view.getPlayerNameTextField().setStyle(null);
        view.getInvalidNameBox().setVisible(false);
        return true;
    }
    /**
     * This method validates the input of the playerGoldTextField
     * It returns false if the input contains other characters than numbers
     * It returns true if the input is blank, or contains only number
     * @return false if input contains other characters than numbers, as boolean
     *         true if the input is blank or contains only number, as boolean
     */
    private boolean validateGoldInput() {
        String numbersOnlyOrBlankRegex = "^$|^[0-9]+$";
        if (!Pattern.matches(numbersOnlyOrBlankRegex, view.getPlayerGoldTextField().getText())) {
            setInvalidTextFieldStyle(view.getPlayerGoldTextField());
            view.getInvalidGoldBox().setVisible(true);
            return false;
        }
        view.getPlayerGoldTextField().setStyle(null);
        view.getInvalidGoldBox().setVisible(false);
        return true;
    }
    private void showInformationBox() {
        view.getInfoButton().setOnMouseClicked(mouseEvent -> {
            Alert information = new Alert(Alert.AlertType.INFORMATION);
            information.setTitle("Story Information");
            information.setHeaderText("Information on '" + pathsFile.getStoryTitle() + "'");
            information.setContentText("Broken Links: " + pathsFile.getBrokenLinks() + "\n\nFile Path: " + pathsFile.getFilePath());
            DialogPane dialogPane = information.getDialogPane();
            dialogPane.getStylesheets().add(
                    Objects.requireNonNull(getClass().getResource("/StyleSheets/DialogBoxStyleSheet.css")).toExternalForm());
            information.show();
        });
    }
}
