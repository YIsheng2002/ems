package com.tutorial.ems.controller.mapper;

import com.tutorial.ems.controller.dto.DepartmentRequest;
import com.tutorial.ems.controller.dto.DepartmentResponse;
import com.tutorial.ems.core.domain.Department;
import com.tutorial.ems.core.port.in.department.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentWebMapper {

    GetDepartmentQuery toGetDepartmentQuery(Long departmentId);

    CreateDepartmentCommand toCreateDepartmentCommand(DepartmentRequest departmentRequest);

    @Mapping(target = "departmentId", source = "id")
    UpdateDepartmentCommand toUpdateDepartmentCommand(Long id, DepartmentRequest departmentRequest);

    @Mapping(target = "departmentId", source = "id")
    DeleteDepartmentCommand toDeleteDepartmentCommand(Long id);

    DepartmentResponse toDepartmentResponse(Department department);
}