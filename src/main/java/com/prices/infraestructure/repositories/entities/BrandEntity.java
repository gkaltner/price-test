package com.prices.infraestructure.repositories.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "BRAND")
public class BrandEntity {

  @Id
  @Column(name = "ID")
  private int id;
  @Column(name = "NAME")
  private String name;
}
