package com.example.librarymanagement.service;

import com.example.librarymanagement.exceptions.AuthorNonExistentException;
import com.example.librarymanagement.model.Author;
import com.example.librarymanagement.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthorById(long id) {
        return authorRepository.findById(id).orElseThrow(AuthorNonExistentException::new);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> getAllAuthorsByLastName(String lastName) {
        return authorRepository.findByLastName(lastName);
    }

    public void addAuthor(Author author) {
        authorRepository.save(author);
    }
}
