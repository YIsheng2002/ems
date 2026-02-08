package com.tutorial.ems.controller;

import com.tutorial.ems.core.port.in.department.CreateDepartmentUseCase;
import com.tutorial.ems.core.port.in.department.DeleteDepartmentUseCase;
import com.tutorial.ems.core.port.in.department.GetDepartmentUseCase;
import com.tutorial.ems.core.port.in.department.UpdateDepartmentUseCase;
import com.tutorial.ems.controller.mapper.DepartmentWebMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GetDepartmentUseCase getDepartmentUseCase;

    @MockitoBean
    private UpdateDepartmentUseCase updateDepartmentUseCase;

    @MockitoBean
    private CreateDepartmentUseCase createDepartmentUseCase;

    @MockitoBean
    private DeleteDepartmentUseCase deleteDepartmentUseCase;

    @MockitoBean
    private DepartmentWebMapper mapper;

    @Test
    public void test_CreateDepartment() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .post("/api/departments/")
                        .content("{\n" +
                                "  \"name\": \"FIL\",\n" +
                                "  \"description\": \"Test Department\"\n" +
                                "}")
                        .contentType("application/json")
                        .accept("application/json");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    public void test_CreateDepartmentUnsuccessful_WithBlankDescription() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .post("/api/departments/")
                        .content("{\n" +
                                "  \"name\": \"FIL\",\n" +
                                "  \"description\": \"\"\n" +
                                "}")
                        .contentType("application/json")
                        .accept("application/json");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest());
    }
}
