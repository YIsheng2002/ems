package com.tutorial.ems.infrastructure.mapper;

import com.tutorial.ems.core.domain.Employee;
import com.tutorial.ems.infrastructure.entity.EmployeeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeePersistenceMapper {
    private final DepartmentPersistenceMapper departmentPersistenceMapper;

    public Employee toDomain(EmployeeEntity employeeEntity) {
        if (employeeEntity == null) return null;

        var department = departmentPersistenceMapper.toDomain(employeeEntity.getDepartment());

        return Employee.builder()
                .id(employeeEntity.getId())
                .firstName(employeeEntity.getFirstName())
                .lastName(employeeEntity.getLastName())
                .email(employeeEntity.getEmail())
                .department(department)
                .phoneNumber(employeeEntity.getPhoneNumber())
                .hireDate(employeeEntity.getHireDate())
                .role(employeeEntity.getRole())
                .build();
    }

    public EmployeeEntity toEntity(Employee employee) {
        if (employee == null) return null;

        var departmentEntity = departmentPersistenceMapper.toEntity(employee.getDepartment());

        var entity = new EmployeeEntity();
        entity.setId(employee.getId());
        entity.setFirstName(employee.getFirstName());
        entity.setLastName(employee.getLastName());
        entity.setEmail(employee.getEmail());
        entity.setPhoneNumber(employee.getPhoneNumber());
        entity.setHireDate(employee.getHireDate());
        entity.setRole(employee.getRole());
        entity.setDepartment(departmentEntity);

        return entity;
    }
}
