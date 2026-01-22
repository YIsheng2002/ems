package com.tutorial.ems.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class EmployeeResponse{
    Long id;
    String firstName;
    String lastName;
    String fullName;
    String email;
    String phoneNumber;
    LocalDate hireDate;
    String role;
    int workingYears;
    DepartmentResponse department;
}

