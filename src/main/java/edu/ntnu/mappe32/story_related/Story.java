package edu.ntnu.mappe32.story_related;

import edu.ntnu.mappe32.action_related.Action;
import edu.ntnu.mappe32.story_related.Link;
import edu.ntnu.mappe32.story_related.Passage;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class represents a story.
 * A story is a collection of passages, linked together by their links.
 */
public class Story {
    /**
     * Title of the story.
     */
    private final String title;
    /**
     * Map of all the passages in an instance of story
     */
    private Map<Link, Passage> passages;
    /**
     * The opening passage of the story
     */
    private final Passage openingPassage;
    /**
     * This constructor facilitates the creation of instances of the class Story.
     * Throws IllegalArgumentException if either title is blank
     * opening passage is null.
     * Instantiates passages as HashMap<Link, Passage>
     * @param title Title of a story, as String
     * @param openingPassage Opening passage of a story, as Passage
     */
    public Story(final String title, final Passage openingPassage) throws IllegalStateException {
        if (title.isBlank()) {
            throw new IllegalArgumentException("A story must have a title");
        }
        if (openingPassage == null) {
            throw new IllegalArgumentException("A story must have an opening passage");
        }
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
        Link searchLink = new Link(link.getReference(), link.getReference());
        searchLink.getActions().removeAll(searchLink.getActions());
        return passages.get(searchLink);

    }
    /**
     * This method return the Map passages.
     * @return Passages, as Collection<Passage>.
     */
    public Collection<Passage> getPassages() {
        return passages.values();
    }

    /**
     * This method removes a passage from Map <Link, Passage>.
     * Throws IllegalArgumentException if the link refers to multiple or zero passages.
     * @param removalLink A link of the passage to be removed, as Link
     */
    public void removePassage(Link removalLink) {
        int matches = (int) passages.keySet().stream().map(Link::getReference)
                .filter(references -> references.contains(removalLink.getReference())).count();
        if (matches > 1) {
            throw new IllegalArgumentException("The passage has multiple links which refer to it and cannot be removed");
        }
        if (matches == 0) {
            throw new IllegalArgumentException("The link refers to no passage");
        }
        passages.remove(removalLink);
    }

    /** This method gets the broken links of a story.
     * A broken link is defined as a link that belongs to a passage that does not yet refer to any passage,
     * meaning that attempting to use it in an actual story would not return an existing passage.
     * @return Broken links in a story, as List<Link>
     */
    public List<Link> getBrokenLinks() {
        return getPassages().stream().flatMap(passage -> passage.getLinks().stream())
                .filter(link -> getPassage(link) == null).toList();
    }
}
