package com.tutorial.ems.infrastructure.adapter;

import com.tutorial.ems.core.domain.Department;
import com.tutorial.ems.core.port.out.DepartmentRepository;
import com.tutorial.ems.infrastructure.mapper.DepartmentPersistenceMapper;
import com.tutorial.ems.infrastructure.repository.DepartmentJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DepartmentRepositoryAdapter implements DepartmentRepository {
    private final DepartmentJPARepository departmentJPARepository;
    private final DepartmentPersistenceMapper mapper;

    @Override
    public Department save(Department department) {
        var entity = mapper.toEntity(department);
        var savedEntity = departmentJPARepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public List<Department> findAll() {
        var entities = departmentJPARepository.findAll();

        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Department> findById(Long id) {
        var entityOpt = departmentJPARepository.findById(id);
        return entityOpt.map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        departmentJPARepository.deleteById(id);
    }
}
