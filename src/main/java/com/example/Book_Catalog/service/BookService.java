package com.example.Book_Catalog.service;

import com.example.Book_Catalog.dto.BookCreateDto;
import com.example.Book_Catalog.entity.AuthorEntity;
import com.example.Book_Catalog.entity.BookEntity;
import com.example.Book_Catalog.exception.BookAlreadyExistException;
import com.example.Book_Catalog.exception.BookNotFoundException;
import com.example.Book_Catalog.repository.AuthorRepository;
import com.example.Book_Catalog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ImageService imageService;

    public Iterable<BookEntity> getAllBooks() throws Exception {
        try{
            return bookRepository.findAll();
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public BookEntity saveNewBook (BookCreateDto dto) throws Exception {
        
        if( bookRepository.findByTitle(dto.getTitle()) != null) {
            throw new BookAlreadyExistException("Such an book already exist");
        }
        String imagePath;
        try {
            imagePath = imageService.saveImage(dto.getImage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        List<AuthorEntity> authors = authorRepository.findAllById(dto.getAuthorsId());

        BookEntity book = new BookEntity(dto.getTitle(), dto.getDescription(), imagePath, authors);

        bookRepository.save(book);
        
        return book;
    }

    public Long deleteBook(Long id) throws Exception {

        BookEntity book = bookRepository.findAllById( id );
        if( book == null ) {
            throw new BookNotFoundException("Book not found");
        }

        if( !imageService.deleteImage(book.getImage()) ) {
            throw new FileNotFoundException();
        }

        try{
            bookRepository.deleteById(id);
            return id;
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public List<BookEntity> sortBooks() throws Exception {
        try{
            return bookRepository.findByOrderByTitle();
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public List<BookEntity> searchBooks(String string) throws Exception {
        try{
            return bookRepository.findByTitleContaining(string);
        }catch (Exception e){
            throw new Exception(e);
        }
    }
}