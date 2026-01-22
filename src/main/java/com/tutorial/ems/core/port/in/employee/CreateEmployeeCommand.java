package com.tutorial.ems.core.port.in.employee;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class CreateEmployeeCommand {
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    LocalDate hireDate;
    String role;
    Long departmentId;
}
