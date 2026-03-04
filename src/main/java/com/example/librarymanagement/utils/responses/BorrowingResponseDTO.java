package com.example.librarymanagement.utils.responses;

import com.example.librarymanagement.model.Borrowing;

import java.time.LocalDate;

public record BorrowingResponseDTO(
        Long id,
        Long bookId,
        String bookTitle,
        String bookAuthorFirstName,
        String bookAuthorLastName,
        Long memberId,
        String memberFirstName,
        String memberLastName,
        LocalDate borrowDate,
        LocalDate returnDate,
        Boolean returned
        ) {

    public static BorrowingResponseDTO createFromBorrowing(Borrowing borrowing) {
        return new BorrowingResponseDTO(
            borrowing.getId(),
            borrowing.getBook().getId(),
            borrowing.getBook().getTitle(),
            borrowing.getBook().getAuthor().getFirstName(),
            borrowing.getBook().getAuthor().getLastName(),
            borrowing.getMember().getId(),
            borrowing.getMember().getFirstName(),
            borrowing.getMember().getLastName(),
            borrowing.getBorrowDate(),
            borrowing.getReturnDate(),
            borrowing.getReturned()
        );
    }
}
