package com.tutorial.ems.controller.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DepartmentResponse{
    Long id;
    String name;
    String description;
}
