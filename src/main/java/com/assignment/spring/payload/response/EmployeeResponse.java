package com.assignment.spring.payload.response;

import com.assignment.spring.dto.EmployeeDto;
import com.assignment.spring.models.Employee;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class EmployeeResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String name;
  private String description;
  private Float price;
  private boolean images;
  private List<EmployeeDto> list;
  private int currentpage;
  private long totalitems;
  private int totalpages;

  public EmployeeResponse(String token, List<EmployeeDto> list) {
    this.token = token;
    this.list = list;
  }

  public EmployeeResponse(List<EmployeeDto> list) {
    this.list = list;
  }

  public EmployeeResponse(Page<Employee> list) {
    this.list = list.getContent().stream().map(EmployeeDto::factoryEmployee).collect(Collectors.toList());
    this.currentpage = list.getNumber();
    this.totalitems = list.getTotalElements();
    this.totalpages = list.getTotalPages();
  }

}
