package edu.ntnu.mappe32.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class PathsSplashScreenView {
    private final Scene scene;
    private final Button playAnExistingStoryButton;

    public PathsSplashScreenView() {
        this.playAnExistingStoryButton = new Button("Play an Existing Story");
        StackPane root = new StackPane();
        root.getChildren().add(playAnExistingStoryButton);
        scene = new Scene(root, 1280, 720);
    }

    public Scene getScene() {
        return scene;
    }

    public Button getPlayAnExistingStoryButton() {
        return playAnExistingStoryButton;
    }
}
