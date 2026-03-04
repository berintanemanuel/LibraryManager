package com.example.librarymanagement.utils.responses;

import com.example.librarymanagement.model.Member;

import java.time.LocalDate;

public record MemberResponseDTO(Long id, String firstName, String lastName, Boolean active, LocalDate dateOfBirth, String email) {
    public static MemberResponseDTO createFromMember(Member member) {
        return new MemberResponseDTO(
                member.getId(),
                member.getFirstName(),
                member.getLastName(),
                member.getActive(),
                member.getDateOfBirth(),
                member.getEmail()
        );
    }
}
