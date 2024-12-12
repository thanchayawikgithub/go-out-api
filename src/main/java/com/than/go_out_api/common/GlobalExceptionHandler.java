package com.than.go_out_api.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.than.go_out_api.common.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  // @ExceptionHandler(MethodArgumentNotValidException.class)
  // public ResponseEntity<ApiResponse>
  // handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
  // log.error("Handling MethodArgumentNotValidException");
  // String errors = e.getBindingResult().getFieldErrors().stream()
  // .map(error -> error.getField() + ": " + error.getDefaultMessage())
  // .collect(Collectors.joining(", "));

  // ApiResponse response = new ApiResponse(
  // HttpStatus.BAD_REQUEST,
  // "Validation failed: " + errors);

  // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  // }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
    log.info("[ResourceNotFoundException] {}", e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(HttpStatus.NOT_FOUND, e.getMessage()));
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException e) {
    log.info("[RuntimeException] {}", e.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
  }
}
