package com.prices.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record Price(
        UUID id,
        Brand brand,
        long product,
        int priority,
        int priceList,
        double value,
        String currency,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
