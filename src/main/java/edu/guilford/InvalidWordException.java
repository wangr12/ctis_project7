package edu.guilford;

public class InvalidWordException extends RuntimeException {
    public InvalidWordException(String message) {
        super(message);
    }
}