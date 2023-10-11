package com.example.jobsearchingapi.unittest;

import com.example.jobsearchingapi.exceptions.InvalidInputException;
import com.example.jobsearchingapi.exceptions.ResourceNotFoundException;
import com.example.jobsearchingapi.models.DTOs.ClientDTO;
import com.example.jobsearchingapi.repositories.ClientRepository;
import com.example.jobsearchingapi.services.ClientServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ClientServiceTest {
  @Mock private ClientRepository clientRepository;
  @InjectMocks private ClientServiceImp clientServiceImp;

  @BeforeEach
  public void setup(){
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void isValidEmail_True(){
    assertTrue(clientServiceImp.isValidEmail("agi@agi.com"));
  }

  @Test
  public void isValidEmail_False(){
    assertTrue(!clientServiceImp.isValidEmail("agi"));
  }

  @Test
  public void checkClientDetails_NameAndEmail_Missing(){
    ClientDTO clientDTO = new ClientDTO(null, null);
    Exception exception = assertThrows(ResourceNotFoundException.class, () -> clientServiceImp.checkClientDetails(clientDTO));
    String expectedMessage = "Please provide client name and email";

    assertTrue(expectedMessage.contains(exception.getMessage()));
  }

  @Test
  public void checkClientDetails_Name_Missing(){
    ClientDTO clientDTO = new ClientDTO(null, "agi@agi.com");
    Exception exception = assertThrows(ResourceNotFoundException.class, () -> clientServiceImp.checkClientDetails(clientDTO));
    String expectedMessage = "Please provide client name";

    assertTrue(expectedMessage.contains(exception.getMessage()));
  }

  @Test
  public void checkClientDetails_Email_Missing(){
    ClientDTO clientDTO = new ClientDTO("agi", null);
    Exception exception = assertThrows(ResourceNotFoundException.class, () -> clientServiceImp.checkClientDetails(clientDTO));
    String expectedMessage = "Please provide client email";

    assertTrue(expectedMessage.contains(exception.getMessage()));
  }

  @Test
  public void checkClientDetails_Name_TooLong(){
    ClientDTO clientDTO = new ClientDTO("LlanfairpwllgwyngyllgogerychwyrndrobwllllantysiliogogogochLlanfairpwllgwyngyllgogerychwyrndrobwllllantysiliogogogoch", "agi@email.com");
    Exception exception = assertThrows(InvalidInputException.class, () -> clientServiceImp.checkClientDetails(clientDTO));
    String expectedMessage = "The provided name must be shorter than 100 character";

    assertTrue(expectedMessage.contains(exception.getMessage()));
  }

  @Test
  public void checkClientDetails_Email_Invalid(){
    ClientDTO clientDTO = new ClientDTO("agi", "agi");
    Exception exception = assertThrows(InvalidInputException.class, () -> clientServiceImp.checkClientDetails(clientDTO));
    String expectedMessage = "Please provide an email in the following format: input@input.com";

    assertTrue(expectedMessage.contains(exception.getMessage()));
  }

  @Test
  public void checkClientDetails_Email_IsTaken(){
    ClientDTO clientDTO = new ClientDTO("agi", "agi@email.com");
    when(clientRepository.existsByEmail(clientDTO.getEmail())).thenReturn(true);
    Exception exception = assertThrows(InvalidInputException.class, () -> clientServiceImp.checkClientDetails(clientDTO));
    String expectedMessage = "The provided email is already taken, please choose another one";

    assertTrue(expectedMessage.contains(exception.getMessage()));
  }

  @Test
  public void isUUIDValid_(){
    when(clientRepository.existsClientByUuid("id")).thenReturn(false);
    Exception exception = assertThrows(InvalidInputException.class, () -> clientServiceImp.isUUIDValid("id"));
    String expectedMessage = "Provided UUID is not correct";

    assertTrue(expectedMessage.contains(exception.getMessage()));
  }
}
