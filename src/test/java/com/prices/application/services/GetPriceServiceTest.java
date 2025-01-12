package com.prices.application.services;

import com.prices.application.port.IPriceRepository;
import com.prices.domain.exceptions.PriceNotFoundException;
import com.prices.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class GetPriceServiceTest {

  @Test
  void getPVPWhenPricesAreAvailable() {
    IPriceRepository mockRepository = Mockito.mock(IPriceRepository.class);
    GetPriceService getPriceService = new GetPriceService(mockRepository);

    int brandId = 1;
    long product = 35455L;
    LocalDateTime dateTime = LocalDateTime.of(2023, 10, 1, 10, 0);

    List<Price> prices = new ArrayList<>();
    prices.add(new Price(UUID.randomUUID(), null, product, 1, 1001, 35.50, "USD",
        LocalDateTime.of(2023, 9, 1, 0, 0), LocalDateTime.of(2023, 10, 31, 23, 59)));
    prices.add(new Price(UUID.randomUUID(), null, product, 2, 1002, 40.00, "USD",
        LocalDateTime.of(2023, 9, 1, 0, 0), LocalDateTime.of(2023, 10, 31, 23, 59)));

    when(mockRepository.findByBrandIdProductAndDateBetween(brandId, product, dateTime)).thenReturn(prices);

    Price result = getPriceService.getPVP(brandId, product, dateTime);

    assertEquals(2, result.priority());
    assertEquals(40.00, result.value());
  }

  @Test
  void throwsPriceNotFoundExceptionWhenNoPricesAreFound() {
    IPriceRepository mockRepository = Mockito.mock(IPriceRepository.class);
    GetPriceService getPriceService = new GetPriceService(mockRepository);

    int brandId = 1;
    long product = 35455L;
    LocalDateTime dateTime = LocalDateTime.of(2023, 10, 1, 10, 0);

    when(mockRepository.findByBrandIdProductAndDateBetween(brandId, product, dateTime)).thenReturn(new ArrayList<>());

    assertThrows(PriceNotFoundException.class, () -> getPriceService.getPVP(brandId, product, dateTime));
  }

  @Test
  void returnsSinglePriceWhenOnlyOnePriceIsAvailable() {
    IPriceRepository mockRepository = Mockito.mock(IPriceRepository.class);
    GetPriceService getPriceService = new GetPriceService(mockRepository);

    int brandId = 1;
    long product = 35455L;
    LocalDateTime dateTime = LocalDateTime.of(2023, 10, 1, 10, 0);

    Price singlePrice = new Price(UUID.randomUUID(), null, product, 1, 1001, 35.50, "USD",
        LocalDateTime.of(2023, 9, 1, 0, 0), LocalDateTime.of(2023, 10, 31, 23, 59));

    List<Price> prices = List.of(singlePrice);

    when(mockRepository.findByBrandIdProductAndDateBetween(brandId, product, dateTime)).thenReturn(prices);

    Price result = getPriceService.getPVP(brandId, product, dateTime);

    assertEquals(singlePrice, result);
  }
}