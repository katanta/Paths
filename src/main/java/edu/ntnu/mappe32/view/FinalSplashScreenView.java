package edu.ntnu.mappe32.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static edu.ntnu.mappe32.view.PassageView.resizableMainFont;

public class FinalSplashScreenView {
    private final Scene scene;
    private StackPane root;
    private Image bigPathsLogo;
    private VBox menuButtonVBox;

    public FinalSplashScreenView() {
        root = new StackPane();
        root.setAlignment(Pos.TOP_CENTER);
        configureLogo(); //configureLogo must be done first to apply correct margins for it
        configureMenuButtonVBox();
        scene = new Scene(root, 1280, 720);
    }

    private void configureLogo() {
        try {
            bigPathsLogo = new Image(new FileInputStream("src/main/resources/img/bigPathsLogo.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        root.getChildren().add(new ImageView(bigPathsLogo));
        StackPane.setMargin(root.getChildren().get(0), new Insets(30, 0, 0, 0));
    }

    private void configureMenuButtonVBox() {
        Button playButton = new Button("Play an existing story");
        playButton.setFont(resizableMainFont(30));
        playButton.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color: #ffffff");
        //Button baitButton = new Button("Create your own path");

        menuButtonVBox = new VBox(playButton);
        menuButtonVBox.setAlignment(Pos.CENTER);
        StackPane.setMargin(menuButtonVBox, new Insets(20, 0, 0, 0));
        root.getChildren().add(menuButtonVBox);
    }

    public Scene getScene() {
        return scene;
    }


}
