package com.prices.application.port;

import com.prices.domain.model.Price;
import java.time.LocalDateTime;
import java.util.List;

public interface IPriceRepository {

  List<Price> findByBrandIdProductAndDateBetween(long brandId, long product, LocalDateTime dateTime);
}
