package com.assignment.spring.payload.response;

import com.assignment.spring.dto.UserDto;
import com.assignment.spring.models.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserResponse {
  private String token;
  private String type = "Bearer";
  private UserDto user;

  public static UserResponse userResponseFactory(User user, String token) {
    return UserResponse.builder().token(token).user(UserDto.userDtoFactory(user)).build();
  }
}
