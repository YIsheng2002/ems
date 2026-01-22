package com.tutorial.ems.core.port.out;

import com.tutorial.ems.core.domain.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {
    Department save(Department department);
    List<Department> findAll();
    Optional<Department> findById(Long id);
    void deleteById(Long id);
}

