package com.example.librarymanagement.exceptions;

public class BookNonExistentException extends RuntimeException{
    public BookNonExistentException(String message) {
        super(message);
    }
    public BookNonExistentException() {
        super("Book Not Found");
    }
}
