package ru.nikskul.customer.audit.spend.request.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SpendRequest(
    Long customerId,
    BigDecimal value,
    LocalDateTime timestamp
) {
    public SpendRequest(Long customerId, double value, LocalDateTime timestamp) {
        this(customerId, BigDecimal.valueOf(value), timestamp);
    }
}
