package com.tutorial.ems.controller.dto;

import java.time.LocalDate;
import java.util.Optional;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeRequest {
        @Size(min = 1, max = 50)
        @NotBlank(message = "First name cannot be blank")
        private  String firstName;

        @Size(min = 1, max = 50)
        @NotBlank(message = "Last name cannot be blank")
        private String lastName;

        @Email(message = "Email format is invalid")
        @NotBlank(message= "Email cannot be blank")
        private String email;

        @Size(min = 10, max = 13)
        @NotBlank(message = "Phone number cannot be blank")
        private String phoneNumber;

        @NotNull(message = "Hire date cannot be null")
        private LocalDate hireDate;

        @NotBlank(message = "Role cannot be blank")
        private String role;

        @NotNull(message = "Department ID cannot be null")
        private Long departmentId;
}
