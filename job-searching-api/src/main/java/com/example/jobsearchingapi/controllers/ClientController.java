package com.example.jobsearchingapi.controllers;

import com.example.jobsearchingapi.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
  private ClientService clientService;

  @Autowired
  public ClientController (ClientService clientService) {
    this.clientService = clientService;
  }
}
