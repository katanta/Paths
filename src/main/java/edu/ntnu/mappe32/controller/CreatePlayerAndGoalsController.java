package edu.ntnu.mappe32.controller;

import edu.ntnu.mappe32.view.CreatePlayerAndGoalsView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CreatePlayerAndGoalsController {
    private final static String fontFamily = "Times New Roman";
    private final static int fontSize = 20;
    public CreatePlayerAndGoalsController(Stage stage, CreatePlayerAndGoalsView view) {
        view.getGoldToggle().setOnAction(actionEvent ->  {
            if (view.getGoalValueTextField().isVisible() || view.getNumberValueGoalLabel().isVisible()
                    && !view.getNumberValueGoalLabel().getText().equals("Gold Goal:")) {
                view.getNumberValueGoalLabel().setText("Gold Goal:");
                view.getNumberValueGoalLabel().setFont(new Font(fontFamily, fontSize));
                view.getNumberValueGoalLabel().setVisible(true);
                view.getNumberValueGoalLabel().setManaged(true);
                return;
            }
            view.getNumberValueGoalLabel().setVisible(false);
            view.getNumberValueGoalLabel().setManaged(false);
        });
        view.getHealthToggle().setOnAction(actionEvent ->  {
            if (view.getGoalValueTextField().isVisible() || view.getNumberValueGoalLabel().isVisible()
                    && !view.getNumberValueGoalLabel().getText().equals("Health Goal:")) {
                view.getNumberValueGoalLabel().setText("Health Goal:");
                view.getNumberValueGoalLabel().setFont(new Font(fontFamily, fontSize));
                view.getNumberValueGoalLabel().setVisible(true);
                view.getNumberValueGoalLabel().setManaged(true);
                return;
            }
            view.getNumberValueGoalLabel().setVisible(false);
            view.getNumberValueGoalLabel().setManaged(false);
        });
        view.getScoreToggle().setOnAction(actionEvent ->  {
            if (view.getGoalValueTextField().isVisible() || view.getNumberValueGoalLabel().isVisible()
                    && !view.getNumberValueGoalLabel().getText().equals("Score Goal:")) {
                view.getNumberValueGoalLabel().setText("Score Goal:");
                view.getNumberValueGoalLabel().setFont(new Font(fontFamily, fontSize));
                view.getNumberValueGoalLabel().setVisible(true);
                view.getNumberValueGoalLabel().setManaged(true);
                return;
            }
            view.getNumberValueGoalLabel().setVisible(false);
            view.getNumberValueGoalLabel().setManaged(false);
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

        });
    }
}
