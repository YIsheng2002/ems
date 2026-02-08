package com.tutorial.ems.core.port.in.employee;

import com.tutorial.ems.core.domain.Employee;

public interface CreateEmployeeUseCase {
    Employee createEmployee(CreateEmployeeCommand command);
}
