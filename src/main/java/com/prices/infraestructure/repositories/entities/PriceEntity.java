package com.prices.infraestructure.repositories.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRICES")
public class PriceEntity {

  @Id
  @Column(name = "ID")
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "BRAND_ID")
  private BrandEntity brand;

  @Column(name = "PRODUCT")
  private int product;

  @Column(name = "PRIORITY")
  private int priority;

  @Column(name = "PRICE_LIST")
  private int priceList;

  @Column(name = "PRICE")
  private double value;

  @Column(name = "CURRENCY")
  private String currency;

  @Column(name = "START_DATE")
  private LocalDateTime startDate;

  @Column(name = "END_DATE")
  private LocalDateTime endDate;

}
