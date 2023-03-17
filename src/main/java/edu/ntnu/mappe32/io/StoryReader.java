package edu.ntnu.mappe32.io;

import edu.ntnu.mappe32.action_related.*;
import edu.ntnu.mappe32.story_related.Link;
import edu.ntnu.mappe32.story_related.Passage;
import edu.ntnu.mappe32.story_related.Story;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class reads a .paths file, instatiating an instance of Story
 * by reading the content of a .paths file.
 */
public class StoryReader {
    /**
     * The line in which the BufferedReader is on at all times.
     */
    private static String currentLine;
    private static final String FILE_EXTENSION = "paths";
    private static final String PASSAGE_TITLE_FORMAT = "::";
    private static final String LINK_TITLE_FORMAT = "[";
    private static final String LINK_TITLE_FORMAT_END = "]";
    private static final String LINK_REFERENCE_FORMAT = "(";
    private static final String LINK_REFERENCE_FORMAT_END = ")";
    private static final String ACTION_FORMAT = "<";
    private static final String ACTION_FORMAT_END = ">";
    private static final char INVENTORY_ACTION_FORMAT = 'i';
    private static final char HEALTH_ACTION_FORMAT = 'h';
    private static final char GOLD_ACTION_FORMAT = 'g';
    private static final char SCORE_ACTION_FORMAT = 's';

    /**
     * This method reads a .paths file and returns a story.
     * @param filePath File path of the .paths file to be read, as String.
     * @return Story of the .paths file, as Story.
     * @throws FileNotFoundException Throws FileNotFoundException if the file is not found.
     * @throws IllegalArgumentException Throws IllegalArgumentException if the file path does not have the extension '.paths'.
     */
    public static Story readStory(String filePath) throws IllegalArgumentException, IOException, FileNotFoundException {
        validateFilePath(filePath);

        File storyFile = new File(filePath);

        Story story;

        // Temporary list of passages which will later be added to the story
        List<Passage> passages = new ArrayList<>();

        try (BufferedReader bufferedReader = Files.newBufferedReader(storyFile.toPath())) {
            currentLine = bufferedReader.readLine();
            String storyTitle = currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {

                if (currentLine.isBlank()) {
                    continue;
                }

                // Parse passage
                if (currentLine.startsWith(PASSAGE_TITLE_FORMAT)) {
                    Passage passage = parsePassage(bufferedReader);
                    passages.add(passage);
                }
            }

            // Create the story with only the opening passage
            story = new Story(storyTitle, passages.get(0));

            // Remove the opening passage
            passages.remove(0);

            // Add remaining passages
            passages.forEach(story::addPassage);

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File does not exist");
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
        return story;
    }

    /**
     * This method checks if the file path has the extension '.paths'.
     * @param filePath File path of file to be read, as String
     * @throws IllegalArgumentException Throws IllegalArgumentException if the file path does not have the extension '.paths'.
     */
    private static void validateFilePath(String filePath) throws IllegalArgumentException {
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("File path cannot be null or left blank.");
        }
        if (!filePath.contains(".")) {
            throw new IllegalArgumentException("This is not a file path.");
        }
        int dotIndex = filePath.lastIndexOf(".");

        String extension =  filePath.substring(dotIndex + 1);
        if (!extension.equals(FILE_EXTENSION)) {
            throw new IllegalArgumentException("The file extension does not correspond with ." + FILE_EXTENSION);
        }
    }

    /**
     * This method reads the current passage in the BufferedReader and returns a new Passage object.
     * @param bufferedReader The BufferedReader to read from, as BufferedReader.
     * @return New Passage object based on the content of the BufferedReader, as Passage.
     * @throws IOException Throws IOException if there's a problem reading from the BufferedReader.
     */
    private static Passage parsePassage(BufferedReader bufferedReader) throws IOException {
        String passageTitle = currentLine.substring(2);
        currentLine = bufferedReader.readLine();
        String passageContent = currentLine;

        Passage passage = new Passage(passageTitle, passageContent);

        if ((currentLine = bufferedReader.readLine()) == null || currentLine.isBlank()) {
            return passage;
        }

        // Parse links and add them to the passage
        while (currentLine != null && currentLine.startsWith(LINK_TITLE_FORMAT)) {
            Link link = parseLink();

            // Parse actions and add them to the link
            while ((currentLine = bufferedReader.readLine()) != null && currentLine.startsWith(ACTION_FORMAT)) {
                parseActionAndAddActionToLink(link);
            }

            passage.addLink(link);
        }

        return passage;
    }

    /**
     * This method parses a line that corresponds to a link (starts with '[').
     * It resolves the title and the refernce of the link using currentLine.
     * @return New link corresponding to the content of the currentLine, as Link
     */
    private static Link parseLink() {
        String linkTitle = currentLine
                .substring(currentLine.indexOf(LINK_TITLE_FORMAT) + 1, currentLine.lastIndexOf(LINK_TITLE_FORMAT_END));
        String linkReference = currentLine
                .substring(currentLine.indexOf(LINK_REFERENCE_FORMAT) + 1, currentLine.lastIndexOf(LINK_REFERENCE_FORMAT_END));
        return new Link(linkTitle, linkReference);
    }

    /**
     * This method parses a line that correspons to an action (starts with '<').
     * It resolves the action type and the value of the action using currentLine and splitting it.
     * The method parses and instantiates an action based on the action type.
     * @param link The link in which the action is to be added to, as Link.
     */
    private static void parseActionAndAddActionToLink(Link link) {
        char actionType = currentLine.charAt(1);
        switch (actionType) {
            case INVENTORY_ACTION_FORMAT -> {
                String itemName = Arrays.stream(currentLine.split(" "))
                        .skip(1)
                        .collect(Collectors.joining(" "));


                link.addAction(new InventoryAction(itemName.substring(0, itemName.length() - 1)));
            }
            case GOLD_ACTION_FORMAT, HEALTH_ACTION_FORMAT, SCORE_ACTION_FORMAT ->
                    addNumberBasedActionToLink(actionType, link);
        }
    }

    /**
     * This method parses an action with a number based value (Goal, Health or Score Action).
     * It uses the same parsing logic for each type of number based action,
     * but instantiates the action according to its action type.
     * @param actionType Type of actions, as char
     * @param link The link in which the action is to be added to, as Link.
     */
    private static void addNumberBasedActionToLink(char actionType, Link link) {
        String[] parts = currentLine.split(" ");
        int value = Integer.parseInt(parts[1].substring(0, parts[1].lastIndexOf(ACTION_FORMAT_END)));

        switch (actionType) {
            case GOLD_ACTION_FORMAT -> link.addAction(new GoldAction(value));
            case HEALTH_ACTION_FORMAT -> link.addAction(new HealthAction(value));
            case SCORE_ACTION_FORMAT -> link.addAction(new ScoreAction(value));
        }
    }
}
