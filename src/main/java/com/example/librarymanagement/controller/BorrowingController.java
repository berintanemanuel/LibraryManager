package com.example.librarymanagement.controller;

import com.example.librarymanagement.utils.filters.SearchBorrowingFilter;
import com.example.librarymanagement.service.BorrowingService;
import com.example.librarymanagement.utils.responses.BorrowingResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<BorrowingResponseDTO>> getBorrowings(@RequestParam(required = false) Long id,
                                                                    @RequestParam(required = false) Long memberId,
                                                                    @RequestParam(required = false) Long bookId,
                                                                    @RequestParam(required = false) Boolean returned,
                                                                    @RequestParam(required = false) LocalDate borrowDate,
                                                                    @RequestParam(required = false) LocalDate returnDate) {
        List<BorrowingResponseDTO> borrowings = borrowingService.getBorrowings(new SearchBorrowingFilter(id, memberId, borrowDate, returnDate, bookId, returned));
        return ResponseEntity.ok().body(borrowings);
    }

    @PostMapping
    public ResponseEntity<BorrowingResponseDTO> borrowBook(@RequestParam Long bookId, @RequestParam Long memberId){
        BorrowingResponseDTO borrowing = borrowingService.borrowBook(bookId, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowing);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBorrowing(@RequestParam Long borrowingId) {
        borrowingService.deleteBorrowing(borrowingId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<BorrowingResponseDTO> closeBorrowing(@RequestParam Long borrowingId) {
        BorrowingResponseDTO borrowing = borrowingService.closeBorrowing(borrowingId);
        return ResponseEntity.ok().body(borrowing);
    }

}
