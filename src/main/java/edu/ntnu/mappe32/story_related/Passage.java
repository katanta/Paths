package edu.ntnu.mappe32.story_related;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A passage is a smaller part of the story, like a paragraph.
 * One can go from one passage to another via a link.
 * @version 0.1
 * @author Kristians J. Matrevics
 */
public class Passage {
    private final String title;
    private String content;
    private List<Link> links;

    /**
     * Constructs an object of the Passage class.
     * @param title (String): A main description which also functions as a unique identificator.
     * @param content (String): Textual content that normally includes some part of a dialogue or a paragraph.
     * @since 0.1
     */
    public Passage(String title, String content) {
        if (title.isBlank()) {
           throw new IllegalArgumentException("Passage must be instatiated with a valid title");
        } else { this.title = title; }

        if (content.isBlank()) {
            throw new IllegalArgumentException("Passage must be instatiated with a valid content");
        } else { this.content = content; }

        links = new ArrayList<>();
    }

    /**
     * @return title (String): A unique descriptor of the passage
     * @since 0.1
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return content (String): block of text that is part of certain scene in the story.
     * @since 0.1
     */
    public String getContent() {
        return content;
    }

    /**
     * @return links (List<link>): The list of all links to this passage.
     * @since 0.1
     */
    public List<Link> getLinks() {
        if (links.isEmpty()) {
            throw new IllegalStateException("This passage contains no links");
        } else { return new ArrayList<>(links); }
    }

    /**
     * @param link (Link): the link one wishes to add to this passage.
     * @return true if the link was unique and added it to the passage.
     * false if the passage already contained this link.
     * @since 0.1
     */
    public boolean addLink(Link link) {
        String match = links.stream().filter(l -> l.equals(link)).map(Link::getReference)
                .reduce("", (total, s) -> total + s );
        if (!match.isBlank()) {
            return false;
        }
        links.add(link);
        return true;
    }

    /**
     * @return true if the passage has any links.
     * false if the passage has no links.
     * @since 0.1
     */
    public boolean hasLinks() {
        return links.size() > 0;
    }

    /**
     * @return String representation of the passage, including all link information.
     * @since 0.1
     */
    @Override
    public String toString() {
        String s = "Title: " + title;
        s += "\nContent: " + content;
        s += "\nLinks Info: ";
        for(Link link : links) {
            s += link.toString() + "\n";
        }
        return s;
    }

    /**
     * Checks if an object is equal to this passage, by comparing titles.
     * @param o (Object): the object one wishes to compare to this one.
     * @return true if the objects are equal, otherwise will return false.
     * @since 0.1
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passage passage = (Passage) o;
        return Objects.equals(title, passage.title);
    }

    /**
     * @return hashCode (int): A generated integer that represents the passage by using its title.
     * @since 0.1
     */
    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
