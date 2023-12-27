package com.demo.employee.respository;


import com.demo.employee.entity.Manager;
import org.springframework.data.repository.CrudRepository;

public interface ManagerRepository extends CrudRepository<Manager, String> {
    Manager findManagerByManagerId(String reportsTo);
}
