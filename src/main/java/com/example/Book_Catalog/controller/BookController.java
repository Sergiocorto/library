package com.example.Book_Catalog.controller;

import com.example.Book_Catalog.dto.BookCreateDto;
import com.example.Book_Catalog.entity.BookEntity;
import com.example.Book_Catalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public Iterable<BookEntity> getAllBooks() throws Exception {
        return bookService.getAllBooks();
    }

    @PostMapping
    public BookEntity createNewBook(@ModelAttribute BookCreateDto book) throws Exception {
        return bookService.saveNewBook(book);
    }

    @DeleteMapping
    public Long deleteBook(@RequestParam Long id) throws Exception {
        return bookService.deleteBook(id);
    }

    @GetMapping("/sort")
    public List<BookEntity> sortBooks() throws Exception {
        return bookService.sortBooks();
    }
    @GetMapping("/search")
    public List<BookEntity> searchBook(@RequestParam String string) throws Exception {
        return bookService.searchBooks(string);
    }
}