package edu.ntnu.mappe32.controller;

import edu.ntnu.mappe32.view.PathsSplashScreenView;
import edu.ntnu.mappe32.view.StorySelecterView;
import javafx.stage.Stage;

public class GameSetupController {
    private PathsSplashScreenView splashScreen;
    private StorySelecterView storySelecter;
    Stage stage;

    public GameSetupController(Stage stage, PathsSplashScreenView splashScreen, StorySelecterView storySelecter) {
        this.stage = stage;
        this.splashScreen = splashScreen;

        splashScreen.getMyButton().setOnAction(actionEvent -> {
            stage.setScene(storySelecter.getScene());
        });
    }
}
