package edu.ntnu.mappe32.controller;

import edu.ntnu.mappe32.model.PathsFile;
import edu.ntnu.mappe32.view.CreatePlayerView;
import edu.ntnu.mappe32.view.PathsSplashScreenView;
import edu.ntnu.mappe32.view.StorySelectorView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class GameSetupController {

    private final ObservableList<PathsFile> pathsFiles;
    private final StorySelectorView storySelectorView;
    private final Stage stage;

    public GameSetupController(Stage stage, PathsSplashScreenView splashScreen, StorySelectorView storySelectorView) {
        this.pathsFiles = FXCollections.observableArrayList();
        this.storySelectorView = storySelectorView;
        storySelectorView.getStoryTable().setItems(pathsFiles);
        this.stage = stage;
        stage.setScene(storySelectorView.getScene());

        getFilesInDirectory();
        setStoryTableActions();
        setAddPathsFileAction();

    }

    private void setAddPathsFileAction() {
        storySelectorView.getAddPathsFileButton().setOnAction(actionEvent -> {
            FileChooser.ExtensionFilter pathsExtension = new FileChooser.ExtensionFilter("Paths Files (.paths)", "*.paths");
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Paths File");
            fileChooser.setInitialDirectory(new File("src/main/resources/saved stories"));
            fileChooser.getExtensionFilters().add(pathsExtension);
            File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile == null)
                return;

            PathsFile selectedPathsFile = new PathsFile(selectedFile);
            if (pathsFiles.contains(selectedPathsFile)) {
                storySelectorView.fileAlreadySelectedAlert().show();
                return;
            }
            pathsFiles.add(selectedPathsFile);
        });
    }

    private void getFilesInDirectory() {
        File directory = new File("src/main/resources/test_stories");
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".paths"));
        if (files != null) {
            Arrays.stream(files).forEach(file -> pathsFiles.add(new PathsFile(file)));
        }
    }

    private void setStoryTableActions() {
        storySelectorView.getStoryTable().setOnMousePressed(mouseEvent -> {

            if (mouseEvent.isPrimaryButtonDown() && (mouseEvent.getClickCount() == 2)) {
                //TODO: Show confirmation alert
                //TODO: Callback get button type is ok
                PathsFile pathsFile = storySelectorView.getStoryTable().getSelectionModel().getSelectedItem();
                CreatePlayerController createPlayerController = new CreatePlayerController(new CreatePlayerView(), stage, pathsFile, null);

            }
        });
    }

}
