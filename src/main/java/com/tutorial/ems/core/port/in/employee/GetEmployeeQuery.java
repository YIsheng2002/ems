package com.tutorial.ems.core.port.in.employee;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetEmployeeQuery {
    Long id;
}
