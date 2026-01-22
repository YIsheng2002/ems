package com.tutorial.ems.core.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
public class Employee {
    @With
    Long id;

    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    LocalDate hireDate;
    String role;
    Department department;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getDepartmentName() {
        return department != null ? department.getName() : null;
    }

    public int getWorkingYears() {
        return LocalDate.now().getYear() - hireDate.getYear();
    }
}
