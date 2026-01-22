package com.tutorial.ems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MySqlDriverLoadTest {

    @Test
    void driverLoads() throws Exception {
        // try to load the MySQL driver class; this fails with ClassNotFoundException if connector is missing
        Class<?> driverClass = Class.forName("com.mysql.cj.jdbc.Driver");
        assertNotNull(driverClass);
    }
}

