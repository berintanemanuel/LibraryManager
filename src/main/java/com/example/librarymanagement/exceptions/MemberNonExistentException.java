package com.example.librarymanagement.exceptions;

public class MemberNonExistentException extends RuntimeException {
    public MemberNonExistentException(String message) {
        super(message);
    }
    public MemberNonExistentException() {
        super("Member does not exist");
    }
}
