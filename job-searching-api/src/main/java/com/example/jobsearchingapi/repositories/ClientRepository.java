package com.example.jobsearchingapi.repositories;

import com.example.jobsearchingapi.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
  boolean existsByEmail(String email);

  boolean existsClientByUuid(String id);
}
