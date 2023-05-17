package edu.ntnu.mappe32.controller;

import edu.ntnu.mappe32.model.PathsFile;
import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.view.CreateGoalsView;
import javafx.stage.Stage;

public class CreateGoalsController {

    private final Stage stage;
    private final CreateGoalsView view;

    public CreateGoalsController(Stage stage, CreateGoalsView view, Player player, PathsFile pathsFile) {
        this.stage = stage;
        this.view = view;

    }
}
