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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
    private TextField valueTextField;
    private ImageView addGoalButton;
    private final Font statLabelFont;
    private final Font textFieldFont;


    public CreateGoalsView() {
        statLabelFont = resizableMainFont(30);
        textFieldFont = Font.loadFont("file:src/main/resources/fonts/PixeloidSans.ttf", 20);

        configureTop();
        configureCenter();
        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setCenter(centerVBox);
        root.getChildren().add(backButton);
        this.scene = new Scene(root, 1080, 720);
    }

    private void configureTop() {
        top = new HBox(50);

        try {
            backButtonImage = new Image(new FileInputStream("src/main/resources/img/restartButton.png"));
            backButtonHover = new Image(new FileInputStream("src/main/resources/img/restartButtonHover.png"));
            infoButtonImage = new Image(new FileInputStream("src/main/resources/img/info.png"));
            infoButtonHover = new Image(new FileInputStream("src/main/resources/img/info_hover.png"));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        backButton = new ImageView(backButtonImage);
        infoButton = new ImageView(infoButtonImage);
        backButton.setFitWidth(62);
        backButton.setFitHeight(62);
        backButton.setX(20);
        backButton.setY(20);
        backButton.setPickOnBounds(true);
        infoButton.setPickOnBounds(true);
        infoButton.setFitWidth(31);
        infoButton.setFitHeight(31);

        Label createYourGoals = new Label("Create Your Goals!");
        createYourGoals.setFont(Font.loadFont("file:src/main/resources/fonts/PixeloidSansBold.ttf", 50));
        createYourGoals.setMaxWidth(700);

        top.getChildren().addAll(createYourGoals, infoButton);
        top.setAlignment(Pos.CENTER);

        top.setPadding(new Insets(20, 0,0,50));

    }
    private void configureGoalsListView() {
        this.goalsListView = new ListView<>();
        goalsListView.setMaxWidth(500);
        goalsListView.setPrefHeight(100);
        goalsListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Goal goal, boolean empty) {
                super.updateItem(goal, empty);
                if (goal == null || empty) {
                    setText("");
                    setStyle("");
                } else {
                    GoalCell cell = new GoalCell(goal);
                    setGraphic(cell.getHBox());
                    Tooltip cellTooltip = new Tooltip("Click to remove '" + goal.goalType() + " Goal: " + goal.goalValue() + "'");
                    cellTooltip.setShowDelay(Duration.millis(200));
                    cellTooltip.setShowDuration(Duration.INDEFINITE);
                    setTooltip(cellTooltip);
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
        centerVBox.getChildren().addAll(yourGoals, goalsListView, pointerBox, goalBox, numericalGoalBox);
    }

    private void configureGoalBox() {
     goalBox = new HBox(80);
     try {
         healthIcon = new ImageView(new Image(new FileInputStream("src/main/resources/img/hp-removebg-preview.png")));
         scoreIcon = new ImageView(new Image(new FileInputStream("src/main/resources/img/score-removebg-preview.png")));
         goldIcon = new ImageView(new Image(new FileInputStream("src/main/resources/img/gold-removebg-preview.png")));
         inventoryIcon = new ImageView(new Image(new FileInputStream("src/main/resources/img/inventory-removebg-preview.png")));
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
     goalBox.setStyle("-fx-border-width: 4;" + "-fx-border-color: black;");

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
        p1.setVisible(false);
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
        pointerBox.setPadding(new Insets(0,0,0,40));
        pointerBox.getChildren().addAll(p1, p2, p3, p4);
        pointerBox.setMaxWidth(500);
        pointerBox.setMaxHeight(50);
    }

    private void configureNumericalGoalBox() {
        numericalGoalBox = new VBox(50);
        numericalGoalBox.setPadding(new Insets(50,0,0,0));
        HBox goalValueBar = new HBox();
        valueTypeLabel = new Label(" goal value:");
        valueTypeLabel.setFont(statLabelFont);
        valueTextField = new TextField();
        valueTextField.setFont(textFieldFont);
        valueTextField.setPromptText("Enter value");
        try {
            addGoalButton = new ImageView(new Image(new FileInputStream("src/main/resources/img/Add.png")));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        HBox.setMargin(valueTextField, new Insets(0,0,0,10));
        VBox addGoalButtonBox = new VBox();
        addGoalButton.setFitHeight(50);
        addGoalButton.setFitWidth(100);
        addGoalButtonBox.getChildren().addAll(addGoalButton);
        addGoalButtonBox.setAlignment(Pos.TOP_CENTER);
        numericalGoalBox.setMaxWidth(500);
        goalValueBar.getChildren().addAll(valueTypeLabel, valueTextField);
        numericalGoalBox.getChildren().addAll(goalValueBar, addGoalButtonBox);
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

    public Scene getScene() {
        return scene;
    }
}
