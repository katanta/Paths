package edu.ntnu.mappe32;

import edu.ntnu.mappe32.controller.Game;
import edu.ntnu.mappe32.model.Player;
import edu.ntnu.mappe32.model.action_related.*;
import edu.ntnu.mappe32.model.goal_related.*;
import edu.ntnu.mappe32.model.story_related.Link;
import edu.ntnu.mappe32.model.story_related.Passage;
import edu.ntnu.mappe32.model.story_related.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    Player mutumbu;
    Player mufasa;
    Player pumba;
    List<String> pumbasTestInventory;
    List<String> mutumbusTestInventory;
    List<String> mufasasTestInventory;
    Link climbTree;
    Link kickTree;
    Link shakeTree;
    Link runHome;
    Link scratchHead;
    Link runToKilimanjaro;
    Link franticallyWaveHands;
    Link takeABite;
    Passage openingPassage;
    Passage kickedShakedTree;
    Passage climbedTree;
    Story storyOfAfrica;
    Action remove10HealthPoints;
    Action add1ScorePoint;
    Action add10GoldPoints;
    Action addMacheteToInventory;
    List<Action> assertionActions;
    List<Link> assertionLinks;
    Map<Link, Passage> assertionPassages;
    Game game1 = null;
    Goal friendsRansom;
    Goal superPowered;
    Goal masterGuardian;
    List<Goal> goals;
    Goal tapeItUp;
    @BeforeEach
    void setup() {
        this.mutumbu = new Player("Mutumbu", 150, 0, 25);
        this.mufasa = new Player("Mufasa", 200, 50, 100);
        this.pumba = new Player("Pumba", 202, 40, 100);
        this.mutumbu.addToInventory("Tape");
        this.mutumbu.addToInventory("Lighter");
        this.mutumbu.addToInventory("Candle");
        this.mutumbu.addToInventory("Potato");
        this.mutumbu.addToInventory("Hammer");
        this.mutumbu.addToInventory("Dagger");
        this.mutumbu.addToInventory("Scarf");
        this.mufasa.addToInventory("Key");
        this.mufasa.addToInventory("Milk");
        this.mufasa.addToInventory("Chicken");
        this.mufasa.addToInventory("Pencil");
        this.mufasa.addToInventory("Wood");
        this.mufasa.addToInventory("Paper");
        this.pumba.addToInventory("N'Tofos");
        this.pumba.addToInventory("Orange");
        this.pumba.addToInventory("Apple");
        this.pumba.addToInventory("Machete");
        this.pumba.addToInventory("Knife");
        this.pumba.addToInventory("Paperclip");
        this.pumba.addToInventory("Rolex");
        this.pumba.addToInventory("Key");
        this.mutumbusTestInventory = new ArrayList<String>(Arrays.asList("Tape", "Lighter", "Candle", "Potato", "Hammer",
                "Dagger", "Scarf"));
        this.mufasasTestInventory = new ArrayList<String>(Arrays.asList("Key", "Milk", "Chicken", "Pencil", "Wood", "Paper"));
        this.pumbasTestInventory = new ArrayList<String>(Arrays.asList("N'Tofos", "Orange",
                "Apple", "Machete", "Knife", "Paperclip", "Rolex", "Key"));

        remove10HealthPoints = new HealthAction(-10);
        add1ScorePoint = new ScoreAction(1);
        add10GoldPoints = new GoldAction(10);
        addMacheteToInventory = new InventoryAction("Machete");

        assertionActions = new ArrayList<>();

        assertionActions.add(remove10HealthPoints);
        assertionActions.add(add1ScorePoint);
        assertionActions.add(add10GoldPoints);
        assertionActions.add(addMacheteToInventory);

        openingPassage = new Passage("Opening Passage", "You just found the enchanted apple tree " +
                "you've been searching for your whole adventure around Serengeti. " +
                "You're hungry and you see a golden apple hanging from the tree.");
        climbTree = new Link("Climb the tree", "Climbed tree Passage");
        kickTree = new Link("Kick the tree", "Kicked/shaked tree Passage");
        kickTree.addAction(remove10HealthPoints);
        kickTree.addAction(add1ScorePoint);
        kickTree.addAction(add10GoldPoints);
        kickTree.addAction(addMacheteToInventory);

        shakeTree = new Link("Shake the tree", "Kicked/shaked tree Passage");

        climbedTree = new Passage("Climbed tree Passage","You climb the tree and notice a large beehive full " +
                "of killer bees. 3 bees sting you just as you pick the apple from the tree.");

        franticallyWaveHands = new Link("Frantically wave hands", "Frantically waved Hands Passage");
        takeABite = new Link("Take a bite", "Took a Bite");


        kickedShakedTree = new Passage("Kicked/shaked tree Passage", "The golden apple fell on your head. " +
                "Your head is bumped. " + "A killer bee flies by!");

        runHome = new Link("Run home", "Run home Passage");

        scratchHead = new Link("Scratch head. You screathed your head. It did not bring any relief. You took 10 damage.",
                "Scratched head 10 damage Passage");
        runToKilimanjaro = new Link("Run to Kilimanjaro", "Run to Kilimanjaro Passage");

        assertionLinks = new ArrayList<>();

        openingPassage.addLink(climbTree);

        assertionLinks.add(climbTree);
        assertionLinks.add(shakeTree);
        assertionLinks.add(franticallyWaveHands);
        assertionLinks.add(takeABite);
        assertionLinks.add(runHome);
        assertionLinks.add(scratchHead);
        kickedShakedTree.addLink(kickTree);
        climbedTree.addLink(climbTree);
        climbedTree.addLink(shakeTree);
        climbedTree.addLink(franticallyWaveHands);
        climbedTree.addLink(takeABite);
        climbedTree.addLink(runHome);
        climbedTree.addLink(scratchHead);

        storyOfAfrica = new Story("Story of Africa", openingPassage);

        storyOfAfrica.addPassage(kickedShakedTree);
        storyOfAfrica.addPassage(climbedTree);
        assertionPassages = new HashMap<>();

        assertionPassages.put(new Link(kickedShakedTree.getTitle(), kickedShakedTree.getTitle()), kickedShakedTree);

        GoldGoal friendsRansom = new GoldGoal(150);
        HealthGoal superPowered = new HealthGoal(125);
        ScoreGoal masterGuardian = new ScoreGoal(800);
        goals = new ArrayList<>();
        goals.add(friendsRansom);
        goals.add(superPowered);
        goals.add(masterGuardian);
        game1 = new Game(mutumbu, storyOfAfrica, goals);
        tapeItUp = new InventoryGoal(new ArrayList<>(List.of("Tape")));
    }
    @DisplayName("constructor")
    @Nested
    public class GameConstructor {
        @DisplayName("throws illegalArguementException when player is null")
        @Test
        void throwsIllegalArgumentExceptionWhenPlayerIsNull() {
            assertThrows(IllegalArgumentException.class, () -> new Game(null, storyOfAfrica, goals));
        }
        @DisplayName("throwsIllegalArguementException when story is null when story is null")
        @Test
        void throwsIllegalArguementExceptionWhenStoryIsNull() {
            assertThrows(IllegalArgumentException.class, () -> new Game(mutumbu, null, goals));
        }
        @DisplayName("throwsIllegalStateExceptionWhenGoalsIsEmpty when goals is null/empty")
        @Test
        void throwsIllegalStateExceptionWhenGoalsIsEmpty() {
            List <Goal> emptyList = new ArrayList<>();
            assertThrows(IllegalStateException.class, () -> new Game(mutumbu, storyOfAfrica, emptyList));
        }
    }
    @DisplayName("getPlayer() returns player")
    @Test
    void getPlayerReturnsPlayer() {
        assertEquals(mutumbu, game1.getPlayer());
    }
    @DisplayName("getStory() retruns story")
    @Test
    void getStoryReturnsStory() {
        assertEquals(storyOfAfrica, game1.getStory());
    }
    @DisplayName("getGoals()")
    @Nested
    public class GetGoalsTest {
        @DisplayName("returns goals()")
        @Test
        void getGoalsReturnsGoals() {
            assertEquals(goals, game1.getGoals());
        }
        @DisplayName("returns deep copy of goals()")
        @Test
        void getGoalsReturnsDeepCopyOfGoals() {
            List<Goal> mutatedGoals = game1.getGoals();
            mutatedGoals.add(tapeItUp);
            assertNotEquals(mutatedGoals, game1.getGoals());
        }
    }
    @DisplayName("begin() returns opening passage a game's story")
    @Test
    void beginReturnsOpeningPassageOfAGamesStory() {
        assertEquals(storyOfAfrica.getOpeningPassage(), game1.begin());
    }
    @DisplayName("go()")
    @Nested
    public class GoTest {
        @DisplayName("returns passage of given link")
        @Test
        void returnsPassageOfAGivenLink() {
            assertEquals(climbedTree, game1.go(climbTree));
        }

        @DisplayName("executes actions on the game's player")
        @Test
        void executesActionsOnTheGamesPlayer() {
            game1.go(kickTree);
            assertEquals(140, mutumbu.getHealth());
            assertEquals(1, mutumbu.getScore());
            assertEquals(35, mutumbu.getGold());
            mutumbusTestInventory.add("Machete");
            assertEquals(mutumbusTestInventory, mutumbu.getInventory());
        }
    }
}
