package com.tutorial.ems.core.service;

import com.tutorial.ems.core.domain.Employee;
import com.tutorial.ems.core.exception.NotFoundException;
import com.tutorial.ems.core.port.in.employee.*;

import com.tutorial.ems.core.port.out.DepartmentRepository;
import com.tutorial.ems.core.port.out.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EmployeeServiceImpl implements
        CreateEmployeeUseCase,
        GetEmployeeUseCase,
        UpdateEmployeeUseCase,
        DeleteEmployeeUseCase
{
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Employee create(
            CreateEmployeeCommand command
    ) {
        var department = departmentRepository.findById(command.getDepartmentId()).orElseThrow(
                () -> new NotFoundException("Department not found")
        );

        var employee = Employee
                .builder()
                .id(null)
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .email(command.getEmail())
                .phoneNumber(command.getPhoneNumber())
                .hireDate(command.getHireDate())
                .role(command.getRole())
                .department(department)
                .build();

        var savedEmployee = employeeRepository.save(employee);
        log.info("Employee Created Successfully");

        return savedEmployee;
    }

    @Override
    public Employee get(GetEmployeeQuery query) {
        var employee = employeeRepository.findById(query.getId());
        return employee.orElse(null);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getByDepartment(GetEmployeeByDepartmentQuery query) {

        return employeeRepository.findByDepartmentId(query.getDepartmentId());
    }

    @Override
    public Employee edit(UpdateEmployeeCommand command) {
        var existingEmployeeOpt = employeeRepository.findById(command.getId());
        if (existingEmployeeOpt.isEmpty()) {
            log.error("Employee not found with id: " + command.getId());
            return null;
        }

        var department = departmentRepository.findById(command.getDepartmentId()).orElseThrow(
                () -> new NotFoundException("Department not found")
        );

        var employee = Employee.builder()
                .id(command.getId())
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .email(command.getEmail())
                .phoneNumber(command.getPhoneNumber())
                .hireDate(command.getHireDate())
                .role(command.getRole())
                .department(department)
                .build();

        var updatedEmployee = employeeRepository.save(employee);
        log.info("Employee Updated Successfully");

        return  updatedEmployee;
    }

    @Override
    public void delete(DeleteEmployeeCommand command) {
        employeeRepository.deleteById(command.getId());
        log.info("Employee Deleted Successfully");
    }
}
