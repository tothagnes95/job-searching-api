package com.example.jobsearchingapi.unittest;

import com.example.jobsearchingapi.exceptions.InvalidInputException;
import com.example.jobsearchingapi.exceptions.ResourceNotFoundException;
import com.example.jobsearchingapi.models.Client;
import com.example.jobsearchingapi.models.DTOs.ClientDTO;
import com.example.jobsearchingapi.repositories.ClientRepository;
import com.example.jobsearchingapi.services.ClientServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClientServiceTest {
  @Mock private ClientRepository clientRepository;
  @InjectMocks private ClientServiceImp clientServiceImp;
  private Client client;
  private ClientDTO clientDTO;

  @BeforeEach
  public void setup(){
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void isValidEmail_true(){
    assertTrue(clientServiceImp.isValidEmail("agi@agi.com"));
  }

  @Test
  public void isValidEmail_false(){
    assertFalse(clientServiceImp.isValidEmail("agi"));
  }

  @Test
  public void checkClientDetails_nameAndEmail_missing(){
    clientDTO = new ClientDTO(null, null);
    Exception exception = assertThrows(ResourceNotFoundException.class, () -> clientServiceImp.checkClientDetails(clientDTO));
    String expectedMessage = "Please provide client name and email";

    assertTrue(expectedMessage.contains(exception.getMessage()));
  }

  @Test
  public void checkClientDetails_name_missing(){
    clientDTO = new ClientDTO(null, "agi@agi.com");
    Exception exception = assertThrows(ResourceNotFoundException.class, () -> clientServiceImp.checkClientDetails(clientDTO));
    String expectedMessage = "Please provide client name";

    assertTrue(expectedMessage.contains(exception.getMessage()));
  }

  @Test
  public void checkClientDetails_email_missing(){
    clientDTO = new ClientDTO("agi", null);
    Exception exception = assertThrows(ResourceNotFoundException.class, () -> clientServiceImp.checkClientDetails(clientDTO));
    String expectedMessage = "Please provide client email";

    assertTrue(expectedMessage.contains(exception.getMessage()));
  }

  @Test
  public void checkClientDetails_name_tooLong(){
    clientDTO = new ClientDTO("LlanfairpwllgwyngyllgogerychwyrndrobwllllantysiliogogogochLlanfairpwllgwyngyllgogerychwyrndrobwllllantysiliogogogoch", "agi@email.com");
    Exception exception = assertThrows(InvalidInputException.class, () -> clientServiceImp.checkClientDetails(clientDTO));
    String expectedMessage = "The provided name must be shorter than 100 character";

    assertTrue(expectedMessage.contains(exception.getMessage()));
  }

  @Test
  public void checkClientDetails_email_invalid(){
    clientDTO = new ClientDTO("agi", "agi");
    Exception exception = assertThrows(InvalidInputException.class, () -> clientServiceImp.checkClientDetails(clientDTO));
    String expectedMessage = "Please provide an email in the following format: input@input.com";

    assertTrue(expectedMessage.contains(exception.getMessage()));
  }

  @Test
  public void checkClientDetails_email_isTaken(){
    clientDTO = new ClientDTO("agi", "agi@email.com");
    when(clientRepository.existsByEmail(clientDTO.getEmail())).thenReturn(true);
    Exception exception = assertThrows(InvalidInputException.class, () -> clientServiceImp.checkClientDetails(clientDTO));
    String expectedMessage = "The provided email is already taken, please choose another one";

    assertTrue(expectedMessage.contains(exception.getMessage()));
  }

  @Test
  public void checkClientDetails_OK () {
    clientDTO = new ClientDTO("agi", "agi@email.com");
    client = new Client("agi", "agi@email.com");
    when(clientRepository.save(client)).thenReturn(client);
    clientServiceImp.checkClientDetails(clientDTO);
    verify(clientRepository).save(Mockito.any(Client.class));
  }

  @Test
  public void isUUIDValid_false(){
    when(clientRepository.existsClientByUuid("id")).thenReturn(false);
    Exception exception = assertThrows(InvalidInputException.class, () -> clientServiceImp.isUUIDValid("id"));
    String expectedMessage = "Provided UUID is not correct";

    assertTrue(expectedMessage.contains(exception.getMessage()));
  }
}
