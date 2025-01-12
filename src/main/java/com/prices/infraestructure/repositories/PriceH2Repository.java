package com.prices.infraestructure.repositories;

import com.prices.infraestructure.repositories.entities.PriceEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceH2Repository extends JpaRepository<PriceEntity, UUID> {

  @Query(value = """
       select p
       from PriceEntity p
       where p.brand.id = :brandId
           and p.product = :product
           and :dateTime between p.startDate and p.endDate
      """)
  List<PriceEntity> findByBrandIdProductAndDateBetween(
      @Param("brandId") long brandId,
      @Param("product") long product,
      @Param("dateTime") LocalDateTime dateTime);
}
