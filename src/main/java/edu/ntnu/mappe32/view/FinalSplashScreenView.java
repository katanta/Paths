package edu.ntnu.mappe32.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
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
    private VBox buttonsVBoxContainer;
    private HBox playButtonHBox;
    private Button playButton;
    private HBox baitButtonHBox;

    public FinalSplashScreenView() {
        root = new StackPane();
        root.setAlignment(Pos.TOP_CENTER);
        configureLogo(); //configureLogo must be done first to apply correct margins for it
        configureButtonsVBox();
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


    private void configureButtonsVBox(){
        buttonsVBoxContainer = new VBox();
        buttonsVBoxContainer.setAlignment(Pos.CENTER);
        buttonsVBoxContainer.setSpacing(20);
        configurePlayButtonHBox();
        configureBaitButtonHBox();
        root.getChildren().add(buttonsVBoxContainer);
    }
    private void configurePlayButtonHBox() {
        playButton = new Button("Play an existing story");
        playButton.setFont(resizableMainFont(30));
        playButton.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color: #ffffff");
        playButtonHBox = new HBox(playButton);
        configureSelectionPointers(playButtonHBox);
        playButtonHBox.getChildren().get(0).setVisible(false);
        playButtonHBox.getChildren().get(2).setVisible(false);
        playButtonHBox.setAlignment(Pos.CENTER);
        playButtonHBox.setSpacing(50);
        StackPane.setMargin(playButtonHBox, new Insets(20, 30, 0, 0));
        buttonsVBoxContainer.getChildren().add(playButtonHBox);
    }

    private void configureSelectionPointers(HBox hBox) {
        ImageView pointer;
        ImageView pointerClone;
        try {
            pointer = new ImageView(new Image(new FileInputStream("src/main/resources/img/Pointer.png")));
            pointer.setFitWidth(75);
            pointer.setFitHeight(75);
            pointer.setRotate(270);
            pointerClone = new ImageView(pointer.getImage());
            pointerClone.setRotate(90);
            pointerClone.setFitWidth(pointer.getFitWidth());
            pointerClone.setFitHeight(pointer.getFitHeight());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        hBox.getChildren().add(0, pointer);
        hBox.getChildren().add(2, pointerClone);
    }

    private void configureBaitButtonHBox() {
        Button baitButton = new Button("Follow your own path \n     (COMING SOON!)");
        baitButton.setFont(resizableMainFont(30));
        baitButton.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color: #6b6b6b");
        baitButton.setTooltip(new Tooltip("This Feature is unavailable but you can still click me :)"));
        baitButton.getTooltip().setFont(resizableMainFont(15));
        baitButtonHBox = new HBox(baitButton);
        baitButtonHBox.setSpacing(50);
        configureSelectionPointers(baitButtonHBox);
        baitButtonHBox.getChildren().get(0).setVisible(false);
        baitButtonHBox.getChildren().get(2).setVisible(false);
        baitButtonHBox.setAlignment(Pos.CENTER);
        buttonsVBoxContainer.getChildren().add(baitButtonHBox);
    }

    public Scene getScene() {
        return scene;
    }

    public HBox getPlayButtonHBox() {
        return playButtonHBox;
    }

    public Button getPlayButton() {
        return playButton;
    }

    public HBox getBaitButtonHBox() {
        return baitButtonHBox;
    }
}
