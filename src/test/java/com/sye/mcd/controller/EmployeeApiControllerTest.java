package com.sye.mcd.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sye.mcd.entity.Employee;
import com.sye.mcd.exceptions.EmployeeException;
import com.sye.mcd.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EmployeeApiControllerTest {

  @Autowired
  WebApplicationContext webApplicationContext;

  @Autowired
  MockMvc mockMvc;

  @MockBean
  EmployeeService employeeService;

  ObjectMapper mapper = new ObjectMapper();

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @AfterEach
  public void cleanUp() {
    Mockito.reset(employeeService);
  }

  @Test
  public void testCreateEmployee_happyPath() throws Exception {
    Employee employee = new Employee();
    employee.setId(11L);
    employee.setName("Dr.Strange");
    String requestBodyJson = mapper.writeValueAsString(employee);
    when(employeeService.createEmployee(any())).thenReturn(employee);
    this.mockMvc
        .perform(
            post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson))
        .andExpect(status().isCreated());
  }

  @Test
  public void testCreateEmployee_fail() throws Exception{
    Employee employee = new Employee();
    employee.setId(11L);
    employee.setName("Dr.Strange");
    String requestBodyJson = mapper.writeValueAsString(employee);
    when(employeeService.createEmployee(any())).thenThrow(new EmployeeException("err"));
    this.mockMvc
        .perform(
            post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson))
        .andExpect(status().isInternalServerError());
  }

  @Test
  public void testUpdateEmployee_happyPath() throws Exception{
    Employee employee = new Employee();
    employee.setId(11L);
    employee.setName("Dr.Strange");
    String requestBodyJson = mapper.writeValueAsString(employee);
    when(employeeService.updateEmployee(any())).thenReturn(employee);
    this.mockMvc
        .perform(
            put("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson))
        .andExpect(status().isOk());
  }

  @Test
  public void testUpdateEmployee_fail() throws Exception{
    Employee employee = new Employee();
    employee.setId(11L);
    employee.setName("Dr.Strange");
    String requestBodyJson = mapper.writeValueAsString(employee);
    when(employeeService.updateEmployee(any())).thenThrow(new EmployeeException("err"));
    this.mockMvc
        .perform(
            put("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson))
        .andExpect(status().isInternalServerError());
  }

  @Test
  public void testGetEmployee_happyPath() throws Exception{
    Employee employee = new Employee();
    employee.setId(11L);
    employee.setName("Dr.Strange");
    String requestBodyJson = mapper.writeValueAsString(employee);
    when(employeeService.getEmployee(any())).thenReturn(employee);
    this.mockMvc
        .perform(
            get("/employees/1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetEmployee_fail() throws  Exception{
    Employee employee = new Employee();
    employee.setId(11L);
    employee.setName("Dr.Strange");
    String requestBodyJson = mapper.writeValueAsString(employee);
    when(employeeService.getEmployee(any())).thenThrow(new EmployeeException("err"));
    this.mockMvc
        .perform(
            get("/employees/1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isInternalServerError());
  }


}
