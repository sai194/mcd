package com.sye.mcd.controller;

import com.sye.mcd.exceptions.EmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvisor extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EmployeeException.class)
  public ResponseEntity<String> handleEmployeeException(EmployeeException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGeneralException(Exception e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
