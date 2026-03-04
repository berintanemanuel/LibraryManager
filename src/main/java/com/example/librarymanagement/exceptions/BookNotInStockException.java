package com.example.librarymanagement.exceptions;

public class BookNotInStockException extends RuntimeException {
    public BookNotInStockException(String message) {
        super(message);
    }

    public BookNotInStockException() {
        super("Book not in stock");
    }
}
