package com.tutorial.ems.infrastructure.repository;

import com.tutorial.ems.infrastructure.entity.EmployeeEntity;
import com.tutorial.ems.core.port.out.EmployeeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeJPARepository extends JpaRepository<EmployeeEntity,Long> {

    @Query("SELECT e FROM EmployeeEntity e WHERE e.department.id = :departmentId")
    List<EmployeeEntity> findByDepartmentId(Long departmentId);
    void deleteById(Long id);
}
