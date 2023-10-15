package com.example.jobsearchingapi.models.DTOs.themuseapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultsDTO {
  @JsonProperty("results")
  private List<PositionFromApiDTO> listOfPositionsFromApi;

  public ResultsDTO() {
    listOfPositionsFromApi = new ArrayList<>();
  }

  public ResultsDTO(List<PositionFromApiDTO> listOfPositionsFromApi) {
    this.listOfPositionsFromApi = listOfPositionsFromApi;
  }

  public List<PositionFromApiDTO> getListOfPositionsFromApi() {
    return listOfPositionsFromApi;
  }

  public void setListOfPositionsFromApi(List<PositionFromApiDTO> listOfPositionsFromApi) {
    this.listOfPositionsFromApi = listOfPositionsFromApi;
  }

  public List<PositionFromApiDTO> findByDescriptionAndLocation (String description) {
    return this.listOfPositionsFromApi.stream().filter(position -> position.getDescription().contains(description)).collect(Collectors.toList());
  }
}
