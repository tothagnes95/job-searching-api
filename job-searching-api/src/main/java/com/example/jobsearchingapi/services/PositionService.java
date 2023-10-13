package com.example.jobsearchingapi.services;

import com.example.jobsearchingapi.models.DTOs.PositionDTO;
import com.example.jobsearchingapi.models.Position;
import com.example.jobsearchingapi.models.themuseapi.PositionFromApiDTO;
import com.example.jobsearchingapi.models.themuseapi.ResultsDTO;

import java.util.List;

public interface PositionService {
    void checkPositionDetails (PositionDTO positionDTO);

    String savePosition (PositionDTO positionDTO);

    List<PositionFromApiDTO> findAllByDescriptionAndLocation (PositionDTO positionDTO);

    Position findById (String id);
}
