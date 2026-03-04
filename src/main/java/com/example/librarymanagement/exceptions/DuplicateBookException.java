package com.example.librarymanagement.exceptions;

public class DuplicateBookException extends RuntimeException {
    public DuplicateBookException(String message) {
        super(message);
    }

    public DuplicateBookException() {
        super("Book already exists");
    }
}
