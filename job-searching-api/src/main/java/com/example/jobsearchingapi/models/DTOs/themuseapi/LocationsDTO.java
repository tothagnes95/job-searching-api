package com.example.jobsearchingapi.models.DTOs.themuseapi;

import java.util.Objects;

public class LocationsDTO {
  private String name;

  public LocationsDTO() {}

  public LocationsDTO(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LocationsDTO that = (LocationsDTO) o;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
