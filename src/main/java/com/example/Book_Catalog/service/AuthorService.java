package com.example.Book_Catalog.service;

import com.example.Book_Catalog.exception.AuthorAlreadyExistException;
import com.example.Book_Catalog.entity.AuthorEntity;
import com.example.Book_Catalog.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Iterable<AuthorEntity> getAllAuthors() throws Exception {
        try{
            return authorRepository.findAll();
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public AuthorEntity saveNewAuthor (AuthorEntity author) throws AuthorAlreadyExistException {
        if( authorRepository.findByNameAndLastnameAndPatronymic(
                author.getName(),
                author.getLastname(),
                author.getPatronymic()) != null) {
            throw new AuthorAlreadyExistException("Such an author already exist");
        }
        return authorRepository.save(author);
    }

    public AuthorEntity editAuthor (AuthorEntity author) throws Exception {
        try {
            AuthorEntity authorInDb = authorRepository.findById(author.getId()).get();

            authorInDb.setName(author.getName());
            authorInDb.setLastname(author.getLastname());
            authorInDb.setPatronymic(author.getPatronymic());
            return authorRepository.save(authorInDb);
        }catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Long deleteAuthor(Long id) throws Exception {
        try{
            authorRepository.deleteById(id);
            return id;
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public List<AuthorEntity> sortAuthors() throws Exception {
        try{
            return authorRepository.findByOrderByLastname();
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public List<AuthorEntity> searchAuthors(String string) throws Exception {
        try{
            return authorRepository.findByNameContainingOrLastnameContainingOrPatronymicContaining(string, string, string);
        }catch (Exception e){
            throw new Exception(e);
        }
    }
}
