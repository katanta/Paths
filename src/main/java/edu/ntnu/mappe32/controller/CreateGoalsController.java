package edu.ntnu.mappe32.controller;

import edu.ntnu.mappe32.model.PathsFile;
import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.model.goal_related.Goal;
import edu.ntnu.mappe32.model.goal_related.GoldGoal;
import edu.ntnu.mappe32.model.goal_related.HealthGoal;
import edu.ntnu.mappe32.model.goal_related.ScoreGoal;
import edu.ntnu.mappe32.view.CreateGoalsView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.regex.Pattern;

import static edu.ntnu.mappe32.view.PassageView.resizableMainFont;

public class CreateGoalsController {
    private final PathsFile pathsFile;
    private final CreateGoalsView view;
    private final Player player;
    private final ObservableList<Goal> createdGoals;

    public CreateGoalsController(Stage stage, CreateGoalsView view, Player player, PathsFile pathsFile) {
        this.player = player;
        this.pathsFile = pathsFile;
        this.view = view;
        createdGoals = FXCollections.observableArrayList();
        view.getGoalsListView().setItems(createdGoals);
        setTopActions();
        setCenterActions();
        stage.setScene(view.getScene());
    }

    private void setTopActions() {

        // Set info button tooltip and change image
        Tooltip tooltip = createTooltip("See Player and Story information", 18);
        Tooltip.install(view.getInfoButton(), tooltip);
        view.getInfoButton().setOnMouseEntered(mouseEvent -> view.getInfoButton().setImage(view.getInfoButtonHover()));
        view.getInfoButton().setOnMouseExited(mouseEvent -> view.getInfoButton().setImage(view.getInfoButtonImage()));
        showInformationBox();

        // Set back button tooltip and change image
        Tooltip backButtonTooltip = createTooltip("Back to Create Player", 18);
        Tooltip.install(view.getBackButton(), backButtonTooltip);
        view.getBackButton().setOnMouseEntered(mouseEvent -> view.getBackButton().setImage(view.getBackButtonHover()));
        view.getBackButton().setOnMouseExited(mouseEvent -> view.getBackButton().setImage(view.getBackButtonImage()));

    }

    private Tooltip createTooltip(String text, int fontSize) {
        Tooltip tooltip = new Tooltip(text);
        tooltip.setShowDelay(Duration.ZERO);
        tooltip.setShowDuration(Duration.INDEFINITE);
        tooltip.setFont(resizableMainFont(fontSize));
        return tooltip;
    }
    private void showInformationBox() {
        view.getInfoButton().setOnMouseClicked(mouseEvent -> {
            Alert information = new Alert(Alert.AlertType.INFORMATION);
            information.setTitle("Player and Story Information");
            information.setHeaderText("Information on " + player.getName() + " and '" + pathsFile.getStoryTitle() + "'");
            String playerStats = player.getName() + "'s Health: " + player.getHealth() + "\n" + player.getName() + "'s Gold: " + player.getGold() + "\n";
            information.setContentText(playerStats + "\nBroken Links: " + pathsFile.getBrokenLinks() + "\n\nFile Path: " + pathsFile.getFilePath());
            DialogPane dialogPane = information.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/StyleSheets/DialogBoxStyleSheet.css").toExternalForm());
            information.show();
        });
    }
    private void setCenterActions() {
        setGoalIconActions();
        setAddGoalActions();
        setListViewActions();
    }

    private void setGoalIconActions() {
        String goalValueText = " goal value:";
        view.getHealthIcon().setOnMouseClicked(mouseEvent -> {
            view.getP1().setVisible(true);
            view.getP2().setVisible(false);
            view.getP3().setVisible(false);
            view.getP4().setVisible(false);

            view.getNumericalGoalBox().setManaged(true);
            view.getNumericalGoalBox().setVisible(true);

            view.getValueTypeLabel().setText("Health" + goalValueText);
        });

        view.getScoreIcon().setOnMouseClicked(mouseEvent -> {
            view.getP1().setVisible(false);
            view.getP2().setVisible(true);
            view.getP3().setVisible(false);
            view.getP4().setVisible(false);

            view.getNumericalGoalBox().setManaged(true);
            view.getNumericalGoalBox().setVisible(true);

            view.getValueTypeLabel().setText("Score" + goalValueText);
        });
        view.getGoldIcon().setOnMouseClicked(mouseEvent -> {
            view.getP1().setVisible(false);
            view.getP2().setVisible(false);
            view.getP3().setVisible(true);
            view.getP4().setVisible(false);

            view.getNumericalGoalBox().setManaged(true);
            view.getNumericalGoalBox().setVisible(true);

            view.getValueTypeLabel().setText("Gold" + goalValueText);
        });
    }

    private void setAddGoalActions() {
        view.getAddGoalButton().setOnMouseClicked(mouseEvent -> {
            if (view.getNumericalGoalBox().isVisible()) {
                if (validateGoalValueInput()) {
                    if (view.getP2().isVisible()) {
                        createdGoals.add(new ScoreGoal(Integer.parseInt(view.getValueTextField().getText())));
                    }
                    if (view.getP1().isVisible()) {
                        createdGoals.add(new HealthGoal(Integer.parseInt(view.getValueTextField().getText())));
                    }
                    if (view.getP3().isVisible()) {
                        createdGoals.add(new GoldGoal(Integer.parseInt(view.getValueTextField().getText())));
                    }
                }
            }
        });
    }

    private void setListViewActions() {
        view.getGoalsListView().setOnMouseClicked(mouseEvent -> {
            createdGoals.remove(view.getGoalsListView().getSelectionModel().getSelectedItem());
        });
    }
    private boolean validateGoalValueInput() {
        String numbersOnlyRegex = "^[0-9]+$";
        if (!Pattern.matches(numbersOnlyRegex, view.getValueTextField().getText())) {
            setInvalidTextFieldStyle(view.getValueTextField());
            return false;
        }
        view.getValueTextField().setStyle(null);
        return true;
    }
    private void setInvalidTextFieldStyle(TextField textField) {
        String borderColor = "red;";
        String borderWidth = "1px;";
        textField.setStyle("-fx-border-color: " + borderColor +
                "-fx-border-width: " + borderWidth);
    }
}
