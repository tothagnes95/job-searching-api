package com.example.jobsearchingapi.models.themuseapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PositionFromApiDTO {
  @JsonProperty("name")
  private String description;
  @JsonProperty("locations")
  private List<LocationsDTO> locations;
  @JsonProperty("landing_page")
  private String url;

  public PositionFromApiDTO () {};

  public PositionFromApiDTO(String description, List<LocationsDTO> locations, String url) {
    this.description = description;
    this.locations = locations;
    this.url =  url;
  }

  public PositionFromApiDTO(String description, String location, String url) {
    this.description = description;
    this.locations = new ArrayList<>();
    locations.add(new LocationsDTO(location));
    this.url =  url;
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

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
