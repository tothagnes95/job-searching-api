package com.example.jobsearchingapi.controllers;

import com.example.jobsearchingapi.models.DTOs.PositionDTO;
import com.example.jobsearchingapi.models.DTOs.themuseapi.PositionFromApiDTO;
import com.example.jobsearchingapi.services.ClientService;
import com.example.jobsearchingapi.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PositionController {
  private PositionService positionService;
  private ClientService clientService;

  @Autowired
  public PositionController(PositionService positionService, ClientService clientService) {
    this.positionService = positionService;
    this.clientService = clientService;
  }

  @PostMapping("/positions")
  public ResponseEntity<String> createPosition(
      @RequestHeader String uuid, @RequestBody PositionDTO positionDTO) {
    clientService.isUUIDValid(uuid);
    positionService.checkPositionDetails(positionDTO);
    return ResponseEntity.ok(positionService.savePosition(positionDTO));
  }

  @GetMapping("/position/{id}")
  public ResponseEntity<PositionDTO> getPositionById(
      @RequestHeader String uuid, @PathVariable String id) {
    clientService.isUUIDValid(uuid);
    return ResponseEntity.ok(positionService.findById(id));
  }

  @GetMapping("/positions")
  public ResponseEntity<List<PositionFromApiDTO>> getPositions(
      @RequestHeader String uuid, @RequestBody PositionDTO positionDTO) {
    clientService.isUUIDValid(uuid);
    positionService.checkPositionDetails(positionDTO);
    return ResponseEntity.ok(positionService.findAllByDescriptionAndLocation(positionDTO));
  }
}
