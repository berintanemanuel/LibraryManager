package com.example.librarymanagement.utils.requests;

public record CreateBookRequest(String title, String isbn, String genre, Long quantityInStock, Long authorId) {
}
