package com.prices.infraestructure.configuration;

import com.prices.application.port.IGetPriceService;
import com.prices.application.port.IPriceRepository;
import com.prices.application.services.GetPriceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.prices")
public class BeanConfiguration {

  @Bean
  public IGetPriceService getPriceService(final IPriceRepository priceRepository) {
    return new GetPriceService(priceRepository);
  }
}
