package com.assignment.spring.security.services;

import com.assignment.spring.dto.EmployeeDto;
import com.assignment.spring.models.Employee;
import com.assignment.spring.payload.request.EmployeeRequest;
import com.assignment.spring.payload.response.EmployeeResponse;
import com.assignment.spring.payload.response.MessageResponse;
import com.assignment.spring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
  @Autowired
  EmployeeRepository employeeRepository;
  @Autowired
  UserDetailsServiceImpl userDetailsServiceImpl;
  @Autowired
  EmailSenderService emailSenderService;

  public ResponseEntity<?> findEmployee(EmployeeRequest employeeRequest) {
    if (employeeRequest.getFirstName() != null && !employeeRequest.getFirstName().isEmpty()) {
//        return findByNameContaining(employeeRequest);
      return null;
    } else {
      return findAllEmployees(employeeRequest);
    }
  }

  public Pageable checkPaging(EmployeeRequest employeeRequest) {
    if (employeeRequest.getPagesize() > 0 && employeeRequest.getPagenumber() >= 0) {
      if (employeeRequest.getSort().isEmpty()) {
        employeeRequest.setSort("employeeId");
      }
      if (Objects.equals(employeeRequest.getSortdirection(), "desc")) {
        return PageRequest.of(employeeRequest.getPagenumber(), employeeRequest.getPagesize(),
          Sort.by(employeeRequest.getSort()).descending());
      } else {
        return PageRequest.of(employeeRequest.getPagenumber(), employeeRequest.getPagesize(),
          Sort.by(employeeRequest.getSort()).ascending());
      }
    }
    return null;
  }

  public ResponseEntity<?> findAllEmployees(EmployeeRequest employeeRequest) {
    Pageable paging = checkPaging(employeeRequest);
    if (paging == null)
      return ResponseEntity.ok(new EmployeeResponse(employeeRepository.findAll().stream().map(EmployeeDto::factoryEmployee).collect(Collectors.toList())));
    return ResponseEntity.ok(new EmployeeResponse(employeeRepository.findAll(paging)));
  }


  public ResponseEntity<?> addEmployee(EmployeeRequest employeeRequest) {
    userDetailsServiceImpl.checkAdmin();
    if (employeeRequest.getFirstName() == null || "".equals(employeeRequest.getFirstName()))
      return ResponseEntity.badRequest().body(new MessageResponse("Error: first name must not be empty"));
    if (employeeRequest.getLastName() == null || "".equals(employeeRequest.getLastName()))
      return ResponseEntity.badRequest().body(new MessageResponse("Error: last name must not be empty"));
    if (employeeRequest.getSalary() == null || employeeRequest.getSalary() <= 0)
      return ResponseEntity.badRequest().body(new MessageResponse("Error: salary must not be empty, zero or negative"));
    Employee employee = new Employee(employeeRequest);
    employeeRepository.save(employee);
    return ResponseEntity.ok(new MessageResponse("employee added successfully!"));
  }

  public ResponseEntity<?> addUpdateEmployee(EmployeeRequest employeeRequest) {
    userDetailsServiceImpl.checkAdmin();
    String msg="Employee added successfully!";
    if (employeeRequest.getFirstName() == null || "".equals(employeeRequest.getFirstName()))
      return ResponseEntity.badRequest().body(new MessageResponse("Error: first name must not be empty"));
    if (employeeRequest.getLastName() == null || "".equals(employeeRequest.getLastName()))
      return ResponseEntity.badRequest().body(new MessageResponse("Error: last name must not be empty"));
    if (employeeRequest.getSalary() == null || employeeRequest.getSalary() <= 0)
      return ResponseEntity.badRequest().body(new MessageResponse("Error: salary must not be empty, zero or negative"));
    Employee employee = new Employee(employeeRequest);
    if (employeeRequest.getEmployeeId() != null && employeeRequest.getEmployeeId() > 0) {
      Optional<Employee> employeeFound = employeeRepository.findById(employeeRequest.getEmployeeId());
      if (employeeFound.isPresent())
        employee = updateEmployee(employeeFound.get(), employeeRequest);
       msg="Employee updated successfully!";
    }
    employeeRepository.save(employee);
    return ResponseEntity.ok(new MessageResponse(msg));
  }

  private Employee updateEmployee(Employee employee, EmployeeRequest employeeRequest) {
    employee.setFirstName(employeeRequest.getFirstName());
    employee.setLastName(employeeRequest.getLastName());
    employee.setEmail(employeeRequest.getEmail());
    employee.setHireDate(Timestamp.valueOf(LocalDateTime.now()));
    employee.setSalary(employeeRequest.getSalary());
    return employee;
  }

  public ResponseEntity<?> deleteEmployee(EmployeeRequest employeeRequest) {
    userDetailsServiceImpl.checkAdmin();
    employeeRepository.deleteById(employeeRequest.getEmployeeId());
    return ResponseEntity.ok(new MessageResponse("employee deleted successfully"));
  }

}
