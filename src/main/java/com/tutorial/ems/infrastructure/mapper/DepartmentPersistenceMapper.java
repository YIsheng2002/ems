package com.tutorial.ems.infrastructure.mapper;

import com.tutorial.ems.core.domain.Department;
import com.tutorial.ems.infrastructure.entity.DepartmentEntity;
import org.springframework.stereotype.Component;

@Component
public class DepartmentPersistenceMapper {

    public Department toDomain(DepartmentEntity entity) {
        if (entity == null) return null;

        return Department.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    public DepartmentEntity toEntity(Department domain) {
        if (domain == null) return null;

        DepartmentEntity entity = new DepartmentEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        return entity;
    }
}
