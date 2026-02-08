package com.tutorial.ems.core.port.in.department;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class GetDepartmentQuery {
    Long departmentId;
}
