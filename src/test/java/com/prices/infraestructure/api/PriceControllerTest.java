package com.prices.infraestructure.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.prices.application.port.IGetPriceService;
import com.prices.domain.exceptions.PriceNotFoundException;
import com.prices.infraestructure.dtos.PriceResponseDto;
import com.prices.infraestructure.mappers.PriceMapper;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PriceController.class)
class PriceControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private IGetPriceService service;

  @MockBean
  private PriceMapper mapper;

  @Test
  void getPVP_WhenValidRequest_ShouldReturnPriceResponse() throws Exception {
    PriceResponseDto responseDto = new PriceResponseDto(
        "Zara",
        35455,
        1,
        35.50,
        "EUR",
        LocalDateTime.of(2020, 6, 14, 0, 0),
        LocalDateTime.of(2020, 12, 31, 23, 59)
    );

    when(service.getPVP(eq(1), eq(35455L), any())).thenReturn(null);
    when(mapper.priceToResponse(any())).thenReturn(responseDto);

    mockMvc.perform(get("/prices")
            .queryParam("dateTime", "2020-06-14T10:00:00")
            .queryParam("product", "35455")
            .queryParam("brandId", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.brand").value("Zara"))
        .andExpect(jsonPath("$.product").value(35455))
        .andExpect(jsonPath("$.priceList").value(1))
        .andExpect(jsonPath("$.price").value(35.50))
        .andExpect(jsonPath("$.currency").value("EUR"))
        .andExpect(jsonPath("$.startDate").value("2020-06-14T00:00:00"))
        .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:00"));
  }

  @Test
  void getPVP_WhenMissingMandatoryFields_ShouldReturnBadRequest() throws Exception {
    mockMvc.perform(get("/prices")
            .contentType(MediaType.APPLICATION_JSON)
            .queryParam("dateTime", "")
            .queryParam("product", "")
            .queryParam("brandId", ""))

        .andExpect(status().isBadRequest());
  }

  @Test
  void getPVP_WhenUnexpectedErrorOccurs_ShouldReturnInternalServerError() throws Exception {
    when(service.getPVP(eq(1), eq(35455L), any())).thenThrow(new RuntimeException("Unexpected error"));

    mockMvc.perform(get("/prices")
            .queryParam("dateTime", "2020-06-14T10:00:00")
            .queryParam("product", "35455")
            .queryParam("brandId", "1"))
        .andExpect(status().isInternalServerError());
  }

  @Test
  void getPVP_WhenPriceNotFound_ShouldReturnNotFound() throws Exception {
    when(service.getPVP(eq(1), eq(35455L), any())).thenThrow(new PriceNotFoundException("Price not found"));

    mockMvc.perform(get("/prices")
            .queryParam("dateTime", "2020-06-14T10:00:00")
            .queryParam("product", "35455")
            .queryParam("brandId", "1"))
        .andExpect(status().isNotFound());
  }

}