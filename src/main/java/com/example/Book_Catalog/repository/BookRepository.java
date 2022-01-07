package com.example.Book_Catalog.repository;

import com.example.Book_Catalog.entity.BookEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    public BookEntity findAllById(Long id);
    public BookEntity findByTitle(String tittle);
    public List<BookEntity> findByOrderByTitle();
    public List<BookEntity> findByTitleContaining(String string);
}
