package edu.ntnu.mappe32.controller;

import edu.ntnu.mappe32.model.PathsFile;
import edu.ntnu.mappe32.view.PathsSplashScreenView;
import edu.ntnu.mappe32.view.StorySelecterView;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class GameSetupController {

    public GameSetupController(Stage stage, PathsSplashScreenView splashScreen,
                               StorySelecterView storySelecterView) {
        splashScreen.getPlayAnExistingStoryButton().setOnAction(actionEvent -> {
            stage.setScene(storySelecterView.getScene());
            storySelecterView.createStoryTable();
            storySelecterView.fillStoryTable();
        });

        storySelecterView.getAddStoryButton().setOnAction(actionEvent -> {
            FileChooser.ExtensionFilter pathsExtension = new FileChooser.ExtensionFilter("Paths Files (.paths)",
                    "*.paths");
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Paths File");
            fileChooser.setInitialDirectory(new File("src/main/resources/saved stories"));
            fileChooser.getExtensionFilters().add(pathsExtension);
            File selectedFile = fileChooser.showOpenDialog(stage);
            try {
                if (selectedFile != null && storySelecterView.getStoryTable().getItems()
                        .contains(new PathsFile(selectedFile))) {
                    Alert fileAlreadySelected = new Alert(Alert.AlertType.WARNING);
                    fileAlreadySelected.setContentText("The file already exists in the table.");
                    fileAlreadySelected.show();
                }
                if (selectedFile != null && !storySelecterView.getStoryTable().getItems()
                        .contains(new PathsFile(selectedFile))) {
                        storySelecterView.addPathsFile(selectedFile);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
