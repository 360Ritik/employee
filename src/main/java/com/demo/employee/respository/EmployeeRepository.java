package com.demo.employee.respository;


import com.demo.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Employee findEmployeeByEmployeeId(String empId);

    Employee findEmployeeByReportingManager(String id);

    void deleteEmployeeByEmployeeId(String id);

    Page<Employee> findAll(Pageable pageable);
}