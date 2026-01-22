package com.tutorial.ems.core.port.in.employee;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UpdateEmployeeCommand {
    Long id;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String role;
    LocalDate hireDate;
    Long departmentId;
}
