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
    /**
     * Title of a passage, as String
     */
    private final String title;
    /**
     * Content of a passage, as String
     */
    private final String content;
    /**
     * Links of a passage, as List<Link>
     */
    private final List<Link> links;
    /**
     * This constructor facilitates the creation of instances of the class Passage.
     * The constructor throws IllegalArgumentExceptions if the title or content is left blank in the parameters.
     * @param title A main description which also functions as a unique identificator, as String
     * @param content Textual content that normally includes some part of a dialogue or a paragraph, as String
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
        return new ArrayList<>(links);
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
     * Checks if an object is equal to this passage, by comparing all fields.
     * @param o (Object): the object one wishes to compare to this one.
     * @return true if the objects are equal, otherwise will return false.
     * @since 0.1
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passage passage = (Passage) o;
        return title.equals(passage.title) && content.equals(passage.content) && links.equals(passage.links);
    }
    /**
     * @return hashCode (int): A generated integer that represents the passage by using its fields.
     * @since 0.1
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, content, links);
    }

}
