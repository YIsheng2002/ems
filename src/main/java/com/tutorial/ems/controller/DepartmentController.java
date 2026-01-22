package com.tutorial.ems.controller;

import com.tutorial.ems.controller.dto.DepartmentRequest;
import com.tutorial.ems.controller.dto.DepartmentResponse;
import com.tutorial.ems.controller.mapper.DepartmentWebMapper;
import com.tutorial.ems.core.port.in.department.CreateDepartmentUseCase;
import com.tutorial.ems.core.port.in.department.DeleteDepartmentUseCase;
import com.tutorial.ems.core.port.in.department.GetDepartmentUseCase;
import com.tutorial.ems.core.port.in.department.UpdateDepartmentUseCase;
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
    public ResponseEntity<DepartmentResponse> create(
            @RequestBody DepartmentRequest departmentRequest
    ){
        var createdDepartment = createDepartmentUseCase.create(
                mapper.toCreateDepartmentCommand(departmentRequest)
        );
        return ResponseEntity.ok(mapper.toDepartmentResponse(createdDepartment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getById(
            @PathVariable Long id
    ){
        var department = getDepartmentUseCase.get(
                mapper.toGetDepartmentQuery(id)
        );
        return ResponseEntity.ok(mapper.toDepartmentResponse(department));
    }

    @GetMapping("/")
    public ResponseEntity<List<DepartmentResponse>> getAll(){
        var departments = getDepartmentUseCase.getAll();
        var response = departments.stream()
                .map(mapper::toDepartmentResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> update(
            @PathVariable Long id,
            @RequestBody DepartmentRequest departmentRequest
    ){
        var updatedDepartment = updateDepartmentUseCase.edit(
                mapper.toUpdateDepartmentCommand(id, departmentRequest)
        );
        return ResponseEntity.ok(mapper.toDepartmentResponse(updatedDepartment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ){
        deleteDepartmentUseCase.delete(
                mapper.toDeleteDepartmentCommand(id)
        );
        return ResponseEntity.noContent().build();
    }
}
