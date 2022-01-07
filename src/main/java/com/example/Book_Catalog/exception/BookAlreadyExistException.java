package com.example.Book_Catalog.exception;

public class BookAlreadyExistException extends Exception{
    public BookAlreadyExistException(String message) {
        super (message);
    }
}
