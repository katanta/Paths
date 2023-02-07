package edu.ntnu.mappe32;

import edu.ntnu.mappe32.story_related.Passage;
import edu.ntnu.mappe32.story_related.Link;
import edu.ntnu.mappe32.story_related.Story;

public class Game {
    private Player player;
    private Story story;
    //private List<Goal> goals

    public Game(Player player, Story story) {
        this.player = player;
        this.story = story;
        //this.goals = goals;
    }

    public Player getPlayer() {
        return player;
    }

    public Story getStory() {
        return story;
    }

    /**
     * public List<Goal> getGoals() {
     *     return goals;
     * }
     */

    public Passage begin() {
        return story.getOpeningPassage();
    }

    public Passage go(Link link) {
        return story.getPassage(link); //TODO: Implement this properly, I don't think this is correct...
    }
}
