package com.sye.mcd.service;

import com.sye.mcd.entity.Employee;
import com.sye.mcd.exceptions.EmployeeException;
import com.sye.mcd.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  private EmployeeRepository employeeRepository;

  Logger logger = LogManager.getLogger(EmployeeService.class);
  @Autowired
  EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public Employee createEmployee(Employee employee) {
    logger.info(employee);
    Employee emp  = new Employee();
    emp.setName(employee.getName());
    emp = employeeRepository.save(emp);
    return emp;
  }

  public Employee updateEmployee(Employee employee) {
    logger.info(employee);
    Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());
    employeeOptional.orElseThrow(() -> new EmployeeException("Employee Not Found"));
    Employee update  = employeeOptional.get();
    update.setName(employee.getName());
    update = employeeRepository.save(update);
    return update;
  }

  public Employee getEmployee(Long id) {
    logger.info(id);
    Optional<Employee> employeeOptional = employeeRepository.findById(id);
    employeeOptional.orElseThrow(() -> new EmployeeException("Employee Not Found"));
    return employeeOptional.get();
  }

  public List<Employee> getEmployees() {
    return employeeRepository.findAll();
  }

}
