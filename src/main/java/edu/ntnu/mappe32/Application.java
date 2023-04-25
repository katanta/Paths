package edu.ntnu.mappe32;

import edu.ntnu.mappe32.controller.GameSetupController;
import edu.ntnu.mappe32.view.PathsSplashScreenView;
import edu.ntnu.mappe32.view.StorySelectorView;
import javafx.stage.Stage;


public class Application extends javafx.application.Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        PathsSplashScreenView splashScreen = new PathsSplashScreenView();
        stage.setScene(splashScreen.getScene());
        GameSetupController gameSetup = new GameSetupController(stage, splashScreen, new StorySelectorView());
        stage.setTitle("Paths Game");
        stage.show();
    }
}
