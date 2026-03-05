package com.example.librarymanagement.service;

import com.example.librarymanagement.exceptions.AuthorNonExistentException;
import com.example.librarymanagement.model.Author;
import com.example.librarymanagement.utils.filters.SearchAuthorFilter;
import com.example.librarymanagement.repository.AuthorRepository;
import com.example.librarymanagement.utils.requests.AuthorRequestDTO;
import com.example.librarymanagement.utils.responses.AuthorResponseDTO;
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

    public List<AuthorResponseDTO> getAuthors(SearchAuthorFilter filter) {
        Specification<Author> specification = Specification
                .where(AuthorSpecifications.byId(filter.id()))
                .and(AuthorSpecifications.byFirstName(filter.firstName()))
                .and(AuthorSpecifications.byLastName(filter.lastName()));
        List<Author> authors = authorRepository.findAll(specification);
        return authors.stream().map(AuthorResponseDTO::createFromAuthor).toList();
    }

    public AuthorResponseDTO addAuthor(AuthorRequestDTO authorDto) {
        Author author = new Author(authorDto.firstName(), authorDto.lastName());
        authorRepository.save(author);
        return AuthorResponseDTO.createFromAuthor(author);
    }

    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }

    public AuthorResponseDTO updateAuthor(Long authorId, AuthorRequestDTO newAuthor) {
        Author author = authorRepository.findById(authorId).orElseThrow(AuthorNonExistentException::new);
        author.setFirstName(newAuthor.firstName());
        author.setLastName(newAuthor.lastName());
        authorRepository.save(author);
        return AuthorResponseDTO.createFromAuthor(author);
    }
}
