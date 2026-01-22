package com.tutorial.ems.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepartmentRequest {
    @NotBlank
    @Size(min = 1, max = 10, message = "Department name must be between 1 and 10 characters")
    private String name;

    @NotBlank
    @Size(min = 1, max = 100, message = "Department description must be between 1 and 50 characters")
    private String description;
}
