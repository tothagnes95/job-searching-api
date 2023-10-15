package com.example.jobsearchingapi.models.DTOs.themuseapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PositionFromApiDTO {
  @JsonProperty("name")
  private String description;
  @JsonProperty("locations")
  private List<LocationsDTO> locations;

  @JsonProperty("refs")
  private RefsDTO refsDTO;

  public PositionFromApiDTO () {};

  public PositionFromApiDTO(String description, List<LocationsDTO> locations, RefsDTO refsDTO) {
    this.description = description;
    this.locations = locations;
    this.refsDTO = refsDTO;
  }

  public PositionFromApiDTO(String description, String location, String url) {
    this.description = description;
    this.locations = new ArrayList<>();
    locations.add(new LocationsDTO(location));
    this.refsDTO = new RefsDTO(url);
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<LocationsDTO> getLocations() {
    return locations;
  }

  public void setLocations(List<LocationsDTO> locations) {
    this.locations = locations;
  }

  public RefsDTO getRefsDTO() {
    return refsDTO;
  }

  public void setRefsDTO(RefsDTO refsDTO) {
    this.refsDTO = refsDTO;
  }
}
