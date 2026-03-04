package com.example.librarymanagement.controller;

import com.example.librarymanagement.exceptions.AuthorNonExistentException;
import com.example.librarymanagement.model.Author;
import com.example.librarymanagement.utils.filters.SearchAuthorFilter;
import com.example.librarymanagement.service.AuthorService;
import com.example.librarymanagement.utils.responses.AuthorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/authors")
@RestController
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public void addAuthor(@RequestBody Author author){
        authorService.addAuthor(author);
    }

    @GetMapping
    public List<AuthorResponseDTO> getAuthors(@RequestParam(required = false) Long id,
                                              @RequestParam(required = false) String firstName,
                                              @RequestParam(required = false) String lastName){
        return authorService.getAuthors(new SearchAuthorFilter(id, firstName, lastName));
    }

    @DeleteMapping
    public void deleteAuthor(@RequestParam Long id){
        authorService.deleteAuthor(id);
    }

    @PutMapping
    public void updateAuthor(@RequestParam Long id, @RequestBody Author author){
        authorService.updateAuthor(id, author);
    }
}
