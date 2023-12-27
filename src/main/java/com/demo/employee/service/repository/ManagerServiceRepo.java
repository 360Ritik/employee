package com.demo.employee.service.repository;

import com.demo.employee.dto.ManagerDto;
import com.demo.employee.entity.Manager;

public interface ManagerServiceRepo {
    Manager addNewManager(ManagerDto managerDto);
}
