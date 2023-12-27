package com.demo.employee.service.imple;

import com.demo.employee.dto.ManagerDto;
import com.demo.employee.entity.Manager;
import com.demo.employee.respository.ManagerRepository;
import com.demo.employee.service.repository.ManagerServiceRepo;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class ManagerServiceImple implements ManagerServiceRepo {

    private final ManagerRepository managerRepository;

    public ManagerServiceImple(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public Manager addNewManager(ManagerDto managerDto) {
        Manager manager = new Manager();
        manager.setManagerName(managerDto.getManagerName());
        String managerUuid = UUID.randomUUID().toString();
        manager.setManagerId(managerUuid);
        manager.setEmail(managerDto.getEmail());

        return managerRepository.save(manager);
    }
}
