package edu.ntnu.mappe32;

import edu.ntnu.mappe32.action_related.GoldAction;
import edu.ntnu.mappe32.action_related.HealthAction;
import edu.ntnu.mappe32.story_related.Link;
import edu.ntnu.mappe32.story_related.Passage;
import edu.ntnu.mappe32.story_related.Story;

import java.io.FileWriter;
import java.io.IOException;


public class TrollStoryPathsWriter {
    public static void main(String[] args) {

        GoldAction gainFiftyGold = new GoldAction(50);
        HealthAction getStompedOn = new HealthAction(-100);

        Passage openingPassage = new Passage("You see a troll", "You see a 3-meter tall troll slowly turning towards you.");
        Link attackTroll = new Link("Attack the troll", "Attack the troll");
        Link runAway = new Link("Run away", "Run Away");
        openingPassage.addLink(runAway);
        openingPassage.addLink(attackTroll);

        Passage attackTrollPassage = new Passage("Attack the troll", "how do you wish to attack the troll");
        Link swingSword = new Link("Swing your sword", "Use sword");
        Link castSpell = new Link("Cast a magic spell", "Cast a magic spell");
        castSpell.addAction(gainFiftyGold);
        swingSword.addAction(getStompedOn);
        attackTrollPassage.addLink(swingSword);
        attackTrollPassage.addLink(castSpell);

        Passage runAwayPassage = new Passage("Run away", "The troll sees you and runs after you.");
        Link keepRunning = new Link("Keep running", "Keep running");
        keepRunning.addAction(getStompedOn);
        runAwayPassage.addLink(keepRunning);
        Link stopAndCastSpell = new Link("Stop and cast a magic spell", "Cast a magic spell");
        stopAndCastSpell.addAction(gainFiftyGold);
        runAwayPassage.addLink(stopAndCastSpell);

        Passage useSwordPassage = new Passage("Use sword",
                "The sword barely does so much as tickle the troll, it destroys you in anger. You are dead");

        Passage keepRunningPassage = new Passage("Keep running",
                "The troll easily catches you with its long strides and eats you for dinner, literally");

        Passage castSpellPassage = new Passage("Cast a magic spell",
                "The troll groans as it slowly turns to stone and stands motionless. " +
                        "50 gold coins drop to the ground.");

        Story trollStory = new Story("The story of the troll", openingPassage);
        trollStory.addPassage(attackTrollPassage);
        trollStory.addPassage(runAwayPassage);
        trollStory.addPassage(useSwordPassage);
        trollStory.addPassage(keepRunningPassage);
        trollStory.addPassage(castSpellPassage);

        try (FileWriter fileWriter = new FileWriter("src/main/resources/saved stories/trollStory.paths")) {
            fileWriter.write(trollStory.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
