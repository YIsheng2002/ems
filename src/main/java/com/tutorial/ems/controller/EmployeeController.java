package com.tutorial.ems.controller;

import com.tutorial.ems.controller.dto.EmployeeRequest;
import com.tutorial.ems.controller.dto.EmployeeResponse;
import com.tutorial.ems.controller.mapper.EmployeeWebMapper;
import com.tutorial.ems.core.port.in.employee.CreateEmployeeUseCase;
import com.tutorial.ems.core.port.in.employee.DeleteEmployeeUseCase;
import com.tutorial.ems.core.port.in.employee.GetEmployeeUseCase;
import com.tutorial.ems.core.port.in.employee.UpdateEmployeeUseCase;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final GetEmployeeUseCase getEmployeeUseCase;
    private final CreateEmployeeUseCase createEmployeeUseCase;
    private final UpdateEmployeeUseCase updateEmployeeUseCase;
    private final DeleteEmployeeUseCase deleteEmployeeUseCase;
    private final EmployeeWebMapper mapper;

    @PostMapping()
    public ResponseEntity<@NonNull EmployeeResponse> createEmployee(
            @Valid @RequestBody EmployeeRequest employeeRequest
    ){
        var createdEmployee = createEmployeeUseCase.createEmployee(
                mapper.toCreateEmployeeCommand(employeeRequest)
        );

        return ResponseEntity.ok(mapper.toEmployeeResponse(createdEmployee));
    }

    @GetMapping("/{id}")
    public ResponseEntity<@NonNull EmployeeResponse> getEmployeeById(
            @Valid @PathVariable Long id
    ){
        var employee = getEmployeeUseCase.getEmployee(
                mapper.toGetEmployeeQuery(id)
        );
        return ResponseEntity.ok(mapper.toEmployeeResponse(employee));
    }

    @GetMapping("/all")
    public ResponseEntity<@NonNull List<EmployeeResponse>> getAllEmployees(){
        var employees = getEmployeeUseCase.getAllEmployees();
        var response = employees.stream()
                .map(mapper::toEmployeeResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<@NonNull List<EmployeeResponse>> getAllEmployeeByDepartmentID(
            @PathVariable Long departmentId
    ) {
        var employees = getEmployeeUseCase.getEmployeesByDepartment(
                mapper.toGetEmployeeByDepartmentQuery(departmentId)
        );
        var response = employees.stream()
                .map(mapper::toEmployeeResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<@NonNull EmployeeResponse> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest employeeRequest
    ){
        var updatedEmployee = updateEmployeeUseCase.editEmployee(
                mapper.toUpdateEmployeeCommand(id, employeeRequest)
        );
        return ResponseEntity.ok(mapper.toEmployeeResponse(updatedEmployee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long id
    ){
        deleteEmployeeUseCase.deleteEmployee(
                mapper.toDeleteEmployeeCommand(id)
        );
        return ResponseEntity.noContent().build();
    }
}
