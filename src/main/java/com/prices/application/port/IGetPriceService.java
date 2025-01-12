package com.prices.application.port;

import com.prices.domain.model.Price;
import java.time.LocalDateTime;

public interface IGetPriceService {

  Price getPVP(int brandId, long product, LocalDateTime dateTime);
}
