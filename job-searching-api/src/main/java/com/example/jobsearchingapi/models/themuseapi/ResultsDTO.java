package com.example.jobsearchingapi.models.themuseapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

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
}
