package edu.ntnu.mappe32.controller;

import edu.ntnu.mappe32.model.PathsFile;
import edu.ntnu.mappe32.view.CreatePlayerAndGoalsView;
import edu.ntnu.mappe32.view.PathsSplashScreenView;
import edu.ntnu.mappe32.view.StorySelectorView;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class GameSetupController {
    // private final Game game;
    public GameSetupController(Stage stage, PathsSplashScreenView splashScreen,
                               StorySelectorView storySelectorView) {

        splashScreen.getPlayAnExistingStoryButton().setOnAction(actionEvent -> {
            stage.setScene(storySelectorView.getScene());
            storySelectorView.createStoryTable();
            storySelectorView.fillStoryTable();
        });

        storySelectorView.getAddStoryButton().setOnAction(actionEvent -> {
            FileChooser.ExtensionFilter pathsExtension = new FileChooser.ExtensionFilter("Paths Files (.paths)",
                    "*.paths");
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Paths File");
            fileChooser.setInitialDirectory(new File("src/main/resources/saved stories"));
            fileChooser.getExtensionFilters().add(pathsExtension);
            File selectedFile = fileChooser.showOpenDialog(stage);
            try {
                if (selectedFile != null)
                    if (storySelectorView.getStoryTable().getItems()
                        .contains(new PathsFile(selectedFile))) {
                    storySelectorView.fileAlreadySelectedAlert().show();
                storySelectorView.addPathsFile(selectedFile);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        storySelectorView.getStoryTable().setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isPrimaryButtonDown() && (mouseEvent.getClickCount() == 2)) {
                PathsFile pathsFile = storySelectorView.getStoryTable().getSelectionModel().getSelectedItem();
                stage.setScene(new CreatePlayerAndGoalsView(pathsFile).getScene());
            }
        });
        // this.game = new Game();
    }
}
