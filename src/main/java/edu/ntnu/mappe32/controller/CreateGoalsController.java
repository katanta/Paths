package edu.ntnu.mappe32.controller;

import edu.ntnu.mappe32.model.PathsFile;
import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.view.CreateGoalsView;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.util.Duration;


import static edu.ntnu.mappe32.view.PassageView.resizableMainFont;

public class CreateGoalsController {
    private final PathsFile pathsFile;
    private final CreateGoalsView view;
    private final Player player;

    public CreateGoalsController(Stage stage, CreateGoalsView view, Player player, PathsFile pathsFile) {
        this.player = player;
        this.pathsFile = pathsFile;
        this.view = view;
        setTopActions();
        stage.setScene(view.getScene());
    }

    private void setTopActions() {

        // Set info button tooltip and change image
        Tooltip tooltip = createTooltip("See Player and Story information", 18);
        Tooltip.install(view.getInfoButton(), tooltip);
        view.getInfoButton().setOnMouseEntered(mouseEvent -> view.getInfoButton().setImage(view.getInfoButtonHover()));
        view.getInfoButton().setOnMouseExited(mouseEvent -> view.getInfoButton().setImage(view.getInfoButtonImage()));
        showInformationBox();

        // Set back button tooltip and change image
        Tooltip backButtonTooltip = createTooltip("Back to Create Player", 18);
        Tooltip.install(view.getBackButton(), backButtonTooltip);
        view.getBackButton().setOnMouseEntered(mouseEvent -> view.getBackButton().setImage(view.getBackButtonHover()));
        view.getBackButton().setOnMouseExited(mouseEvent -> view.getBackButton().setImage(view.getBackButtonImage()));

    }

    private Tooltip createTooltip(String text, int fontSize) {
        Tooltip tooltip = new Tooltip(text);
        tooltip.setShowDelay(Duration.ZERO);
        tooltip.setShowDuration(Duration.INDEFINITE);
        tooltip.setFont(resizableMainFont(fontSize));
        return tooltip;
    }
    private void showInformationBox() {
        view.getInfoButton().setOnMouseClicked(mouseEvent -> {
            Alert information = new Alert(Alert.AlertType.INFORMATION);
            information.setTitle("Player and Story Information");
            information.setHeaderText("Information on " + player.getName() + " and '" + pathsFile.getStoryTitle() + "'");
            String playerStats = player.getName() + "'s Health: " + player.getHealth() + "\n" + player.getName() + "'s Gold: " + player.getGold() + "\n";
            information.setContentText(playerStats + "\nBroken Links: " + pathsFile.getBrokenLinks() + "\n\nFile Path: " + pathsFile.getFilePath());
            DialogPane dialogPane = information.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/StyleSheets/DialogBoxStyleSheet.css").toExternalForm());
            information.show();
        });
    }
}
