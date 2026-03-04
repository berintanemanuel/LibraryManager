package com.example.librarymanagement.utils.requests;

public record BookRequestDTO(String title, String isbn, String genre, Long quantityInStock, Long authorId) {
}
