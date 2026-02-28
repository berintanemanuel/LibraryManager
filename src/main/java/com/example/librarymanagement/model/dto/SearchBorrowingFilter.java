package com.example.librarymanagement.model.dto;

import java.time.LocalDate;

public record SearchBorrowingFilter(Long memberId, LocalDate borrowDate, LocalDate returnDate, Long bookId, Boolean returned) {
}
