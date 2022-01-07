package com.example.Book_Catalog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping
    public String getIndexPage(Model model) {
        model.addAttribute("title", "Book Catalog");
        return "index";
    }

    @GetMapping("authors")
    public String getAuthorPage(Model model) {
        model.addAttribute("title", "Authors");
        return "author";
    }

    @GetMapping("books")
    public String getBookPage(Model model) {
        model.addAttribute("title", "Books");
        return "book";
    }
}
