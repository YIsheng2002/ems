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
        @NotBlank
        @Size(min = 1, max = 50)
        private  String firstName;

        @NotBlank
        @Size(min = 1, max = 50)
        private String lastName;

        @NotBlank
        @Email
        private String email;

        @NotBlank
        @Size(min = 10, max = 13)
        private String phoneNumber;

        @NotNull
        private LocalDate hireDate;

        @NotBlank
        private String role;

        @NotNull
        private Long departmentId;
}
