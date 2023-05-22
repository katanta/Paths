package edu.ntnu.mappe32.view;

import edu.ntnu.mappe32.ViewUtils;
import edu.ntnu.mappe32.model.PathsFile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class StorySelectorView {
    private final Scene scene;
    private TableView<PathsFile> storyTable;
    private HBox top;
    private TableColumn<PathsFile, String> storyTitleColumn;
    private TableColumn<PathsFile, String> fileLocationColumn;
    private TableColumn<PathsFile, Integer> brokenLinksColumn;
    private VBox center;
    private Button addPathsFile;
    private Image backButtonHover;
    private ImageView backButton;

    public StorySelectorView() {
        configureTop();
        configureCenter();
        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setCenter(center);
        this.scene = new Scene(root, 1280, 720);
    }


    public void configureTop() {
        top = new HBox(270);
        Label selectYourStory = new Label("Select Your Story!");
        selectYourStory.setFont(ViewUtils.pixeloidSansBold(50));
        selectYourStory.setMaxWidth(700);

        HBox backButtonHBox = new HBox(10);
        backButton = new ImageView(ViewUtils.restartImage());
        backButton.setFitWidth(62);
        backButton.setFitHeight(62);
        backButton.setPickOnBounds(true);


        backButtonHBox.getChildren().addAll(backButton);
        backButtonHBox.setAlignment(Pos.TOP_LEFT);
        top.getChildren().addAll(backButtonHBox, selectYourStory);

        top.setPadding(new Insets(20, 0, 0, 20));
    }

    private void configureCenter() {
        center = new VBox(30);
        center.setAlignment(Pos.TOP_CENTER);
        center.setPadding(new Insets(30, 0, 0, 0));
        center.setMaxWidth(1000);
        Label clickStoryLabel = new Label("Double-click to Play a Story!");
        clickStoryLabel.setFont(ViewUtils.pixeloidSansBold(30));
        clickStoryLabel.setStyle("-fx-text-fill: #2b862c");
        createStoryTable();
        setCellFactories();
        configureAddButton();
        center.getChildren().addAll(clickStoryLabel, storyTable, addPathsFile);
    }

    private void configureAddButton() {
        addPathsFile = new Button("Add Paths File");
        addPathsFile.setFont(ViewUtils.pixeloidSans(30));
    }

    public void createStoryTable() {
        storyTable = new TableView<>();
        storyTable.setFixedCellSize(40);
        storyTable.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/StyleSheets/TableViewStyleSheet.css")).toExternalForm());
        storyTitleColumn = new TableColumn<>("Story Title");
        fileLocationColumn = new TableColumn<>("File Location");
        brokenLinksColumn = new TableColumn<>("Broken Links");
        storyTitleColumn.setCellValueFactory(new PropertyValueFactory<>("storyTitle"));
        fileLocationColumn.setCellValueFactory(new PropertyValueFactory<>("filePath"));
        brokenLinksColumn.setCellValueFactory(new PropertyValueFactory<>("brokenLinks"));

        storyTitleColumn.prefWidthProperty().bind(storyTable.widthProperty().multiply(0.15));
        fileLocationColumn.prefWidthProperty().bind(storyTable.widthProperty().multiply(0.67));
        brokenLinksColumn.prefWidthProperty().bind(storyTable.widthProperty().multiply(0.18));
        storyTable.getColumns().addAll(storyTitleColumn, fileLocationColumn, brokenLinksColumn);
    }

    private void setCellFactories() {
        storyTitleColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String string, boolean empty) {
                setGraphic(null);
                setText(null);

                if (string != null && !empty) {
                    setFont(ViewUtils.pixeloidSans(12));
                    setText(string);
                    Tooltip tooltip = ViewUtils.createTooltip(string, 10);

                    setTooltip(tooltip);
                }
            }
        });

        fileLocationColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String string, boolean empty) {
                setGraphic(null);
                setText(null);
                if (string != null && !empty) {
                    setFont(ViewUtils.pixeloidSans(12));
                    setText(string);
                    Tooltip tooltip = ViewUtils.createTooltip(string, 10);
                    setTooltip(tooltip);
                }
            }
        });

        brokenLinksColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Integer integer, boolean empty) {
                setGraphic(null);
                setText(null);
                if (integer != null && !empty) {
                    setFont(ViewUtils.pixeloidSans(12));
                    setText(integer.toString());
                }
            }
        });
    }

    public Button getAddPathsFileButton() {
        return addPathsFile;
    }

    public TableView<PathsFile> getStoryTable() {
        return storyTable;
    }

    public Scene getScene() {
        return scene;
    }

    public Alert fileAlreadySelectedAlert() {
        Alert fileAlreadySelected = new Alert(Alert.AlertType.WARNING);
        fileAlreadySelected.setContentText("The file already exists in the table.");
        return fileAlreadySelected;
    }

    public ImageView getBackButton() {
        return backButton;
    }

    public Image getBackButtonHover() {
        return backButtonHover;
    }

}
