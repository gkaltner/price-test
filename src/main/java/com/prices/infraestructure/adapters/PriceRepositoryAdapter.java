package com.prices.infraestructure.adapters;

import com.prices.application.port.IPriceRepository;
import com.prices.domain.model.Price;
import com.prices.infraestructure.mappers.PriceMapper;
import com.prices.infraestructure.repositories.PriceH2Repository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PriceRepositoryAdapter implements IPriceRepository {

  private final PriceH2Repository repository;
  private final PriceMapper mapper;

  public PriceRepositoryAdapter(PriceH2Repository repository, PriceMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public List<Price> findByBrandIdProductAndDateBetween(long brandId, long product, LocalDateTime dateTime) {
    return repository.findByBrandIdProductAndDateBetween(brandId, product, dateTime).stream().map(mapper::entityToPrice).toList();
  }
}
