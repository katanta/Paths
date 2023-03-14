package edu.ntnu.mappe32;

import edu.ntnu.mappe32.io.StoryReader;
import edu.ntnu.mappe32.story_related.*;
import edu.ntnu.mappe32.goal_related.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TrollStoryPaths {
    public static void main(String[] args) throws FileNotFoundException {

        Story trollStory = StoryReader.readStory("src/main/resources/saved stories/trollStory.paths");

        Player player = new Player("Daffen", 100, 0, 0);

        Goal payDebt = new GoldGoal(50);
        ArrayList<Goal> gameGoals = new ArrayList<>();
        gameGoals.add(payDebt);

        Game paths = new Game(player, trollStory, gameGoals);

        Scanner sc = new Scanner(System.in);
        Passage currentPassage = trollStory.getOpeningPassage();
        boolean exit = false;
        System.out.println(paths.begin().toString());
        while(!exit) {
            for (Link link : currentPassage.getLinks()) {
                int i = currentPassage.getLinks().indexOf(link) + 1;
                System.out.println(i + ". " + link.toString());
            }
            try {
                currentPassage = paths.go(currentPassage.getLinks().get(sc.nextInt() - 1));
            } catch (Exception e) {
                System.out.println("Your input should be a whole between 1 and " + currentPassage.getLinks().size());
                sc.next();
            }
            if (paths.getPlayer().getHealth() <= 0) {
                System.out.println("Oh dear! You died!");
                exit = true;
            }
        }
    }
}
