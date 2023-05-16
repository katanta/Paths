package edu.ntnu.mappe32;

import edu.ntnu.mappe32.model.Game;
import edu.ntnu.mappe32.model.Item;
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
    List<Item> mutumbusTestInventory;
    Link climbTree;
    Link kickTree;
    Link shakeTree;
    Link runHome;
    Link scratchHead;
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
    List<Goal> goals;
    Goal tapeItUp;
    @BeforeEach
    void setup() {
        this.mutumbu = new Player("Mutumbu", 150, 0, 25, new HashMap<>());
        this.mufasa = new Player("Mufasa", 200, 50, 100, new HashMap<>());
        this.pumba = new Player("Pumba", 202, 40, 100, new HashMap<>());
        this.mutumbu.addToInventory(new Item("Tape", new ScoreAction(10)));
        this.mutumbu.addToInventory(new Item("Lighter", new ScoreAction(10)));
        this.mutumbu.addToInventory(new Item("Candle", new ScoreAction(10)));
        this.mutumbu.addToInventory(new Item("Potato", new HealthAction(10)));
        this.mutumbu.addToInventory(new Item("Hammer", new ScoreAction(10)));
        this.mutumbu.addToInventory(new Item("Dagger", new ScoreAction(10)));
        this.mutumbu.addToInventory(new Item("Scarf", new HealthAction(10)));
        this.mufasa.addToInventory(new Item("Key", new ScoreAction(10)));
        this.mufasa.addToInventory(new Item("Milk", new HealthAction(10)));
        this.mufasa.addToInventory(new Item("Chicken", new HealthAction(50)));
        this.mufasa.addToInventory(new Item("Pencil", new ScoreAction(10)));
        this.mufasa.addToInventory(new Item("Wood", new ScoreAction(10)));
        this.mufasa.addToInventory(new Item("Paper", new ScoreAction(10)));
        this.pumba.addToInventory(new Item("N'Tofos", new ScoreAction(10)));
        this.pumba.addToInventory(new Item("Orange", new HealthAction(20)));
        this.pumba.addToInventory(new Item("Apple", new HealthAction(10)));
        this.pumba.addToInventory(new Item("Machete", new ScoreAction(10)));
        this.pumba.addToInventory(new Item("Knife", new ScoreAction(10)));
        this.pumba.addToInventory(new Item("Paperclip", new ScoreAction(10)));
        this.pumba.addToInventory(new Item("Rolex", new ScoreAction(10)));
        this.pumba.addToInventory(new Item("Key", new ScoreAction(10)));
        this.mutumbusTestInventory = new ArrayList<>(Arrays.asList(new Item("Tape", new ScoreAction(10)),
                new Item("Lighter", new ScoreAction(10)), new Item("Candle", new ScoreAction(10)),
                new Item("Potato", new HealthAction(10)), new Item("Hammer", new ScoreAction(10)),
                new Item("Dagger", new ScoreAction(10)), new Item("Scarf", new HealthAction(10))));

        remove10HealthPoints = new HealthAction(-10);
        add1ScorePoint = new ScoreAction(1);
        add10GoldPoints = new GoldAction(10);
        addMacheteToInventory = new InventoryAction(new Item("Machete", new ScoreAction(10)), true);

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
        HashMap<Item, Integer> mandatoryItems = new HashMap<>();
        mandatoryItems.put(new Item("Tape"), 1);
        tapeItUp = new InventoryGoal(mandatoryItems);
    }
    @DisplayName("constructor")
    @Nested
    public class GameConstructor {
        @DisplayName("does not throw IllegalArgumentExcpetion when paramters are not null")
        @Test
        void doesNotThrowIllegalArgumentExceptionWhenParametersAreNotNull() {
            assertDoesNotThrow(() -> new Game(mutumbu, storyOfAfrica, goals));
        }
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
        assertEquals(mutumbu, game1.player());
    }
    @DisplayName("getStory() retruns story")
    @Test
    void getStoryReturnsStory() {
        assertEquals(storyOfAfrica, game1.story());
    }
    @DisplayName("getGoals()")
    @Nested
    public class GetGoalsTest {
        @DisplayName("returns goals()")
        @Test
        void getGoalsReturnsGoals() {
            assertEquals(goals, game1.goals());
        }
        @DisplayName("returns deep copy of goals()")
        @Test
        void getGoalsReturnsDeepCopyOfGoals() {
            List<Goal> mutatedGoals = game1.goals();
            mutatedGoals.add(tapeItUp);
            assertNotEquals(mutatedGoals, game1.goals());
        }
    }
    @DisplayName("begin() returns opening passage of a game's story")
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

            mutumbusTestInventory.add(new Item("Machete", new ScoreAction(10)));

            assertTrue(mutumbusTestInventory.containsAll(mutumbu.getInventory().keySet()));
            assertTrue(mutumbu.getInventory().keySet().containsAll(mutumbusTestInventory));
            assertEquals(mutumbusTestInventory.size(), mutumbu.getInventory().keySet().size());
        }
    }
}
