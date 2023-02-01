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

    public Link(String text, String reference) {
        this.text = text;
        this.reference = reference;
    }

    public String getText() {
        return text;
    }

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

    @Override
    public String toString() {
        String s = "Text: " + text;
        s += " \nReference: " + reference;
        //TODO: make this toString also return the actions list for the link
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(reference, link.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }

}
