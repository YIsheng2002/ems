package com.tutorial.ems.core.service;

import com.tutorial.ems.core.domain.Department;
import com.tutorial.ems.core.port.in.department.*;
import com.tutorial.ems.core.port.out.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DepartmentServiceImpl implements
        CreateDepartmentUseCase,
        GetDepartmentUseCase,
        UpdateDepartmentUseCase,
        DeleteDepartmentUseCase{

    private final DepartmentRepository departmentRepository;

    @Override
    public Department create(CreateDepartmentCommand command) {
        var department = Department
                            .builder()
                            .id(null)
                            .name(command.getName())
                            .description(command.getDescription())
                            .build();

        var savedDepartment = departmentRepository.save(department);
        log.info("Department Created Successfully");

        return savedDepartment;
    }

    @Override
    public Department get(GetDepartmentQuery query) {
        var department = departmentRepository.findById(query.getDepartmentId());

        return department.orElse(null);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department edit(UpdateDepartmentCommand command) {
        var existingDepartmentOpt = departmentRepository.findById(command.getDepartmentId());
        if (existingDepartmentOpt.isEmpty()) {
            log.error("Department not found with id: " + command.getDepartmentId());
            return null;
        }

        var department = Department.builder()
                .id(command.getDepartmentId())
                .name(command.getName())
                .description(command.getDescription())
                .build();

        var updatedDepartment = departmentRepository.save(department);
        log.info("Department Updated Successfully");

        return updatedDepartment;
    }

    @Override
    public void delete(DeleteDepartmentCommand command) {
        departmentRepository.deleteById(command.getDepartmentId());
        log.info("Department Deleted Successfully");
    }
}
