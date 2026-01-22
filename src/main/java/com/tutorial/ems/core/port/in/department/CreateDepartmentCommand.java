package com.tutorial.ems.core.port.in.department;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateDepartmentCommand {
    String name;
    String description;
}
