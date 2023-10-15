package com.example.jobsearchingapi.services;

import com.example.jobsearchingapi.models.DTOs.ClientDTO;

public interface ClientService {
  String checkClientDetails(ClientDTO clientDTO);

  void isUUIDValid(String id);
}
