package com.tutorial.ems.core.port.in.employee;

import lombok.Value;

@Value
public class GetEmployeeByDepartmentQuery {
    Long departmentId;
}
