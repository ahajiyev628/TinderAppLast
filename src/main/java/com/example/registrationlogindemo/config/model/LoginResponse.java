package com.example.registrationlogindemo.config.model;

import lombok.Data;

@Data
public class LoginResponse {

  private String token;
  private String message;

  public static LoginResponse Ok(String token) {
    LoginResponse rs = new LoginResponse();
    rs.setToken(token);
    return rs;
  }

  public static LoginResponse Error(String message) {
    LoginResponse rs = new LoginResponse();
    rs.setMessage(message);
    return rs;
  }

}
