package edu.ntnu.mappe32.view;

import edu.ntnu.mappe32.model.PathsFile;
import edu.ntnu.mappe32.model.StorySelecter;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.IOException;

public class StorySelecterView {
    Scene scene;
    Button addStoryButton;
    TableView<PathsFile> storyTable;

    public StorySelecterView() {
        this.storyTable = new TableView<>();
        this.addStoryButton = new Button("Add another .paths file");
        addStoryButton.setLayoutX(1280);
        StackPane root = new StackPane();
        root.getChildren().addAll(storyTable, addStoryButton);
        this.scene = new Scene(root, 1280,720);
    }

    public void createStoryTable() {
        TableColumn<PathsFile, String> storyTitleColumn = new TableColumn<>("Story Title");
        storyTitleColumn.setCellValueFactory(new PropertyValueFactory<>("storyTitle"));
        storyTitleColumn.prefWidthProperty().bind(storyTable.widthProperty().divide(9));
        TableColumn<PathsFile, String> fileLocationColumn = new TableColumn<>("File Location");
        fileLocationColumn.setCellValueFactory(new PropertyValueFactory<>("filePath"));
        fileLocationColumn.prefWidthProperty().bind(storyTable.widthProperty().divide(1.29));
        TableColumn<PathsFile, Integer> brokenLinksColumn = new TableColumn<>("Broken Links");
        brokenLinksColumn.setCellValueFactory(new PropertyValueFactory<>("brokenLinks"));
        brokenLinksColumn.prefWidthProperty().bind(storyTable.widthProperty().divide(9));
        storyTable.getColumns().addAll(storyTitleColumn, fileLocationColumn, brokenLinksColumn);
    }

    public void fillStoryTable() {
        storyTable.setItems(FXCollections.observableArrayList(new StorySelecter().getListPathsFiles()));

    }

    public Button getAddStoryButton() {
        return addStoryButton;
    }

    public void addPathsFile(File file) throws IOException {
        storyTable.getItems().add(new PathsFile(file));
    }

    public TableView<PathsFile> getStoryTable() {
        return storyTable;
    }

    public Scene getScene() {
        return scene;
    }
}
