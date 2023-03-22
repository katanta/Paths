package edu.ntnu.mappe32.io;

import edu.ntnu.mappe32.model.story_related.Story;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The PathsFileWriter class writes a .paths file, which is a
 * story of the paths game.
 * @author Kristians J. Matrevics
 */
public class PathsFileWriter {
    public PathsFileWriter() {
    }

    public void writeStory(Story story, String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.getName().endsWith(".paths")) {
            throw new IOException("Unsupported file format. Only .paths-files are supported.");
        }

        try(FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(story.toString());
        } catch (IOException e) {
            throw new IOException("Unable to write to paths-file: " + e.getMessage());
        }
    }
}
