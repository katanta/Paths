package edu.ntnu.mappe32;


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
            String linkTitle;
            String linkReference;
            StringBuilder value = new StringBuilder();

            if (currentLine.isBlank()) {
                continue;
            }

            //Scan for passage

            while (currentLine.startsWith("::")) {
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
                while (currentLine.startsWith("[")) {
                    linkTitle = currentLine.substring(currentLine.indexOf("[") + 1, currentLine.lastIndexOf("]"));
                    linkReference = currentLine.substring(currentLine.indexOf("(") + 1, currentLine.lastIndexOf(")"));
                    currentLine = scan.nextLine();

                    if (currentLine.startsWith("[") || currentLine.isBlank()) {
                        link = new Link(linkTitle, linkReference);
                        links.add(link);

                        if (currentLine.isBlank()) {
                            currentLine = scan.nextLine();
                        }
                        continue;
                    }

                    if (currentLine.isBlank()) {
                        currentLine = scan.nextLine();
                        continue;
                    }

                    while (currentLine.startsWith("<")) {

                        if (currentLine.startsWith("<g")) {
                            String split = currentLine.split(" ")[1];
                            value = new StringBuilder(split.substring(0, split.lastIndexOf('>')));
                            actions.add(new GoldAction(Integer.parseInt(value.toString())));
                            currentLine = scan.nextLine();
                            continue;
                        }

                        //Health action

                        if (currentLine.startsWith("<h")) {
                            String split = currentLine.split(" ")[1];
                            value = new StringBuilder(split.substring(0, split.lastIndexOf('>')));
                            actions.add(new HealthAction(Integer.parseInt(value.toString())));
                            currentLine = scan.nextLine();
                            continue;
                        }

                        //Inventory action

                        if (currentLine.startsWith("<i")) {
                            String[] splitLine = currentLine.split(" ");
                            int i = 1;

                            while (i < splitLine.length) {

                                if (i == splitLine.length - 1) {
                                    String split = splitLine[i];
                                    value.append(split, 0, split.length() - 1);
                                }

                                value.append(splitLine[i]).append(" ");
                                i++;
                            }
                            actions.add(new InventoryAction(value.toString()));
                            currentLine = scan.nextLine();

                            continue;
                        }

                        //Score action

                        if (currentLine.startsWith("<s")) {
                            String split = currentLine.split(" ")[1];
                            value = new StringBuilder(split.substring(0, split.lastIndexOf('>')));
                            actions.add(new ScoreAction(Integer.parseInt(value.toString())));
                            currentLine = scan.nextLine();
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
        story = new Story(storyTitle, passages.get(0));
        passages.remove(0);
        passages.forEach(story::addPassage);
        return story;
    }

}
