package com.example.jobsearchingapi.exceptions;

public class InvalidInputException extends RuntimeException{

  public InvalidInputException (String msg) {
    super(msg);
  }
}
