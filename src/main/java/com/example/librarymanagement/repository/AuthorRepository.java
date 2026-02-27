package com.example.librarymanagement.repository;

import com.example.librarymanagement.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByFirstName(String firstName);
    List<Author> findByLastName(String lastName);
}
