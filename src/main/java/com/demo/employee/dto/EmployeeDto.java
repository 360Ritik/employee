package com.demo.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private String employeeName;
    private Long phoneNumber;
    private String email;
    private String reportingManager;
    private MultipartFile image;

    // Getters and setters
}