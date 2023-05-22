package edu.ntnu.mappe32;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ViewUtils {

    private static ViewUtils instance;
    private static final String errorMessage = "Error occurred when ";
    private static Image helpButtonHoverImage;
    private static Image homeButtonImage;
    private static Image homeButtonHoverImage;
    private static Image infoImage;
    private static Image infoHoverImage;
    private static Image restartHoverImage;
    private static Image nextImage;
    private static Image pointerImage;
    private static Image restartImage;
    private static Font pixeloidMonoFile;
    private static Font pixeloidSansFile;
    private static Font pixeloidSansBoldFile;
    private static Image errorCircleImage;
    private static Image goldImage;
    private static Image scoreImage;
    private static Image startImage;
    private static Image healthImage;
    private static Image inventoryImage;
    private static Image helpButtonImage;

    private ViewUtils() {
        instaintiateImages();
    }

    public static ViewUtils getInstance() {
        if (instance == null) {
            synchronized (ViewUtils.class) {
                if (instance == null) {
                    instance = new ViewUtils();
                }
            }
        }
        return instance;
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
    private void instaintiateImages() {
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

    public static void setHoverSound(Node node) {

    }
}
