package edu.ntnu.idatt2001.groups.group32;

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
    //private List<Action> actions

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
        this.text = text;
        this.reference = reference;
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

    /*
    public void addAction(Action action) {
        actions.add(action);
     }

     public List<Action> getActions() {
        return actions;
     */

    /**
     * @return String representation of the object.
     * @since 0.1
     */
    @Override
    public String toString() {
        String s = "Text: " + text;
        s += " \nReference: " + reference;
        //TODO: make this toString also return the actions list for the link
        return s;
    }

    /**
     * Returns whether or not two links are equal.
     * @param o (Object): The object we wish to compare to an object of this class.
     * @return true if instance of class is equal to o.
     * @return false otherwise
     * @since 0.1
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(reference, link.reference);
    }

    /**
     * @since 0.1
     * @return hashCode (int): a generated integer value representing the link, using its reference.
     */
    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }

}
