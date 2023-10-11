package com.example.jobsearchingapi.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String uuid;

  private String name;
  private String email;

  public Client () {
  }

  public Client(String name, String email) {
    this.name = name;
    this.email = email;
    this.uuid = UUID.randomUUID().toString();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
