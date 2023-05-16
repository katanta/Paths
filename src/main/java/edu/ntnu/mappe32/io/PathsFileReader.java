package edu.ntnu.mappe32.io;

import edu.ntnu.mappe32.model.Item;
import edu.ntnu.mappe32.model.action_related.*;
import edu.ntnu.mappe32.model.story_related.Link;
import edu.ntnu.mappe32.model.story_related.Passage;
import edu.ntnu.mappe32.model.story_related.Story;

import java.io.BufferedReader;
import java.io.File;
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
public class PathsFileReader {
    /**
     * The line in which the BufferedReader is on at all times.
     */
    private static String currentLine;
    private static final String FILE_EXTENSION = ".paths";
    private static final String PASSAGE_TITLE_DELIMITER = "::";
    private static final String LINK_TITLE_DELIMITER = "[";
    private static final String LINK_TITLE_END_DELIMITER = "]";
    private static final String LINK_REFERENCE_DELIMITER = "(";
    private static final String LINK_REFERENCE_END_DELIMITER = ")";
    private static final String ACTION_DELIMITER = "<";
    private static final String ACTION_END_DELIMITER = ">";
    private static final String INVENTORY_ACTION_FORMAT = "inventory";
    private static final String HEALTH_ACTION_FORMAT = "health";
    private static final String GOLD_ACTION_FORMAT = "gold";
    private static final String SCORE_ACTION_FORMAT = "score";

    /**
     * This method reads a .paths file and returns a story.
     * @param filePath File path of the .paths file to be read, as String.
     * @return Story of the .paths file, as Story.
     * @throws IllegalArgumentException Throws IllegalArgumentException if the file path does not have the extension '.paths'.
     */
    public static Story readStory(String filePath) throws IllegalArgumentException {
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
                if (currentLine.startsWith(PASSAGE_TITLE_DELIMITER)) {
                    Passage passage = parsePassage(bufferedReader);
                    passages.add(passage);
                }
            }

            // Create the story with only the opening passage
            story = new Story(storyTitle, passages.get(0));

            // Add remaining passages, excluding the opening passage
            passages.stream().skip(1).forEach(story::addPassage);

        } catch (IOException e) {
            throw new RuntimeException("An IOException occurred while attempting to read the file: "
                    + e.getMessage());
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

        String extension =  filePath.substring(dotIndex);
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
        while (currentLine != null && currentLine.startsWith(LINK_TITLE_DELIMITER)) {
            Link link = parseLink();

            // Check if currentLine contains actions
            if (currentLine.substring(currentLine.lastIndexOf(LINK_REFERENCE_END_DELIMITER))
                    .contains(ACTION_DELIMITER)) {
                //Parse actions and add them to the link
                Arrays.stream(splitCurrentLineIntoActions()).forEach(action ->{
                    Action parsedAction = parseAction(action);
                        link.addAction(parsedAction);
                        });

            }
            currentLine = bufferedReader.readLine();
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
                .substring(currentLine.indexOf(LINK_TITLE_DELIMITER) + 1,
                        currentLine.lastIndexOf(LINK_TITLE_END_DELIMITER));
        String linkReference = currentLine
                .substring(currentLine.indexOf(LINK_REFERENCE_DELIMITER) + 1,
                        currentLine.lastIndexOf(LINK_REFERENCE_END_DELIMITER));

        return new Link(linkTitle, linkReference);
    }

    /**
     * This method splits the currentLine into an Array of String,
     * where each String represents actions of a link.
     * @return Array of String which represents actions, as String[].
     */
    public static String[] splitCurrentLineIntoActions() {
        String actions = currentLine.substring(currentLine.lastIndexOf(LINK_REFERENCE_END_DELIMITER) + 1);
        return actions.split(ACTION_END_DELIMITER + ACTION_DELIMITER);
    }
    public static String[] getActionsOfInventoryAction(String invetoryAction, String itemName) {
        String actions = invetoryAction.substring(invetoryAction.lastIndexOf(itemName) + itemName.length() + 1);
        String[] actionsArray = actions.split(ACTION_END_DELIMITER);

        return Arrays.stream(actionsArray).limit(actionsArray.length - 1).map(String::trim).toArray(String[]::new);
    }

    /**
     * This method parses a String action,
     * which contains an action in the format "<'actionType' 'value'>".
     * It resolves the action type and the value of the action by splitting the String.
     * The method parses and instantiates an action based on the action type.
     * @param action String representation of an action, as String
     */
    private static Action parseAction(String action) {
        String actionType = action.startsWith(ACTION_DELIMITER) ? action.substring(1, action.indexOf(" ")).toLowerCase()
                : action.substring(0, action.indexOf(" ")).toLowerCase();
        switch (actionType) {

            case INVENTORY_ACTION_FORMAT -> {
                return parseInventoryAction(action);
            }

            case GOLD_ACTION_FORMAT, HEALTH_ACTION_FORMAT, SCORE_ACTION_FORMAT -> {
                return parseNumberBasedAction(action, actionType);
            }
            default -> throw new IllegalArgumentException("This Action type could not be found");
        }
    }

    /**
     * This method parses an action with a number based value (Goal, Health or Score Action).
     * It uses the same parsing logic for each type of number based action,
     * but instantiates the action according to its action type.
     * @param actionType Type of actions, as String
     * @param action String representation of an action, as String
     */
    private static Action parseNumberBasedAction(String action, String actionType) {
        int value = action.endsWith(ACTION_END_DELIMITER) ?
                Integer.parseInt(action.substring(action.indexOf(" ") + 1, action.length() -1)) :
                Integer.parseInt(action.substring(action.indexOf(" ") + 1));

        switch (actionType) {
            case GOLD_ACTION_FORMAT -> {
                return new GoldAction(value);
            }
            case HEALTH_ACTION_FORMAT -> {
                return new HealthAction(value);
            }
            case SCORE_ACTION_FORMAT -> {
                return new ScoreAction(value);
            }
            default -> throw new IllegalArgumentException("This Action type could not be found");
        }
    }
    private static Action parseInventoryAction(String invetoryAction) {

        String[] parts = invetoryAction.split(" ");
        String firstAction = Arrays.stream(parts).skip(1)
                .filter(part -> part.startsWith("<"))
                .findFirst()
                .get();

        int index = Arrays.asList(parts).indexOf(firstAction);
        String itemName = Arrays.stream(parts).skip(2)
                .limit(index - 2)
                .collect(Collectors.joining(" "));

        List<Action> itemsActions = new ArrayList<>();
        Arrays.stream(getActionsOfInventoryAction(invetoryAction, itemName))
                .forEach(action -> itemsActions.add(parseAction(action)));

        Item item = new Item(itemName, itemsActions.toArray(Action[]::new));
        boolean add = Arrays.asList(parts).contains("true");

        return new InventoryAction(item, add);
    }
}
