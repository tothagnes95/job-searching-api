package com.example.jobsearchingapi.models.DTOs;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionDTO that = (PositionDTO) o;
        return Objects.equals(description, that.description) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, location);
    }
}
