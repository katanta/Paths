package edu.ntnu.mappe32.controller;

import edu.ntnu.mappe32.model.PathsFile;
import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.view.CreateGoalsView;
import edu.ntnu.mappe32.view.CreatePlayerView;
import edu.ntnu.mappe32.view.StorySelectorView;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    public CreatePlayerController(CreatePlayerView view, Stage stage, PathsFile pathsFile, Player player) {
        this.stage = stage;
        this.view = view;
        this.pathsFile = pathsFile;
        setActions();
        view.getStoryTitle().setText(pathsFile.getStoryTitle());
        stage.setScene(view.getScene());

        if (player != null) {
            view.getPlayerGoldTextField().setText(String.valueOf(player.getGold()));
            view.getPlayerHealthTextField().setText(String.valueOf(player.getHealth()));
            view.getPlayerNameTextField().setText(player.getName());
        }
    }

    private void setActions() {

        //Info button
        setButtonTooltip(view.getInfoButton(), "Click here to see Story information...");
        view.getInfoButton().setOnMouseEntered(mouseEvent -> setButtonImage(view.getInfoButton(), view.getInfoButtonHover()));
        view.getInfoButton().setOnMouseExited(mouseEvent -> setButtonImage(view.getInfoButton(), view.getInfoButtonImage()));
        showInformationBox();

        //Back button
        setButtonTooltip(view.getBackButton(), "Click to go back to Select Story");
        view.getBackButton().setOnMouseEntered(mouseEvent -> setButtonImage(view.getBackButton(), view.getBackButtonHover()));
        view.getBackButton().setOnMouseExited(mouseEvent -> setButtonImage(view.getBackButton(), view.getBackButtonImage()));
        view.getBackButton().setOnMouseClicked(mouseEvent -> new GameSetupController(stage, new StorySelectorView()));
        view.getNextButton().setOnMouseClicked(mouseEvent -> goToCreateGoals());
        setButtonTooltip(view.getNextButton(), "Click to continue");

        //Help button
        setHelpButtonClickAction();
        view.getHelpButton().setOnMouseEntered(mouseEvent -> {
            setButtonImage(view.getHelpButton(), view.getHelpButtonHover());
            setButtonTooltip(view.getHelpButton(), "Need help? Press me! :)");
        });

        view.getHelpButton().setOnMouseExited(mouseEvent -> setButtonImage(view.getHelpButton(), view.getHelpButtonImage()));
        setKeyPressedAction(view.getPlayerHealthTextField(), this::goToCreateGoals);
        setKeyPressedAction(view.getPlayerGoldTextField(), this::goToCreateGoals);
        setKeyPressedAction(view.getPlayerNameTextField(), this::goToCreateGoals);
    }

    private void setHelpButtonClickAction() {
        view.getHelpButton().setOnMouseClicked(e -> {
            view.getTutorialImageView().toFront();
            view.getTutorialImageView().setVisible(true);
            view.getTutorialImageView().setOnMouseClicked(e1 -> {
                view.getTutorialImageView().toBack();
                view.getTutorialImageView().setVisible(false);
            });
        });
    }

    private void setButtonTooltip(ImageView button, String text) {
        Tooltip tooltip = createTooltip(text);
        Tooltip.install(button, tooltip);
    }

    private void setButtonImage(ImageView button, Image image) {
        button.setImage(image);
    }

    private void setKeyPressedAction(TextField textField, Runnable action) {
        textField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                action.run();
            }
        });
    }

    private Tooltip createTooltip(String text) {
        Tooltip tooltip = new Tooltip(text);
        tooltip.setShowDelay(Duration.ZERO);
        tooltip.setShowDuration(Duration.INDEFINITE);
        tooltip.setFont(resizableMainFont(18));
        return tooltip;
    }

    /**
     * This method sends the user to CreateGoalsView
     */
    private void goToCreateGoals() {
        if (validateGoldInput() & validateHealthInput() & validatePlayerNameInput()) {
            CreateGoalsController createGoalsController = new CreateGoalsController(stage, new CreateGoalsView(),
                    createPlayer(), pathsFile);
        }
    }

    /**
     * This method creates a player based on the input from the textfields.
     *
     * @return The game's player based on the input, as Player
     */
    private Player createPlayer() {
        int playerGold = 0;
        String playerGoldText = view.getPlayerGoldTextField().getText();
        if (!playerGoldText.isBlank())
            playerGold = Integer.parseInt(playerGoldText);


        int playerHealth = Integer.parseInt(view.getPlayerHealthTextField().getText());
        String playerName = view.getPlayerNameTextField().getText();

        return new Player.PlayerBuilder(playerName, playerHealth)
                .gold(playerGold)
                .build();
    }

    /**
     * This method sets border color of textfields to red
     *
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
     *
     * @return false if input contains other characters than numbers or is empty, as boolean
     * true otherwise, as boolean
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
     *
     * @return false if input is blank, as boolean
     * true otherwise, as boolean
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
     *
     * @return false if input contains other characters than numbers, as boolean
     * true if the input is blank or contains only number, as boolean
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
