package com.prices.infraestructure.api;

import com.prices.infraestructure.dtos.PriceRequestDto;
import com.prices.infraestructure.dtos.PriceResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/prices")
@Tag(name = "Price", description = "API prices")
public interface IPriceController {

  @Operation(summary = "Get Price PVP", description = "Get Price PVP")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = PriceResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "Not Found",
          content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal Server Error",
          content = @Content)
  })
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  PriceResponseDto getPVP(PriceRequestDto priceRequest);
}
