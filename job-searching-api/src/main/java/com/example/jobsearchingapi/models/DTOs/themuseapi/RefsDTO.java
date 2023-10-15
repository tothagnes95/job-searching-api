package com.example.jobsearchingapi.models.DTOs.themuseapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RefsDTO {
  @JsonProperty("landing_page")
  private String url;

  public RefsDTO() {};

  public RefsDTO(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
