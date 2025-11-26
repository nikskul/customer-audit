package ru.nikskul.customer.audit.spend.request.impl;

import java.math.BigDecimal;

public record SpendRequest(
    Long customerId,
    BigDecimal value
) {
    public SpendRequest(Long id, double value) {
        this(id, BigDecimal.valueOf(value));
    }
}
