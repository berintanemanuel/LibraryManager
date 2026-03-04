package com.example.librarymanagement.controller;

import com.example.librarymanagement.utils.filters.SearchBorrowingFilter;
import com.example.librarymanagement.service.BorrowingService;
import com.example.librarymanagement.utils.responses.BorrowingResponseDTO;
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
    public List<BorrowingResponseDTO> getBorrowings(@RequestParam(required = false) Long id,
                                                    @RequestParam(required = false) Long memberId,
                                                    @RequestParam(required = false) Long bookId,
                                                    @RequestParam(required = false) Boolean returned,
                                                    @RequestParam(required = false) LocalDate borrowDate,
                                                    @RequestParam(required = false) LocalDate returnDate) {
        return borrowingService.getBorrowings(new SearchBorrowingFilter(id, memberId, borrowDate, returnDate, bookId, returned));
    }

    @PostMapping
    public void borrowBook(@RequestParam Long bookId, @RequestParam Long memberId){
        borrowingService.borrowBook(bookId, memberId);
    }

    @DeleteMapping
    public void deleteBorrowing(@RequestParam Long borrowingId) {
        borrowingService.deleteBorrowing(borrowingId);
    }

    @PutMapping
    public void closeBorrowing(@RequestParam Long borrowingId) {
        borrowingService.closeBorrowing(borrowingId);
    }

}
