package com.tutorial.ems.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepartmentRequest {
    @Size(min = 1, max = 10, message = "Department name must be between 1 and 10 characters")
    @NotBlank(message = "Department name cannot be blank")
    private String name;

    @Size(min = 1, max = 100, message = "Department description must be between 1 and 50 characters")
    @NotBlank(message = "Department description cannot be blank")
    private String description;
}
