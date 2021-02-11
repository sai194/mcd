package com.sye.mcd.controller;

import com.sye.mcd.entity.Employee;
import com.sye.mcd.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeApiController {

  private EmployeeService employeeService;

  @Autowired
  public EmployeeApiController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
    Employee emp = employeeService.createEmployee(employee);
    return new ResponseEntity<>(emp, HttpStatus.CREATED);
  }

  @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
    Employee emp = employeeService.updateEmployee(employee);
    return new ResponseEntity<>(emp, HttpStatus.OK);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<Employee>> getEmployees() {
    List<Employee> employees = employeeService.getEmployees();
    return new ResponseEntity<>(employees, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
    Employee emp = employeeService.getEmployee(id);
    return new ResponseEntity<>(emp, HttpStatus.OK);
  }

}
