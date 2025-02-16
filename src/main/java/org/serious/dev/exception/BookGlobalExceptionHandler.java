package org.serious.dev.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class BookGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleBookNotFoundException(NoSuchBookException e) {
        Map<String, String> response = Map.of("error", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleBookIsAlreadyExistsException(BookIsAlreadyExistsException e) {
        Map<String, String> response = Map.of("error", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleBookIsAlreadyReadException(BookIsAlreadyReadException e) {
        Map<String, String> response = Map.of("error", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
