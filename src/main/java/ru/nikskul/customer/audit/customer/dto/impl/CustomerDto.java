package ru.nikskul.customer.audit.customer.dto.impl;

import java.math.BigDecimal;

public record CustomerDto(
    Long id,
    String fullName,
    String phone,
    BigDecimal spent
) {
}
