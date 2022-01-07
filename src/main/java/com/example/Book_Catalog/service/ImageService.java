package com.example.Book_Catalog.service;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.UUID;

@Service
public class ImageService {

    @Value("${upload.dir}")
    private String uploadDirPath;

    public String saveImage(MultipartFile image) throws FileUploadException {

        if (!image.isEmpty()) {

            File uploadDir = new File(uploadDirPath);

            if(!uploadDir.exists()) uploadDir.mkdir();

            String uuidFile = UUID.randomUUID().toString();
            String fileName = uuidFile+ "-" +image.getOriginalFilename();

            File filePath = new File(uploadDirPath + fileName);
            try {
                image.transferTo(filePath);
                return fileName;
            } catch (Exception e) {
                throw new FileUploadException(e.getMessage());
            }
        } else {
            throw new FileUploadException();
        }
    }

    public Boolean deleteImage(String imageName) throws FileNotFoundException {

        String imagePath = uploadDirPath + imageName;
        
        File image = new File(imagePath);
        
        if (image.exists()) {
            image.delete();
            return true;
        } else {
            throw new FileNotFoundException();
        }
    }
}
