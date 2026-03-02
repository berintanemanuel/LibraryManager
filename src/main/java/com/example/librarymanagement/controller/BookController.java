package com.example.librarymanagement.controller;

import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.utils.requests.CreateBookRequest;
import com.example.librarymanagement.utils.filters.SearchBookFilter;
import com.example.librarymanagement.service.BookService;
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
    public List<Book> getBooks(@RequestParam(required = false) String title,
                               @RequestParam(required = false) String isbn,
                               @RequestParam(required = false) Long authorId,
                               @RequestParam(required = false) Long id,
                               @RequestParam(required = false) Long quantityInStockLowerBound,
                               @RequestParam(required = false) Long quantityInStockUpperBound,
                               @RequestParam(required = false) String genre){
        return bookService.getBooks(new SearchBookFilter(id, title, isbn, genre, authorId, quantityInStockLowerBound, quantityInStockUpperBound));
    }

    @PostMapping
    public void addBook(@RequestBody CreateBookRequest createBookRequest){
        bookService.addBook(createBookRequest);
    }

    @DeleteMapping
    public void deleteBook(@RequestParam Long id){
        bookService.deleteBook(id);
    }

    @PutMapping
    public void updateBook(@RequestParam Long id, @RequestBody CreateBookRequest createBookRequest){
        bookService.updateBook(id, createBookRequest);
    }

}
