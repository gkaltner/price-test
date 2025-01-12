package com.prices.infraestructure.api;

import com.prices.domain.exceptions.PriceNotFoundException;
import com.prices.infraestructure.dtos.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

  @ExceptionHandler(PriceNotFoundException.class)
  public ResponseEntity<ErrorResponseDto> priceNotFound(PriceNotFoundException ex) {
    log.error(ex.getMessage(), ex);
    return getResponse(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponseDto> validationException(WebExchangeBindException ex) {
    log.error(ex.getMessage(), ex);
    return getResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDto> internalServerError(Exception ex) {
    log.error(ex.getMessage(), ex);
    return getResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
  }

  private ResponseEntity<ErrorResponseDto> getResponse(HttpStatus status, String message) {
    return ResponseEntity.status(status)
        .body(ErrorResponseDto.builder().code(status.name()).message(message).build());
  }
}
