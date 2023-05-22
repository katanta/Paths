package edu.ntnu.mappe32.controller;

import edu.ntnu.mappe32.ViewUtils;
import edu.ntnu.mappe32.model.PathsFile;
import edu.ntnu.mappe32.view.CreatePlayerView;
import edu.ntnu.mappe32.view.FinalSplashScreenView;
import edu.ntnu.mappe32.view.StorySelectorView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class GameSetupController {

    private final ObservableList<PathsFile> pathsFiles;
    private final StorySelectorView storySelectorView;
    private final Stage stage;

    public GameSetupController(Stage stage, StorySelectorView storySelectorView) {
        this.pathsFiles = FXCollections.observableArrayList();
        this.storySelectorView = storySelectorView;
        storySelectorView.getStoryTable().setItems(pathsFiles);
        this.stage = stage;
        stage.setScene(storySelectorView.getScene());

        getFilesInDirectory();
        setStoryTableActions();
        setAddPathsFileAction();
        setBackButtonActions();

    }

    private void setBackButtonActions() {
        storySelectorView.getBackButton().setOnMouseEntered(mouseEvent -> setButtonImage(storySelectorView.getBackButton(), ViewUtils.restartHoverImage()));
        storySelectorView.getBackButton().setOnMouseExited(mouseEvent -> setButtonImage(storySelectorView.getBackButton(), ViewUtils.restartImage()));
        storySelectorView.getBackButton().setOnMouseClicked(mouseEvent -> new FinalSplashScreenController(stage, new FinalSplashScreenView()));
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
                PathsFile pathsFile = storySelectorView.getStoryTable().getSelectionModel().getSelectedItem();
                if (pathsFile == null) {
                    return;
                }
                Optional<ButtonType> result = getConfirmationBox(pathsFile).showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    CreatePlayerController createPlayerController = new CreatePlayerController(new CreatePlayerView(), stage, pathsFile, null);
                }
            }
        });
    }

    private Alert getConfirmationBox(PathsFile pathsFile) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Selected Story");
        confirmation.setHeaderText("Confirm: " + pathsFile.getStoryTitle());
        confirmation.setContentText("Story Title: " + pathsFile.getStoryTitle() + "\n\nBroken Links: " + pathsFile.getBrokenLinks() + "\n\nFile Path: " + pathsFile.getFilePath());
        DialogPane dialogPane = confirmation.getDialogPane();
        dialogPane.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/StyleSheets/DialogBoxStyleSheet.css")).toExternalForm());
        return confirmation;
    }
    private void setButtonImage(ImageView button, Image image) {
        button.setImage(image);
    }
}
