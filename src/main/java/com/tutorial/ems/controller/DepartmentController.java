package com.tutorial.ems.controller;

import com.tutorial.ems.controller.dto.DepartmentRequest;
import com.tutorial.ems.controller.dto.DepartmentResponse;
import com.tutorial.ems.controller.mapper.DepartmentWebMapper;
import com.tutorial.ems.core.port.in.department.CreateDepartmentUseCase;
import com.tutorial.ems.core.port.in.department.DeleteDepartmentUseCase;
import com.tutorial.ems.core.port.in.department.GetDepartmentUseCase;
import com.tutorial.ems.core.port.in.department.UpdateDepartmentUseCase;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController {
    private final GetDepartmentUseCase getDepartmentUseCase;
    private final UpdateDepartmentUseCase updateDepartmentUseCase;
    private final CreateDepartmentUseCase createDepartmentUseCase;
    private final DeleteDepartmentUseCase deleteDepartmentUseCase;
    private final DepartmentWebMapper mapper;

    @PostMapping("/")
    public ResponseEntity<@NonNull DepartmentResponse> createDepartment(
            @Valid @RequestBody DepartmentRequest departmentRequest
    ){
        var createdDepartment = createDepartmentUseCase.createDepartment(
                mapper.toCreateDepartmentCommand(departmentRequest)
        );
        return ResponseEntity.ok(mapper.toDepartmentResponse(createdDepartment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<@NonNull DepartmentResponse> getDepartmentById(
            @Valid @PathVariable Long id
    ){
        var department = getDepartmentUseCase.getDepartment(
                mapper.toGetDepartmentQuery(id)
        );
        return ResponseEntity.ok(mapper.toDepartmentResponse(department));
    }

    @GetMapping()
    public ResponseEntity<@NonNull List<DepartmentResponse>> getAllDepartments(){
        var departments = getDepartmentUseCase.getAllDepartments();
        var response = departments.stream()
                .map(mapper::toDepartmentResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<@NonNull DepartmentResponse> updateDepartment(
            @Valid @PathVariable Long id,
            @Valid @RequestBody DepartmentRequest departmentRequest
    ){
        var updatedDepartment = updateDepartmentUseCase.editDepartment(
                mapper.toUpdateDepartmentCommand(id, departmentRequest)
        );
        return ResponseEntity.ok(mapper.toDepartmentResponse(updatedDepartment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(
            @Valid @PathVariable Long id
    ){
        deleteDepartmentUseCase.deleteDepartment(
                mapper.toDeleteDepartmentCommand(id)
        );
        return ResponseEntity.noContent().build();
    }
}
