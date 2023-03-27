package edu.ntnu.mappe32.model;

import edu.ntnu.mappe32.io.StoryReader;
import edu.ntnu.mappe32.model.story_related.Link;
import edu.ntnu.mappe32.model.story_related.Story;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class PathsFile {
    private final String filePath;
    private final Integer brokenLinks;
    private final String storyTitle;

    public PathsFile(File file) throws IOException {
        this.filePath = file.getAbsolutePath();
        Story story = StoryReader.readStory(filePath);
        this.storyTitle = story.getTitle();
        this.brokenLinks = story.getBrokenLinks().size();
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
        return filePath.equals(pathsFile.filePath) && Objects.equals(brokenLinks, pathsFile.brokenLinks)
                && storyTitle.equals(pathsFile.storyTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filePath, brokenLinks, storyTitle);
    }
}
