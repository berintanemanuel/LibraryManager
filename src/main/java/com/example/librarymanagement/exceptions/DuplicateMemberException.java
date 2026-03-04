package com.example.librarymanagement.exceptions;

public class DuplicateMemberException extends RuntimeException{
    public DuplicateMemberException(String message) {
        super(message);
    }

    public DuplicateMemberException() {
        super("Member already exists");
    }
}
