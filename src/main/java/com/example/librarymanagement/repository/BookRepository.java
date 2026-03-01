package com.example.librarymanagement.repository;

import com.example.librarymanagement.model.Author;
import com.example.librarymanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    List<Book> findByTitle(String bookTitle);
    List<Book> findByAuthorId(Long authorId);
    List<Book> findByGenre(String bookGenre);
    Book findByIsbn(String bookIsbn);
}
