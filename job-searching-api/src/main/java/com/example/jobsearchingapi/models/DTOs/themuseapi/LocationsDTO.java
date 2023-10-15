package com.example.jobsearchingapi.models.DTOs.themuseapi;

public class LocationsDTO {
  private String name;


  public LocationsDTO(){};

  public LocationsDTO(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
