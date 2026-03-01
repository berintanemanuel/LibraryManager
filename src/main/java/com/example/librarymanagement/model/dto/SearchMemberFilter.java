package com.example.librarymanagement.model.dto;

import java.time.LocalDate;

public record SearchMemberFilter(Long id, String firstName, String lastName, Boolean active, LocalDate startDateOfBirth,  LocalDate endDateOfBirth, String email) {
}
