package com.tutorial.ems.core.port.out;

import com.tutorial.ems.core.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    List<Employee> findAll();
    Optional<Employee> findById(Long id);
    List<Employee> findByDepartmentId(Long departmentId);
    Employee save(Employee employee);
    void deleteById(Long id);
}

