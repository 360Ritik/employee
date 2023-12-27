package com.demo.employee.controller;

import com.demo.employee.dto.EmployeeDto;
import com.demo.employee.dto.ManagerDto;
import com.demo.employee.entity.Employee;
import com.demo.employee.entity.Manager;
import com.demo.employee.service.repository.EmployeeService;
import com.demo.employee.service.repository.ManagerServiceRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {


    private final EmployeeService employeeService;

    private final ManagerServiceRepo managerServiceRepo;

    public EmployeeController(EmployeeService employeeService, ManagerServiceRepo managerServiceRepo) {
        this.employeeService = employeeService;
        this.managerServiceRepo = managerServiceRepo;
    }


    @PostMapping("/registerEmployee")
    public ResponseEntity<String> addEmployee(@ModelAttribute EmployeeDto employeeDto) {
        try {
            String employeeId = employeeService.addEmployeeDetails(employeeDto);
            return new ResponseEntity<>(employeeId, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error adding employee", e);
        }
    }

    @PostMapping("/registerManager")
    public ResponseEntity<Manager> addManager(@RequestBody ManagerDto managerDto) {
        try {
            Manager manager1 = managerServiceRepo.addNewManager(managerDto);
            return new ResponseEntity<>(manager1, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error adding Manager", e);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(required = false, defaultValue = "0") int offSet,
                                                          @RequestParam(required = false, defaultValue = "10") int size,
                                                          @RequestParam(required = false, defaultValue = "ASC") String order,
                                                          @RequestParam(required = false, defaultValue = "id") String sortBy) {
        try {
            List<Employee> employees = employeeService.getAllEmployees(offSet, size, order, sortBy);
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving employees", e);
        }
    }

    @DeleteMapping("/empId/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id) {
        try {
            employeeService.deleteEmployeeById(id);
            return new ResponseEntity<>("Employee data deleted", HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting employee", e);
        }
    }

    @PutMapping("/empId/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
        try {
            Employee employee1 = employeeService.updateEmployeeById(id, employee);
            return new ResponseEntity<>(employee1, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating employee", e);
        }
    }

    @GetMapping("/manager/{employeeId}/{level}")
    public ResponseEntity<Employee> getNthLevelManager(@PathVariable String employeeId, @PathVariable int level) {
        try {
            Employee manager = employeeService.getNthLevelManager(employeeId, level);
            return new ResponseEntity<>(manager, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error getting nth level manager", e);
        }
    }
}
