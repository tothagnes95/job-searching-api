package com.example.jobsearchingapi.services;

import com.example.jobsearchingapi.exceptions.InvalidInputException;
import com.example.jobsearchingapi.exceptions.ResourceNotFoundException;
import com.example.jobsearchingapi.models.DTOs.PositionDTO;
import com.example.jobsearchingapi.models.Position;
import com.example.jobsearchingapi.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionServiceImp implements PositionService{
    private PositionRepository positionRepository;

    @Autowired
    public PositionServiceImp(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public void checkPositionDetails (PositionDTO positionDTO) {
        if(positionDTO.getLocation() == null && positionDTO.getDescription() == null) {
            throw  new ResourceNotFoundException("Please provide description and location");
        } else if(positionDTO.getDescription() == null) {
            throw  new ResourceNotFoundException("Please provide description");
        } else if(positionDTO.getLocation() == null) {
            throw  new ResourceNotFoundException("Please provide location");
        } else if(positionDTO.getDescription().length() > 50){
            throw new InvalidInputException("The provided description must be shorter than 50 character");
        } else if(positionDTO.getLocation().length() > 50) {
            throw new InvalidInputException("The provided location must be shorter than 50 character");
        }
    }

    public String savePosition (PositionDTO positionDTO) {
        Position position = new Position(positionDTO.getDescription(), positionDTO.getLocation());
        positionRepository.save(position);
        return position.getUrl();
    }

    public Position findById (String id) {
    return positionRepository
        .findById(Long.valueOf(id))
        .orElseThrow(() -> new ResourceNotFoundException("Please provide position id"));
    }

    public List<Position> findAllByDescriptionAndLocation (PositionDTO positionDTO) {
        return positionRepository.findAllByDescriptionContainingAndLocation(positionDTO.getDescription(), positionDTO.getLocation());
    }
}
