package edu.ntnu.mappe32.story_related;

import edu.ntnu.mappe32.story_related.Link;
import edu.ntnu.mappe32.story_related.Passage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a story.
 * A story is a collection of passages, linked together by their links.
 */
public class Story {
    // Title of the story.
    private String title;
    // Map of all the passages in an instance of story.
    private Map<Link, Passage> passages;
    // The opening passage of the story.
    private Passage openingPassage;

    /**
     * This constructor facilitates the creation of instances of the class Story.
     * @param title
     * @param openingPassage
     */
    public Story(String title, Passage openingPassage) {
        this.title = title;
        this.openingPassage = openingPassage;

        passages = new HashMap<Link, Passage>();
    }

    /**
     * This method returns the title a story.
     * @return Title of story as String.
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method returns the opening passage of a story.
     * @return openingPassage, as int.
     */
    public Passage getOpeningPassage() {
        return openingPassage;
    }

    /**
     * This method adds a passage to the passages Map.
     * @param passage Passage to be added, as Passage
     */
    public void addPassage(Passage passage) {
        passages.put(new Link(passage.getTitle(), passage.getTitle()), passage);
    }

    /**
     * This method returns a passage from the passages Map, using the passage's Link.
     * @param link Link of the passage, as Link.
     * @return Passage as passage.
     */
    public Passage getPassage(Link link) {
        return passages.get(link);
    }

    /**
     * This method return the Map passages.
     * @return Passages, as Collection<Passage>.
     */
    public Collection<Passage> getPassages() {
        return passages.values();
    }
}
