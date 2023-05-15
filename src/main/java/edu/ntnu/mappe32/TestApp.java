package edu.ntnu.mappe32;

import edu.ntnu.mappe32.controller.Game;
import edu.ntnu.mappe32.model.Item;
import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.model.action_related.HealthAction;
import edu.ntnu.mappe32.model.action_related.ScoreAction;
import edu.ntnu.mappe32.model.goal_related.Goal;
import edu.ntnu.mappe32.model.goal_related.GoldGoal;
import edu.ntnu.mappe32.model.goal_related.ScoreGoal;
import edu.ntnu.mappe32.model.story_related.Story;
import edu.ntnu.mappe32.view.PassageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static edu.ntnu.mappe32.io.PathsFileReader.readStory;


public class TestApp extends javafx.application.Application {

    public static void main(String[] args) {
        launch();
    }

    public static Game setUpGame() throws IOException {
        Player player = new Player("Ole", 100, 0, 50, new HashMap<Item, Integer>());
        Story story = readStory("src/main/resources/test_stories/main_test_story.paths");
        Goal goldGoal = new GoldGoal(100);
        Goal scoreGoal = new ScoreGoal(50);
        List<Goal> goals = new ArrayList<>();
        goals.add(goldGoal);
        goals.add(scoreGoal);
        player.addToInventory(new Item("abc"));
        player.addToInventory(new Item("abcd"));
        player.addToInventory(new Item("blahblahblah"));
        player.addToInventory(new Item("another item"));
        player.addToInventory(new Item("Tape", new ScoreAction(10)));
        player.addToInventory(new Item("Lighter", new ScoreAction(10)));
        player.addToInventory(new Item("Candle", new ScoreAction(10)));
        player.addToInventory(new Item("Potato", new HealthAction(10)));
        player.addToInventory(new Item("Hammer", new ScoreAction(10)));
        player.addToInventory(new Item("Dagger", new ScoreAction(10)));
        player.addToInventory(new Item("Scarf", new HealthAction(10)));


        for (int i = 1; i <= 50; i++) {
            //goals.add(new ScoreGoal(i));
            player.addToInventory(new Item("Pumpkin"));
        }
        return new Game(player, story, goals);
    }
    @Override
    public void start(Stage stage) throws IOException {
        //stage.setScene(new PassageView(setUpGame()).getScene()); // TODO FIX
        stage.setTitle("Paths Game");
        stage.show();
    }
}
