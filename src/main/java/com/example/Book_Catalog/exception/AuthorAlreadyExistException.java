package com.example.Book_Catalog.exception;

public class AuthorAlreadyExistException extends Exception{
    public AuthorAlreadyExistException (String message) {
        super (message);
    }
}
