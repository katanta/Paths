package edu.ntnu.mappe32.view;

import edu.ntnu.mappe32.model.PathsFile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;
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
    private Image backButtonImage;
    private Image backButtonHover;
    private ImageView backButton;

    public StorySelectorView() {
        configureTop();
        configureCenter();
        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setCenter(center);
        root.getChildren().add(backButton);
        this.scene = new Scene(root, 1280, 720);
    }


    public void configureTop() {
        top = new HBox(50);
        Label selectYourStory = new Label("Select Your Story!");
        selectYourStory.setFont(Font.loadFont("file:src/main/resources/fonts/PixeloidSansBold.ttf", 50));
        selectYourStory.setMaxWidth(700);
        try {
            backButtonImage = new Image(new FileInputStream("src/main/resources/img/restartButton.png"));
            backButtonHover = new Image(new FileInputStream("src/main/resources/img/restartButtonHover.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        backButton = new ImageView(backButtonImage);
        backButton.setFitWidth(62);
        backButton.setFitHeight(62);
        backButton.setX(20);
        backButton.setY(20);
        backButton.setPickOnBounds(true);
        top.getChildren().addAll(selectYourStory);
        top.setAlignment(Pos.CENTER);

        top.setPadding(new Insets(20, 0, 0, 0));
    }

    private void configureCenter() {
        center = new VBox(30);
        center.setAlignment(Pos.TOP_CENTER);
        center.setPadding(new Insets(30, 0, 0, 0));
        center.setMaxWidth(1000);
        Label clickStoryLabel = new Label("Double-click to Play a Story!");
        clickStoryLabel.setFont(Font.loadFont("file:src/main/resources/fonts/PixeloidSansBold.ttf", 30));
        clickStoryLabel.setStyle("-fx-text-fill: #2b862c");
        createStoryTable();
        setCellFactories();
        configureAddButton();
        center.getChildren().addAll(clickStoryLabel, storyTable, addPathsFile);
    }

    private void configureAddButton() {
        addPathsFile = new Button("Add Paths File");
        addPathsFile.setFont(Font.loadFont("file:src/main/resources/fonts/PixeloidSans.ttf", 30));
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
                    setFont(Font.loadFont("file:src/main/resources/fonts/PixeloidSans.ttf", 12));
                    setText(string);
                    Tooltip tooltip = new Tooltip(string);
                    tooltip.setFont(Font.loadFont("file:src/main/resources/fonts/PixeloidSans.ttf", 10));
                    tooltip.setShowDelay(Duration.millis(100));
                    tooltip.setShowDuration(Duration.INDEFINITE);
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
                    setFont(Font.loadFont("file:src/main/resources/fonts/PixeloidSans.ttf", 12));
                    setText(string);
                    Tooltip tooltip = new Tooltip(string);
                    tooltip.setFont(Font.loadFont("file:src/main/resources/fonts/PixeloidSans.ttf", 10));
                    tooltip.setShowDelay(Duration.millis(100));
                    tooltip.setShowDuration(Duration.INDEFINITE);
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
                    setFont(Font.loadFont("file:src/main/resources/fonts/PixeloidSans.ttf", 12));
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

    public Image getBackButtonImage() {
        return backButtonImage;
    }
}
