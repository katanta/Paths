package edu.ntnu.mappe32.io;

import edu.ntnu.mappe32.model.Item;
import edu.ntnu.mappe32.model.action_related.*;
import edu.ntnu.mappe32.model.story_related.Link;
import edu.ntnu.mappe32.model.story_related.Passage;
import edu.ntnu.mappe32.model.story_related.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PathsFileWriterTest {
    Story mainTestStory;

    Passage lastPassage;
    Link lastLink;
    Action goldPlus100;
    @BeforeEach
    void setUp() {
        Action scorePlus10 = new ScoreAction(10);
        Action scorePlus90 = new ScoreAction(90);
        Action scorePlus100 = new ScoreAction(100);
        Action scoreMinus100 = new ScoreAction(-100);
        Action healthPlus100 = new HealthAction(100);
        Action healthMinus100 = new HealthAction(-100);
        Action medalOfTheCoward = new InventoryAction(new Item("Medal Of The Coward", new ScoreAction(-10)), true);
        Action keysYoWhatsUp = new InventoryAction(new Item("Keys Yo Whats Up", new ScoreAction(10)), true);
        Action helmet = new InventoryAction(new Item("Helmet", new HealthAction(100)), true);
        Action goldPlus50 = new GoldAction(50);
        goldPlus100 = new GoldAction(100);
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

        lastLink = new Link("Oooops", "Keep running");

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
        lastPassage = new Passage("Keep running", "The troll easily catches you with its long strides and eats you for dinner, literally");
        mainTestStory = new Story("The story of the troll", openingPassage1);

        mainTestStory.addPassage(useSword);
        mainTestStory.addPassage(castAMagicSpellPassage);
        mainTestStory.addPassage(attackTheTrollPassage);
        mainTestStory.addPassage(runAwayPassage);
        mainTestStory.addPassage(lastPassage);
    }
    @DisplayName("writeStory()")
    @Nested
    class WriteStoryTest {
        @DisplayName("writes story accurately")
        @Test
        void writeStoryAccurately() throws IOException {
            String testFilePath = "src/main/resources/test_stories/write_main_story_test.paths";
            PathsFileWriter.writeStory(mainTestStory, testFilePath);
            File outputFile = new File(testFilePath);
            assertTrue(outputFile.exists());
            assertTrue(outputFile.isFile());
            assertTrue(outputFile.canRead());
            assertEquals(mainTestStory.toString(),
                    PathsFileReader.readStory(outputFile.getPath()).toString());

            outputFile.delete();
        }
        @DisplayName("throws IllegalArgumentException if file path does not have .paths as extension")
        @Test
        void throwsIllegalArgumentExceptionIfFilePathDoesNotHaveDotPathsAsExtension() {
            String testFilePath = "src/main/resources/test_stories/test.txt";
            assertThrows(IllegalArgumentException.class,
                    () -> PathsFileWriter.writeStory(mainTestStory, testFilePath));
        }
        @DisplayName("throws IOException if directory does not exist")
        @Test
        void throwsIOExceptionWhenDirectoryDoesNotExist() {
            String testFilePath = "src/non/existent/directory/test.paths";
            assertThrows(IOException.class,
                    () -> PathsFileWriter.writeStory(mainTestStory, testFilePath));
        }
    }
}