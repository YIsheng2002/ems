package com.tutorial.ems.core.port.in.department;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateDepartmentCommand {
    Long departmentId;
    String name;
    String description;
}
