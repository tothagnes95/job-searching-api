package com.example.jobsearchingapi.services;

import com.example.jobsearchingapi.exceptions.InvalidInputException;
import com.example.jobsearchingapi.exceptions.ResourceNotFoundException;
import com.example.jobsearchingapi.models.DTOs.PositionDTO;
import com.example.jobsearchingapi.models.DTOs.themuseapi.PositionFromApiDTO;
import com.example.jobsearchingapi.models.DTOs.themuseapi.ResultsDTO;
import com.example.jobsearchingapi.models.Position;
import com.example.jobsearchingapi.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

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
        position.setUrl("http://localhost:8080/position/" + position.getId());
        positionRepository.save(position);
        return position.getUrl();
    }

    public Position findById (String id) {
    return positionRepository
        .findById(Long.valueOf(id))
        .orElseThrow(() -> new ResourceNotFoundException("Please provide position id"));
    }

    public List<PositionFromApiDTO> findAllByDescriptionAndLocation (PositionDTO positionDTO) {
        String uri = "https://www.themuse.com/api/public/jobs?page=1";
        RestTemplate restTemplate = new RestTemplate();
        ResultsDTO positions = restTemplate.getForObject(uri + "&location=" + positionDTO.getLocation(), ResultsDTO.class);

        List<PositionFromApiDTO> positionsFromAPI = positions.findByDescriptionAndLocation(positionDTO.getDescription());

        List<PositionFromApiDTO> positionsFromDB = positionRepository
            .findAllByDescriptionContainingAndLocation(
                positionDTO.getDescription(), positionDTO.getLocation())
            .stream()
            .map(position -> new PositionFromApiDTO(position.getDescription(), position.getLocation(), position.getUrl())).collect(Collectors.toList());

        positionsFromAPI.addAll(positionsFromDB);

        return positionsFromAPI;


    }
}
