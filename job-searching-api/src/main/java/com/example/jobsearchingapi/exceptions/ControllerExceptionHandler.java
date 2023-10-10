package com.example.jobsearchingapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

    return new ErrorMessage(ex.getMessage(), request.getDescription(false));
  }

  @ExceptionHandler(InvalidInputException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorMessage invalidInputException(InvalidInputException ex, WebRequest request) {

    return new ErrorMessage(ex.getMessage(), request.getDescription(false));
  }
}
