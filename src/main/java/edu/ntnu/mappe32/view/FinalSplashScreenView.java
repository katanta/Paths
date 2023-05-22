package edu.ntnu.mappe32.view;

import edu.ntnu.mappe32.ViewUtils;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class FinalSplashScreenView {
    private final Scene scene;
    private StackPane root;
    private Image bigPathsLogo;
    private VBox buttonsVBoxContainer;
    private HBox playButtonHBox;
    private Button playButton;
    private HBox baitButtonHBox;
    private ImageView backgroundTrees;
    private ImageView middleTrees;
    private ImageView lightLayer;
    private ImageView frontTrees;
    private MediaPlayer musicPlayer;
    private MediaPlayer selectionPlayer;


    public FinalSplashScreenView() {
        ViewUtils.getInstance();
        root = new StackPane();
        root.setAlignment(Pos.TOP_CENTER);
        configureLogo(); //configureLogo must be done first to apply correct margins for it
        configureButtonsVBox();
        configureBackGround();
        configureMediaPlayers();
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

    private void configureBackGround() {
        ObjectProperty<Integer> backgroundOffsetProperty = new SimpleObjectProperty<>(0);
        ObjectProperty<Integer> middlegroundOffsetProperty = new SimpleObjectProperty<>(0);
        ObjectProperty<Integer> lightLayerOffsetProperty = new SimpleObjectProperty<>(0); //dry code just since i'm learning parallax animaton
        ObjectProperty<Integer> frontgroundOffsetProperty = new SimpleObjectProperty<>(0);
        //each image needed its own property to be able to move at different speeds, creating a parallax effect.
        try {
            backgroundTrees = new ImageView(new Image(new FileInputStream("src/main/resources/img/animationExperiment/backTrees.png")));
            middleTrees = new ImageView(new Image(new FileInputStream("src/main/resources/img/animationExperiment/middleTrees.png")));
            lightLayer = new ImageView(new Image(new FileInputStream("src/main/resources/img/animationExperiment/lights.png")));
            frontTrees = new ImageView(new Image(new FileInputStream("src/main/resources/img/animationExperiment/frontTrees.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("One or more background images are missing..." + e.getMessage());
        }
        Rectangle2D backGroundViewPort = new Rectangle2D(0, 0, 1280, 720);
        backgroundTrees.setViewport(backGroundViewPort);
        middleTrees.setViewport(backGroundViewPort);
        backgroundOffsetProperty.addListener(observable -> backgroundTrees.setViewport(new Rectangle2D(backgroundOffsetProperty.get(), 0, 1280, 720)));
        middlegroundOffsetProperty.addListener(observable -> middleTrees.setViewport(new Rectangle2D(middlegroundOffsetProperty.get(), 0, 1280, 720)));
        lightLayerOffsetProperty.addListener(observable -> lightLayer.setViewport(new Rectangle2D(lightLayerOffsetProperty.get(), 0, 1280, 720)));
        frontgroundOffsetProperty.addListener(observable -> frontTrees.setViewport(new Rectangle2D(frontgroundOffsetProperty.get(), 0, 1280, 720)));
        createBackgroundAnimation(backgroundOffsetProperty, 20000).play();
        createBackgroundAnimation(middlegroundOffsetProperty, 15000).play();
        createBackgroundAnimation(lightLayerOffsetProperty, 10000).play();
        createBackgroundAnimation(frontgroundOffsetProperty, 6000).play();
        root.getChildren().add(0, backgroundTrees);
        root.getChildren().add(1, middleTrees);
        root.getChildren().add(2, lightLayer);
        root.getChildren().add(3, frontTrees);
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
        playButton.setFont(ViewUtils.pixeloidSans(30));
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
        ImageView pointer = new ImageView(ViewUtils.pointerImage());
        ImageView pointerClone;
        pointer.setFitWidth(75);
        pointer.setFitHeight(75);
        pointer.setRotate(270);
        pointerClone = new ImageView(pointer.getImage());
        pointerClone.setRotate(90);
        pointerClone.setFitWidth(pointer.getFitWidth());
        pointerClone.setFitHeight(pointer.getFitHeight());
        hBox.getChildren().add(0, pointer);
        hBox.getChildren().add(2, pointerClone);
    }

    private void configureBaitButtonHBox() {
        Button baitButton = new Button("Follow your own path \n     (COMING SOON!)");
        baitButton.setFont(ViewUtils.pixeloidSans(30));
        baitButton.setStyle("-fx-border-color: #000000; -fx-border-width: 5px; -fx-background-color: #6b6b6b");

        Tooltip baitButtonTooltip = ViewUtils.createTooltip("This Feature is unavailable but you can still click me :)", 15);
        baitButton.setTooltip(baitButtonTooltip);

        baitButtonHBox = new HBox(baitButton);
        baitButtonHBox.setSpacing(50);
        configureSelectionPointers(baitButtonHBox);
        baitButtonHBox.getChildren().get(0).setVisible(false);
        baitButtonHBox.getChildren().get(2).setVisible(false);
        baitButtonHBox.setAlignment(Pos.CENTER);
        buttonsVBoxContainer.getChildren().add(baitButtonHBox);
    }

    private Animation createBackgroundAnimation(ObjectProperty<Integer> backgroundOffsetProperty, int milliDuration) {
        Animation animation = new Transition() {
            @Override
            protected void interpolate(double frac) {
                int backGroundOffset = (int) ((frac * 1280) % 12800);
                backgroundOffsetProperty.set(backGroundOffset);
            }

            {
                setCycleDuration(Duration.millis(milliDuration));
                setInterpolator(Interpolator.LINEAR);
            }
        };
        animation.setCycleCount(Animation.INDEFINITE);
        return animation;
    }

    private void configureMediaPlayers() {
        musicPlayer = new MediaPlayer(new Media(new File("src/main/resources/audio/music/MainMenuMusic.mp3").toURI().toString()));
        musicPlayer.setVolume(0.25);
        selectionPlayer = new MediaPlayer(new Media(new File("src/main/resources/audio/Menu Selection Click.wav").toURI().toString()));

        musicPlayer.play();
        musicPlayer.setOnEndOfMedia(() -> musicPlayer.seek(Duration.ZERO));
    }



    public MediaPlayer getMusicPlayer() {
        return musicPlayer;
    }
    public MediaPlayer getSelectionPlayer() {
        return selectionPlayer;
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
