package com.example.librarymanagement.controller;

import com.example.librarymanagement.model.Author;
import com.example.librarymanagement.model.dto.SearchAuthorFilter;
import com.example.librarymanagement.service.AuthorService;
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
    public List<Author> getAuthors(@RequestParam(required = false) Long id,
                                   @RequestParam(required = false) String firstName,
                                   @RequestParam(required = false) String lastName){
        return authorService.getAuthors(new SearchAuthorFilter(id, firstName, lastName));
    }

}
