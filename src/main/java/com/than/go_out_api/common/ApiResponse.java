package com.than.go_out_api.common;

import org.springframework.http.HttpStatus;
import lombok.Data;

@Data
public class ApiResponse {
  private HttpStatus status;
  private int code;
  private String message;
  private Object data;

  public ApiResponse(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
    this.code = status.value();
  }

  public ApiResponse(HttpStatus status, String message, Object data) {
    this.status = status;
    this.message = message;
    this.code = status.value();
    this.data = data;
  }
}
