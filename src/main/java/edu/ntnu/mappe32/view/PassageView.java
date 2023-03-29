package edu.ntnu.mappe32.view;

import edu.ntnu.mappe32.controller.Game;
import edu.ntnu.mappe32.model.story_related.Passage;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
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
    Passage currentPassage;
    VBox linkButtons;

    public PassageView(Game game) {
        this.game = game;
    }

    public void configurePassageContent() {
        currentPassage = game.begin();
        StackPane root = new StackPane();
        scene = new Scene(root, 1280, 720);
        storyTitle = new Label(game.getStory().getTitle());
        storyTitle.setLabelFor(passageTitle);
        storyTitle.setMinSize(10, 10);
        storyTitle.setFont(new Font(45));
        passageTitle = new Text(currentPassage.getTitle());
        passageTitle.setFont(new Font(25));
        ScrollPane passageScrollPane = new ScrollPane();
        passageContent = new Text(currentPassage.getContent() + " ejsdhfkjadshfkjdsahfksdfkjahsdkjhaskjhaksljhkjsdahvkljashvjasdhvjashvjashfjashfkjasfhjashfjkSHDFJKSHFJSHFJSDHFJHSDFKJHASDFHASDF@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@222");
        passageContent.setFont(new Font(14));
        passageScrollPane.setMaxWidth(600);
        passageContent.setWrappingWidth(passageScrollPane.getMaxWidth() - 15);
        passageScrollPane.setMaxHeight(180);
        passageScrollPane.setContent(passageContent);
        VBox center = new VBox(storyTitle, passageTitle, passageScrollPane);
        center.setMargin(passageTitle, new Insets(0, 0, 25, 0));
        center.setAlignment(Pos.TOP_CENTER);
        root.getChildren().add(center);

        //Configure initial links
        ScrollPane linksScrollPane = new ScrollPane();
        linkButtons = new VBox();
        game.begin().getLinks().forEach(link -> {
            Button linkButton = new Button(link.getText());
            linkButton.setPrefSize(200, 40);
            linkButton.setOnMouseClicked(mouseEvent -> {currentPassage = game.go(link); //this.game can be used to update player information
            updateScene(); //make the buttons change the current active passage, changing the scene
            });
            linkButton.setTooltip(new Tooltip(link.getText()));
            linkButton.getTooltip().setFont(new Font(12));
            linkButtons.getChildren().add(linkButton);
        });
        linkButtons.setAlignment(Pos.CENTER);
        linksScrollPane.setContent(linkButtons);
        linksScrollPane.setMaxSize(200, 500);
        linksScrollPane.setFitToWidth(true);
        center.getChildren().add(linksScrollPane);
        center.setMargin(linksScrollPane, new Insets(35, 0, 0, 0));
    }

    public Scene getScene() {
        configurePassageContent();
        return scene;
    }

    public void updateScene() {
        linkButtons.getChildren().clear();
        passageContent.setText(currentPassage.getContent());
        passageTitle.setText(currentPassage.getTitle());
        currentPassage.getLinks().forEach(link -> {
            Button linkButton = new Button(link.getText());
            linkButton.setPrefSize(200, 40);
            linkButton.setOnMouseClicked(mouseEvent -> {this.currentPassage = game.go(link);
                updateScene();
            });
            linkButtons.getChildren().add(linkButton);
        });
    }
}
