package edu.ntnu.mappe32.story_related;

import edu.ntnu.mappe32.model.Item;
import edu.ntnu.mappe32.model.action_related.*;
import edu.ntnu.mappe32.model.story_related.Link;
import edu.ntnu.mappe32.model.story_related.Passage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("constructor")
    @Nested
    class PassageConstructor {
        @DisplayName("does not throw IllegalArgumentException when title and content are not blank")
        @Test
        void doesNotThrowIllegalArgumentExceptionWhenTitleAndContentAreNotBlank() {
            assertDoesNotThrow(() -> new Passage("Title", "Content"));
        }
        @DisplayName("throws IllegalArgumentException when title is blank")
        @Test
        void throwsIllegalArgumentWhenTitleIsBlank() {
            assertThrows(IllegalArgumentException.class, () -> new Passage("  ", "ABC"));
        }
        @DisplayName("throws IllegalArgumentException when title is null")
        @Test
        void throwsIllegalArgumentWhenTitleIsNull() {
            assertThrows(IllegalArgumentException.class, () -> new Passage(null, "ABC"));
        }
        @DisplayName("throws IllegalArgumentException when content is blank")
        @Test
        void throwsIllegalArgumentWhenContentIsBlank() {
            assertThrows(IllegalArgumentException.class, () -> new Passage("ABC", ""));
        }
    }
    @DisplayName("getTitle() returns title")
    @Test
    void getTitleReturnsTitle() {
        assertEquals("Opening Passage", openingPassage.getTitle());
        assertEquals("Climbed tree Passage", climbedTree.getTitle());
        assertEquals("Kicked/shaked tree Passage", kickedShakedTree.getTitle());
    }
    @DisplayName("getContent() returns content")
    @Test
    void getContentReturnsContent() {
        assertEquals("You just found the enchanted apple tree " +
                "you've been searching for your whole adventure around Serengeti. " +
                "You're hungry and you see a golden apple hanging from the tree.", openingPassage.getContent());
        assertEquals("You climb the tree and notice a large beehive full " +
                "of killer bees. 3 bees sting you just as you pick the apple from the tree.", climbedTree.getContent());
        assertEquals("The golden apple fell on your head. " +
                "Your head is bumped. " + "A killer bee flies by!", kickedShakedTree.getContent());

    }
    @DisplayName("getLinks()")
    @Nested
    class GetLinksTest {
        @DisplayName("returns links")
        @Test
        void getLinksReturnsLinks() {

            assertEquals(assertionLinks, climbedTree.getLinks());
        }
        @DisplayName("returns copy of links")
        @Test
        void getLinksReturnsCopyOfLinks() {
            List<Link> mutatedClimbedTreeLinks = climbedTree.getLinks();
            mutatedClimbedTreeLinks.add(runToKilimanjaro);
            assertNotEquals(mutatedClimbedTreeLinks, climbedTree.getLinks());
            mutatedClimbedTreeLinks.remove(runToKilimanjaro);
            assertEquals(mutatedClimbedTreeLinks, climbedTree.getLinks());
        }
    }
    @DisplayName("addLink()")
    @Nested
    class AddLinkTest {
        @DisplayName("adds link")
        @Test
        void addLinkAddsLink() {
            climbedTree.addLink(runToKilimanjaro);
            assertionLinks.add(runToKilimanjaro);
            assertEquals(assertionLinks, climbedTree.getLinks());
        }
        @DisplayName("return true when adding link that does not exist in passage")
        @Test
        void addLinkReturnsTrueWhenAddingLinkThatDoesNotExistInPassage() {
            assertTrue(climbedTree.addLink(runToKilimanjaro));
        }
        @DisplayName("returns false when adding a link that already exists in a passage")
        @Test
        void addLinkReturnsFalseWhenAddingLinkThatAlreadyExistsInPassage() {
            assertFalse(climbedTree.addLink(runHome));
        }
        @DisplayName("does not add a link that already exists in a passage")
        @Test
        void addLinkDoesNotAddLinkThatAlreadyExistsInPassage() {
            climbedTree.addLink(runHome);
            assertEquals(assertionLinks, climbedTree.getLinks());
        }
        @DisplayName("does not add link with same text as another link")
        @Test
        void addLinkDoesNotAddLinkWithSameTextAsAnotherLink() {
            climbedTree.addLink(new Link(runHome.getText(), "Unique reference"));
        }
        @DisplayName("adds link with the same reference as another link")
        @Test
        void addLinkAddsLinkWithTheSameReferenceAsAnotherLink() {
            climbedTree.addLink(new Link("Unique text", runHome.getReference()));
        }
    }
    @DisplayName("hasLinks()")
    @Nested
    class hasLinksTest {
        @DisplayName("returns false when passage has no links")
        @Test
        void returnsFalseWhenPassageHasNoLinks() {
            assertFalse(kickedShakedTree.hasLinks());
        }
        @DisplayName("returns true when passage has links")
        @Test
        void returnsTrueWhenPassageHasLinks() {
            assertTrue(climbedTree.hasLinks());
        }
        @DisplayName("returns true when passage has 1 link")
        @Test
        void returnsTrueWhenPassageHasOneLink() {
            kickedShakedTree.addLink(climbTree);
            assertTrue(kickedShakedTree.hasLinks());
        }
    }
    @DisplayName("toString() returns correct String")
    @Test
    void toStringReturnsToString() {

        String s = "::Opening Passage\nYou just found the enchanted apple tree you've been searching for your whole adventure around Serengeti. "
                + "You're hungry and you see a golden apple hanging from the tree.\n" +
                climbTree;
        assertEquals(s, openingPassage.toString());
    }

    @DisplayName("hashCode is symmetric")
    @Test
    void hashCodeIsSymmetric() {
        Passage passage1 = new Passage("passage", "content");
        Passage passage2 = new Passage("passage", "content");
        assertEquals(passage1.hashCode(), passage2.hashCode());
    }

}
