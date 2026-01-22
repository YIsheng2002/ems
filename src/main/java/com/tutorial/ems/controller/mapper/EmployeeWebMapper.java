package com.tutorial.ems.controller.mapper;

import com.tutorial.ems.controller.dto.EmployeeRequest;
import com.tutorial.ems.controller.dto.EmployeeResponse;
import com.tutorial.ems.controller.dto.DepartmentResponse;
import com.tutorial.ems.core.domain.Employee;
import com.tutorial.ems.core.port.in.employee.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EmployeeWebMapper {
    GetEmployeeQuery toGetEmployeeQuery(Long id);

    @Mapping(target = "departmentId", source = "id")
    GetEmployeeByDepartmentQuery toGetEmployeeByDepartmentQuery(Long id);

    CreateEmployeeCommand toCreateEmployeeCommand(EmployeeRequest employee);

    // Use explicit default method to build UpdateEmployeeCommand to avoid MapStruct/Lombok builder detection issues
    default UpdateEmployeeCommand toUpdateEmployeeCommand(Long id, EmployeeRequest employee) {
        return UpdateEmployeeCommand.builder()
                .id(id)
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .hireDate(employee.getHireDate())
                .role(employee.getRole())
                .departmentId(employee.getDepartmentId())
                .build();
    }

    // Use explicit default method to map domain Employee to controller EmployeeResponse, computing fullName and workingYears
    default EmployeeResponse toEmployeeResponse(Employee employee) {
        if (employee == null) return null;
        DepartmentResponse dept = null;
        if (employee.getDepartment() != null) {
            dept = DepartmentResponse.builder()
                    .id(employee.getDepartment().getId())
                    .name(employee.getDepartment().getName())
                    .description(employee.getDepartment().getDescription())
                    .build();
        }

        return EmployeeResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .fullName(employee.getFullName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .hireDate(employee.getHireDate())
                .role(employee.getRole())
                .workingYears(employee.getWorkingYears())
                .department(dept)
                .build();
    }

    DeleteEmployeeCommand toDeleteEmployeeCommand(Long id);
}