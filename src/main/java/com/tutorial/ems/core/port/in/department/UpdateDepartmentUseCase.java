package com.tutorial.ems.core.port.in.department;

import com.tutorial.ems.core.domain.Department;

public interface UpdateDepartmentUseCase {
    Department edit(UpdateDepartmentCommand command);
}
