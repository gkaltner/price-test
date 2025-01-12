package com.prices.infraestructure.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {

  @Schema(description = "Error code", example = "404")
  private String code;
  @Schema(description = "Error cause", example = "Price not found")
  private String message;
}
