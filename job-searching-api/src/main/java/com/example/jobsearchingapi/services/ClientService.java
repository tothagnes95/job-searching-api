package com.example.jobsearchingapi.services;

import com.example.jobsearchingapi.models.DTOs.ClientDTO;

import java.util.UUID;

public interface ClientService {
  UUID checkClientDetails (ClientDTO clientDTO);
}
