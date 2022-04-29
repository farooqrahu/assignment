package com.assignment.spring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@Entity
@Table(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
public class Department extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long departmentId;
  @NotBlank
  @Size(max = 25)
  private String departmentName;
  private Long managerId;
}
