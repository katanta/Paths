package edu.ntnu.idatt2001.groups.group32;

import java.util.HashMap;

/**
 * A story is a collection of passages, linked together by their links.
 */
public class Story {

    private String title;

    private Map<Link, Passage> passages;

    private Passage openingPassage;

    Story(title, openingPassage) {
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
        passages.add(passage.getTitle(), passage);
    }
    public Passage getPassage(Link link) {
        return passages.get(link);
    }
}
