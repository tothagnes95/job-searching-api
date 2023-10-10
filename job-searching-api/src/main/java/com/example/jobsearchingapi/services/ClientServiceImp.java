package com.example.jobsearchingapi.services;

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
}
