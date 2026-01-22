package com.tutorial.ems.core.port.in.department;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class DeleteDepartmentCommand {
    Long departmentId;
}
