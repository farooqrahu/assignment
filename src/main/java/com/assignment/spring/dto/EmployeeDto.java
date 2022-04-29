package com.assignment.spring.dto;

import com.assignment.spring.models.Employee;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Builder
public class EmployeeDto {
  private Long employeeId;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private Timestamp hireDate;
  private Long salary;
  private Long managerId;
  private Long departmentId;

  public static EmployeeDto factoryEmployee(Employee employee) {
    return EmployeeDto.builder().employeeId(employee.getEmployeeId()).firstName(employee.getFirstName()).lastName(employee.getLastName()).email(employee.getEmail())
      .phoneNumber(employee.getPhoneNumber()).hireDate(employee.getHireDate()).salary(employee.getSalary()).managerId(employee.getManagerId())
      .departmentId(employee.getDepartmentId()).build();
  }
}
