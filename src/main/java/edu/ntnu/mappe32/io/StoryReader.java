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

    /**
     * This method reads a .paths file and returns a story.
     * @param filePath File path of the .paths file to be read, as String.
     * @return Story of the .paths file, as Story.
     * @throws FileNotFoundException Throws FileNotFoundException if the file is not found.
     * @throws IllegalArgumentException Throws IllegalArgumentException if the file path does not have the extension '.paths'.
     */
    public static Story readStory(String filePath) throws FileNotFoundException, IllegalArgumentException {
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
                if (currentLine.startsWith("::")) {
                    String passageTitle = currentLine.substring(2);
                    currentLine = bufferedReader.readLine();
                    String passageContent = currentLine;

                    if ((currentLine = bufferedReader.readLine()) == null || currentLine.isBlank()) {
                        passages.add(new Passage(passageTitle, passageContent));
                        continue;
                    }

                    // Parse links and add them to the passage
                    Passage passage = new Passage(passageTitle, passageContent);
                    addLinkloop:
                    while (currentLine.startsWith("[")) {
                        Link link = parseLink();

                        if ((currentLine = bufferedReader.readLine()) == null) {
                            passage.addLink(link);
                            break;
                        }

                        // Parse actions and add them to the link
                        while (currentLine.startsWith("<")) {
                            parseActionAndAddActionToLink(link);
                            if ((currentLine = bufferedReader.readLine()) == null) {
                                passage.addLink(link);
                                break addLinkloop;
                            }
                        }
                        passage.addLink(link);
                    }
                    passages.add(passage);
                }
            }

            // Create the story and add passages to it
            story = new Story(storyTitle, passages.get(0));
            passages.remove(0);
            passages.forEach(story::addPassage);

        } catch (IOException e) {
            throw new FileNotFoundException("File does not exist");
        }
        return story;
    }

    /**
     * This method checks if the file path has the extension '.paths'.
     * @param filePath File path of file to be read, as String
     * @throws IllegalArgumentException Throws IllegalArgumentException if the file path does not have the extension '.paths'.
     */
    private static void validateFilePath(String filePath) throws IllegalArgumentException {
        int dotIndex = filePath.lastIndexOf(".");

        String extension =  filePath.substring(dotIndex + 1);
        if (!extension.equals("paths")) {
            throw new IllegalArgumentException("The file extension does not correspond with .paths");
        }
    }

    /**
     * This method parses a line that corresponds to a link (starts with '[').
     * It resolves the title and the refernce of the link using currentLine.
     * @return New link corresponding to the content of the currentLine, as Link
     */
    private static Link parseLink() {
        String linkTitle = currentLine.substring(currentLine.indexOf("[") + 1, currentLine.lastIndexOf("]"));
        String linkReference = currentLine.substring(currentLine.indexOf("(") + 1, currentLine.lastIndexOf(")"));
        return new Link(linkTitle, linkReference);
    }

    /**
     * This method parses a line that correspons to an action (starts with '<').
     * It resolves the action type and the value of the action using currentLine.
     * The method parses and instantiates an action based on the action type.
     * @param link The link in which the action is to be added to, as Link.
     */
    private static void parseActionAndAddActionToLink(Link link) {
        char actionType = currentLine.charAt(1);
        switch (actionType) {
            case 'i' -> {
                String itemName = Arrays.stream(currentLine.split(" "))
                        .skip(1)
                        .limit(currentLine.split(" ").length - 1)
                        .collect(Collectors.joining(" "));


                link.addAction(new InventoryAction(itemName.substring(0, itemName.length() - 1)));
            }
            case 'g', 'h', 's' -> addNumberBasedActionToLink(actionType, link);
        }
    }

    /**
     * This method parses an action with a number based value (Goal, Health or Score Action).
     * It uses the same parsing logic, but instantiates the action according to its action type.
     * @param actionType Type of actions, as char
     * @param link The link in which the action is to be added to, as Link.
     */
    private static void addNumberBasedActionToLink(char actionType, Link link) {
        String[] parts = currentLine.split(" ");
        int value = Integer.parseInt(parts[1].substring(0, parts[1].lastIndexOf('>')));

        switch (actionType) {
            case 'g' -> link.addAction(new GoldAction(value));
            case 'h' -> link.addAction(new HealthAction(value));
            case 's' -> link.addAction(new ScoreAction(value));
        }
    }
}
