package edu.ntnu.mappe32;

import edu.ntnu.mappe32.story_related.Link;
import edu.ntnu.mappe32.story_related.Passage;
import org.junit.jupiter.api.BeforeEach;

public class LinkTest {

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

    @BeforeEach
    void setup() {
        openingPassage = new Passage("Opening Passage", "You just found the enchanted apple tree " +
                "you've been searching for your whole adventure around Serengeti. " +
                "You're hungry and you see a golden apple hanging from the tree.");

        climbTree = new Link("Climb the tree", "Climbed tree Passage");
        kickTree = new Link("Kick the tree", "Kicked/shaked tree Passage");
        shakeTree = new Link("Shake the tree", "Kicked/shaked tree Passage");

        climbedTree = new Passage("Climbed tree Passage","You climb the tree and notice a large beehive full " +
                "of killer bees. 3 bees sting you just as you pick the apple from the tree.");

        franticallyWaveHands = new Link("Frantically wave hands", "Frantically waved Hands");
        takeABite = new Link("Take a bite", "Took a Bite");


        kickedShakedTree = new Passage("Kicked/shaked tree Passage", "The golden apple fell on your head. " +
                "Your head is bumped." + "A killer bee flies by!");

        runHome = new Link("Run home", "Run home Passage");

        scratchHead = new Link("Scratch head. You screathed your head. It did not bring any relief. You took 10 damage.",
                "Scratched head 10 damage Passage");
        runToKilimanjaro = new Link("Run to Kilimanjaro", "Run to Kilimanjaro Passage");

    }
}
