package com.assignment.spring.payload.response;

import java.util.List;
import java.util.stream.Collectors;

import com.assignment.spring.dto.UserDto;
import com.assignment.spring.models.User;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class UserListResponse {

  private String token;
  private String type = "Bearer";
  private List<UserDto> users;


  public static UserListResponse userResponseFactory(List<User> users) {
    return UserListResponse.builder().users(users.stream().map(UserDto::userDtoFactory).collect(Collectors.toList())).build();
  }

}
