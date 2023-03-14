package edu.ntnu.mappe32.io;


import edu.ntnu.mappe32.action_related.*;
import edu.ntnu.mappe32.story_related.Link;
import edu.ntnu.mappe32.story_related.Passage;
import edu.ntnu.mappe32.story_related.Story;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StoryReader {

    public static Story readStory(String filePath) throws FileNotFoundException {
        File storyFile = new File(filePath);
        Story story;
        Scanner scan = new Scanner(storyFile);
        String storyTitle;
        storyTitle = scan.nextLine();

        List<Action> actions = new ArrayList<>();
        List<Link> links = new ArrayList<>();
        List<Passage> passages = new ArrayList<>();


        while (scan.hasNextLine()) {
            String currentLine = scan.nextLine();
            String passageTitle;
            String passageContent;
            if (currentLine.isBlank()) {
                continue;
            }

            //Scan for passage

            if (currentLine.startsWith("::")) {
                passageTitle = currentLine.substring(2);
                currentLine = scan.nextLine();
                passageContent = currentLine;

                if (scan.hasNextLine()) {
                    currentLine = scan.nextLine();
                }

                if (currentLine.isBlank()) {
                    passages.add(new Passage(passageTitle, passageContent));
                    continue;
                }


                //Scan for link
                Link link;
                String linkTitle;
                String linkReference;
                while (currentLine.startsWith("[")) {
                    linkTitle = currentLine.substring(currentLine.indexOf("[") + 1, currentLine.lastIndexOf("]"));
                    linkReference = currentLine.substring(currentLine.indexOf("(") + 1, currentLine.lastIndexOf(")"));

                    if (scan.hasNextLine()) {
                        currentLine = scan.nextLine();
                    } else {
                        currentLine = "";
                    }

                    if (currentLine.startsWith("[") || currentLine.isBlank()) {
                        link = new Link(linkTitle, linkReference);
                        links.add(link);

                        continue;
                    }

                    while (currentLine.startsWith("<")) {
                        char actionType = currentLine.charAt(1);
                        StringBuilder value = new StringBuilder();
                        String splitActionString;
                        switch (actionType) {
                            case 'g' -> {
                                splitActionString = currentLine.split(" ")[1];
                                value.append(splitActionString, 0, splitActionString.lastIndexOf('>'));
                                actions.add(new GoldAction(Integer.parseInt(value.toString())));
                                if (scan.hasNextLine()) {
                                    currentLine = scan.nextLine();
                                } else {
                                    currentLine = "";
                                }
                            }
                            case 'h' -> {
                                splitActionString = currentLine.split(" ")[1];
                                value.append(splitActionString, 0, splitActionString.lastIndexOf('>'));
                                actions.add(new HealthAction(Integer.parseInt(value.toString())));
                                if (scan.hasNextLine()) {
                                    currentLine = scan.nextLine();
                                } else {
                                    currentLine = "";
                                }
                            }
                            case 'i' -> {
                                String[] splitLine = currentLine.split(" ");
                                int i = 1;
                                while (i < splitLine.length) {

                                    if (i == splitLine.length - 1) {
                                        splitActionString = splitLine[i];
                                        value.append(splitActionString, 0, splitActionString.length() - 1);
                                    }

                                    value.append(splitLine[i]).append(" ");
                                    i++;
                                }
                                actions.add(new InventoryAction(value.toString()));
                                if (scan.hasNextLine()) {
                                    currentLine = scan.nextLine();
                                } else {
                                    currentLine = "";
                                }
                            }
                            case 's' -> {
                                splitActionString = currentLine.split(" ")[1];
                                value.append(splitActionString,0, splitActionString.lastIndexOf('>'));
                                actions.add(new ScoreAction(Integer.parseInt(value.toString())));
                                if (scan.hasNextLine()) {
                                    currentLine = scan.nextLine();
                                } else {
                                    currentLine = "";
                                }
                            }
                        }
                    }
                    link = new Link(linkTitle, linkReference);
                    actions.forEach(link::addAction);
                    links.add(link);
                    actions.clear();
                }
                Passage passage = new Passage(passageTitle, passageContent);
                links.forEach(passage::addLink);
                passages.add(passage);
                links.clear();
            }
        }
        scan.close();
        story = new Story(storyTitle, passages.get(0));
        passages.remove(0);
        passages.forEach(story::addPassage);
        return story;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Story story = StoryReader.readStory("src/main/resources/saved stories/trollStory.paths");
        System.out.println(story);
    }


}
