package com.example.Book_Catalog.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class BookCreateDto {
    private String title;
    private String description;
    private List<Long> authorsId ;
    private MultipartFile image;

    public BookCreateDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getAuthorsId() {
        return authorsId;
    }

    public void setAuthorsId(List<Long> authorsId) {
        this.authorsId = (List<Long>) authorsId;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
