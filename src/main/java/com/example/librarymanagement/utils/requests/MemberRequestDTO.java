package com.example.librarymanagement.utils.requests;

import java.time.LocalDate;

public record MemberRequestDTO(String lastName, String firstName, String email, Boolean active, LocalDate dateOfBirth) {
}
