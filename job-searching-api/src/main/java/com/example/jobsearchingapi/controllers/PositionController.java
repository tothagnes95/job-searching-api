package com.example.jobsearchingapi.controllers;

import com.example.jobsearchingapi.exceptions.InvalidInputException;
import com.example.jobsearchingapi.models.DTOs.PositionDTO;
import com.example.jobsearchingapi.services.ClientService;
import com.example.jobsearchingapi.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.UUID;

@RestController
public class PositionController {
    private PositionService positionService;
    private ClientService clientService;

    @Autowired
    public PositionController (PositionService positionService, ClientService clientService) {
        this.positionService = positionService;
        this.clientService = clientService;
    }

    @PostMapping("/positions")
    public ResponseEntity<?> createPosition (@RequestHeader (value="UUID") String id, @RequestBody PositionDTO positionDTO) {
        clientService.isUUIDValid(id);
        return ResponseEntity.ok(positionService.checkPositionDetails(positionDTO));
    }
}
