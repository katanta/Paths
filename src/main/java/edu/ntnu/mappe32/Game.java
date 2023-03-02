package edu.ntnu.mappe32;

import edu.ntnu.mappe32.goal_related.Goal;
import edu.ntnu.mappe32.story_related.Passage;
import edu.ntnu.mappe32.story_related.Link;
import edu.ntnu.mappe32.story_related.Story;

import java.util.List;

/**
 * The game is the facade of the paths game.
 * The class connects a player to a story, and has methods to start
 * and maneuver through the game.
 * @author Kristians J. Matrevics
 * @version 0.1
 */
public class Game {
    private Player player;
    private Story story;
    private List<Goal> goals;

    /**
     * A constructor for objects of the game class.
     * @param player (Player): The player object that this game uses.
     * @param story (Story): The story object that this game uses.
     * @param goals (List<Goal>): The goals of this particular game.
     * @since 0.1
     */
    public Game(Player player, Story story, List<Goal> goals) throws IllegalArgumentException {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null.");
        }
        if (story == null) {
            throw new IllegalArgumentException("Story cannot be null.");
        }
        if (goals.isEmpty()) {
            throw new IllegalArgumentException("A game must have atleast 1 goal.");
        }

        this.player = player;
        this.story = story;
        this.goals = goals;
    }

    /**
     * @return The player object of this game, as player.
     * @since 0.1
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return the story object of this game, as story.
     * @since 0.1
     */
    public Story getStory() {
        return story;
    }

    /**
     * @return the goals of this game, as a List of goal-objects.
     * @since 0.1
     */
     public List<Goal> getGoals() {
         return goals;
     }


    /**
     * @return Begins the game by returning the opening passage of a story.
     * @since 0.1
     */
    public Passage begin() {
        return story.getOpeningPassage();
    }

    /**
     * @param link (Link): The link object that a passage uses as its key in a story.
     * @return The passage that is the value to the given link/key in a story.
     * @since 0.1
     */
    public Passage go(Link link) {
        link.getActions().forEach(a -> a.execute(player));
        return story.getPassage(link);
    }
}