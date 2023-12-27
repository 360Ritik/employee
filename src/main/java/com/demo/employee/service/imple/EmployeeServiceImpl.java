package com.demo.employee.service.imple;

import com.demo.employee.dto.EmployeeDto;
import com.demo.employee.entity.Employee;
import com.demo.employee.entity.Manager;
import com.demo.employee.respository.EmployeeRepository;
import com.demo.employee.respository.ManagerRepository;
import com.demo.employee.service.repository.EmailSenderRepo;
import com.demo.employee.service.repository.EmployeeService;
import com.demo.employee.service.repository.ImageServiceRepo;
import jakarta.mail.MessagingException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;
    private final ImageServiceRepo imageServiceRepo;
    private final EmailSenderRepo emailSenderRepo;

    private final ManagerRepository managerRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ImageServiceRepo imageServiceRepo, EmailSenderRepo emailSenderRepo, ManagerRepository managerRepository) {
        this.employeeRepository = employeeRepository;
        this.imageServiceRepo = imageServiceRepo;
        this.emailSenderRepo = emailSenderRepo;
        this.managerRepository = managerRepository;
    }

    @Override
    public String addEmployeeDetails(EmployeeDto employeeDto) throws IOException, MessagingException {

        String employeeUuid = UUID.randomUUID().toString();
        String lastFourChars = employeeUuid.substring(employeeUuid.length() - 4);
        String location = imageServiceRepo.uploadImage(employeeDto.getImage().getBytes(), employeeDto.getImage().getOriginalFilename(), lastFourChars);

        Employee employee = Employee.builder()
                .employeeId(employeeUuid)
                .employeeName(employeeDto.getEmployeeName())
                .phoneNumber(employeeDto.getPhoneNumber())
                .email(employeeDto.getEmail())
                .reportingManager(employeeDto.getReportingManager())
                .profileImageLocation(location)
                .build();


        // Construct email message
        String emailSubject = "A New Employee Added ";
        String emailBody = String.format(
                "<b>%s</b> will now work under you. <br/>"
                        + "<b>Employee Phone Number:</b> %s <br/>"
                        + "<b>Email:</b> %s.",
                employee.getEmployeeName().toUpperCase(),
                employee.getPhoneNumber(),
                employee.getEmail()
        );
        Employee employee1 = employeeRepository.save(employee);
        Employee employeeEmail = employeeRepository.findEmployeeByEmployeeId(employeeDto.getReportingManager());
        Manager managerEmail = managerRepository.findManagerByManagerId(employeeDto.getReportingManager());

        if (employeeEmail != null) {
            emailSenderRepo.sendSimpleEmail(employeeEmail.getEmail(), emailBody, emailSubject);
        }
        if (managerEmail != null) {
            emailSenderRepo.sendSimpleEmail(managerEmail.getEmail(), emailBody, emailSubject);
        }
        // Send email


        return employee1.getEmployeeId();
    }


    @Override
    public List<Employee> getAllEmployees(int offSet, int size, String order, String sortBy) {
        PageRequest pageRequest = PageRequest.of(offSet, size, Sort.Direction.valueOf(order), sortBy);
        return employeeRepository.findAll(pageRequest).stream().toList();
    }

    @Override
    public void deleteEmployeeById(String id) {
        employeeRepository.deleteEmployeeByEmployeeId(id);
    }

    @Override
    public Employee updateEmployeeById(String id, Employee employee) {
        // Implement logic to update employee
        Employee employee1 = employeeRepository.findEmployeeByEmployeeId(id);
        employee1.setEmployeeName(employee.getEmployeeName());
        employee1.setPhoneNumber(employee.getPhoneNumber());
        employee1.setEmail(employee.getEmail());
        employee1.setReportingManager(employee.getReportingManager());
        return employeeRepository.save(employee1);
    }

    @Override
    public Employee getNthLevelManager(String reportingId, int level) {
        Employee employee = employeeRepository.findEmployeeByEmployeeId(reportingId);

        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }

        if (level <= 0) {
            throw new IllegalArgumentException("Invalid level");
        }

        return getNthLevelManagerRecursive(employee, level);
    }

    private Employee getNthLevelManagerRecursive(Employee employee, int level) {
        if (level == 1 || employee.getReportingManager() == null) {
            // Assuming the manager information is stored in a separate collection (e.g., Manager class)
            return employeeRepository.findEmployeeByReportingManager(employee.getReportingManager());
        }
        String reportsToUuid = employee.getReportingManager();

        // Recursive call to get the (level - 1) manager
        return getNthLevelManagerRecursive(employeeRepository.findEmployeeByReportingManager(reportsToUuid), level - 1);
    }


}
