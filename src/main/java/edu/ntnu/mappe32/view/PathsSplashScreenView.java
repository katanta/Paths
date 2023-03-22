package edu.ntnu.mappe32.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class PathsSplashScreen {
    private final Stage stage;
    private final Scene scene;
    private final Button myButton;

    public PathsSplashScreen() {
        this.stage = new Stage();
        this.myButton = new Button("Play an Existing Story");
        StackPane root = new StackPane();
        root.getChildren().add(myButton);
        scene = new Scene(root, 1280,720);
        stage.setScene(scene);
    }

    public void show() {
        stage.show();
    }

    public Button getMyButton() {
        return myButton;
    }
}
