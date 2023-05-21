package edu.ntnu.mappe32.model.action_related;

public class PlayerDoesNotHaveItemException extends Exception{
    public PlayerDoesNotHaveItemException(String message) {
        super(message);
    }
}
