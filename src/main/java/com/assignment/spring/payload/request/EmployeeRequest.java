package com.assignment.spring.payload.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class EmployeeRequest {
  private Long employeeId;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String hireDate;
  private Long salary;
  private Long managerId;
  private Long departmentId;
  private String sort;
  private String sortdirection = "asc";
  private int pagesize = 0;
  private int pagenumber = 0;
}
