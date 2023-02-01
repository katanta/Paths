package edu.ntnu.idatt2001.groups.group32;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A story is a collection of passages, linked together by their links.
 */
public class Story {

    private String title;

    private Map<Link, Passage> passages;

    private Passage openingPassage;

    Story(String title, Passage openingPassage) {
        this.title = title;
        this.openingPassage = openingPassage;

        passages = new HashMap<Link, Passage>();
    }

    public String getTitle() {
        return title;
    }

    public Passage getOpeningPassage() {
        return openingPassage;
    }

    public void addPassage(Passage passage) {
        String text = "";
        passages.put(new Link(passage.getTitle(), passage.getTitle()), passage);
    }
    public Passage getPassage(Link link) {
        return passages.get(link);
    }

    public Collection<Passage> getPassages() {
        return passages.values();
    }
}
