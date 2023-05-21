package edu.ntnu.mappe32.view;

import edu.ntnu.mappe32.model.goal_related.Goal;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;

import static edu.ntnu.mappe32.view.PassageView.resizableMainFont;


public class CreateGoalsView {
    private final Scene scene;
    private HBox top;
    private ImageView backButton;
    private Image backButtonHover;
    private ImageView infoButton;
    private Image infoButtonHover;
    private Image infoButtonImage;
    private Image backButtonImage;
    private ListView<Goal> goalsListView;
    private VBox centerVBox;
    private HBox goalBox;
    private ImageView healthIcon;
    private ImageView scoreIcon;
    private ImageView goldIcon;
    private ImageView inventoryIcon;
    private HBox pointerBox;
    private ImageView p1;
    private ImageView p2;
    private ImageView p3;
    private ImageView p4;
    private Label valueTypeLabel;
    private VBox numericalGoalBox;
    private VBox inventoryGoalBox;
    private TextField valueTextField;
    private ImageView addGoalButton1;
    private ImageView addGoalButton2;
    private final Font statLabelFont;
    private final Font textFieldFont;
    private Image addGoalButtonImage;
    private HBox invalidValueBox;
    private TextField quantity;
    private ComboBox<String> selectItem;
    private Button addToInventoryGoal;
    private ListView<String> currentMandatoryInventory;
    private ImageView startButton;
    private ImageView helpButton;
    private Image helpButtonImage;
    private Image helpButtonHover;
    private HBox helpAndBackButtonHBox;

    private ImageView tutorialImageView;
    private final BorderPane root;

    public CreateGoalsView() {
        this.root = new BorderPane();
        statLabelFont = resizableMainFont(30);
        textFieldFont = Font.loadFont("file:src/main/resources/fonts/PixeloidSans.ttf", 20);

        configureTop();
        configureCenter();
        configureTutorialImageView();
        root.setTop(top);
        root.setCenter(centerVBox);
        root.getChildren().addAll(helpAndBackButtonHBox, startButton);
        this.scene = new Scene(root, 1280, 720);
    }
    private void configureTop() {
        top = new HBox(50);

        try {
            helpButtonImage = new Image(new FileInputStream("src/main/resources/img/helpButton.png"));
            helpButtonHover = new Image(new FileInputStream("src/main/resources/img/helpButtonHover.png"));
            backButtonImage = new Image(new FileInputStream("src/main/resources/img/restartButton.png"));
            backButtonHover = new Image(new FileInputStream("src/main/resources/img/restartButtonHover.png"));
            infoButtonImage = new Image(new FileInputStream("src/main/resources/img/info.png"));
            infoButtonHover = new Image(new FileInputStream("src/main/resources/img/info_hover.png"));
            startButton = new ImageView(new Image(new FileInputStream("src/main/resources/img/start.png")));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        startButton.setFitWidth(200);
        startButton.setFitHeight(100);
        startButton.setX(1020);
        startButton.setY(560);
        startButton.setPickOnBounds(true);
        infoButton = new ImageView(infoButtonImage);

        infoButton.setPickOnBounds(true);
        infoButton.setFitWidth(31);
        infoButton.setFitHeight(31);
        configureHelpAndBackButton();

        Label createYourGoals = new Label("Create Your Goals!");
        createYourGoals.setFont(Font.loadFont("file:src/main/resources/fonts/PixeloidSansBold.ttf", 50));
        createYourGoals.setMaxWidth(700);

        top.getChildren().addAll(createYourGoals, infoButton);
        top.setAlignment(Pos.CENTER);

        top.setPadding(new Insets(20, 0,0,50));

    }
    private void configureHelpAndBackButton() {
        helpAndBackButtonHBox = new HBox(10);
        helpAndBackButtonHBox.setAlignment(Pos.TOP_LEFT);
        helpAndBackButtonHBox.setPadding(new Insets(20,0,0,20));
        helpButton = new ImageView(helpButtonImage);
        helpButton.setFitWidth(62);
        helpButton.setFitHeight(62);
        helpButton.setPickOnBounds(true);
        backButton = new ImageView(backButtonImage);
        backButton.setFitWidth(62);
        backButton.setFitHeight(62);
        backButton.setPickOnBounds(true);

        helpAndBackButtonHBox.getChildren().addAll(backButton, helpButton);
    }
    private void configureGoalsListView() {
        this.goalsListView = new ListView<>();
        goalsListView.setMaxWidth(500);
        goalsListView.setPrefHeight(100);
        goalsListView.setFixedCellSize(40);
        getGoalsListView().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        goalsListView.setCellFactory(param -> new ListCell<>() {
            @Override
            public void updateItem(Goal goal, boolean empty) {
                super.updateItem(goal, empty);

                setText(null);
                setStyle(null);
                setGraphic(null);
                if (goal != null && !empty) {
                    GoalCell cell = new GoalCell(goal);
                    setGraphic(cell.getHBox());
                    Tooltip cellTooltip = new Tooltip("Click to remove '" + goal.goalType() + " Goal: " + goal.goalValue() + "'");
                    cellTooltip.setShowDelay(Duration.ZERO);
                    cellTooltip.setShowDuration(Duration.INDEFINITE);
                    setTooltip(cellTooltip);
                    cellTooltip.setFont(resizableMainFont(20));
                    cellTooltip.setStyle("-fx-text-fill: red;");
                }
            }
        });
    }
    private void configureCenter() {
        centerVBox = new VBox(10);
        centerVBox.setPadding(new Insets(15,0,0,0));
        centerVBox.setAlignment(Pos.TOP_CENTER);
        Label yourGoals = new Label("Your Goals");
        yourGoals.setFont(resizableMainFont(30));
        yourGoals.setAlignment(Pos.CENTER);
        configureGoalsListView();
        configureGoalBox();
        configurePointerBox();
        configureNumericalGoalBox();
        configureInventoryGoalBox();
        centerVBox.getChildren().addAll(yourGoals, goalsListView, pointerBox, goalBox, numericalGoalBox, inventoryGoalBox);
    }
    private void configureGoalBox() {
     goalBox = new HBox(80);
     try {
         healthIcon = new ImageView(new Image(new FileInputStream("src/main/resources/img/hp-removebg-preview.png")));
         scoreIcon = new ImageView(new Image(new FileInputStream("src/main/resources/img/score-removebg-preview.png")));
         goldIcon = new ImageView(new Image(new FileInputStream("src/main/resources/img/gold-removebg-preview.png")));
         inventoryIcon = new ImageView(new Image(new FileInputStream("src/main/resources/img/inventory-removebg-preview.png")));
         addGoalButtonImage = new Image(new FileInputStream("src/main/resources/img/Add.png"));
     } catch (IOException e) {
         throw new RuntimeException(e.getMessage());
     }

     healthIcon.setFitHeight(50);
     healthIcon.setFitWidth(50);
     scoreIcon.setFitWidth(50);
     scoreIcon.setFitHeight(50);
     goldIcon.setFitWidth(50);
     goldIcon.setFitHeight(50);
     inventoryIcon.setFitWidth(50);
     inventoryIcon.setFitHeight(50);
     goalBox.getChildren().addAll(healthIcon, scoreIcon, goldIcon, inventoryIcon);

        goalBox.setMaxWidth(500);
        goalBox.setMaxHeight(100);
        goalBox.setPadding(new Insets(10,0,10,20));

    }

    private void configurePointerBox() {
        final int imageSize = 20;
        Image pointerImage;
        try {
            pointerImage = new Image(new FileInputStream("src/main/resources/img/Pointer.png"));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        p1 = new ImageView(pointerImage);
        p1.setFitWidth(imageSize);
        p1.setFitHeight(imageSize);
        p1.setVisible(true);
        p2 = new ImageView(pointerImage);
        p2.setFitWidth(imageSize);
        p2.setVisible(false);
        p2.setFitHeight(imageSize);
        p3 = new ImageView(pointerImage);
        p3.setFitWidth(imageSize);
        p3.setFitHeight(imageSize);
        p3.setVisible(false);
        p4 = new ImageView(pointerImage);
        p4.setFitWidth(imageSize);
        p4.setFitHeight(imageSize);
        p4.setVisible(false);
        pointerBox = new HBox(110);
        pointerBox.setPadding(new Insets(0,0,0,35));
        pointerBox.getChildren().addAll(p1, p2, p3, p4);
        pointerBox.setMaxWidth(500);
        pointerBox.setMaxHeight(50);
    }

    private void configureNumericalGoalBox() {
        configureInvalidValueBox();
        numericalGoalBox = new VBox(50);
        numericalGoalBox.setPadding(new Insets(50,0,0,0));
        HBox goalValueBar = new HBox();
        valueTypeLabel = new Label();
        valueTypeLabel.setFont(statLabelFont);
        valueTextField = new TextField();
        valueTextField.setMaxWidth(150);
        valueTextField.setFont(textFieldFont);
        valueTextField.setPromptText("Enter value");
        valueTextField.setTextFormatter(maxCharactersFormatter(9));
        HBox.setMargin(valueTextField, new Insets(0,0,0,20));
        VBox addGoalButtonBox = new VBox();
        addGoalButton1 = new ImageView(addGoalButtonImage);
        addGoalButton1.setFitHeight(100);
        addGoalButton1.setFitWidth(200);
        addGoalButtonBox.getChildren().addAll(addGoalButton1);
        addGoalButtonBox.setAlignment(Pos.TOP_CENTER);
        numericalGoalBox.setMaxWidth(800);
        goalValueBar.getChildren().addAll(valueTypeLabel, valueTextField, invalidValueBox);
        goalValueBar.setPadding(new Insets(0,0,0,150));
        numericalGoalBox.getChildren().addAll(goalValueBar, addGoalButtonBox);
        numericalGoalBox.setVisible(true);
        numericalGoalBox.setManaged(true);
    }
    private void configureInventoryGoalBox() {
        inventoryGoalBox = new VBox(20);
        inventoryGoalBox.setPadding(new Insets(10,0,0,0));

        HBox currentGoalBar = new HBox(10);

        Label currentGoal = new Label("Mandatory Items:");

        currentGoal.setFont(statLabelFont);
        currentMandatoryInventory = new ListView<>();
        currentMandatoryInventory.setMaxHeight(115);
        currentMandatoryInventory.setPrefWidth(280);

        currentMandatoryInventory.setCellFactory(cell -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(null);
                setText(null);
                if (item != null) {
                    setText(item);
                    setFont(Font.loadFont("file:src/main/resources/fonts/PixeloidSans.ttf", 15));
                    Tooltip tooltip = new Tooltip("Click to remove " + item);
                    tooltip.setFont(Font.loadFont("file:src/main/resources/fonts/PixeloidSans.ttf", 15));
                    tooltip.setStyle("-fx-text-fill: red;");
                    tooltip.setShowDuration(Duration.INDEFINITE);
                    tooltip.setShowDelay(Duration.ZERO);
                    setTooltip(tooltip);
                }
            }
        });

        currentGoalBar.getChildren().addAll(currentGoal, currentMandatoryInventory);

        selectItem = new ComboBox<>();

        selectItem.setPrefWidth(210);

        String fontFamily = "Pixeloid Sans";
        selectItem.setStyle("-fx-font-family: " + fontFamily + ";");
        selectItem.setStyle("-fx-font-size: 20;");
        selectItem.setButtonCell(new ListCell<>() {
             @Override
             protected void updateItem(String item, boolean empty) {
                 super.updateItem(item, empty);
                 if (item == null || empty) {
                     setText(selectItem.getPromptText());
                 } else {
                     setText(item);
                 }
             }
         });

        selectItem.setPromptText("Select Item");
        HBox createInventoryBar = new HBox(20);
        quantity = new TextField();
        quantity.setMaxWidth(110);
        quantity.setFont(textFieldFont);
        quantity.setPromptText("Quantity");
        quantity.setTextFormatter(maxCharactersFormatter(6));
        addToInventoryGoal = new Button("Add to Goal");
        addToInventoryGoal.setFont(resizableMainFont(20));
        createInventoryBar.getChildren().addAll(selectItem, quantity, addToInventoryGoal);
        currentGoalBar.setAlignment(Pos.TOP_CENTER);
        createInventoryBar.setAlignment(Pos.TOP_CENTER);
        VBox addGoalButtonBox = new VBox();
        addGoalButton2 = new ImageView(addGoalButtonImage);
        addGoalButton2.setFitWidth(200);
        addGoalButton2.setFitHeight(100);
        addGoalButtonBox.getChildren().add(addGoalButton2);
        addGoalButtonBox.setAlignment(Pos.TOP_CENTER);
        addGoalButtonBox.setPadding(new Insets(10,0,0,0));
        inventoryGoalBox.getChildren().addAll(currentGoalBar, createInventoryBar, addGoalButtonBox);
        inventoryGoalBox.setVisible(false);
        inventoryGoalBox.setManaged(false);
        inventoryGoalBox.setMaxWidth(600);
        HBox.setMargin(currentGoal, new Insets(40,0,0,0));
    }
    private TextFormatter<TextFormatter.Change> maxCharactersFormatter(int numberOfCharacters) {
        Pattern pattern = Pattern.compile(".{0," + numberOfCharacters + "}");
        return new TextFormatter<>((change -> pattern.matcher(change.getControlNewText()).matches() ? change : null));
    }

    private void configureInvalidValueBox() {
        ImageView errorCircle;
        try {
            errorCircle = new ImageView(new Image(new FileInputStream("src/main/resources/img/error-circle-solid-24.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        errorCircle.setFitWidth(20);
        errorCircle.setFitHeight(20);
        VBox errorCircleBox = new VBox(errorCircle);
        VBox.setMargin(errorCircle, new Insets(9.2,10,0, 10));
        Label invalidValue = new Label("Invalid value");
        invalidValue.setFont(resizableMainFont(15));
        invalidValue.setStyle("-fx-text-fill: #D80D0DFF;");
        invalidValueBox = new HBox();
        invalidValueBox.getChildren().addAll(errorCircleBox, invalidValue);
        HBox.setMargin(invalidValue, new Insets(10,0,0,0));
        invalidValueBox.setVisible(false);
        HBox.setHgrow(invalidValueBox, Priority.ALWAYS);
    }
    private void configureTutorialImageView() {
        try {
            tutorialImageView = new ImageView(new Image(new FileInputStream("src/main/resources/img/createGoalTutorial.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BorderPane.setAlignment(tutorialImageView, Pos.CENTER);
        root.getChildren().add(tutorialImageView);
        tutorialImageView.toBack();
        tutorialImageView.setVisible(false);
    }

    public ImageView getAddGoalButton2() {
        return addGoalButton2;
    }
    public ImageView getBackButton() {
        return backButton;
    }

    public Image getBackButtonImage() {
        return backButtonImage;
    }

    public Image getInfoButtonImage() {
        return infoButtonImage;
    }

    public Image getBackButtonHover() {
        return backButtonHover;
    }

    public Image getInfoButtonHover() {
        return infoButtonHover;
    }

    public ImageView getInfoButton() {
        return infoButton;
    }

    public VBox getNumericalGoalBox() {
        return numericalGoalBox;
    }

    public ImageView getP1() {
        return p1;
    }

    public ImageView getP2() {
        return p2;
    }
    public VBox getInventoryGoalBox() {
        return inventoryGoalBox;
    }

    public HBox getInvalidValueBox() {
        return invalidValueBox;
    }

    public Button getAddToInventoryGoal() {
        return addToInventoryGoal;
    }

    public ListView<String> getCurrentMandatoryInventory() {
        return currentMandatoryInventory;
    }

    public ImageView getP3() {
        return p3;
    }

    public ImageView getGoldIcon() {
        return goldIcon;
    }

    public ImageView getHealthIcon() {
        return healthIcon;
    }

    public ImageView getInventoryIcon() {
        return inventoryIcon;
    }

    public ImageView getScoreIcon() {
        return scoreIcon;
    }

    public Label getValueTypeLabel() {
        return valueTypeLabel;
    }

    public ImageView getStartButton() {
        return startButton;
    }

    public ImageView getP4() {
        return p4;
    }
    public ImageView getAddGoalButton1() {
        return addGoalButton1;
    }

    public TextField getValueTextField() {
        return valueTextField;
    }

    public ListView<Goal> getGoalsListView() {
        return goalsListView;
    }

    public TextField getQuantity() {
        return quantity;
    }

    public ComboBox<String> getSelectItem() {
        return selectItem;
    }

    public Scene getScene() {
        return scene;
    }

    public ImageView getTutorialImageView() {
        return tutorialImageView;
    }

    public Image getHelpButtonHover() {
        return helpButtonHover;
    }

    public Image getHelpButtonImage() {
        return helpButtonImage;
    }

    public ImageView getHelpButton() {
        return helpButton;
    }
}
