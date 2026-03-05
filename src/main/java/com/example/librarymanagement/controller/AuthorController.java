package com.example.librarymanagement.controller;

import com.example.librarymanagement.utils.filters.SearchAuthorFilter;
import com.example.librarymanagement.service.AuthorService;
import com.example.librarymanagement.utils.requests.AuthorRequestDTO;
import com.example.librarymanagement.utils.responses.AuthorResponseDTO;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/authors")
@RestController
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> addAuthor(@RequestBody AuthorRequestDTO author){
        AuthorResponseDTO response = authorService.addAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAuthors(@RequestParam(required = false) Long id,
                                              @RequestParam(required = false) String firstName,
                                              @RequestParam(required = false) String lastName){
        return ResponseEntity.ok().body(authorService.getAuthors(new SearchAuthorFilter(id, firstName, lastName)));
    }

    @DeleteMapping
    public ResponseEntity<AuthorResponseDTO> deleteAuthor(@RequestParam Long id){
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@RequestParam Long id, @RequestBody AuthorRequestDTO authorDto){
        return ResponseEntity.ok().body(authorService.updateAuthor(id, authorDto));
    }
}
