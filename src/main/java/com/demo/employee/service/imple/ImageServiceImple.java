package com.demo.employee.service.imple;


import com.demo.employee.service.repository.ImageServiceRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageServiceImple implements ImageServiceRepo {

    @Value("${base.path}")
    String RESOURCES_DIR;

    @Override
    public String uploadImage(byte[] content, String imageName, String id) throws IOException {

        Path newFile = Paths.get(RESOURCES_DIR + id + "-" + imageName);
        Files.createDirectories(newFile.getParent());
        Files.write(newFile, content);

        return newFile.toAbsolutePath()
                .toString();

    }

    @Override
    public FileSystemResource downloadImage(String path, String fileName) {
        try {
            return new FileSystemResource(Paths.get(path));
        } catch (Exception e) {
            // Handle access or file not found problems.
            throw new RuntimeException();
        }
    }

}
