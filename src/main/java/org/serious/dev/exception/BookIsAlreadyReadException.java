package org.serious.dev.exception;

public class BookIsAlreadyReadException extends RuntimeException {

    private static final String TEMPLATE = "the book with ID %d has already been marked as read";

    public BookIsAlreadyReadException(long id) {
        super(String.format(TEMPLATE, id));
    }
}
