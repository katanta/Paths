package edu.ntnu.mappe32.view;

import edu.ntnu.mappe32.controller.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PassageView {
    Scene scene;
    Text passageContent;
    Label storyTitle;
    Text passageTitle;
    Game game;

    public PassageView(Game game) {
        this.game = game;
    }

    public void configurePassageContent() {
        StackPane root = new StackPane();
        scene = new Scene(root, 1280, 720);
        storyTitle = new Label(game.getStory().getTitle());
        storyTitle.setLabelFor(passageTitle);
        storyTitle.setMinSize(10, 10);
        storyTitle.setFont(new Font(45));
        passageTitle = new Text(game.getStory().getOpeningPassage().getTitle());
        passageTitle.setFont(new Font(25));
        ScrollPane passageScrollPane = new ScrollPane();
        //TODO: setup Passage Content and make it scrollable if it's too large to fit. At the moment you can still scroll horizontally.
        passageContent = new Text(game.getStory().getOpeningPassage().getContent() + "ejsdhfkjadshfkjdsahfksdfkjahsdkjhaskjhaksljhkjsdahvkljashvjasdhvjashvjashfjashfkjasfhjashfjkSHDFJKSHFJSHFJSDHFJHSDFKJHASDFHASDF@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@222");
        passageContent.setFont(new Font(14));
        passageContent.wrappingWidthProperty().bind(scene.widthProperty());
        passageScrollPane.setMaxSize(800, 500);
        passageScrollPane.setContent(passageContent);
        VBox center = new VBox(storyTitle, passageTitle, passageScrollPane);
        center.setMargin(passageContent, new Insets(25, 0, 0, 0));
        center.setAlignment(Pos.TOP_CENTER);
        root.getChildren().add(center);
    }

    public Scene getScene() {
        configurePassageContent();
        return scene;
    }
}
