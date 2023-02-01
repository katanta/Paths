package edu.ntnu.idatt2001.groups.group32;

import java.util.ArrayList;
import java.util.Objects;

public class Passage {
    private final String title;
    private String content;
    private ArrayList<Link> links;

    public Passage(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public boolean addLink(Link link) {
        for(Link l : links) {
            if (link.equals(l)) {
                return false;
            }
        }
        links.add(link);
        return true;
    }

    public boolean hasLinks() {
        return links.size() > 0;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passage passage = (Passage) o;
        return Objects.equals(title, passage.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
