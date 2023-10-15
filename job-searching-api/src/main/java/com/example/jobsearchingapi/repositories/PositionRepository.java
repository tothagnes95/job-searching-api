package com.example.jobsearchingapi.repositories;

import com.example.jobsearchingapi.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
  List<Position> findAllByDescriptionContainingAndLocation(String description, String Location);

  Optional<Position> findById(Long id);
}
