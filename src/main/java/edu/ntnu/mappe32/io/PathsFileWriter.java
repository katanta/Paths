package edu.ntnu.mappe32.io;

import edu.ntnu.mappe32.model.story_related.Story;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The PathsFileWriter class writes a .paths file, which is a
 * story of the paths game.
 *
 * @author Kristians J. Matrevics
 */
public class PathsFileWriter {
    public static void writeStory(Story story, String filePath) {
        File file = new File(filePath);
        if (!file.getPath().endsWith(".paths")) {
            throw new IllegalArgumentException("Unsupported file format. Only .paths-files are supported.");
        }

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(story.toString());
        } catch (IOException e) {
            throw new RuntimeException("Unable to write the file: " + e.getMessage());
        }

    }
}
