package com.example.librarymanagement.utils.responses;

import com.example.librarymanagement.model.Book;

public record BookResponseDTO(Long id, String title, String isbn, String genre, Long authorId, String authorFirstName, String authorLastName, Long quantityInStock) {
    public static BookResponseDTO createFromBook(Book book) {
        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getGenre(),
                book.getAuthor().getId(),
                book.getAuthor().getFirstName(),
                book.getAuthor().getLastName(),
                book.getQuantityInStock()
        );
    }
}
