package com.example.librarymanagement.service;

import com.example.librarymanagement.exceptions.AuthorNonExistentException;
import com.example.librarymanagement.exceptions.BookNonExistentException;
import com.example.librarymanagement.exceptions.DuplicateBookException;
import com.example.librarymanagement.model.Author;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.utils.requests.CreateBookRequest;
import com.example.librarymanagement.utils.filters.SearchBookFilter;
import com.example.librarymanagement.repository.AuthorRepository;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.utils.specifications.BookSpecifications;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
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

    public List<Book> getBooks(SearchBookFilter filter){
        Specification<Book> specification = Specification
                .where(BookSpecifications.byId(filter.id())
                .and(BookSpecifications.byIsbn(filter.isbn()))
                .and(BookSpecifications.byAuthorId(filter.authorId()))
                .and(BookSpecifications.byGenre(filter.genre()))
                .and(BookSpecifications.byTitle(filter.title()))
                .and(BookSpecifications.byQuantityInStockLowerBound(filter.quantityInStockLowerBound()))
                .and(BookSpecifications.byQuantityInStockUpperBound(filter.quantityInStockUpperBound())));
        return bookRepository.findAll(specification);
    }

    public void deleteBook(Long id){
        try{
            bookRepository.deleteById(id);
        } catch(DataIntegrityViolationException e){
            throw new BookNonExistentException();
        }
    }

    public void updateBook(Long id, CreateBookRequest bookRequest){
        Book book = bookRepository.findById(id).orElseThrow(BookNonExistentException::new);

        Author newAuthor = authorRepository.findById(bookRequest.authorId()).orElseThrow(AuthorNonExistentException::new);

        book.setTitle(bookRequest.title());
        book.setIsbn(bookRequest.isbn());
        book.setGenre(bookRequest.genre());
        book.setAuthor(newAuthor);
        book.setQuantityInStock(bookRequest.quantityInStock());
        bookRepository.save(book);
    }

}
