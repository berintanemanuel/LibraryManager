package com.example.librarymanagement.controller;

import com.example.librarymanagement.exceptions.*;
import com.example.librarymanagement.utils.errors.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(DuplicateMemberException.class)
    public ResponseEntity<ErrorDetails> exceptionDuplicateMember(){
        ErrorDetails errorDetails = new ErrorDetails("Member already exists");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(AuthorNonExistentException.class)
    public ResponseEntity<ErrorDetails> exceptionAuthorNonExistent(){
        ErrorDetails errorDetails = new ErrorDetails("Author Not Found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(BookNonExistentException.class)
    public ResponseEntity<ErrorDetails> exceptionBookNonExistent(){
        ErrorDetails errorDetails = new ErrorDetails("Book Not Found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(BookNotInStockException.class)
    public ResponseEntity<ErrorDetails> exceptionBookNotInStock(){
        ErrorDetails errorDetails = new ErrorDetails("Book Not In Stock");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(DuplicateBookException.class)
    public ResponseEntity<ErrorDetails> exceptionDuplicateBook(){
        ErrorDetails errorDetails = new ErrorDetails("Book already exists");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<ErrorDetails> exceptionInvalidDate(){
        ErrorDetails errorDetails = new ErrorDetails("Invalid Date");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(MemberNonExistentException.class)
    public ResponseEntity<ErrorDetails> exceptionMemberNonExistent(){
        ErrorDetails errorDetails = new ErrorDetails("Member Not Found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }
}
