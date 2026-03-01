package com.example.librarymanagement.utils.filters;

import java.time.LocalDate;

public record SearchBorrowingFilter(Long memberId, LocalDate borrowDate, LocalDate returnDate, Long bookId, Boolean returned) {
}
