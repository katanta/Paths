package edu.ntnu.mappe32.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class StorySelecterView {
    Scene scene;
    Button myButton;

    public StorySelecterView() {
        this.myButton = new Button("Select story");
        StackPane root = new StackPane();
        root.getChildren().add(myButton);
        scene = new Scene(root, 1280,720);
    }

    public Scene getScene() {
        return scene;
    }
}
