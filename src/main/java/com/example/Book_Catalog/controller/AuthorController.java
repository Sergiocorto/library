package com.example.Book_Catalog.controller;

import com.example.Book_Catalog.exception.AuthorAlreadyExistException;
import com.example.Book_Catalog.entity.AuthorEntity;
import com.example.Book_Catalog.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public Iterable<AuthorEntity> getAllAuthors() throws Exception {
        return authorService.getAllAuthors();
    }

    @PostMapping
    public AuthorEntity createNewAuthor(@ModelAttribute AuthorEntity author) throws AuthorAlreadyExistException {
            return authorService.saveNewAuthor(author);
    }

    @PutMapping
    public AuthorEntity editAuthor(@ModelAttribute AuthorEntity author) throws Exception {
        return authorService.editAuthor(author);
    }

    @DeleteMapping
    public Long deleteAuthor(@RequestParam Long id) throws Exception {
        return authorService.deleteAuthor(id);
    }

    @GetMapping("/sort")
    public List<AuthorEntity> sortAuthor() throws Exception {
        return authorService.sortAuthors();
    }
    @GetMapping("/search")
    public List<AuthorEntity> searchAuthor(@RequestParam String string) throws Exception {
        return authorService.searchAuthors(string);
    }
}
