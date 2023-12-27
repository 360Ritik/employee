package com.demo.employee.service.repository;

import org.springframework.core.io.FileSystemResource;

import java.io.IOException;

public interface ImageServiceRepo {

    String uploadImage(byte[] content, String imageName, String id) throws IOException;

    FileSystemResource downloadImage(String path, String fileName);
}
