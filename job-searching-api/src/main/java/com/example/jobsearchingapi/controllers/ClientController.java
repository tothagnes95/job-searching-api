package com.example.jobsearchingapi.controllers;

import com.example.jobsearchingapi.models.DTOs.ClientDTO;
import com.example.jobsearchingapi.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
  private ClientService clientService;

  @Autowired
  public ClientController (ClientService clientService) {
    this.clientService = clientService;
  }

  @PostMapping("/clients")
  public ResponseEntity<String> register (@RequestBody ClientDTO clientDTO) {
    return ResponseEntity.ok(clientService.checkClientDetails(clientDTO));
  }
}
