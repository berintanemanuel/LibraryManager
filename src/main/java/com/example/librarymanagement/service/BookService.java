package com.example.librarymanagement.service;

import com.example.librarymanagement.exceptions.AuthorNonExistentException;
import com.example.librarymanagement.exceptions.BookNonExistentException;
import com.example.librarymanagement.exceptions.DuplicateBookException;
import com.example.librarymanagement.model.Author;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.utils.requests.BookRequestDTO;
import com.example.librarymanagement.utils.filters.SearchBookFilter;
import com.example.librarymanagement.repository.AuthorRepository;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.utils.responses.BookResponseDTO;
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
    public BookResponseDTO addBook(BookRequestDTO bookRequestDTO) {
        Author author = authorRepository.findById(bookRequestDTO.authorId()).orElseThrow(AuthorNonExistentException::new);
        Book book = new Book();
        book.setTitle(bookRequestDTO.title());
        book.setIsbn(bookRequestDTO.isbn());
        book.setGenre(bookRequestDTO.genre());
        book.setAuthor(author);
        book.setQuantityInStock(bookRequestDTO.quantityInStock());
        try{
            bookRepository.save(book);
            return BookResponseDTO.createFromBook(book);
        } catch(DataIntegrityViolationException e){
            throw new DuplicateBookException("Book already exists");
        }
    }

    public List<BookResponseDTO> getBooks(SearchBookFilter filter){
        Specification<Book> specification = Specification
                .where(BookSpecifications.byId(filter.id())
                .and(BookSpecifications.byIsbn(filter.isbn()))
                .and(BookSpecifications.byAuthorId(filter.authorId()))
                .and(BookSpecifications.byGenre(filter.genre()))
                .and(BookSpecifications.byTitle(filter.title()))
                .and(BookSpecifications.byQuantityInStockLowerBound(filter.quantityInStockLowerBound()))
                .and(BookSpecifications.byQuantityInStockUpperBound(filter.quantityInStockUpperBound())));
        List<Book> books = bookRepository.findAll(specification);
        return books.stream().map(BookResponseDTO::createFromBook).toList();
    }

    public void deleteBook(Long id){
        try{
            bookRepository.deleteById(id);
        } catch(DataIntegrityViolationException e){
            throw new BookNonExistentException("No book with given id found");
        }
    }

    public BookResponseDTO updateBook(Long id, BookRequestDTO bookRequest){
        Book book = bookRepository.findById(id).orElseThrow(BookNonExistentException::new);

        Author newAuthor = authorRepository.findById(bookRequest.authorId()).orElseThrow(AuthorNonExistentException::new);

        book.setTitle(bookRequest.title());
        book.setIsbn(bookRequest.isbn());
        book.setGenre(bookRequest.genre());
        book.setAuthor(newAuthor);
        book.setQuantityInStock(bookRequest.quantityInStock());
        bookRepository.save(book);
        return BookResponseDTO.createFromBook(book);
    }

}
