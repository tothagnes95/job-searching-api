package com.example.jobsearchingapi.exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorMessage {
  private String message;
  private String endpoint;
  private String time;

  public ErrorMessage(String message, String endpoint) {
    this.message = message;
    this.endpoint = endpoint;
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    this.time = LocalDateTime.now().format(myFormatObj);
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }
}
