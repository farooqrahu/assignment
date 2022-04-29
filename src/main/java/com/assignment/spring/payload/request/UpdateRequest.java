package com.assignment.spring.payload.request;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
public class UpdateRequest {
  @Id
  private Long id;
  private String firstName;
  private String lastName;
  @Size(max = 50)
  @Email
  private String email;
  @Nullable
  private String phoneNumber;
  private Timestamp hireDate;
  private Long salary;
  @Nullable
  private int age;
  @Nullable
  private String username;
  @Nullable
  private String name;
  @Nullable
  private String surname;
  @Nullable
  private String address;
  @Nullable
  private String city;
  @Nullable
  private String country;
  @Nullable
  private String job;
  @Nullable
  @Size(max = 600)
  private String description;
  private String password;

  @Override
  public String toString() {
    return "UpdateRequest{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", email='" + email + '\'' +
      ", phoneNumber='" + phoneNumber + '\'' +
      ", hireDate=" + hireDate +
      ", salary=" + salary +
      ", age=" + age +
      ", name='" + name + '\'' +
      ", surname='" + surname + '\'' +
      ", address='" + address + '\'' +
      ", city='" + city + '\'' +
      ", country='" + country + '\'' +
      ", job='" + job + '\'' +
      ", description='" + description + '\'' +
      ", password='" + password + '\'' +
      '}';
  }
}
