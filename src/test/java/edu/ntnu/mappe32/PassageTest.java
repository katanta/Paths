package edu.ntnu.mappe32;

import edu.ntnu.mappe32.story_related.Link;
import edu.ntnu.mappe32.story_related.Passage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PassageTest {
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
    Action remove10HealthPoints;
    Action add1ScorePoint;
    Action add10GoldPoints;
    Action addMacheteToInventory;
    List<Action> assertionActions;
    List<Link> assertionLinks;
    @BeforeEach
    void setup(){
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
                "Your head is bumped." + "A killer bee flies by!");

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

        climbedTree.addLink(climbTree);
        climbedTree.addLink(shakeTree);
        climbedTree.addLink(franticallyWaveHands);
        climbedTree.addLink(takeABite);
        climbedTree.addLink(runHome);
        climbedTree.addLink(scratchHead);
    }

    @Nested
    class PassageConstructorThrowsExceptions {
        @Test
        void throwsIllegalArgumentWhenTitleIsBlank() {
            assertThrows(IllegalArgumentException.class, () -> new Passage("", "ABC"));
        }
        @Test
        void throwsIllegalArgumentWhenContentIsBlank() {
            assertThrows(IllegalArgumentException.class, () -> new Passage("ABC", ""));
        }
    }
    @Test
    void getTitleReturnsText() {
        assertEquals("Opening Passage", openingPassage.getTitle());
        assertEquals("Climbed tree Passage", climbedTree.getTitle());
        assertEquals("Kicked/shaked tree Passage", kickedShakedTree.getTitle());
    }

    @Test
    void getContentReturnsContent() {
        assertEquals("You just found the enchanted apple tree " +
                "you've been searching for your whole adventure around Serengeti. " +
                "You're hungry and you see a golden apple hanging from the tree.", openingPassage.getContent());
        assertEquals("You climb the tree and notice a large beehive full " +
                "of killer bees. 3 bees sting you just as you pick the apple from the tree.", climbedTree.getContent());
        assertEquals("The golden apple fell on your head. " +
                "Your head is bumped." + "A killer bee flies by!", kickedShakedTree.getContent());

    }
    @Nested
    class GetLinksTest {
        @Test
        void getLinksReturnsLinks() {

            assertEquals(assertionLinks, climbedTree.getLinks());
        }
        @Test
        void getLinksThrowsIllegalStateException() {
            assertThrows(IllegalStateException.class,() -> kickedShakedTree.getLinks());
        }
        @Test
        void getLinksRetunsDeepCopyOfActions() {
            List<Link> mutatedClimbedTreeLinks = climbedTree.getLinks();
            mutatedClimbedTreeLinks.add(runToKilimanjaro);
            assertNotEquals(mutatedClimbedTreeLinks, climbedTree.getLinks());
            mutatedClimbedTreeLinks.remove(runToKilimanjaro);
            assertEquals(mutatedClimbedTreeLinks, climbedTree.getLinks());
        }
    }

    @Nested
    class AddLinkTest {
        @Test
        void addLinkAddsLink() {
            climbedTree.addLink(runToKilimanjaro);
            assertionLinks.add(runToKilimanjaro);
            assertEquals(assertionLinks, climbedTree.getLinks());
        }
        @Test
        void addLinkReturnsFalseWhenAddingLinkThatAlreadyExistsInPassage() {
            assertFalse(climbedTree.addLink(runHome));
        }
        @Test
        void addLinkDoesNotAddLinkThatAlreadyExistsInPassage() {
            climbedTree.addLink(runHome);
            assertEquals(assertionLinks, climbedTree.getLinks());
        }
    }
    @Nested
    class hasLinksTest {
        @Test
        void returnsFalseWhenPassageHasNoLinks() {
            assertFalse(kickedShakedTree.hasLinks());
        }
        @Test
        void returnsTrueWhenPassageHasLinks() {
            assertTrue(climbedTree.hasLinks());
        }
        @Test
        void returnsTrueWhenPassageHasOneLink() {
            kickedShakedTree.addLink(climbTree);
            assertTrue(kickedShakedTree.hasLinks());
        }
    }

    @Test
    void toStringReturnsToString() {

        String openingPassageToString = "Title: Opening Passage\nContent: " +
                "You just found the enchanted apple tree you've been searching for your whole adventure around Serengeti. " +
                "You're hungry and you see a golden apple hanging from the tree.\nLinks Info: Link{text='" +
                "Climb the tree\'" + ", reference='Climbed tree Passage\'" + ", actions=[]}\n";
        assertEquals(openingPassageToString, openingPassage.toString());
    }

}
