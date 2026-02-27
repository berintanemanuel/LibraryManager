package com.example.librarymanagement.service;

import com.example.librarymanagement.exceptions.AuthorNonExistentException;
import com.example.librarymanagement.exceptions.BookNonExistentException;
import com.example.librarymanagement.exceptions.DuplicateBookException;
import com.example.librarymanagement.model.Author;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.dto.CreateBookRequest;
import com.example.librarymanagement.repository.AuthorRepository;
import com.example.librarymanagement.repository.BookRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public  BookService(BookRepository bookRepository,  AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(BookNonExistentException::new);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public void addBook(CreateBookRequest createBookRequest) {
        Author author = authorRepository.findById(createBookRequest.authorId()).orElseThrow(AuthorNonExistentException::new);
        Book book = new Book();
        book.setTitle(createBookRequest.title());
        book.setIsbn(createBookRequest.isbn());
        book.setGenre(createBookRequest.genre());
        book.setAuthor(author);
        book.setQuantityInStock(createBookRequest.quantityInStock());
        try{
            bookRepository.save(book);
        } catch(DataIntegrityViolationException e){
            throw new DuplicateBookException();
        }
    }

    public Book getBookByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> getBookByTitle(String title){
        return bookRepository.findByTitle(title);
    }

    public List<Book> getBooksByAuthorId(Long authorId){
        return bookRepository.findByAuthorId(authorId);
    }

    public List<Book> getBooks(String title, String isbn, Long authorId){
        if(isbn != null)
            return List.of(bookRepository.findByIsbn(isbn));
        if(title != null)
            return bookRepository.findByTitle(title);
        if(authorId != null)
            return bookRepository.findByAuthorId(authorId);
        return bookRepository.findAll();
    }
}
