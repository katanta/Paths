package edu.ntnu.mappe32.story_related;

import edu.ntnu.mappe32.model.Item;
import edu.ntnu.mappe32.model.action_related.*;
import edu.ntnu.mappe32.model.story_related.Link;
import edu.ntnu.mappe32.model.story_related.Passage;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LinkTest {
    Link climbTree;
    Link kickTree;
    Link shakeTree;
    Link franticallyWaveHands;
    Action remove10HealthPoints;
    Action add1ScorePoint;
    Action add10GoldPoints;
    Action addMacheteToInventory;
    Action add20GoldPoints;
    List<Action> testActions;
    @BeforeEach
    void setup() {

        remove10HealthPoints = new HealthAction(-10);
        add1ScorePoint = new ScoreAction(1);
        add10GoldPoints = new GoldAction(10);
        addMacheteToInventory = new InventoryAction(new Item("Machete", new ScoreAction(100)), true);
        add20GoldPoints = new GoldAction(20);
        testActions = new ArrayList<>();

        testActions.add(remove10HealthPoints);
        testActions.add(add1ScorePoint);
        testActions.add(add10GoldPoints);
        testActions.add(addMacheteToInventory);

        climbTree = new Link("Climb the tree", "Climbed tree Passage");
        kickTree = new Link("Kick the tree", "Kicked/shaked tree Passage");
        kickTree.addAction(remove10HealthPoints);
        kickTree.addAction(add1ScorePoint);
        kickTree.addAction(add10GoldPoints);
        kickTree.addAction(addMacheteToInventory);

        shakeTree = new Link("Shake the tree", "Kicked/shaked tree Passage");

        franticallyWaveHands = new Link("Frantically wave hands", "Frantically waved Hands Passage");

    }

    @Nested
    @DisplayName("constructor")
    class LinkConstructorThrowsExceptions {
        @Test
        @DisplayName("does not throw IllegalArgumentException when text and reference are not blank")
        void contructorDoesNotThrowIllegalArgumentExceptionWhenTextAndReferenceAreNotBlank() {
            assertDoesNotThrow(() -> new Link("Text", "Reference"));
        }
        @Test
        @DisplayName("throws IllegalArgumentException for a link with blank text")
        void throwsIllegalArgumentWhenTextIsBlank() {
            assertThrows(IllegalArgumentException.class, () -> new Link("", "Reference"));
        }
        @Test
        @DisplayName("throws IllegalArgumentException for a link with null text")
        void throwsIllegalArgumentWhenTextIsNull() {
            assertThrows(IllegalArgumentException.class, () -> new Link(null, "Reference"));
        }
        @DisplayName("throws IllegalArgumentException when reference is null")
        @Test
        void throwsIllegalArgumentWhenReferenceIsBlank() {
            assertThrows(IllegalArgumentException.class, () -> new Link("Text", ""));
        }
        @DisplayName("throws IllegalArgumentException for a link with null reference")
        @Test
        void throwsIllegalArgumentWhenLinkReferenceIsNull() {
            assertThrows(IllegalArgumentException.class, () -> new Link("Text", null));
        }
    }
    @DisplayName("getText() retuns text")
    @Test
    void getTextReturnsText() {
        assertEquals("Climb the tree", climbTree.getText(), "Returns ");
        assertEquals("Kick the tree", kickTree.getText());
        assertEquals("Shake the tree", shakeTree.getText());
    }
    @DisplayName("getReference() returns reference")
    @Test
    void getReferenceReturnsReference() {
        assertEquals("Climbed tree Passage", climbTree.getReference());
        assertEquals("Kicked/shaked tree Passage", kickTree.getReference());
        assertEquals("Frantically waved Hands Passage", franticallyWaveHands.getReference());
    }
    @DisplayName("addAction() adds action")
    @Test
    void addActionAddsAction() {
        assertEquals(testActions, kickTree.getActions());
    }
    @DisplayName("getAction()")
    @Nested
    class GetActionsTest {
        @DisplayName("returns actions")
        @Test
        void getActionReturnsActions() {
            assertEquals(testActions, kickTree.getActions());
        }
        @DisplayName("returns deep copy of actions")
        @Test
        void getActionReturnsDeepCopyOfActions() {
            List<Action> mutatedKickTreeActions = kickTree.getActions();
            mutatedKickTreeActions.add(add20GoldPoints);
            assertNotEquals(mutatedKickTreeActions, kickTree.getActions());
            assertNotSame(kickTree.getActions(), kickTree.getActions());
        }
    }
    @DisplayName("toString() returns String representation")
    @Test
    void toStringReturnsToString() {
        StringBuilder s = new StringBuilder();
        s.append("[" + "Kick the tree" + ']' +
                "(" + "Kicked/shaked tree Passage" + ')');
        testActions.forEach(s::append);
        assertEquals(s.toString(), kickTree.toString());
    }
}
