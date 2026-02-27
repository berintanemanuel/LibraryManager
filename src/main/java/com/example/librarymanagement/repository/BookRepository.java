package com.example.librarymanagement.repository;

import com.example.librarymanagement.model.Author;
import com.example.librarymanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String bookTitle);
    List<Book> findByAuthorId(Long authorId);
    List<Book> findByGenre(String bookGenre);
    Book findByIsbn(String bookIsbn);
}
