package com.assignment.spring.controllers;

import com.assignment.spring.payload.request.EmployeeRequest;
import com.assignment.spring.repository.EmployeeRepository;
import com.assignment.spring.security.jwt.JwtUtils;
import com.assignment.spring.security.services.EmployeeService;
import com.assignment.spring.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/employee")
public class EmployeeController {
  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  EmployeeRepository employeeRepository;
  @Autowired
  JwtUtils jwtUtils;
  @Autowired
  UserDetailsServiceImpl userDetailsServiceImpl;
  @Autowired
  EmployeeService productService;

  @PostMapping("/find")
  public ResponseEntity<?> findEmployee(@Valid @RequestBody EmployeeRequest productRequest) {
    return productService.findEmployee(productRequest);
  }

  @PostMapping("/add-update")
  public ResponseEntity<?> addUpdateEmployee(@Valid @RequestBody EmployeeRequest productRequest) {
    return productService.addUpdateEmployee(productRequest);

  }

  @PostMapping("/delete")
  public ResponseEntity<?> deleteEmployee(@Valid @RequestBody EmployeeRequest productRequest) {
    return productService.deleteEmployee(productRequest);

  }
}
