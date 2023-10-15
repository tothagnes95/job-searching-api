package com.example.jobsearchingapi.unittest;

import com.example.jobsearchingapi.exceptions.InvalidInputException;
import com.example.jobsearchingapi.exceptions.ResourceNotFoundException;
import com.example.jobsearchingapi.models.DTOs.PositionDTO;
import com.example.jobsearchingapi.models.DTOs.themuseapi.PositionFromApiDTO;
import com.example.jobsearchingapi.models.DTOs.themuseapi.ResultsDTO;
import com.example.jobsearchingapi.models.Position;
import com.example.jobsearchingapi.repositories.PositionRepository;
import com.example.jobsearchingapi.services.PositionServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PositionServiceTest {
    @InjectMocks
    PositionServiceImp positionServiceImp;
    private PositionDTO positionDTO;
    private Position position;
    private PositionFromApiDTO positionFromApiDTO;
    @Mock
    private PositionRepository positionRepository;
    @Mock
    private List<PositionFromApiDTO> positionsFromAPI;
    @Mock
    private ResultsDTO positions;
    @Mock
    private RestTemplate restTemplate;


    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void checkPositionDetails_descriptionAndLocation_missing () {
        positionDTO = new PositionDTO(null, null);
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> positionServiceImp.checkPositionDetails(positionDTO));
        String expectedMessage = "Please provide description and location";

        assertTrue(expectedMessage.contains(exception.getMessage()));
    }

    @Test
    public void checkPositionDetails_description_missing () {
        positionDTO = new PositionDTO(null, "Budapest");
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> positionServiceImp.checkPositionDetails(positionDTO));
        String expectedMessage = "Please provide description";

        assertTrue(expectedMessage.contains(exception.getMessage()));
    }

    @Test
    public void checkPositionDetails_location_missing () {
        positionDTO = new PositionDTO("Junior Java Developer", null);
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> positionServiceImp.checkPositionDetails(positionDTO));
        String expectedMessage = "Please provide location";

        assertTrue(expectedMessage.contains(exception.getMessage()));
    }

    @Test
    public void checkPositionDetails_description_tooLong () {
        positionDTO = new PositionDTO("pneumonoultramicroscopicsilicovolcanoconiosis123456789", "Budapest");
        Exception exception = assertThrows(InvalidInputException.class, () -> positionServiceImp.checkPositionDetails(positionDTO));
        String expectedMessage = "The provided description must be shorter than 50 character";

        assertTrue(expectedMessage.contains(exception.getMessage()));
    }

    @Test
    public void checkPositionDetails_location_tooLong () {
        positionDTO = new PositionDTO("Junior Java Developer", "pneumonoultramicroscopicsilicovolcanoconiosis123456789");
        Exception exception = assertThrows(InvalidInputException.class, () -> positionServiceImp.checkPositionDetails(positionDTO));
        String expectedMessage = "The provided location must be shorter than 50 character";

        assertTrue(expectedMessage.contains(exception.getMessage()));
    }

    @Test
    public void savePosition () {
        position = new Position("Software", "Budapest");
        positionDTO = new PositionDTO("Software", "Budapest");
        when(positionRepository.save(position)).thenReturn(position);
        positionServiceImp.savePosition(positionDTO);
        verify(positionRepository).save(Mockito.any(Position.class));
    }

    @Test
    public void findById () {
        positionDTO = new PositionDTO("Junior Java Developer", "Budapest");
        position = new Position("Junior Java Developer", "Budapest");
        when(positionRepository.findById(1L)).thenReturn(Optional.of(position));

        assertEquals(positionDTO, positionServiceImp.findById("1"));
    }

    @Test
    public void findById_fail () {
        when(positionRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> positionServiceImp.findById("1"));
        String expectedMessage = "Please provide position id";

        assertTrue(expectedMessage.contains(exception.getMessage()));
    }

    @Test
    public void findAllByDescriptionAndLocation () {
        positionDTO = new PositionDTO("Software", "Flexible / Remote");
        positionFromApiDTO = new PositionFromApiDTO("Senior Software Engineer, React to React Native", "Flexible / Remote", "https://www.themuse.com/jobs/glossgenius/senior-software-engineer-react-to-react-native-4f4672");
        positionsFromAPI = new ArrayList<>(List.of(positionFromApiDTO));
        when(restTemplate.getForObject("https://www.themuse.com/api/public/jobs?page=1&location=Flexible / Remote", ResultsDTO.class)).thenReturn(positions);
        when(positions.getListOfPositionsFromApi()).thenReturn(positionsFromAPI);
        when(positionRepository.findAllByDescriptionContainingAndLocation("Software", "Flexible / Remote")).thenReturn(new ArrayList<>());

        assertEquals(positionFromApiDTO.getDescription(), positionServiceImp.findAllByDescriptionAndLocation(positionDTO).get(0).getDescription());
    }
}
