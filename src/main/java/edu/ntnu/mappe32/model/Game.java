package edu.ntnu.mappe32.model;

import edu.ntnu.mappe32.model.goal_related.Goal;
import edu.ntnu.mappe32.model.story_related.Passage;
import edu.ntnu.mappe32.model.story_related.Link;
import edu.ntnu.mappe32.model.story_related.Story;
import java.util.ArrayList;
import java.util.List;

/**
 * The game is the facade of the paths game.
 * The class connects a player to a story, and has methods to start
 * and maneuver through the game.
 *
 * @author Kristians J. Matrevics
 * @version 0.1
 */
public record Game(Player player, Story story, List<Goal> goals) {
    /**
     * A constructor for objects of the game class.
     *
     * @param player (Player): The player object that this game uses.
     * @param story  (Story): The story object that this game uses.
     * @param goals  (List<Goal>): The goals of this particular game.
     * @throws IllegalArgumentException Throws IllegalArguemntException if player or story is null.
     * @throws IllegalStateException Throws IllegalStateException if goals is null.
     * @since 0.1
     */
    public Game {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (story == null) {
            throw new IllegalArgumentException("Story cannot be null");
        }
        if (goals == null || goals.isEmpty()) {
            throw new IllegalStateException("A game must have a list of goals with atleast one goal");
        }
    }

    /**
     * @return The player object of this game, as player.
     * @since 0.1
     */
    @Override
    public Player player() {
        return player;
    }

    /**
     * @return the story object of this game, as story.
     * @since 0.1
     */
    @Override
    public Story story() {
        return story;
    }

    /**
     * @return the goals of this game, as a List of goal-objects.
     * @since 0.1
     */
    @Override
    public List<Goal> goals() {
        return new ArrayList<>(goals);
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