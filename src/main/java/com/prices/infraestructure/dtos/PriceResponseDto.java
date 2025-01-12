package com.prices.infraestructure.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceResponseDto {

  @Schema(description = "Brand", example = "Zara")
  private String brand;
  @Schema(description = "Product code identifier", example = "35455")
  private int product;
  @Schema(description = "Applicable pricing rate identifier", example = "1")
  private int priceList;
  @Schema(description = "final sale price", example = "35.50")
  private double price;
  @Schema(description = "currency iso", example = "EUR")
  private String currency;
  @Schema(description = "date range in which the indicated price rate applies", example = "2020-06-14T00.00.00", format = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime startDate;
  @Schema(description = "date range in which the indicated price rate applies", example = "2020-12-31T23.59.59", format = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime endDate;
}
