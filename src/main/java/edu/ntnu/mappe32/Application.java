package edu.ntnu.mappe32;

import edu.ntnu.mappe32.controller.GameSetupController;
import edu.ntnu.mappe32.view.PathsSplashScreenView;
import edu.ntnu.mappe32.view.StorySelecterView;
import javafx.stage.Stage;


public class Application extends javafx.application.Application {
    Stage stage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        PathsSplashScreenView splashScreen = new PathsSplashScreenView();
        StorySelecterView storySelecterView = new StorySelecterView();
        stage.setScene(splashScreen.getScene());
        GameSetupController gameSetup = new GameSetupController(stage, splashScreen, storySelecterView);
        stage.show();
    }
}
