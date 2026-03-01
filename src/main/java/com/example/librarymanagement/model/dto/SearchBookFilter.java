package com.example.librarymanagement.model.dto;

public record SearchBookFilter(Long id, String title, String isbn, String genre, Long authorId, Long quantityInStockLowerBound, Long quantityInStockUpperBound) {
}
