package com.example.jobsearchingapi.unittest;

import com.example.jobsearchingapi.exceptions.InvalidInputException;
import com.example.jobsearchingapi.exceptions.ResourceNotFoundException;
import com.example.jobsearchingapi.models.DTOs.PositionDTO;
import com.example.jobsearchingapi.services.PositionServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PositionServiceTest {
    @InjectMocks
    PositionServiceImp positionServiceImp;
    private PositionDTO positionDTO;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void checkPositionDetails_missing_description_location () {
        positionDTO = new PositionDTO(null, null);
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> positionServiceImp.checkPositionDetails(positionDTO));
        String expectedMessage = "Please provide description and location";

        assertTrue(expectedMessage.contains(exception.getMessage()));
    }

    @Test
    public void checkPositionDetails_missing_description () {
        positionDTO = new PositionDTO(null, "Budapest");
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> positionServiceImp.checkPositionDetails(positionDTO));
        String expectedMessage = "Please provide description";

        assertTrue(expectedMessage.contains(exception.getMessage()));
    }

    @Test
    public void checkPositionDetails_missing_location () {
        positionDTO = new PositionDTO("Junior Java Developer", null);
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> positionServiceImp.checkPositionDetails(positionDTO));
        String expectedMessage = "Please provide location";

        assertTrue(expectedMessage.contains(exception.getMessage()));
    }

    @Test
    public void checkPositionDetails_tooLong_description () {
        positionDTO = new PositionDTO("pneumonoultramicroscopicsilicovolcanoconiosis123456789", "Budapest");
        Exception exception = assertThrows(InvalidInputException.class, () -> positionServiceImp.checkPositionDetails(positionDTO));
        String expectedMessage = "The provided description must be shorter than 50 character";

        assertTrue(expectedMessage.contains(exception.getMessage()));
    }

    @Test
    public void checkPositionDetails_tooLong_location () {
        positionDTO = new PositionDTO("Junior Java Developer", "pneumonoultramicroscopicsilicovolcanoconiosis123456789");
        Exception exception = assertThrows(InvalidInputException.class, () -> positionServiceImp.checkPositionDetails(positionDTO));
        String expectedMessage = "The provided location must be shorter than 50 character";

        assertTrue(expectedMessage.contains(exception.getMessage()));
    }

}
