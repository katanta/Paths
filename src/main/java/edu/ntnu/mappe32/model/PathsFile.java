package edu.ntnu.mappe32;

import edu.ntnu.mappe32.io.StoryReader;
import edu.ntnu.mappe32.model.story_related.Link;
import edu.ntnu.mappe32.model.story_related.Story;

import java.io.IOException;
import java.util.List;

public class PathsFile {
    private final String filePath;
    private final Integer brokenLinks;
    private final String storyTitle;

    public PathsFile(String filePath) throws IOException {
        this.filePath = filePath;
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
}
