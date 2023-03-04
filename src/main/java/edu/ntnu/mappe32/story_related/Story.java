package edu.ntnu.mappe32.story_related;

import edu.ntnu.mappe32.action_related.Action;
import edu.ntnu.mappe32.story_related.Link;
import edu.ntnu.mappe32.story_related.Passage;

import java.util.*;

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
    public void removePassage(Link removalLink) {
        int match = (int) passages.values().stream().map(Passage::getLinks)
                .filter(links -> (links.contains(removalLink))).count();
        if (match > 1) {
            throw new IllegalArgumentException("The link refers to multiple passages and cannot be removed");
        }
        if (match == 0) {
            throw new IllegalArgumentException("The link refers to no passage");
        }
        passages.remove(removalLink);
    }

    /**
     * @return All broken links in a story, as a List of Links.
     * A broken link is defined as a link that belongs to a passage that does not yet refer to any passage,
     * meaning that attempting to use it in an actual story would not return an existing passage.
     */
    public List<Link> getBrokenLinks() {
        List<Link> foundLinks = new ArrayList<>();
        getPassages().forEach(p -> p.getLinks().forEach(link -> {
            if (getPassage(link) == null) foundLinks.add(link);
        }));
        return foundLinks;
    }
}
