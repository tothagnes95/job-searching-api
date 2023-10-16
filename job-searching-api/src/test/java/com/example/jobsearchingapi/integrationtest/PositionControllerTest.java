package com.example.jobsearchingapi.integrationtest;

import com.example.jobsearchingapi.controllers.PositionController;
import com.example.jobsearchingapi.exceptions.ErrorMessage;
import com.example.jobsearchingapi.models.Client;
import com.example.jobsearchingapi.models.DTOs.PositionDTO;
import com.example.jobsearchingapi.models.DTOs.themuseapi.PositionFromApiDTO;
import com.example.jobsearchingapi.models.Position;
import com.example.jobsearchingapi.repositories.ClientRepository;
import com.example.jobsearchingapi.repositories.PositionRepository;
import com.example.jobsearchingapi.services.PositionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = WireMockInitializer.class)
public class PositionControllerTest {
  private final ObjectMapper mapper = new ObjectMapper();
  @Autowired private PositionRepository positionRepository;
  @Autowired private ClientRepository clientRepository;
  @Mock private PositionService positionService;
  @InjectMocks private PositionController positionController;
  @Autowired private MockMvc mockMvc;
  // @Autowired private WireMockServer wireMockServer;
  private Client client;
  private PositionDTO positionDTO;
  private Position position;

  @BeforeEach
  public void setup() {
    client = new Client("Agi", "agi@agi.com");
    clientRepository.save(client);
    positionDTO = new PositionDTO("Software", "Flexible / Remote");
    position = new Position("Software", "Flexible / Remote");
  }

  @Test
  public void createPosition_OK() throws Exception {
    mockMvc
        .perform(
            post("/positions")
                .header("UUID", client.getUuid())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(positionDTO)))
        .andExpect(status().is(200))
        .andExpect(content().string("http://localhost:8080/position/2"));
  }

  @Test
  public void createPosition_UUID_incorrect() throws Exception {
    mockMvc
        .perform(
            post("/positions")
                .header("UUID", "wert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(positionDTO)))
        .andExpect(status().is(400))
        .andExpect(
            content()
                .json(
                    mapper.writeValueAsString(
                        new ErrorMessage("Provided UUID is not correct", "uri=/positions"))));
  }

  @Test
  public void getPositionById_OK() throws Exception {
    positionRepository.save(position);
    mockMvc
        .perform(get("/position/" + position.getId()).header("UUID", client.getUuid()))
        .andExpect(status().is(200))
        .andExpect(content().json(mapper.writeValueAsString(positionDTO)));
  }

  @Test
  public void getPositionById_id_incorrect() throws Exception {
    mockMvc
        .perform(get("/position/123").header("UUID", client.getUuid()))
        .andExpect(status().is(400))
        .andExpect(
            content()
                .json(
                    mapper.writeValueAsString(
                        new ErrorMessage("Please provide position id", "uri=/position/123"))));
  }

  /*@Test
  public void getPositions() throws Exception {
    List<PositionFromApiDTO> result = new ArrayList<>(List.of(new PositionFromApiDTO("Software", "Flexible / Remote", "http://localhost:8080/position/2")));
    wireMockServer.stubFor(
        WireMock.get(urlEqualTo("https://www.themuse.com/api/public/jobs?page=1&location="))
            .willReturn(
                aResponse()
                    .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .withBody(mapper.writeValueAsString(position))));

    mockMvc
        .perform(
            get("/positions")
                .header("UUID", client.getUuid())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(positionDTO)))
        .andExpect(status().is(200))
        .andExpect(content().json(mapper.writeValueAsString(result)));
  }*/
}
