package edu.ntnu.mappe32.controller;

import edu.ntnu.mappe32.model.PathsFile;
import edu.ntnu.mappe32.model.story_related.Story;
import edu.ntnu.mappe32.view.CreatePlayerView;
import javafx.stage.Stage;

public class CreatePlayerController {

    private final CreatePlayerView view;

    private final Stage stage;

    private final PathsFile pathsFile;

    public CreatePlayerController(CreatePlayerView view, Stage stage, PathsFile pathsFile) {
        this.view = view;
        this.stage = stage;
        this.pathsFile = pathsFile;

        view.getStoryTitle().setText(pathsFile.getStoryTitle());
        stage.setScene(view.getScene());
    }

}
