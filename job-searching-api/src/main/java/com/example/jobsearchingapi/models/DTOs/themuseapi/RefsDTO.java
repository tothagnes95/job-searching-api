package com.example.jobsearchingapi.models.DTOs.themuseapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class RefsDTO {
  @JsonProperty("landing_page")
  private String url;

  public RefsDTO() {}

  public RefsDTO(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RefsDTO refsDTO = (RefsDTO) o;
    return Objects.equals(url, refsDTO.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(url);
  }
}
