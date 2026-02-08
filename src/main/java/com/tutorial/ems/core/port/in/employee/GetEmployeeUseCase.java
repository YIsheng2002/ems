package com.tutorial.ems.core.port.in.employee;

import com.tutorial.ems.core.domain.Employee;

import java.util.List;

public interface GetEmployeeUseCase {
    Employee getEmployee(GetEmployeeQuery query);
    List<Employee> getEmployeesByDepartment(GetEmployeeByDepartmentQuery query);
    List<Employee> getAllEmployees();
}
