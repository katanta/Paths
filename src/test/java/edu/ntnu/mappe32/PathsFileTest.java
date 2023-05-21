package edu.ntnu.mappe32;

import edu.ntnu.mappe32.io.PathsFileWriter;
import edu.ntnu.mappe32.model.Item;
import edu.ntnu.mappe32.model.PathsFile;
import edu.ntnu.mappe32.model.action_related.*;
import edu.ntnu.mappe32.model.story_related.Link;
import edu.ntnu.mappe32.model.story_related.Passage;
import edu.ntnu.mappe32.model.story_related.Story;
import org.junit.jupiter.api.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PathsFile Test")
class PathsFileTest {

    private File testFile;
    private Story testStory;

    @BeforeEach
    void setUp() {
        Action scorePlus10 = new ScoreAction(10);
        Action scorePlus90 = new ScoreAction(90);
        Action scorePlus100 = new ScoreAction(100);
        Action scoreMinus100 = new ScoreAction(-100);
        Action healthPlus100 = new HealthAction(100);
        Action healthMinus100 = new HealthAction(-100);
        Action medalOfTheCoward = new InventoryAction(new Item("Medal Of The Coward", true, new ScoreAction(-10)), true, 1);
        Action keysYoWhatsUp = new InventoryAction(new Item("Keys Yo Whats Up", true, new ScoreAction(10)), true, 1);
        Action helmet = new InventoryAction(new Item("Helmet", true, new HealthAction(100)), true, 1);
        Action goldPlus50 = new GoldAction(50);
        Action goldPlus1000 = new GoldAction(1000);
        Action goldMinus1000 = new GoldAction(-1000);

        Link runAway = new Link("Run away", "Run Away");
        runAway.addAction(scoreMinus100);
        runAway.addAction(healthPlus100);
        runAway.addAction(medalOfTheCoward);

        Link attackTheTroll = new Link("Attack the troll", "Attack the troll");
        attackTheTroll.addAction(goldPlus1000);
        attackTheTroll.addAction(goldMinus1000);
        attackTheTroll.addAction(healthPlus100);
        attackTheTroll.addAction(scorePlus100);

        Link callTheChopper = new Link("Call the chopper", "Call the chopper");
        callTheChopper.addAction(scorePlus90);
        callTheChopper.addAction(healthPlus100);
        callTheChopper.addAction(scorePlus10);
        callTheChopper.addAction(keysYoWhatsUp);
        callTheChopper.addAction(helmet);

        Link swingYourSword = new Link("Swing your sword", "Use sword");
        swingYourSword.addAction(healthMinus100);

        Link castAMagicSpell = new Link("Cast a magic spell", "Cast a magic spell");
        castAMagicSpell.addAction(goldPlus50);

        Link keepRunning = new Link("Keep running", "Keep running");
        keepRunning.addAction(healthMinus100);
        Link stopAndCastAMagicSpell = new Link("Stop and cast a magic spell", "Cast a magic spell");
        stopAndCastAMagicSpell.addAction(goldPlus50);


        Passage openingPassage1 = new Passage("::You see a :troll", "::You see a:: 3-meter( )tall troll slowly turning towards you.");
        openingPassage1.addLink(runAway);
        openingPassage1.addLink(attackTheTroll);
        openingPassage1.addLink(callTheChopper);
        Passage useSword = new Passage("Use sword", "The sword barely does so much as tickle the troll, it destroys you in anger. You are dead");
        Passage castAMagicSpellPassage = new Passage("Cast a magic spell", "The troll groans as it slowly turns to stone and stands motionless. 50 gold coins drop to the ground.");
        Passage attackTheTrollPassage = new Passage("Attack the troll", "how do you wish to attack the troll");
        attackTheTrollPassage.addLink(swingYourSword);
        attackTheTrollPassage.addLink(castAMagicSpell);

        Passage runAwayPassage = new Passage("Run away", "The troll sees you and runs after you.");
        runAwayPassage.addLink(keepRunning);
        runAwayPassage.addLink(stopAndCastAMagicSpell);
        Passage lastPassage = new Passage("Keep running", "The troll easily catches you with its long strides and eats you for dinner, literally");
        testStory = new Story("The story of the troll", openingPassage1);

        testStory.addPassage(useSword);
        testStory.addPassage(castAMagicSpellPassage);
        testStory.addPassage(attackTheTrollPassage);
        testStory.addPassage(runAwayPassage);
        testStory.addPassage(lastPassage);
        testFile = new File("src/main/resources/test_stories/main_test_story2.paths");
    }

    @Nested
    @DisplayName("Constructor")
    class ConstructorTests {

        @Test
        @DisplayName("creates a PathsFile when valid file is entered")
        void constructorCreatesAPathsFileWhenValidFileIsEntered() {

            PathsFileWriter.writeStory(testStory, testFile.getAbsolutePath());

            PathsFile pathsFile = new PathsFile(testFile);

            assertNotNull(pathsFile);
            assertEquals(testFile.getAbsolutePath(), pathsFile.getFilePath());

            assertEquals(testStory.toString(), pathsFile.getStory().toString());
            assertEquals(testStory.getTitle(), pathsFile.getStoryTitle());
            assertEquals(1, pathsFile.getBrokenLinks());
        }

        @Test
        @DisplayName("throws IllegalArgumentException when file is null")
        void constructorThrowsIllegalArguemntExceptionWhenFileIsNull() {

            File file = null;

            assertThrows(IllegalArgumentException.class, () -> new PathsFile(file));
        }

        @Test
        @DisplayName("Negative Case: Non-existent File")
        void constructorThrowsIllegalArguemntExceptionWhenFileDoesNotExist() {

            File file = new File("nonExistentFilePath.txt");

            assertThrows(IllegalArgumentException.class, () -> new PathsFile(file));
        }
    }

    @Nested
    @DisplayName("Getter Methods")
    class GetterMethodsTests {

        @Test
        @DisplayName("getStory()")
        void testGetStory() {

            PathsFile pathsFile = new PathsFile(testFile);

            Story actualStory = pathsFile.getStory();

            assertEquals(testStory.toString(), actualStory.toString());
        }

        @Test
        @DisplayName("getBrokenLinks()")
        void testGetBrokenLinks() {

            PathsFile pathsFile = new PathsFile(testFile);

            Integer actualBrokenLinks = pathsFile.getBrokenLinks();

            assertEquals(1, actualBrokenLinks);
        }

        @Test
        @DisplayName("getFilePath()")
        void testGetFilePath() {

            PathsFile pathsFile = new PathsFile(testFile);

            String actualFilePath = pathsFile.getFilePath();

            assertEquals(testFile.getAbsolutePath(), actualFilePath);
        }

        @Test
        @DisplayName("getStoryTitle()")
        void testGetStoryTitle() {

            PathsFile pathsFile = new PathsFile(testFile);

            String actualStoryTitle = pathsFile.getStoryTitle();

            assertEquals(testStory.getTitle(), actualStoryTitle);
        }
    }
}