package edu.ntnu.mappe32;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FrontendUtils {

    private static final FrontendUtils instance = new FrontendUtils();
    private static final String errorMessage = "Error occurred when ";
    private static MediaPlayer musicPlayer;
    private static Image helpButtonHoverImage;
    private static Image homeButtonImage;
    private static Image homeButtonHoverImage;
    private static Image infoImage;
    private static Image infoHoverImage;
    private static Image restartHoverImage;
    private static Image nextImage;
    private static Image pointerImage;
    private static Image restartImage;
    private static Image errorCircleImage;
    private static Image goldImage;
    private static Image scoreImage;
    private static Image startImage;
    private static Image healthImage;
    private static Image inventoryImage;
    private static Image helpButtonImage;
    private static MediaPlayer selectionPlayer;

    private FrontendUtils() {
        configureMediaPlayers();
        instantiateImages();
    }

    private void configureMediaPlayers() {
        musicPlayer = new MediaPlayer(new Media(new File("src/main/resources/audio/music/MainMenuMusic.mp3").toURI().toString()));
        musicPlayer.setOnEndOfMedia(() -> musicPlayer.seek(Duration.ZERO));
        musicPlayer.setVolume(0.25);
        selectionPlayer = new MediaPlayer(new Media(new File("src/main/resources/audio/Menu Selection Click.wav").toURI().toString()));
    }

    public static FrontendUtils getInstance() {
        return instance;
    }

    public static void playSelectionSound() {
        selectionPlayer.play();
        selectionPlayer.seek(Duration.ZERO);
    }

    public static void playStartScreenMusic() {
        musicPlayer.play();
    }

    public static void stopStartScreenMusic() {
        musicPlayer.stop();
        musicPlayer.seek(Duration.ZERO);
    }

    public static void setHoverSound(Node node) {
        node.setOnMouseEntered(mouseEvent -> playSelectionSound());
    }

    public static Tooltip createTooltip(String text, int fontSize) {
        Tooltip tooltip = new Tooltip(text);
        tooltip.setShowDelay(Duration.ZERO);
        tooltip.setShowDuration(Duration.INDEFINITE);
        tooltip.setFont(pixeloidSans(fontSize));
        return tooltip;
    }

    public static Font pixeloidMono(int fontSize) {
        return Font.loadFont("file:src/main/resources/fonts/PixeloidMono.ttf", fontSize);
    }

    public static Font pixeloidSans(int fontSize) {
        return Font.loadFont("file:src/main/resources/fonts/PixeloidSans.ttf", fontSize);
    }

    public static Font pixeloidSansBold(int fontSize) {
        return Font.loadFont("file:src/main/resources/fonts/PixeloidSansBold.ttf", fontSize);
    }

    private void instantiateImages() {
        try {
            errorCircleImage = new Image(new FileInputStream("src/main/resources/img/errorCircle.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading errorCircle.png " + e.getMessage());
        }

        try {
            goldImage = new Image(new FileInputStream("src/main/resources/img/gold.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading gold.png " + e.getMessage());
        }

        try {
            scoreImage = new Image(new FileInputStream("src/main/resources/img/score.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading score.png " + e.getMessage());
        }

        try {
            healthImage = new Image(new FileInputStream("src/main/resources/img/hp.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading hp.png " + e.getMessage());
        }

        try {
            inventoryImage = new Image(new FileInputStream("src/main/resources/img/inventory.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading inventory.png " + e.getMessage());
        }

        try {
            helpButtonImage = new Image(new FileInputStream("src/main/resources/img/helpButton.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading helpButton.png " + e.getMessage());
        }

        try {
            helpButtonHoverImage = new Image(new FileInputStream("src/main/resources/img/helpButtonHover.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading helpButtonHover.png " + e.getMessage());
        }

        try {
            homeButtonImage = new Image(new FileInputStream("src/main/resources/img/homeButton.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading home.png " + e.getMessage());
        }

        try {
            homeButtonHoverImage = new Image(new FileInputStream("src/main/resources/img/homeButtonHover.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading homeButtonHover.png " + e.getMessage());
        }

        try {
            infoImage = new Image(new FileInputStream("src/main/resources/img/info.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading info.png " + e.getMessage());
        }

        try {
            infoHoverImage = new Image(new FileInputStream("src/main/resources/img/info_hover.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading info_hover.png " + e.getMessage());
        }

        try {
            nextImage = new Image(new FileInputStream("src/main/resources/img/next_button.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading next_button " + e.getMessage());
        }

        try {
            pointerImage = new Image(new FileInputStream("src/main/resources/img/Pointer.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading Pointer.png " + e.getMessage());
        }

        try {
            restartImage = new Image(new FileInputStream("src/main/resources/img/restartButton.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading restartButton.png " + e.getMessage());
        }

        try {
            restartHoverImage = new Image(new FileInputStream("src/main/resources/img/restartButtonHover.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading restartButtonHover.png " + e.getMessage());
        }
        try {
            startImage = new Image(new FileInputStream("src/main/resources/img/start.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading start.png " + e.getMessage());
        }
    }

    public static Image errorCircleImage() {
        return errorCircleImage;
    }

    public static Image goldImage() {
        return goldImage;
    }

    public static Image scoreImage() {
        return scoreImage;
    }

    public static Image healthImage() {
        return healthImage;
    }

    public static Image inventoryImage() {
        return inventoryImage;
    }

    public static Image helpButtonImage() {
        return helpButtonImage;
    }

    public static Image helpButtonHoverImage() {
        return helpButtonHoverImage;
    }

    public static Image homeButtonImage() {
        return homeButtonImage;
    }

    public static Image homeButtonHoverImage() {
        return homeButtonHoverImage;
    }

    public static Image infoImage() {
        return infoImage;
    }

    public static Image infoHoverImage() {
        return infoHoverImage;
    }

    public static Image nextImage() {
        return nextImage;
    }

    public static Image pointerImage() {
        return pointerImage;
    }

    public static Image restartImage() {
        return restartImage;
    }

    public static Image restartHoverImage() {
        return restartHoverImage;
    }

    public static Image startImage() {
        return startImage;
    }

}
