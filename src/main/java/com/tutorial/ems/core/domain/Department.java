package com.tutorial.ems.core.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;


@Value
@Builder(toBuilder = true)
public class Department{
    @With
    Long id;

    String name;
    String description;
}

