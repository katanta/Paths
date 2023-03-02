package edu.ntnu.mappe32.story_related;

import edu.ntnu.mappe32.action_related.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A link makes it possible to go from one passage to another.
 * Links bind separate parts of the story together.
 * @author Kristians J. Matrevics
 * @version 0.1
 */
public class Link {
    private String text;
    private final String reference;
    private List<Action> actions;

    /**
     * Constructor for objects of the Link class.
     * @param text (String): A descriptive text that indicates a choice or action in a story.
     *             The text is the part of the link that is visible to the player.
     * @param reference (String): A string that identifies a passage (a part of the story).
     *                  In practice this would be the title of the passage one wishes to refer to.
     * //@param actions: A list of special objects that make it possible to affect various attributes of the player.
     * @since 0.1
     */
    public Link(String text, String reference) {
        if (text.isBlank()) {
            throw new IllegalArgumentException("Link must be instatiated with a valid text");
        } else {
            this.text = text;
        }
        if (reference.isBlank()) {
            throw new IllegalArgumentException("Link must be instantiated with a valid text");
        } else {
            this.reference = reference;
        }
        this.actions = new ArrayList<>();
    }

    /**
     * @return text (String): Indicator of a choice or action in the story.
     * @since 0.1
     */
    public String getText() {
        return text;
    }

    /**
     * @return reference (String): Which passage the link refers to.
     * @since 0.1
     */
    public String getReference() {
        return reference;
    }


    public void addAction(Action action) {
        actions.add(action);
     }

    public List<Action> getActions() {
            return new ArrayList<>(actions);
    }

    @Override
    public String toString() {
        return "Link{" +
                "text='" + text + '\'' +
                ", reference='" + reference + '\'' +
                ", actions=" + actions +
                '}';
        // TODO: Make toString for actions
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return text.equalsIgnoreCase(link.text)
                && reference.equalsIgnoreCase(link.reference)
                && actions.equals(link.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text.toLowerCase(), reference.toLowerCase(), actions);
    }
}
