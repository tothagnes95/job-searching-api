package com.example.jobsearchingapi.integrationtest;

import com.example.jobsearchingapi.controllers.PositionController;
import com.example.jobsearchingapi.exceptions.ErrorMessage;
import com.example.jobsearchingapi.models.DTOs.PositionDTO;
import com.example.jobsearchingapi.repositories.PositionRepository;
import com.example.jobsearchingapi.services.PositionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class PositionControllerTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private PositionRepository positionRepository;
    @Mock
    private PositionService positionService;
    @InjectMocks
    private PositionController positionController;

    @Autowired
    private MockMvc mockMvc;

 /* @Test
  public void positions_OK_test() throws Exception {
    PositionDTO positionDTO = new PositionDTO("Junior Java Backend Developer", "Budapest");
    mockMvc
        .perform(
            post("/positions")
                    .header("UUID", "wert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(positionDTO)))
        .andExpect(status().is(200));
  } */

    @Test
    public void positions_incorrectUUID_test() throws Exception {
        PositionDTO positionDTO = new PositionDTO("Junior Java Backend Developer", "Budapest");
        mockMvc
                .perform(
                        post("/positions")
                                .header("UUID", "wert")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(positionDTO)))
                .andExpect(status().is(400))
                .andExpect(content().json(mapper.writeValueAsString(new ErrorMessage("Provided UUID is not correct", "uri=/positions"))));
    }
}
