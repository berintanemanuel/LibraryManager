package com.example.librarymanagement.controller;

import com.example.librarymanagement.utils.requests.BookRequestDTO;
import com.example.librarymanagement.utils.filters.SearchBookFilter;
import com.example.librarymanagement.service.BookService;
import com.example.librarymanagement.utils.responses.BookResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/books")
@RestController
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getBooks(@RequestParam(required = false) String title,
                                                          @RequestParam(required = false) String isbn,
                                                          @RequestParam(required = false) Long authorId,
                                                          @RequestParam(required = false) Long id,
                                                          @RequestParam(required = false) Long quantityInStockLowerBound,
                                                          @RequestParam(required = false) Long quantityInStockUpperBound,
                                                          @RequestParam(required = false) String genre){
        List<BookResponseDTO> books = bookService.getBooks(new SearchBookFilter(id, title, isbn, genre, authorId, quantityInStockLowerBound, quantityInStockUpperBound));
        return ResponseEntity.ok().body(books);
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> addBook(@RequestBody BookRequestDTO bookRequestDTO){
        BookResponseDTO book = bookService.addBook(bookRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @DeleteMapping
    public ResponseEntity<BookResponseDTO> deleteBook(@RequestParam Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<BookResponseDTO> updateBook(@RequestParam Long id, @RequestBody BookRequestDTO bookRequestDTO){
        BookResponseDTO book = bookService.updateBook(id, bookRequestDTO);
        return ResponseEntity.ok().body(book);
    }

}
