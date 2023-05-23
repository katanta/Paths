package edu.ntnu.mappe32.model;

import edu.ntnu.mappe32.io.PathsFileReader;
import edu.ntnu.mappe32.model.story_related.Story;

import java.io.File;
import java.util.Objects;

public class PathsFile {
    private final String filePath;
    private final Integer brokenLinks;
    private final String storyTitle;
    private final Story story;

    public PathsFile(File file) {
        if (file == null)
            throw new IllegalArgumentException("File cannot be null");
        this.filePath = file.getAbsolutePath();
        this.story = PathsFileReader.readStory(filePath);
        this.storyTitle = story.getTitle();
        this.brokenLinks = story.getBrokenLinks().size();
    }

    public Story getStory() {
        return story;
    }

    public Integer getBrokenLinks() {
        return brokenLinks;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PathsFile pathsFile)) return false;
        return filePath.equals(pathsFile.filePath) && brokenLinks.equals(pathsFile.brokenLinks) && storyTitle.equals(pathsFile.storyTitle) && story.equals(pathsFile.story);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filePath, brokenLinks, storyTitle, story);
    }
}
