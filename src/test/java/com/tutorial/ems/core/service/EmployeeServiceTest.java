package com.tutorial.ems.core.service;

import com.tutorial.ems.core.domain.Department;
import com.tutorial.ems.core.domain.Employee;

import com.tutorial.ems.core.exception.NotFoundException;
import com.tutorial.ems.core.port.in.employee.CreateEmployeeCommand;
import com.tutorial.ems.core.port.in.employee.GetEmployeeQuery;
import com.tutorial.ems.core.port.in.employee.UpdateEmployeeCommand;
import com.tutorial.ems.core.port.out.DepartmentRepository;
import com.tutorial.ems.core.port.out.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Employee Service Tests")
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Department testDepartment;
    private Employee testEmployee;
    private CreateEmployeeCommand createCommand;

    // run before each test
    @BeforeEach
    void setUp() {
        testDepartment = Department.builder()
                .id(1L)
                .name("FIM")
                .description("Marketing and Sales Department")
                .build();

        testEmployee = Employee.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@company.com")
                .phoneNumber("+1234567890")
                .hireDate(LocalDate.of(2024, 1, 15))
                .role("DEVELOPER")
                .department(testDepartment)
                .build();

        createCommand = CreateEmployeeCommand.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@company.com")
                .phoneNumber("+1234567890")
                .hireDate(LocalDate.of(2024, 1, 15))
                .role("DEVELOPER")
                .departmentId(1L)
                .build();
    }

    @Test
    @DisplayName("Should create employee successfully")
    void shouldCreateEmployeeSuccessfully() {
        // Given
        given(departmentRepository.findById(1L))
                .willReturn(Optional.of(testDepartment));
        given(employeeRepository.save(any(Employee.class)))
                .willReturn(testEmployee);

        // When
        Employee result = employeeService.create(createCommand);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("john.doe@company.com");
        assertThat(result.getFullName()).isEqualTo("John Doe");
        assertThat(result.getPhoneNumber()).isEqualTo("+1234567890");
        assertThat(result.getWorkingYears()).isEqualTo(2);
        assertThat(result.getDepartmentName()).isEqualTo("FIM");

        // Verify interactions
        verify(departmentRepository).findById(1L);
       verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    @DisplayName("Should throw exception when department not found")
    void shouldThrowExceptionWhenDepartmentNotFound() {
        // Given
        given(departmentRepository.findById(1L))
                .willReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> employeeService.create(createCommand))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Department not found");

        verify(employeeRepository, never()).save(any());
    }


    @Test
    @DisplayName("Should get employee by ID successfully")
    void shouldGetEmployeeById() {
        // Given
        GetEmployeeQuery query = GetEmployeeQuery.builder().id(1L).build();
        given(employeeRepository.findById(1L))
                .willReturn(Optional.of(testEmployee));

        // When
        Employee result = employeeService.get(query);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getEmail()).isEqualTo("john.doe@company.com");

        verify(employeeRepository).findById(1L);
    }

    @Test
    @DisplayName("Should update employee successfully")
    void shouldUpdateEmployeeSuccessfully() {
        // Given
        UpdateEmployeeCommand updateCommand = UpdateEmployeeCommand.builder()
                .id(1L)
                .firstName("Jane")
                .lastName("Smith")
                .email("jane.smith@company.com")
                .phoneNumber("+9876543210")
                .hireDate(LocalDate.of(2024, 1, 15))
                .role("SENIOR_DEVELOPER")
                .departmentId(1L)
                .build();

        var updatedEmployee = Employee.builder()
                .id(1L)
                .firstName("Jane")
                .lastName("Smith")
                .email("jane.smith@company.com")
                .phoneNumber("+9876543210")
                .hireDate(LocalDate.of(2024, 1, 15))
                .role("SENIOR_DEVELOPER")
                .department(testDepartment)
                .build();

        given(departmentRepository.findById(1L))
                .willReturn(Optional.of(testDepartment));
        given(employeeRepository.findById(1L))
                .willReturn(Optional.of(testEmployee));
        given(employeeRepository.save(any(Employee.class)))
                .willReturn(updatedEmployee);

        // When
        Employee result = employeeService.edit(updateCommand);

        // Then
        assertThat(result.getFirstName()).isEqualTo("Jane");
        assertThat(result.getLastName()).isEqualTo("Smith");
        assertThat(result.getFullName()).isEqualTo("Jane Smith");

        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(any(Employee.class));
    }
}