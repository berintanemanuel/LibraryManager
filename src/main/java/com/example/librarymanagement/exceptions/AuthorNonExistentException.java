package com.example.librarymanagement.exceptions;

public class AuthorNonExistentException extends RuntimeException {
    public AuthorNonExistentException(String message) {
        super(message);
    }

    public AuthorNonExistentException() {
        super("Author Not Found");
    }
}
