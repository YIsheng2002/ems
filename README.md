# EMS (Employee Management System)

This is a simple Employee Management System (EMS) built using java Spring Boot framework. It provides basic functionalities to manage employee records including creating, reading, updating, and deleting employee information.

## Features
- Add new employee
- View all employees
- Update employee details
- Delete employee
- Search employees by department

## Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- Actuator

##  Flow Summary
HTTP Request
    ↓
CreateEmployeeRequest (validation)
    ↓
[WebMapper] → CreateEmployeeCommand
    ↓
CreateEmployeeUseCase.execute(command)
    ↓
EmployeeServiceImpl (business logic)
    ↓
EmployeeRepository.save(employee) [PORT]
    ↓
EmployeeRepositoryAdapter [ADAPTER]
    ↓
[PersistenceMapper] → EmployeeEntity
    ↓
JpaRepository.save(entity)
    ↓
Database
    ↓
[Return path reverses the flow]
    ↓
EmployeeResponse
    ↓
HTTP Response (JSON)

## Learning Notes
1. @Builder Pattern (Readability & Safety)

Why
- Improves object creation readability
- Avoids large constructors with unclear parameter order
- Makes optional fields explicit

Example
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

What I learned
- Builder makes code easier to review and less error-prone
- Very useful for DTOs and command objects

2 Ports & Adapters (Hexagonal Architecture)

Why
- Decouple business logic from infrastructure
- Make code testable without real dependencies
- Support multiple implementations

Example
public interface DepartmentRepository {
    Department save(Department department);
}

@Component
public class DepartmentRepositoryAdapter implements DepartmentRepository {
}

What I learned
- Interfaces protect use cases from infrastructure changes

3 Use Case vs Command / Query Separation (SRP – Single Responsibility)

Why
- Each class has one clear responsibility
- Commands → state change
- Queries → data retrieval

Structure
usecase/
├── CreateEmployeeUseCase
├── GetEmployeeByIdUseCase
command/
├── CreateEmployeeCommand
query/
├── GetEmployeeByIdQuery


What I learned
- Makes future changes safer and localized

4 Dependency Injection via Interfaces (DI Best Practice)

Why
- Reduce coupling
- Improve testability
- Enable mocking

What I learned
- Spring resolves implementations at runtime
- Wrong dependency versions cause UnsatisfiedDependencyException

5 JUnit + Mockito Testing Patterns

Key Patterns Learned
Given – When – Then
given(clusterClient.execute(any()))
.willReturn(result);

Assert
assertThat(response).isNotNull();
assertThat(response.size()).isEqualTo(3);

Verify
verify(clusterClient).execute(any());

6 Mapping (Domain ↔ DTO ↔ Response)

Why
- Avoid leaking domain models to API layer
- Support API evolution without breaking business logic

Example
- mapper.toCreateDepartmentCommand(departmentRequest)

What I learned
- Mapping adds clarity at boundaries
- Makes testing and refactoring easier