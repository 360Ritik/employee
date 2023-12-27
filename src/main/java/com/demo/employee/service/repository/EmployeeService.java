package com.demo.employee.service.repository;// Import statements

import com.demo.employee.dto.EmployeeDto;
import com.demo.employee.entity.Employee;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {
    String addEmployeeDetails(EmployeeDto employeeDto) throws IOException, MessagingException;

    List<Employee> getAllEmployees(int offSet, int size, String order, String sortBy);

    void deleteEmployeeById(String id);

    Employee updateEmployeeById(String id, Employee employee);

    Employee getNthLevelManager(String employeeId, int level);

}
