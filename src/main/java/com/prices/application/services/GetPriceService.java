package com.prices.application.services;

import com.prices.application.port.IGetPriceService;
import com.prices.application.port.IPriceRepository;
import com.prices.domain.exceptions.PriceNotFoundException;
import com.prices.domain.model.Price;
import java.time.LocalDateTime;

public class GetPriceService implements IGetPriceService {

  private final IPriceRepository priceRepository;

  public GetPriceService(IPriceRepository priceRepository) {
    this.priceRepository = priceRepository;
  }

  @Override
  public Price getPVP(int brandId, long product, LocalDateTime dateTime) {
    return this.priceRepository.findByBrandIdProductAndDateBetween(brandId, product, dateTime)
        .stream()
        .reduce((price, price2) -> price.priority() > price2.priority() ? price : price2)
        .orElseThrow(() -> new PriceNotFoundException("Price Not Found"));
  }
}
