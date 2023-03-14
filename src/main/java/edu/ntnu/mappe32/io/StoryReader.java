package edu.ntnu.mappe32.io;

import edu.ntnu.mappe32.action_related.*;
import edu.ntnu.mappe32.story_related.Link;
import edu.ntnu.mappe32.story_related.Passage;
import edu.ntnu.mappe32.story_related.Story;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * This class reads a .paths file, instatiating an instance of Story
 * by reading the content of a .paths file.
 */
public class StoryReader {
    private static String currentLine;
    public static Story readStory(String filePath) throws IOException {
        File storyFile = new File(filePath);
        int dotIndex = storyFile.getName().lastIndexOf(".");

        if (!storyFile.getName().substring(dotIndex + 1).equals("paths")) {
            throw new IOException("The file type does not correspond with .paths");
        }
        Story story;
        String storyTitle;

        List<Passage> passages = new ArrayList<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(storyFile.toPath())) {
            storyTitle = bufferedReader.readLine();
            while ((currentLine = bufferedReader.readLine()) != null)  {

                if (currentLine.isBlank()) {
                    continue;
                }

                String passageTitle;
                String passageContent;

                if (currentLine.startsWith("::")) {
                    passageTitle = currentLine.substring(2);
                    currentLine = bufferedReader.readLine();
                    passageContent = currentLine;

                    currentLine = bufferedReader.readLine();
                    StoryReader.checkIfEndOfFile();

                    if (currentLine.isBlank()) {
                        passages.add(new Passage(passageTitle, passageContent));
                        continue;
                    }

                    //Scan for link
                    Link link;
                    String linkTitle;
                    String linkReference;
                    Passage passage = new Passage(passageTitle, passageContent);

                    while (currentLine.startsWith("[")) {
                        linkTitle = currentLine.substring(currentLine.indexOf("[") + 1, currentLine.lastIndexOf("]"));
                        linkReference = currentLine.substring(currentLine.indexOf("(") + 1, currentLine.lastIndexOf(")"));
                        link = new Link(linkTitle, linkReference);
                        currentLine = bufferedReader.readLine();
                        StoryReader.checkIfEndOfFile();

                        if (currentLine.startsWith("[") || currentLine.isBlank()) {
                            link = new Link(linkTitle, linkReference);
                            passage.addLink(link);
                            continue;
                        }

                        //Scan for actions
                        while (currentLine.startsWith("<")) {
                            char actionType = currentLine.charAt(1);
                            String splitActionString;
                            switch (actionType) {
                                case 'g', 'h', 's' -> StoryReader.addNumericalActionToLink(actionType, currentLine, link);
                                case 'i' -> {
                                    StringBuilder itemName = new StringBuilder();
                                    String[] splitUpInventoryAction = currentLine.split(" ");
                                    int i = 1;
                                    while (i < splitUpInventoryAction.length - 1) {
                                        itemName.append(splitUpInventoryAction[i]).append(" ");
                                        i++;
                                    }
                                    itemName.append(splitUpInventoryAction[i], 0, splitUpInventoryAction[i].length() - 1);

                                    link.addAction(new InventoryAction(itemName.toString()));
                                }
                            }
                            currentLine = bufferedReader.readLine();
                            StoryReader.checkIfEndOfFile();
                        }
                        passage.addLink(link);
                    }
                    passages.add(passage);
                }
            }
            bufferedReader.close();
            story = new Story(storyTitle, passages.get(0));
            passages.remove(0);
            passages.forEach(story::addPassage);
        } catch (IOException e) {
            throw new IOException("File does not exist");
        }
        return story;
    }

    private static void checkIfEndOfFile() {
        if (currentLine == null) {
            StoryReader.currentLine = "";
        }
    }
    private static void addNumericalActionToLink(char actionType, String currentLine, Link link) {
        if (actionType == 'g') {
            link.addAction(new GoldAction(Integer.parseInt(currentLine.split(" ")[1].substring(0, currentLine.split(" ")[1].lastIndexOf('>')))));
        }
        if (actionType == 's') {
            link.addAction(new ScoreAction(Integer.parseInt(currentLine.split(" ")[1].substring(0, currentLine.split(" ")[1].lastIndexOf('>')))));
        }
        if (actionType == 'h') {
            link.addAction(new HealthAction(Integer.parseInt(currentLine.split(" ")[1].substring(0, currentLine.split(" ")[1].lastIndexOf('>')))));
        }

    }
    public static void main(String[] args) throws IOException {
        Story story = StoryReader.readStory("src/main/resources/saved stories/trollStory.paths");
        System.out.println(story);
    }

}
