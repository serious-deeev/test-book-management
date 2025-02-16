package org.serious.dev.exception;

public class BookIsAlreadyExistsException extends RuntimeException {

    private static final String TEMPLATE = "the book with ISBN %s has already been created in the database";

    public BookIsAlreadyExistsException(String isbn) {
        super(String.format(TEMPLATE, isbn));
    }
}
