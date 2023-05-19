package edu.ntnu.mappe32.controller;

import edu.ntnu.mappe32.model.Game;
import edu.ntnu.mappe32.model.Item;
import edu.ntnu.mappe32.model.PathsFile;
import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.model.goal_related.*;
import edu.ntnu.mappe32.view.CreateGoalsView;
import edu.ntnu.mappe32.view.CreatePlayerView;
import edu.ntnu.mappe32.view.PassageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static edu.ntnu.mappe32.view.PassageView.resizableMainFont;

public class CreateGoalsController {
    private final PathsFile pathsFile;
    private final CreateGoalsView view;
    private final Player player;
    private final ObservableList<Goal> createdGoals;
    private final List<String> itemsAsString;
    private final HashMap<Item, Integer> mandatoryItems;
    private final Stage stage;
    private Tooltip addGoalButtonTooltip;

    public CreateGoalsController(Stage stage, CreateGoalsView view, Player player, PathsFile pathsFile) {
        this.player = player;
        this.pathsFile = pathsFile;
        this.view = view;
        createdGoals = FXCollections.observableArrayList();
        this.itemsAsString = pathsFile.getStory().getItems()
                .stream().map(Item::getItemName).collect(Collectors.toList());
        this.mandatoryItems = new HashMap<>();
        view.getGoalsListView().setItems(createdGoals);
        view.getValueTypeLabel().setText("Health Goal Value:");
        setTopActions();
        setCenterActions();
        stage.setScene(view.getScene());
        this.stage = stage;
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

        view.getBackButton().setOnMouseClicked(mouseEvent -> {
            CreatePlayerController createPlayerController = new CreatePlayerController(new CreatePlayerView(), stage, pathsFile, player);
        });

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
                    Objects.requireNonNull(getClass().getResource("/StyleSheets/DialogBoxStyleSheet.css")).toExternalForm());
            information.show();
        });
    }
    private void setCenterActions() {
        addGoalButtonTooltip = createTooltip("Press to add your Goal", 18);
        setGoalIconActions();
        setAddGoalButton1Actions();
        setAddGoalButton2Actions();
        setListViewActions();
        setAddGoalToInventoryActions();
        setStartButtonActions();
        setGoalsIconsTooltips();
    }
    private void setStartButtonActions() {
        Tooltip startButtonTooltip = createTooltip("Press To Start Game!", 18);

        Tooltip.install(view.getStartButton(), startButtonTooltip);
        view.getStartButton().setPickOnBounds(true);
        view.getStartButton().setOnMouseClicked(mouseEvent -> {
            if (createdGoals.isEmpty()) {
                showNoGoalsAlert();
                return;
            }
            Game game = new Game(player, pathsFile.getStory(), createdGoals);
            PassageView passageView = new PassageView();
            PassageViewController passageViewController = new PassageViewController(stage, passageView, game);
            stage.setScene(passageView.getScene());
        });
    }

    private void setGoalsIconsTooltips() {
        Tooltip healthIconTooltip = createTooltip("Create a Health Goal!", 15);
        Tooltip scoreIconTooltip = createTooltip("Create a Score Goal!", 15);
        Tooltip goldIconTooltip = createTooltip("Create a Gold Goal!", 15);
        Tooltip inventoryIconTooltip = createTooltip("Create an Inventory Goal!", 15);
        view.getHealthIcon().setPickOnBounds(true);
        view.getScoreIcon().setPickOnBounds(true);
        view.getGoldIcon().setPickOnBounds(true);
        view.getInventoryIcon().setPickOnBounds(true);
        Tooltip.install(view.getHealthIcon(), healthIconTooltip);
        Tooltip.install(view.getScoreIcon(), scoreIconTooltip);
        Tooltip.install(view.getGoldIcon(), goldIconTooltip);
        Tooltip.install(view.getInventoryIcon(), inventoryIconTooltip);
    }
    private void setGoalIconActions() {
        String goalValueText = " Goal Value:";
        view.getHealthIcon().setOnMouseClicked(mouseEvent -> {
            view.getP1().setVisible(true);
            view.getP2().setVisible(false);
            view.getP3().setVisible(false);
            view.getP4().setVisible(false);

            view.getInvalidValueBox().setVisible(false);
            view.getValueTextField().clear();
            view.getValueTextField().setStyle(null);

            view.getCurrentMandatoryInventory().getItems().clear();
            view.getInventoryGoalBox().setManaged(false);
            view.getInventoryGoalBox().setVisible(false);
            view.getNumericalGoalBox().setManaged(true);
            view.getNumericalGoalBox().setVisible(true);

            view.getValueTypeLabel().setText("Health" + goalValueText);
        });

        view.getScoreIcon().setOnMouseClicked(mouseEvent -> {
            view.getP1().setVisible(false);
            view.getP2().setVisible(true);
            view.getP3().setVisible(false);
            view.getP4().setVisible(false);

            view.getInvalidValueBox().setVisible(false);
            view.getValueTextField().clear();
            view.getValueTextField().setStyle(null);

            view.getCurrentMandatoryInventory().getItems().clear();
            view.getInventoryGoalBox().setManaged(false);
            view.getInventoryGoalBox().setVisible(false);
            view.getNumericalGoalBox().setManaged(true);
            view.getNumericalGoalBox().setVisible(true);

            view.getValueTypeLabel().setText("Score" + goalValueText);
        });
        view.getGoldIcon().setOnMouseClicked(mouseEvent -> {
            view.getP1().setVisible(false);
            view.getP2().setVisible(false);
            view.getP3().setVisible(true);
            view.getP4().setVisible(false);

            view.getInvalidValueBox().setVisible(false);
            view.getValueTextField().clear();
            view.getValueTextField().setStyle(null);

            view.getCurrentMandatoryInventory().getItems().clear();
            view.getInventoryGoalBox().setManaged(false);
            view.getInventoryGoalBox().setVisible(false);
            view.getNumericalGoalBox().setManaged(true);
            view.getNumericalGoalBox().setVisible(true);

            view.getValueTypeLabel().setText("Gold" + goalValueText);
        });

        view.getInventoryIcon().setOnMouseClicked(mouseEvent -> {
            view.getP1().setVisible(false);
            view.getP2().setVisible(false);
            view.getP3().setVisible(false);
            view.getP4().setVisible(true);

            view.getInvalidValueBox().setVisible(false);
            view.getValueTextField().clear();
            view.getValueTextField().setStyle(null);

            view.getNumericalGoalBox().setManaged(false);
            view.getNumericalGoalBox().setVisible(false);
            view.getInventoryGoalBox().setManaged(true);
            view.getInventoryGoalBox().setVisible(true);
            List<String> copy = new ArrayList<>(itemsAsString);

            view.getSelectItem().setItems(FXCollections.observableArrayList(copy));
        });
    }

    private void setAddGoalButton1Actions() {
        view.getAddGoalButton1().setOnMouseClicked(mouseEvent -> {
            if (view.getNumericalGoalBox().isVisible()) {
                if (validateGoalValueInput()) {
                    if (view.getP2().isVisible()) {
                        createdGoals.add(new ScoreGoal(Integer.parseInt(view.getValueTextField().getText())));
                        view.getValueTextField().clear();
                    }
                    if (view.getP1().isVisible()) {
                        createdGoals.add(new HealthGoal(Integer.parseInt(view.getValueTextField().getText())));
                        view.getValueTextField().clear();
                    }
                    if (view.getP3().isVisible()) {
                        createdGoals.add(new GoldGoal(Integer.parseInt(view.getValueTextField().getText())));
                        view.getValueTextField().clear();
                    }
                }
            }
        });
        Tooltip.install(view.getAddGoalButton1(), addGoalButtonTooltip);
    }

    private void setAddGoalButton2Actions() {
        view.getAddGoalButton2().setOnMouseClicked(mouseEvent -> {
            if (mandatoryItems.isEmpty()) {
                showNoMandatoryItemAlert();
                return;
            }
            createdGoals.add(new InventoryGoal(new HashMap<>(mandatoryItems)));
            view.getCurrentMandatoryInventory().getItems().forEach(item -> view
                    .getSelectItem().getItems().add(item.substring(0, item.lastIndexOf(':'))));
            view.getCurrentMandatoryInventory().getItems().clear();
            view.getQuantity().clear();
            mandatoryItems.clear();
        });
        Tooltip.install(view.getAddGoalButton2(),addGoalButtonTooltip);
    }
    private void setAddGoalToInventoryActions() {
        view.getAddToInventoryGoal().setOnMouseClicked(mouseEvent -> {
            if (view.getSelectItem().getItems().isEmpty())
                return;
            if (validateQuantityInput() & view.getSelectItem().getValue() != null) {
                Optional<Item> selectedItem = pathsFile.getStory().getItems().stream()
                        .filter(item -> item.getItemName().equals(view.getSelectItem().getValue()))
                        .findFirst();
                Integer quantity = Integer.parseInt(view.getQuantity().getText());
                Item item;

                if (selectedItem.isPresent()) {
                    item = selectedItem.get();
                    mandatoryItems.put(item, quantity);
                    view.getCurrentMandatoryInventory().getItems().add((item.getItemName()) + ": " + quantity);
                    view.getSelectItem().getItems().remove(item.getItemName());
                    view.getQuantity().clear();
                }
            }
        });
    }

    private void setListViewActions() {
        view.getGoalsListView().setOnMouseClicked(mouseEvent -> createdGoals
                .remove(view.getGoalsListView().getSelectionModel().getSelectedItem()));
        view.getCurrentMandatoryInventory().setOnMouseClicked(mouseEvent -> {
            String item = view.getCurrentMandatoryInventory().getSelectionModel().getSelectedItem();
            if (item != null) {
                String itemName = item.substring(0, item.lastIndexOf(':'));
                view.getCurrentMandatoryInventory().getItems()
                        .remove(item);
                view.getSelectItem().getItems().add(itemName);
                Optional<Item> removalItem = mandatoryItems.keySet()
                        .stream().filter(item1 -> item1.getItemName().equals(itemName)).findFirst();

                removalItem.ifPresent(remove -> mandatoryItems.remove(removalItem.get()));

                }
            });
    }
    private boolean validateGoalValueInput() {
        String numbersOnlyRegex = "^[0-9]+$";
        if (!Pattern.matches(numbersOnlyRegex, view.getValueTextField().getText())) {
            setInvalidTextFieldStyle(view.getValueTextField());
            view.getInvalidValueBox().setVisible(true);
            return false;
        }
        view.getValueTextField().setStyle(null);
        view.getInvalidValueBox().setVisible(false);
        return true;
    }
    private void setInvalidTextFieldStyle(TextField textField) {
        String borderColor = "red;";
        String borderWidth = "1px;";
        textField.setStyle("-fx-border-color: " + borderColor +
                "-fx-border-width: " + borderWidth);
    }
    /**
     * This method validates the input of the playerHealthTextField
     * It returns false if the input contains other characters than numbers or is empty
     * Otherwise, it returns true
     * @return false if input contains other characters than numbers or is empty, as boolean
     *         true otherwise, as boolean
     */
    private boolean validateQuantityInput() {
        String numbersOnlyRegex = "^[0-9]+$";
        if (!Pattern.matches(numbersOnlyRegex, view.getQuantity().getText())) {
            setInvalidTextFieldStyle(view.getQuantity());
            return false;
        }
        view.getQuantity().setStyle(null);
        return true;
    }
    private void showNoMandatoryItemAlert() {
        Alert noMandatoryItems = new Alert(Alert.AlertType.WARNING);
        noMandatoryItems.setTitle("No Mandatory Items");
        noMandatoryItems.setHeaderText("Your Inventory Goal has no mandatory items!");
        noMandatoryItems.setContentText("Select and enter the mandatory quantities for each item you want to include in your item goal!");
        DialogPane dialogPane = noMandatoryItems.getDialogPane();
        dialogPane.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/StyleSheets/DialogBoxStyleSheet.css")).toExternalForm());
        noMandatoryItems.show();
    }

    private void showNoGoalsAlert() {
        Alert noGoals = new Alert(Alert.AlertType.WARNING);
        noGoals.setTitle("No Goals!");
        noGoals.setHeaderText("You have no goals for your Game!");
        noGoals.setContentText("You must have atleast one goal before proceeding into a Game!");
        DialogPane dialogPane = noGoals.getDialogPane();
        dialogPane.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/StyleSheets/DialogBoxStyleSheet.css")).toExternalForm());
        noGoals.show();
    }
}
