package com.example.jobsearchingapi.services;

import com.example.jobsearchingapi.exceptions.InvalidInputException;
import com.example.jobsearchingapi.exceptions.ResourceNotFoundException;
import com.example.jobsearchingapi.models.Client;
import com.example.jobsearchingapi.models.DTOs.ClientDTO;
import com.example.jobsearchingapi.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImp implements ClientService{
  private ClientRepository clientRepository;

  @Autowired
  public ClientServiceImp (ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public boolean isValidEmail(String email) {
    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    return email.matches(emailRegex);
  }

  public String checkClientDetails (ClientDTO clientDTO){
    if(clientDTO.getName() == null && clientDTO.getEmail() == null) {
      throw new ResourceNotFoundException("Please provide client name and email");
    } else if(clientDTO.getName() == null) {
      throw new ResourceNotFoundException("Please provide client name");
    } else if(clientDTO.getEmail() == null) {
      throw new ResourceNotFoundException("Please provide client email");
    } else if(clientDTO.getName().length() > 100) {
      throw new InvalidInputException("The provided name must be shorter than 100 character");
    } else if (!isValidEmail(clientDTO.getEmail())) {
      throw new InvalidInputException("Please provide an email in the following format: input@input.com");
    } else if (clientRepository.existsByEmail(clientDTO.getEmail())) {
      throw new InvalidInputException("The provided email is already taken, please choose another one");
    }
    Client client = new Client(clientDTO.getName(), clientDTO.getEmail());
    clientRepository.save(client);
      return client.getUuid();
  }

  public void isUUIDValid (String id) {
    if(!clientRepository.existsClientByUuid(id)) {
      throw new InvalidInputException("Provided UUID is not correct");
    }
  }
}
