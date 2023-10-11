package com.example.jobsearchingapi.integrationtest;

import com.example.jobsearchingapi.controllers.ClientController;
import com.example.jobsearchingapi.exceptions.ErrorMessage;
import com.example.jobsearchingapi.models.DTOs.ClientDTO;
import com.example.jobsearchingapi.repositories.ClientRepository;
import com.example.jobsearchingapi.services.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ClientControllerTest {
  private final ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private ClientRepository clientRepository;
  @Mock private ClientService clientService;
  @InjectMocks
  private ClientController clientController;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void register_OK_test() throws Exception {
    ClientDTO clientDTO = new ClientDTO("agi", "agi@job.com");
    mockMvc.perform(post("/clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(clientDTO)))
            .andExpect(status().is(200));
  }

  @Test
  public void register_missing_name() throws Exception{
    ClientDTO clientDTO = new ClientDTO(null, "agi@job.com");
    mockMvc.perform(post("/clients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(clientDTO)))
            .andExpect(status().is(400))
            .andExpect(content().json(mapper.writeValueAsString(new ErrorMessage("Please provide client name", "uri=/clients"))));
  }
}
