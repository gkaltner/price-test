package com.prices.infraestructure.api;

import com.prices.application.port.IGetPriceService;
import com.prices.infraestructure.dtos.PriceRequestDto;
import com.prices.infraestructure.dtos.PriceResponseDto;
import com.prices.infraestructure.mappers.PriceMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PriceController implements IPriceController {

  private final IGetPriceService service;
  private final PriceMapper mapper;

  public PriceController(IGetPriceService service, PriceMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @Override
  public PriceResponseDto getPVP(@Valid PriceRequestDto request) {
    return mapper.priceToResponse(
        this.service.getPVP(request.getBrandId(), request.getProduct(), request.getDateTime()));
  }
}
