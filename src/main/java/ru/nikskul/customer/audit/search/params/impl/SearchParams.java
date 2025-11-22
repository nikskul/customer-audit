package ru.nikskul.customer.audit.search.params.impl;

import java.util.Map;

public record SearchParams<T>(
    T filter,
    long key,
    int limit,
    Map<String, String> sorting
) {
}
