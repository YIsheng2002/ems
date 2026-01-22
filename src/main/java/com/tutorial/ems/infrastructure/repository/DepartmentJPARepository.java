package com.tutorial.ems.infrastructure.repository;

import com.tutorial.ems.infrastructure.entity.DepartmentEntity;
import com.tutorial.ems.core.port.out.DepartmentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentJPARepository extends JpaRepository<DepartmentEntity,Long> {

    List<DepartmentEntity> findAll();
    Optional<DepartmentEntity> findById(long id);
    Optional<DepartmentEntity> findByName(String name);
    DepartmentEntity save(DepartmentEntity departmentEntity);
    void deleteById(Long id);
}
