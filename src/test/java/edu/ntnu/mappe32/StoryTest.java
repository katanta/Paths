package edu.ntnu.mappe32;

import edu.ntnu.mappe32.action_related.*;
import edu.ntnu.mappe32.story_related.Link;
import edu.ntnu.mappe32.story_related.Passage;
import edu.ntnu.mappe32.story_related.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StoryTest {
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
    Passage franticallyWavedHands;
    Story storyOfAfrica;
    Action remove10HealthPoints;
    Action add1ScorePoint;
    Action add10GoldPoints;
    Action addMacheteToInventory;
    List<Link> assertionBrokenLinks;
    Map<Link, Passage> assertionPassages;

    @BeforeEach
    void setup() {
        remove10HealthPoints = new HealthAction(-10);
        add1ScorePoint = new ScoreAction(1);
        add10GoldPoints = new GoldAction(10);
        addMacheteToInventory = new InventoryAction("Machete");

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

        franticallyWavedHands = new Passage("Frantically waved Hands Passage", "Your hands fell off");
        kickedShakedTree = new Passage("Kicked/shaked tree Passage", "The golden apple fell on your head. " +
                "Your head is bumped. " + "A killer bee flies by!");

        runHome = new Link("Run home", "Run home Passage");

        scratchHead = new Link("Scratch head. You screathed your head. It did not bring any relief. You took 10 damage.",
                "Scratched head 10 damage Passage");

        assertionBrokenLinks = new ArrayList<>();

        openingPassage.addLink(climbTree);

        assertionBrokenLinks.add(takeABite);
        assertionBrokenLinks.add(runHome);
        assertionBrokenLinks.add(scratchHead);
        assertionBrokenLinks.add(franticallyWaveHands);

        climbedTree.addLink(climbTree);
        climbedTree.addLink(shakeTree);
        climbedTree.addLink(franticallyWaveHands);
        climbedTree.addLink(takeABite);
        climbedTree.addLink(runHome);
        climbedTree.addLink(scratchHead);

        storyOfAfrica = new Story("Story of Africa", openingPassage);

        storyOfAfrica.addPassage(kickedShakedTree);
        assertionPassages = new HashMap<>();

        assertionPassages.put(new Link(kickedShakedTree.getTitle(), kickedShakedTree.getTitle()), kickedShakedTree);
    }
    @DisplayName("constructor")
    @Nested
    class ConstructorTest {
        @DisplayName("throws IllegalArgumentException when title is blank")
        @Test
        void constructorThrowsIllegalArgumentExceptionWhenTitleIsBlank() {
            assertThrows(IllegalArgumentException.class, () -> new Story(" ", openingPassage));
        }
        @DisplayName("throws IllegalArgumentException when opening passage is null")
        @Test
        void constructorThrowsIllegalArgumentExceptionWhenOpeningPassageIsNull() {
            assertThrows(IllegalArgumentException.class, () -> new Story("Story of Africa", null));
        }
    }
    @DisplayName("getTitle() returns title")
    @Test
    void getTitleReturnsTitle() {
        assertEquals("Story of Africa", storyOfAfrica.getTitle());
    }
    @DisplayName("getOpeningPassage() returns opening passage")
    @Test
    void getOpeningPassageReturnsOpeningPassage() {
        assertEquals(openingPassage, storyOfAfrica.getOpeningPassage());
    }
    @DisplayName("addPassage() adds passage")
    @Test
    void addPassageAddsPassage() {
        storyOfAfrica.addPassage(climbedTree);
        assertionPassages.put(new Link(climbedTree.getTitle(),climbedTree.getTitle()), climbedTree);
        assertEquals(assertionPassages.values().toString(), storyOfAfrica.getPassages().toString());
    }
    @DisplayName("getPassage() returns passage")
    @Test
    void getPassageReturnsPassage() {
        assertEquals(kickedShakedTree, storyOfAfrica.getPassage(kickTree));
    }
    @DisplayName("getPassages() returns List<Passage> passages")
    @Test
    void getPassagesReturnsPassages() {
        assertEquals(assertionPassages.values().toString(), storyOfAfrica.getPassages().toString());
    }

    @DisplayName("removePassage()")
    @Nested
    class RemovePassageTest {
        @DisplayName("removes passage with 1 link referring to it")
        @Test
        void removesPassageWith1LinkReferringToIt() {
            storyOfAfrica.addPassage(franticallyWavedHands);
            storyOfAfrica.addPassage(climbedTree);
            climbedTree.addLink(kickTree);
            storyOfAfrica.removePassage(franticallyWaveHands);
            assertFalse(storyOfAfrica.getPassages().contains(franticallyWavedHands));
        }
        @DisplayName("throws IllegalStateException when multiple links refer to a passage")
        @Test
        void throwsIllegalStateExceptionWhenMultipleLinksReferToAPassage() {
            storyOfAfrica.addPassage(kickedShakedTree);
            storyOfAfrica.addPassage(climbedTree);
            climbedTree.addLink(kickTree);
            climbedTree.addLink(shakeTree);
            assertThrows(IllegalStateException.class, () -> storyOfAfrica.removePassage(kickTree));
        }
    }
    @DisplayName("getBrokenLinks() returns all of the broken links in no particular order")
    @Test
    void getBrokenLinksReturnsBrokenLinks() {
        storyOfAfrica.addPassage(kickedShakedTree);
        storyOfAfrica.addPassage(climbedTree);
        climbedTree.addLink(kickTree);
        climbedTree.addLink(shakeTree);
        assertEquals(assertionBrokenLinks.size(), storyOfAfrica.getBrokenLinks().size());
        assertTrue(storyOfAfrica.getBrokenLinks().containsAll(assertionBrokenLinks));
        assertTrue(assertionBrokenLinks.containsAll(storyOfAfrica.getBrokenLinks()));

    }
}