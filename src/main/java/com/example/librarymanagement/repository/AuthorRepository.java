package com.example.librarymanagement.repository;

import com.example.librarymanagement.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {
    List<Author> findByFirstName(String firstName);
    List<Author> findByLastName(String lastName);
}
