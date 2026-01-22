# EMS (Employee Management System)

This project is a small Spring Boot application used for tutorial/demo purposes.

What I changed for you
- Ensured the MySQL JDBC driver is available via Gradle (`com.mysql:mysql-connector-j:8.3.0` is already declared in `build.gradle`).
- Added a minimal test to verify the MySQL driver can be loaded: `src/test/java/com/tutorial/ems/MySqlDriverLoadTest.java`.
- Applied two Clean Architecture improvements (low-risk, incremental):
  - Introduced repository ports (interfaces) in `core.port.repository` and made the Spring Data JPA interfaces implement those ports.
    - Files: `src/main/java/com/tutorial/ems/core/port/repository/DepartmentRepositoryPort.java` and `EmployeeRepositoryPort.java`
  - Introduced a small domain model for `Department` and mapping helpers:
    - Domain: `src/main/java/com/tutorial/ems/core/domain/Department.java`
    - Mapper changes: `src/main/java/com/tutorial/ems/infrastructure/mapper/DepartmentMapper.java` (added toDomain/toEntity methods)
  - Updated services to depend on the repository ports (dependency inversion) and to use the domain for `Department` logic:
    - `src/main/java/com/tutorial/ems/core/service/DepartmentServiceImpl.java`
    - `src/main/java/com/tutorial/ems/core/service/EmployeeServiceImpl.java` (now depends on ports and receives the `DepartmentMapper` in constructor)

Quick start (Windows PowerShell)

- Build the project and run tests (refreshing dependencies):

```powershell
.\gradlew clean build --refresh-dependencies
```

- Run only the driver-load smoke test (verifies the MySQL driver class is on the test classpath):

```powershell
.\gradlew test --tests *MySqlDriverLoadTest
```

- Run the application (will attempt to connect to the database using `application.properties`):

```powershell
.\gradlew bootRun
```

Sample `application.properties` (edit values as needed)

```
spring.datasource.url=jdbc:mysql://localhost:3306/ems?serverTimezone=UTC&useSSL=false
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```

If you want tests to use an in-memory DB (so they don't try to contact your MySQL), add or edit `src/test/resources/application-test.properties` to point to H2:

```
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.hibernate.ddl-auto=create-drop
```

Troubleshooting
- ClassNotFoundException: com.mysql.cj.jdbc.Driver
  - Verify `build.gradle` contains `implementation 'com.mysql:mysql-connector-j:8.3.0'` and run `.\gradlew clean build --refresh-dependencies`.
  - Re-import the Gradle project in your IDE or invalidate caches (IDE-dependent).
  - If you must build offline, add the connector jar to `libs/` and reference with `implementation files('libs/mysql-connector-java-<ver>.jar')`.

- Dependency conflicts or multiple connector versions:
  - Inspect runtimeClasspath: `.\gradlew dependencies --configuration runtimeClasspath`
  - Force a version in Gradle if necessary with a resolutionStrategy.

Notes about the Clean Architecture changes
- Ports (interfaces) live in `src/main/java/com/tutorial/ems/core/port/repository/` and define the persistence operations used by services.
- Concrete JPA repositories remain in the `infrastructure.repository` package and now implement the port interfaces.
- Domain objects live under `src/main/java/com/tutorial/ems/core/domain/` (currently `Department` record). Mappers translate between JPA entities and domain objects, and between entities and DTOs.
- This is an incremental move toward Clean Architecture — services now depend on ports (interfaces) declared in the core/application layer instead of concrete infrastructure classes.

Suggested next steps (pick one)
1. Update controllers to only accept and return DTOs and to avoid importing JPA entities directly (low-risk).
2. Add small unit tests for `DepartmentMapper` to validate `toDomain`/`toEntity` roundtrips.
3. Introduce domain-based DTOs and adapt services/controllers to use domain objects internally (medium work).

If you want one of the above I can implement it for you (I can add unit tests or update controllers automatically and run the build/tests).

Contact / notes
- If your IDE still reports unresolved types or shows red imports, re-import the Gradle project and/or invalidate the IDE cache.
- I ran a full build in this workspace after making changes; build and tests completed successfully.
