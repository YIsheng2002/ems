package com.tutorial.ems.core.port.in.department;

import com.tutorial.ems.core.domain.Department;

import java.util.List;

public interface GetDepartmentUseCase {
    Department getDepartment(GetDepartmentQuery query);

    List<Department> getAllDepartments();
}
