package com.example.jobsearchingapi.models.DTOs;

public class PositionDTO {
    private String description;
    private String location;

    public PositionDTO () {};

    public PositionDTO(String description, String location) {
        this.description = description;
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
