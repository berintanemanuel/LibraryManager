package com.example.librarymanagement.exceptions;

public class BorrowingNonExistentException extends RuntimeException {
    public BorrowingNonExistentException(String message) {
        super(message);
    }
    public BorrowingNonExistentException() {
        super("Borrowing not found");
    }
}
