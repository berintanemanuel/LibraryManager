package com.example.librarymanagement.model.dto;

public record CreateBookRequest(String title, String isbn, String genre, Long quantityInStock, Long authorId) {
}
