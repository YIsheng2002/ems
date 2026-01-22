package com.tutorial.ems.core.port.in.employee;

import com.tutorial.ems.core.domain.Employee;

public interface UpdateEmployeeUseCase {
    Employee edit(UpdateEmployeeCommand command);
}
