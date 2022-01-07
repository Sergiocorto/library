package com.example.Book_Catalog.repository;

import com.example.Book_Catalog.entity.AuthorEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    public AuthorEntity findByNameAndLastnameAndPatronymic(String name, String lastname, String patronymic);
    public List<AuthorEntity> findByOrderByLastname();
    public List<AuthorEntity> findByNameContainingOrLastnameContainingOrPatronymicContaining(String name, String lastname, String patronymic);
}
