package com.prices.infraestructure.mappers;

import com.prices.domain.model.Price;
import com.prices.infraestructure.dtos.PriceResponseDto;
import com.prices.infraestructure.repositories.entities.BrandEntity;
import com.prices.infraestructure.repositories.entities.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BrandEntity.class})
public interface PriceMapper {

    Price entityToPrice(PriceEntity entity);

    @Mapping(target = "price", source = "value")
    @Mapping(target = "brand", source = "brand.name")
    PriceResponseDto priceToResponse(Price price);
}
