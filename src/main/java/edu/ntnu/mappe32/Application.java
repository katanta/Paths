package edu.ntnu.mappe32;

import edu.ntnu.mappe32.controller.FinalSplashScreenController;
import edu.ntnu.mappe32.controller.GameSetupController;
import edu.ntnu.mappe32.view.FinalSplashScreenView;
import edu.ntnu.mappe32.view.PathsSplashScreenView;
import edu.ntnu.mappe32.view.StorySelectorView;
import javafx.stage.Stage;


public class Application extends javafx.application.Application {

    public static void main(String[] args) { launch(); }

    @Override
    public void start(Stage stage) {
        FinalSplashScreenController splashScreenController = new FinalSplashScreenController(stage, new FinalSplashScreenView());
        stage.setResizable(false);
        stage.setTitle("Paths Game");
        stage.show();
    }
}
