package edu.ntnu.mappe32;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ViewUtils {
    private static final String errorMessage = "Error occurred when ";

    public static Font pixeloidMono(int fontSize) {
        return Font.loadFont("file:src/main/resources/fonts/PixeloidMono.ttf", fontSize);
    }

    public static Font pixeloidSans(int fontSize) {
        return Font.loadFont("file:src/main/resources/fonts/PixeloidSans.ttf", fontSize);
    }

    public static Font pixeloidSansBold(int fontSize) {
        return Font.loadFont("file:src/main/resources/fonts/PixeloidSansBold.ttf", fontSize);
    }

    public static Image errorCircleImage() {
        try {
            return new Image(new FileInputStream("src/main/resources/img/errorCircle.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading errorCircle.png " + e.getMessage());
        }
    }

    public static Image goldImage() {
        try {
            return new Image(new FileInputStream("src/main/resources/img/gold.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading gold.png " + e.getMessage());
        }
    }

    public static Image scoreImage() {
        try {
            return new Image(new FileInputStream("src/main/resources/img/score.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading score.png " + e.getMessage());
        }
    }


    public static Image healthImage() {
        try {
            return new Image(new FileInputStream("src/main/resources/img/hp.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading hp.png " + e.getMessage());
        }    }

    public static Image inventoryImage() {
        try {
            return new Image(new FileInputStream("src/main/resources/img/inventory.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading inventory.png " + e.getMessage());
        }
    }

    public static Image helpButtonImage() {
        try {
            return new Image(new FileInputStream("src/main/resources/img/helpButton.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading helpButton.png " + e.getMessage());
        }
    }

    public static Image helpButtonHoverImage() {
        try {
            return new Image(new FileInputStream("src/main/resources/img/helpButtonHover.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading helpButtonHover.png " + e.getMessage());
        }
    }

    public static Image homeButtonImage() {
        try {
            return new Image(new FileInputStream("src/main/resources/img/homeButton.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading home.png " + e.getMessage());
        }
    }

    public static Image homeButtonHoverImage() {
        try {
            return new Image(new FileInputStream("src/main/resources/img/homeButtonHover.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading homeButtonHover.png " + e.getMessage());
        }
    }

    public static Image infoImage() {
        try {
            return new Image(new FileInputStream("src/main/resources/img/info.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading info.png " + e.getMessage());
        }
    }
    public static Image infoHoverImage() {
        try {
            return new Image(new FileInputStream("src/main/resources/img/info_hover.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading info_hover.png " + e.getMessage());
        }
    }

    public static Image nextImage() {
        try {
            return new Image(new FileInputStream("src/main/resources/img/next_button.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading next_button " + e.getMessage());
        }
    }

    public static Image pointerImage() {
        try {
            return new Image(new FileInputStream("src/main/resources/img/Pointer.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading Pointer.png " + e.getMessage());
        }
    }

    public static Image restartImage() {
        try {
            return new Image(new FileInputStream("src/main/resources/img/restartButton.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading restartButton.png " + e.getMessage());
        }
    }
    public static Image restartHoverImage() {
        try {
            return new Image(new FileInputStream("src/main/resources/img/restartButtonHover.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading restartButtonHover.png " + e.getMessage());
        }
    }

    public static Image startImage() {
        try {
            return new Image(new FileInputStream("src/main/resources/img/start.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(errorMessage + "reading start.png " + e.getMessage());
        }
    }
    public static Tooltip createTooltip(String text, int fontSize) {
        Tooltip tooltip = new Tooltip(text);
        tooltip.setShowDelay(Duration.ZERO);
        tooltip.setShowDuration(Duration.INDEFINITE);
        tooltip.setFont(pixeloidSans(fontSize));
        return tooltip;
    }
}
