package com.tutorial.ems;

import com.tutorial.ems.infrastructure.mapper.DepartmentPersistenceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DepartmentPersistenceMapperBeanTest {

    @Autowired
    DepartmentPersistenceMapper departmentPersistenceMapper;

    @Test
    void mapperBeanShouldBePresent() {
        assertNotNull(departmentPersistenceMapper, "DepartmentPersistenceMapper should be autowired by Spring");
    }
}

