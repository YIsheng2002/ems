package com.tutorial.ems.core.port.in.employee;

import com.tutorial.ems.core.domain.Employee;

import java.util.List;

public interface GetEmployeeUseCase {
    Employee get(GetEmployeeQuery query);
    List<Employee> getByDepartment(GetEmployeeByDepartmentQuery query);
    List<Employee> getAll();
}
