package edu.ntnu.mappe32.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents the model for the StorySelectorView.
 * More specifically, the storyTable gets its items by instantiating an object of this class.
 */
public class StorySelector {
    /**
     * List<PathFile> in a given directory
     */
    private final List<PathsFile> listOfPathsFiles;

    /**
     * This constructor checks for .paths files in a given directory and instatiates a new
     * PathFile for each file that ends with .paths
     * @throws RuntimeException
     */
    public StorySelector() throws RuntimeException {
        // Directory of the folder which contains .paths files
        File directory = new File("src/main/resources/test_stories");
        this.listOfPathsFiles = new ArrayList<>();

        File[] files = directory.listFiles((dir, name) -> name.endsWith(".paths"));
        if (files != null) {
            Arrays.stream(files).forEach(file -> {
                try {
                    listOfPathsFiles.add(new PathsFile(file));
                } catch (IOException e) {
                    throw new RuntimeException("An error occurred when reading the file: " + e.getMessage());
                }
            });
        }

    }
    public List<PathsFile> getListOfPathsFiles() {
        return new ArrayList<>(listOfPathsFiles);
    }
}