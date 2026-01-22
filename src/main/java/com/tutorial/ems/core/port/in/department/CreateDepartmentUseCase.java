package com.tutorial.ems.core.port.in.department;

import com.tutorial.ems.core.domain.Department;

public interface CreateDepartmentUseCase {
    //One mathod per use case (Single Responsibility Principle)
    //Name methods execute() for consistency
    //Document exceptions in JavaDoc
    //Use commands, not primitive parameters
    Department create(CreateDepartmentCommand command);
}
