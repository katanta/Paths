package edu.ntnu.mappe32.controller;

import edu.ntnu.mappe32.model.Game;
import edu.ntnu.mappe32.model.PathsFile;
import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.model.goal_related.Goal;
import edu.ntnu.mappe32.model.goal_related.GoldGoal;
import edu.ntnu.mappe32.model.goal_related.HealthGoal;
import edu.ntnu.mappe32.model.goal_related.ScoreGoal;
import edu.ntnu.mappe32.view.CreatePlayerAndGoalsView;
import edu.ntnu.mappe32.view.PassageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class CreatePlayerAndGoalsController {
    private final static String fontFamily = "Times New Roman";
    private final static int fontSize = 20;
    private final CreatePlayerAndGoalsView view;
    private final ObservableList<Goal> createdGoals;
    public CreatePlayerAndGoalsController(Stage stage, CreatePlayerAndGoalsView view, PathsFile pathsFile) {
        this.view = view;
        this.createdGoals = FXCollections.observableArrayList();
        view.getGoalsListView().setItems(createdGoals);


        view.getGoldToggle().setOnAction(actionEvent ->  {
            view.getGoalValueTextField().clear();
            view.getGoalValueTextField().setStyle(null);
            if (view.getGoalValueTextField().isVisible() || view.getNumberValueGoalLabel().isVisible()
                    && !view.getNumberValueGoalLabel().getText().equals("Gold Goal:")) {
                view.getNumberValueGoalLabel().setText("Gold Goal:");
                view.getNumberValueGoalLabel().setFont(new Font(fontFamily, fontSize));
                view.getNumberValueGoalLabel().setVisible(true);
                view.getNumberValueGoalLabel().setManaged(true);
                view.getAddGoalButton().setVisible(true);
                view.getAddGoalButton().setManaged(true);
                return;
            }
            view.getNumberValueGoalLabel().setVisible(false);
            view.getNumberValueGoalLabel().setManaged(false);
            view.getAddGoalButton().setVisible(false);
            view.getAddGoalButton().setManaged(false);
        });
        view.getHealthToggle().setOnAction(actionEvent ->  {
            view.getGoalValueTextField().clear();
            view.getGoalValueTextField().setStyle(null);
            if (view.getGoalValueTextField().isVisible() || view.getNumberValueGoalLabel().isVisible()
                    && !view.getNumberValueGoalLabel().getText().equals("Health Goal:")) {
                view.getNumberValueGoalLabel().setText("Health Goal:");
                view.getNumberValueGoalLabel().setFont(new Font(fontFamily, fontSize));
                view.getNumberValueGoalLabel().setVisible(true);
                view.getNumberValueGoalLabel().setManaged(true);
                view.getAddGoalButton().setVisible(true);
                view.getAddGoalButton().setManaged(true);
                return;
            }
            view.getNumberValueGoalLabel().setVisible(false);
            view.getNumberValueGoalLabel().setManaged(false);
            view.getAddGoalButton().setVisible(false);
            view.getAddGoalButton().setManaged(false);
        });
        view.getScoreToggle().setOnAction(actionEvent ->  {
            view.getGoalValueTextField().clear();
            view.getGoalValueTextField().setStyle(null);
            if (view.getGoalValueTextField().isVisible() || view.getNumberValueGoalLabel().isVisible()
                    && !view.getNumberValueGoalLabel().getText().equals("Score Goal:")) {
                view.getNumberValueGoalLabel().setText("Score Goal:");
                view.getNumberValueGoalLabel().setFont(new Font(fontFamily, fontSize));
                view.getNumberValueGoalLabel().setVisible(true);
                view.getNumberValueGoalLabel().setManaged(true);
                view.getAddGoalButton().setVisible(true);
                view.getAddGoalButton().setManaged(true);
                return;
            }
            view.getNumberValueGoalLabel().setVisible(false);
            view.getNumberValueGoalLabel().setManaged(false);
            view.getAddGoalButton().setVisible(false);
            view.getAddGoalButton().setManaged(false);
        });

        view.getGoalTypeToggle().selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            Toggle selectedToggle = view.getGoalTypeToggle().getSelectedToggle();
            if (selectedToggle == view.getInventoryToggle() || selectedToggle  == null) {
                view.getGoalValueTextField().setVisible(false);
                view.getGoalValueTextField().setManaged(false);
                view.getNumberValueGoalLabel().setVisible(false);
                view.getNumberValueGoalLabel().setManaged(false);
                return;
            }
            view.getGoalValueTextField().setManaged(true);
            view.getGoalValueTextField().setVisible(true);
        });
        view.getPlayButton().setOnMouseClicked(mouseEvent -> {
            if (createdGoals.isEmpty()) {
                Alert noGoalsAlert = new Alert(Alert.AlertType.WARNING);
                noGoalsAlert.setTitle("No Goals");
                noGoalsAlert.setContentText("A game must have a list of goals with atleast one goal");
                noGoalsAlert.setHeaderText("You have not made any goals!");
                noGoalsAlert.showAndWait();
            }
            if (validateGoldInput() & validateHealthInput() & validatePlayerNameInput()) {
                if (createdGoals.isEmpty())
                    return;
                Player player = createPlayer();
                Game game = new Game(player, pathsFile.getStory(), createdGoals);
                PassageView passageView = new PassageView();
                PassageViewController passageViewController = new PassageViewController(stage, passageView, game);
                stage.setScene(passageView.getScene());
            }
        });

        view.getAddGoalButton().setOnMouseClicked(mouseEvent -> {
            Toggle selectedToggle = view.getGoalTypeToggle().getSelectedToggle();
            if (validateGoalValueInput()) {
                if (selectedToggle == view.getScoreToggle()) {
                    createdGoals.add(new ScoreGoal(Integer.parseInt(view.getGoalValueTextField().getText())));
                }
                if (selectedToggle == view.getHealthToggle()) {
                    createdGoals.add(new HealthGoal(Integer.parseInt(view.getGoalValueTextField().getText())));
                }
                if (selectedToggle == view.getGoldToggle()) {
                    createdGoals.add(new GoldGoal(Integer.parseInt(view.getGoalValueTextField().getText())));
                }
            }
        });
        view.getGoalsListView().setOnMouseClicked(mouseEvent ->
            createdGoals.remove(view.getGoalsListView().getSelectionModel().getSelectedItem()));
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
    private boolean validateGoalValueInput() {
        String numbersOnlyRegex = "^[0-9]+$";
        if (!Pattern.matches(numbersOnlyRegex, view.getGoalValueTextField().getText())) {
            setInvalidTextFieldStyle(view.getGoalValueTextField());
            return false;
        }
        view.getGoalValueTextField().setStyle(null);
        return true;
    }
}
