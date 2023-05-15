package edu.ntnu.mappe32.controller;

import edu.ntnu.mappe32.io.PathsFileReader;
import edu.ntnu.mappe32.model.PathsFile;
import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.model.goal_related.Goal;
import edu.ntnu.mappe32.model.goal_related.GoldGoal;
import edu.ntnu.mappe32.model.goal_related.HealthGoal;
import edu.ntnu.mappe32.model.goal_related.ScoreGoal;
import edu.ntnu.mappe32.model.story_related.Story;
import edu.ntnu.mappe32.view.CreatePlayerAndGoalsView;
import edu.ntnu.mappe32.view.PassageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.HashMap;
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
            if (validateGoldInput() & validateHealthInput() & validatePlayerNameInput()) {
                Player player = createPlayer();
                Game game = new Game(player, pathsFile.getStory(), createdGoals);
                PassageView passageView = new PassageView(game);
                try {
                    stage.setScene(passageView.getScene());
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
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

        return new Player(playerName, playerHealth, 0, playerGold, new HashMap<>());
    }

    /**
     * This method sets border color of textfields to red
     * @param textField Input textfield, as TextField
     */
    private void setInvalidTextFieldStyle(TextField textField) {
        String borderColor = "red;";
        String borderWidth = "0.3px;";
        textField.setStyle("-fx-border-color: " + borderColor +
                "-fx-border-width:" + borderWidth);
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
            return false;
        }
        view.getPlayerHealthTextField().setStyle(null);
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
            return false;
        }
        view.getPlayerNameTextField().setStyle(null);
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
            return false;
        }
        view.getPlayerGoldTextField().setStyle(null);
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
