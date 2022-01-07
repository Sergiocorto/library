package com.example.Book_Catalog.exception;

public class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super (message);
    }
}
