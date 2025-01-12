package com.prices.infraestructure.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceRequestDto {

  @NotNull
  @Schema(description = "Date to apply the price", example = "2020-06-14T00:00:00", format = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime dateTime;
  @NotNull
  @Schema(description = "Product id to apply the price", example = "35455")
  @Min(value = 1, message = "product should be greater than 0")
  private long product;
  @NotNull
  @Schema(description = "Brand ID", example = "1")
  @Min(value = 1, message = "Brand ID should be greater than 0")
  private int brandId;
}
