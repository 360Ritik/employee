package com.demo.employee.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @MongoId
    private String id;
    private String employeeName;
    private String employeeId;
    private Long phoneNumber;
    private String email;
    private String reportingManager;
    private String profileImageLocation;

    @Override
    public String toString() {
        return "Employee{" +
                ", employeeName='" + employeeName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                +'\'' +
                '}';
    }


}