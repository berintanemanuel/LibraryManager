package com.example.librarymanagement.service;

import com.example.librarymanagement.model.Author;
import com.example.librarymanagement.utils.filters.SearchAuthorFilter;
import com.example.librarymanagement.repository.AuthorRepository;
import com.example.librarymanagement.utils.specifications.AuthorSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthors(SearchAuthorFilter filter) {
        Specification<Author> specification = Specification
                .where(AuthorSpecifications.byId(filter.id()))
                .and(AuthorSpecifications.byFirstName(filter.firstName()))
                .and(AuthorSpecifications.byLastName(filter.lastName()));
        return authorRepository.findAll(specification);
    }

    public void addAuthor(Author author) {
        authorRepository.save(author);
    }
}
