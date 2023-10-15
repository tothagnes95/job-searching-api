package com.example.jobsearchingapi.services;

import com.example.jobsearchingapi.models.DTOs.PositionDTO;
import com.example.jobsearchingapi.models.Position;
import com.example.jobsearchingapi.models.DTOs.themuseapi.PositionFromApiDTO;

import java.util.List;

public interface PositionService {
    void checkPositionDetails (PositionDTO positionDTO);

    String savePosition (PositionDTO positionDTO);

    List<PositionFromApiDTO> findAllByDescriptionAndLocation (PositionDTO positionDTO);

    Position findById (String id);
}
