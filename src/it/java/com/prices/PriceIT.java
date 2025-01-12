package com.prices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.prices.infraestructure.dtos.ErrorResponseDto;
import com.prices.infraestructure.dtos.PriceResponseDto;
import java.time.LocalDateTime;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ZaraTestApplication.class})
class PriceIT {

  @Autowired
  private TestRestTemplate testRestTemplate;

  public static Stream<Arguments> priceFilter() {
    return Stream.of(
        Arguments.of(35455, 1, LocalDateTime.of(2020, 6, 14, 10, 0), 1, 35.5),
        Arguments.of(35455, 1, LocalDateTime.of(2020, 6, 14, 16, 0), 2, 25.45),
        Arguments.of(35455, 1, LocalDateTime.of(2020, 6, 14, 21, 0), 1, 35.5),
        Arguments.of(35455, 1, LocalDateTime.of(2020, 6, 15, 10, 0), 3, 30.50),
        Arguments.of(35455, 1, LocalDateTime.of(2020, 6, 16, 21, 0), 4, 38.95)
    );
  }

  @ParameterizedTest
  @MethodSource("priceFilter")
  void shouldGetPVPTest(long product, int brandId, LocalDateTime dateTime, int priceList, double price) {
    PriceResponseDto response = testRestTemplate.getForEntity(
            "/prices?product=" + product + "&brandId=" + brandId + "&dateTime=" + dateTime, PriceResponseDto.class)
        .getBody();

    assertNotNull(response);
    assertEquals(price, response.getPrice());
    assertEquals(priceList, response.getPriceList());
    assertEquals("Zara", response.getBrand());
  }

  @Test
  void shouldResponseErrorIfIsInvalid() {
    int brandId = 0;
    long product = 0;
    LocalDateTime dateTime = LocalDateTime.now();

    ErrorResponseDto error = testRestTemplate.getForEntity(
            "/prices?product=" + product + "&brandId=" + brandId + "&dateTime=" + dateTime, ErrorResponseDto.class)
        .getBody();

    assertNotNull(error);
    assertNotNull(HttpStatus.BAD_REQUEST, error.getCode());
  }

  @Test
  void shouldResponseNotFoundIfNotExist() {
    int brandId = 1;
    long product = 35455;
    LocalDateTime dateTime = LocalDateTime.now();

    ErrorResponseDto error = testRestTemplate.getForEntity(
            "/prices?product=" + product + "&brandId=" + brandId + "&dateTime=" + dateTime, ErrorResponseDto.class)
        .getBody();

    assertNotNull(error);
    assertNotNull(HttpStatus.NOT_FOUND, error.getCode());
  }
}