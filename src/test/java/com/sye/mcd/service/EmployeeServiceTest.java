package com.sye.mcd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sye.mcd.entity.Employee;
import com.sye.mcd.exceptions.EmployeeException;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@ActiveProfiles("test")
@SpringBootTest
@Sql("classpath:dbunitDatasets/employee.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EmployeeServiceTest {

  @Autowired
  EmployeeService employeeService;

  @Test
  public void testCreateEmployee_happyPath() {
    Employee employee = new Employee();
    employee.setName("Test");
    employee = employeeService.createEmployee(employee);
    assertNotNull(employee.getId());
  }

  @Test
  public void testCreateEmployee_error() {
    Employee employee = new Employee();
    employee.setName("");
    Executable tocall = () -> employeeService.createEmployee(employee);
    assertThrows(ConstraintViolationException.class, tocall);
  }

  @Test
  public  void testUpdateEmployee_happyPath() {
    Employee employee = new Employee();
    employee.setId(1L);
    employee.setName("Updated Dr.Strange");
    Employee updated  = employeeService.updateEmployee(employee);
    assertEquals(updated.getName(), "Updated Dr.Strange");
  }

  @Test
  public void testUpdateEmployee_error() {
    Employee employee = new Employee();
    employee.setId(108L);
    employee.setName("Test");
    Executable tocall = () -> employeeService.updateEmployee(employee);
    assertThrows(EmployeeException.class, tocall);
  }

  @Test
  public void testGetEmployee_found() {
    Employee employee = employeeService.getEmployee(1L);
    assertNotNull(employee);
  }

  @Test
  public void testGetEmployee_notFound() {
    Executable tocall = () -> employeeService.getEmployee(108L);
    assertThrows(EmployeeException.class, tocall);
  }

}
