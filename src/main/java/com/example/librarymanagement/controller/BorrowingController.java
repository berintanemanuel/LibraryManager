package com.example.librarymanagement.controller;

import com.example.librarymanagement.model.Borrowing;
import com.example.librarymanagement.utils.filters.SearchBorrowingFilter;
import com.example.librarymanagement.service.BorrowingService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/borrowings")
@RestController
public class BorrowingController {

    private final BorrowingService borrowingService;

    public  BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @GetMapping
    public List<Borrowing> getBorrowings(@RequestParam(required = false) Long memberId,
                                         @RequestParam(required = false) Long bookId,
                                         @RequestParam(required = false) Boolean returned,
                                         @RequestParam(required = false) LocalDate borrowDate,
                                         @RequestParam(required = false) LocalDate returnDate) {
        return borrowingService.getBorrowings(new SearchBorrowingFilter(memberId, borrowDate, returnDate, bookId, returned));
    }

}
