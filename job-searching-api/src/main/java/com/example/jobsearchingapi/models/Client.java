package com.example.jobsearchingapi.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Client {
  @Id
  @GeneratedValue(generator = "uuid-hibernate-generator")
  @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;
  private String name;
  private String email;

  public Client () {
  }

  public Client(String name, String email) {
    this.name = name;
    this.email = email;
  }
}
