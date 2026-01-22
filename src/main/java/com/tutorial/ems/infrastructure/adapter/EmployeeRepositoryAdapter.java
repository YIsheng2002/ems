package com.tutorial.ems.infrastructure.adapter;

import com.tutorial.ems.core.domain.Employee;
import com.tutorial.ems.core.port.out.EmployeeRepository;
import com.tutorial.ems.infrastructure.mapper.EmployeePersistenceMapper;
import com.tutorial.ems.infrastructure.repository.EmployeeJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeRepositoryAdapter implements EmployeeRepository {
    private final EmployeeJPARepository employeeJPARepository;
    private final EmployeePersistenceMapper mapper;

    @Override
    public Employee save(Employee employee) {
        var entity = mapper.toEntity(employee);
        var savedEntity = employeeJPARepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        var entityOpt = employeeJPARepository.findById(id);
        return entityOpt.map(mapper::toDomain);
    }

    @Override
    public List<Employee> findAll() {
        var entities = employeeJPARepository.findAll();

        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Employee> findByDepartmentId(Long departmentId) {
        var entities =  employeeJPARepository.findByDepartmentId(departmentId);

        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        employeeJPARepository.deleteById(id);
    }

}
