package com.example.librarymanagement.utils.responses;

import com.example.librarymanagement.model.Author;

public record AuthorResponseDTO(Long id, String firstName, String lastName) {
    public static AuthorResponseDTO createFromAuthor(Author author) {
        return new AuthorResponseDTO(author.getId(), author.getFirstName(), author.getLastName());
    }
}
