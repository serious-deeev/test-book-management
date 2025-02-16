package org.serious.dev.exception;

public class NoSuchBookException extends RuntimeException {

    private static final String TEMPLATE = "the book with ID %d not found in database";

    public NoSuchBookException(long id) {
        super(String.format(TEMPLATE, id));
    }
}
