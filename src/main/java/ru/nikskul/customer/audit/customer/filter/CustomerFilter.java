package ru.nikskul.customer.audit.customer.filter;

public record CustomerFilter(
    Long id,
    String name,
    String phone
) {
}
