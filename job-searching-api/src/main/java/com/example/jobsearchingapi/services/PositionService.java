package com.example.jobsearchingapi.services;

import com.example.jobsearchingapi.models.DTOs.PositionDTO;
import com.example.jobsearchingapi.models.Position;
import com.example.jobsearchingapi.models.themuseapi.ResultsDTO;

public interface PositionService {
    void checkPositionDetails (PositionDTO positionDTO);

    String savePosition (PositionDTO positionDTO);

    ResultsDTO findAllByDescriptionAndLocation (PositionDTO positionDTO);

    Position findById (String id);
}
