package edu.ntnu.mappe32.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class PathsSplashScreenView {
    private final Scene scene;
    private final Button myButton;

    public PathsSplashScreenView() {
        this.myButton = new Button("Play an Existing Story");
        StackPane root = new StackPane();
        root.getChildren().add(myButton);
        scene = new Scene(root, 1280,720);
    }

    public Scene getScene() {
        return scene;
    }

    public Button getMyButton() {
        return myButton;
    }
}
