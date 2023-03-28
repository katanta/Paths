package edu.ntnu.mappe32.model;

import edu.ntnu.mappe32.model.story_related.Story;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StorySelector {
    // This is the directory of the folder which contains different .paths
    File directory;
    List<PathsFile> listPathsFiles;
    File[] files;
    public StorySelector() {
        directory = new File("src/main/resources/test_stories");
        listPathsFiles = new ArrayList<>();
        files = directory.listFiles((dir, name) -> name.endsWith(".paths"));
        if (files != null) {
            Arrays.stream(files).toList().forEach(file -> {
                try {
                    listPathsFiles.add(new PathsFile(file));
                } catch (IOException e) {
                    throw new RuntimeException("An error occurred when reading the file");
                }
            });
        }
    }

    public List<PathsFile> getListPathsFiles() {
        return new ArrayList<>(listPathsFiles);
    }
}