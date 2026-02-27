package com.example.librarymanagement.controller;

import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.dto.CreateBookRequest;
import com.example.librarymanagement.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/books")
@RestController
public class BookController {
    private final Logger log = LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String title,
                               @RequestParam(required = false) String isbn,
                               @RequestParam(required = false) Long authorId)
    {
        return  bookService.getBooks(title, isbn, authorId);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id)
    {
        return bookService.getBookById(id);
    }


    @PostMapping
    public void addBook(@RequestBody CreateBookRequest createBookRequest){
        bookService.addBook(createBookRequest);
    }

    /// TODO: Implement a get by author method which checks for both first name and last name
}
