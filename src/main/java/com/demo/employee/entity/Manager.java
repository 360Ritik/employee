package com.demo.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document(collection = "manager")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Manager {

    @MongoId
    private String id;
    private String managerId;
    private String managerName;
    private String email;
}
