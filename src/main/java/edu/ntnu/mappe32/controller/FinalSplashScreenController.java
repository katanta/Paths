package edu.ntnu.mappe32.controller;

import edu.ntnu.mappe32.FrontendUtils;
import edu.ntnu.mappe32.view.FinalSplashScreenView;
import edu.ntnu.mappe32.view.StorySelectorView;
import javafx.stage.Stage;

public class FinalSplashScreenController {
    private final FinalSplashScreenView splashScreenView;
    private final Stage stage;

    public FinalSplashScreenController(Stage stage, FinalSplashScreenView splashScreenView) {
        FrontendUtils.playStartScreenMusic();
        this.stage = stage;
        this.splashScreenView = splashScreenView;
        configureButtonActions();
        stage.setScene(splashScreenView.getScene());
    }

    private void configureButtonActions() {
        splashScreenView.getPlayButton().setOnMouseEntered(e -> {
            splashScreenView.getPlayButtonHBox().getChildren().get(0).setVisible(true);
            splashScreenView.getPlayButtonHBox().getChildren().get(2).setVisible(true);
            splashScreenView.getPlayButton().setStyle(("-fx-border-color: #ffffff; -fx-border-width: 5px; -fx-background-color: #000000; -fx-text-fill: #ffffff;"));
            FrontendUtils.playSelectionSound();
        });
        splashScreenView.getPlayButton().setOnMouseExited(e -> {
            splashScreenView.getPlayButton().setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color: #ffffff; -fx-text-fill: #000000");
            splashScreenView.getPlayButtonHBox().getChildren().get(0).setVisible(false);
            splashScreenView.getPlayButtonHBox().getChildren().get(2).setVisible(false);
        });
        splashScreenView.getPlayButton().setOnMouseClicked(e -> {
            splashScreenView.getPlayButton().setStyle("-fx-background-color: #888383");
            GameSetupController gameSetupController = new GameSetupController(stage, new StorySelectorView());
        });
        splashScreenView.getBaitButtonHBox().getChildren().get(1).setOnMouseEntered(e -> {
            splashScreenView.getBaitButtonHBox().getChildren().get(0).setVisible(true);
            splashScreenView.getBaitButtonHBox().getChildren().get(2).setVisible(true);
            FrontendUtils.playSelectionSound();

        });
        splashScreenView.getBaitButtonHBox().getChildren().get(1).setOnMouseExited(e -> {
            splashScreenView.getBaitButtonHBox().getChildren().get(0).setVisible(false);
            splashScreenView.getBaitButtonHBox().getChildren().get(2).setVisible(false);
        });
        splashScreenView.getBaitButtonHBox().getChildren().get(1).setOnMouseClicked(e -> {
            //Todo: add a positively hilarious sound effect to hoodwink, bamboozle, lead astray, run amok and flat out deceive the players.
        });
    }

}
