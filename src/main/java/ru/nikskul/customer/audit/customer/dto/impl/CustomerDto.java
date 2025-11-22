package ru.nikskul.customer.audit.customer.dto.impl;

import java.math.BigDecimal;

public record CustomerDto(
    Long id,
    String fullName,
    String phone,
    BigDecimal spent
) {
    public CustomerDto(Long id, String fullName, String phone, double spent) {
        this(id, fullName, phone, BigDecimal.valueOf(spent));
    }
    public CustomerDto(Long id, String fullName, String phone) {
        this(id, fullName, phone, BigDecimal.ZERO);
    }
}
