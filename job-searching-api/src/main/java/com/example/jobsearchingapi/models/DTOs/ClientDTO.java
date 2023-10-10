package com.example.jobsearchingapi.models.DTOs;

public class ClientDTO {
  private String name;
  private String email;

  public ClientDTO (){}

  public ClientDTO(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


}
